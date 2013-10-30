package com.example.android_eprh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import beans.*;


public class EPRH extends Activity  {
	public static final String URL_SERVER="http://10.0.2.2:8080/PROJET_EPRH_WEB/Admin";
	public static  final int SUCCES=1;
	public static final int ECHEC=-1;
	public static  final int CONNECTION_EXCEPTION=0;
	public Admin admin;
	EditText mail;
	EditText password;
	public Animation errorAnimation=null;
	public  ProgressDialog connectionloading;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eprh);
		connectionloading= new ProgressDialog(this);
		connectionloading.setCancelable(true);

		final  UserHandle userLoaderHandler= new UserHandle(this);
		mail= (EditText)findViewById(R.id.editText1);
		password=(EditText)findViewById(R.id.editText2);
		Button connect=(Button)findViewById(R.id.button1);


		connect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				final  Admin admin=new Admin(mail.getText().toString(),password.getText().toString());
				connectionloading=ProgressDialog.show(v.getContext(), "Attente de la connection", "Connection");
				EPRH.testUserConnection(admin, userLoaderHandler);
			}
		});
	}

	public static Admin adminConnect(Admin admin) throws Exception{

		URL url = new URL(EPRH.URL_SERVER + "?login=" +admin.login+"&password="+admin.password);
		System.out.println(url.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (connection.getResponseCode() /100 != 2) {
			throw new IOException("Response not OK Version" + connection.getResponseCode());
		}
		InputStreamReader in= new InputStreamReader(connection.getInputStream());
		BufferedReader reader= new BufferedReader(in);
		StringBuffer sbf= new StringBuffer();
		String line= null;
		while((line=reader.readLine())!=null){
			sbf.append(line);
		}
		JSONObject jsonObject = new JSONObject(sbf.toString());

		if(jsonObject.getString("statut").equals("oui")) return new Admin(jsonObject);
		return null;
	}

	public static void testUserConnection(final Admin admin,final Handler receiver) {
		new Thread() {
			public void run() {

				try{
					Admin admin1= EPRH.adminConnect(admin);
					Message msg = Message.obtain();
					if(admin1!=null)
					{
						msg.arg1=EPRH.SUCCES;
						msg.obj=admin1;
					}
					else
					{
						msg.arg1=EPRH.ECHEC;
					}
					receiver.sendMessage(msg);
				}catch (Exception e){
					Message msg = Message.obtain();
					msg.arg1 = CONNECTION_EXCEPTION;
					msg.obj = e;
					e.printStackTrace();
					receiver.sendMessage(msg);
				}
			}
		}.start();
	}

	private class UserHandle extends Handler {

		private Context parent;
        
		public UserHandle(Context parent) {
			super();
			this.parent = parent;
		}
		public void handleMessage(Message msg) {
			connectionloading.dismiss();

			switch (msg.arg1) {
			case EPRH.SUCCES :
				admin = (Admin) msg.obj;

				break;
			case EPRH.ECHEC :
				mail.startAnimation(errorAnimation);
				password.startAnimation(errorAnimation);
				break;
			case EPRH.CONNECTION_EXCEPTION :
				Exception e = (Exception) msg.obj;
				e.printStackTrace();
				Builder builder = new AlertDialog.Builder(parent);
				builder.setTitle("Echec de connection "); 
				builder.setMessage("Problème de connection réseau: "+e.getCause());
				builder.show(); 
				break;
			}
		}
	}

}

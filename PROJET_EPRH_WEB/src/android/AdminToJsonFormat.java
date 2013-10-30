package android;

import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;
import beans.*;
/**
 * Containt only an static method.
 * @author FOKO
 *
 */

public class AdminToJsonFormat {
	public static void userToJson(Administrateur admin, JsonWriter jsonWriter) throws Exception {
		jsonWriter.beginObject();
		jsonWriter.name("statut").value("yes");
		jsonWriter.name("login").value(admin.getIdentifiant_admin());
		jsonWriter.name("password").value(admin.getPassword());
		jsonWriter.name("nom").value(admin.getNom_Admin());
		jsonWriter.endObject();
	}

}

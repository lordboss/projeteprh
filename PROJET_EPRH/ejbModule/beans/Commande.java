package beans;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@SequenceGenerator (
	    name="SEQ_COMMANDE",
	    sequenceName="SEQ_COMMANDE",
	    initialValue=1,
	    allocationSize=1
	)
public  class Commande implements Serializable{ 
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_COMMANDE")
	int IdCommande;
	String DateCommande;
	String ObjetCommande;
	String CodeObjetCommande;
	int QteCommande;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="id_admin",nullable=false)
	Administrateur administrateur;

	public int getId_Commande() {
		return IdCommande;
	}

	public void setId_Commande(int id_Commande) {
		IdCommande = id_Commande;
	}

	public String getDate_Commande() {
		return DateCommande;
	}

	public void setDate_Commande(String date_Commande) {
		DateCommande = date_Commande;
	}

	public String getCode_Commande() {
		return CodeObjetCommande;
	}

	public void setCode_Commande(String date_Commande) {
		CodeObjetCommande = date_Commande;
	}
	

	public String getObjet_Commande() {
		return ObjetCommande;
	}

	public void setObjet_Commande(String objet_Commande) {
		ObjetCommande = objet_Commande;
	}

	public int getQte_Commande() {
		return QteCommande;
	}

	public void setQte_Commande(int qte_Commande) {
		QteCommande = qte_Commande;
	}

	public Administrateur getAdmin() {
		return administrateur;
	}

	public void setAdmin(Administrateur admin) {
		this.administrateur = admin;
	}

	@Override
	public String toString() {
		return "Commande [Id_Commande=" + IdCommande + ", Date_Commande="
				+ DateCommande + ", Objet_Commande=" + ObjetCommande
				+ ", Qte_Commande=" + QteCommande + ", admin=" + administrateur + "]";
	}
	
	
	

} 

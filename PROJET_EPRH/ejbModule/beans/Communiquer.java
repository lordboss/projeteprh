package beans;


import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator (
		name="SEQ_COMMUNIQUER",
		sequenceName="SEQ_COMMUNIQUER",
		initialValue=1,
		allocationSize=1


		)
public  class Communiquer implements Serializable{ 

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_COMMUNIQUER")
	int IdCommuniquer;
	String DateCommuniquer;
	String ContenuCommuniquer;

	//justo
	
	int emmeteur;//String emmeteur
	String Destination;
	String ObjetCommunique;
	String EtatCommunique;
	String Suprimeur;
	String Lastdate;

	//fin justo


	@ManyToOne(fetch=FetchType.LAZY)
	/*@JoinColumn (name="id_admin",nullable=false)
	Administrateur administrateuEtatr;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="id_enseignant",nullable=false)
	Enseignant enseignant;*/

	public int getId_Communiquer() {
		return IdCommuniquer;
	}
	public void setId_Communiquer(int id_Communiquer) {
		IdCommuniquer = id_Communiquer;
	}
	public String getDate_Communiquer() {
		return this.DateCommuniquer;
	}
	public void setDate_Communiquer(String date_Communiquer) {
		DateCommuniquer = date_Communiquer;
	}
	/*public String getContenu_Communiquer() {
		return ContenuCommuniquer;
	}
	public void setContenu_Communiquer(String contenu_Communiquer) {
		ContenuCommuniquer = contenu_Communiquer;
	}*/
	/*public Administrateur getAdmin() {
		return administrateur;
	}
	public void setAdmin(Administrateur admin) {
		this.administrateur = admin;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}*/

	public String getDestination() {
		return this.Destination;
	}
	public void setDestination(String destination) {
		this.Destination = destination;
	}

	public String getObjetCommunique() {
		return this.ObjetCommunique;
	}
	public void setObjetCommunique(String objetCommunique) {
		this.ObjetCommunique =objetCommunique;
	}

	public String getContenuCommuniquer() {
		return this.ContenuCommuniquer;
	}
	public String setContenuCommuniquer(String contenu){
		return this.ContenuCommuniquer =contenu;
	}
	
	
	public int getemmeteur() {
		return this.emmeteur;
	}
	public void setemmeteur(int emmeteur) {
		this.emmeteur =emmeteur;
	}
	public String getEtatCommunique() {
		return this.EtatCommunique;
	}
	public String setEtatCommunique(String EtatCommunique) {
		return this.EtatCommunique =EtatCommunique;
	}
	public String getSuprimeur() {
		return this.Suprimeur;
	}
	public String setSuprimeur(String Suprimeur) {
		return this.Suprimeur =Suprimeur;
	}
	public String getLastdate() {
		return this.Lastdate;
	}
	public String setLastdate(String Lastdate) {
		return this.Lastdate =Lastdate;
	}
	

	/*@Override
	public String toString() {
		return "Communiquer [Id_Communiquer=" + IdCommuniquer
				+ ", Date_Communiquer="+ DateCommuniquer
				+ ", Contenu_Communiquer=" + ContenuCommuniquer + ", admin="
				+ administrateur + ", enseignant=" + enseignant + "]";
	}*/




} //End Class Communiquer


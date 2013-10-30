package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ_ADMIN", sequenceName = "SEQ_ADMIN", initialValue = 1, allocationSize = 1)
public class Administrateur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN")
	int IdAdmin;
	String NomAdmin;
	String password;
	@Column(unique = true)
	String identifiantAdmin;
	@OneToMany(mappedBy = "administrateur", cascade = { CascadeType.ALL })
	List<Proces_Verbaux> pv = new ArrayList<Proces_Verbaux>();

	@OneToMany(mappedBy = "administrateur", cascade = { CascadeType.ALL })
	List<Enseignant> enseignant = new ArrayList<Enseignant>();

	/*@OneToMany(mappedBy = "administrateur", cascade = { CascadeType.ALL })
	List<Communiquer> communique = new ArrayList<Communiquer>();*/

	@OneToMany(mappedBy = "administrateur", cascade = { CascadeType.ALL })
	List<Commande> commande = new ArrayList<Commande>();

	public String getIdentifiant_admin() {
		return identifiantAdmin;
	}

	public void setIdentifiant_admin(String identifiant_admin) {
		this.identifiantAdmin = identifiant_admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId_Admin() {
		return IdAdmin;
	}

	public void setId_Admin(int id_Admin) {
		IdAdmin = id_Admin;
	}

	public String getNom_Admin() {
		return NomAdmin;
	}

	public void setNom_Admin(String nom_Admin) {
		NomAdmin = nom_Admin;
	}

	public List<Proces_Verbaux> getPv() {
		return pv;
	}

	public void setPv(List<Proces_Verbaux> pv) {
		this.pv = pv;
	}

	public List<Enseignant> getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(List<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}

	/*public List<Communiquer> getCommunique() {
		return communique;
	}*/

	/*public void setCommunique(List<Communiquer> communique) {
		this.communique = communique;
	}*/

	public List<Commande> getCommande() {
		return commande;
	}

	public void setCommande(List<Commande> commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "Administrateur [Id_Admin=" + IdAdmin + ", Nom_Admin="
				+ NomAdmin + ", pv=" + pv + ", enseignant=" + enseignant
				+ ", commande=" + commande + "]";
	}

}

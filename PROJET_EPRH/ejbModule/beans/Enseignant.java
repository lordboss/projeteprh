package beans;

/**************************************************************************
 * Source File	:  Enseignant.java
 * Author                   :  foko  
 * Project name         :  Non enregistré* Created                 :  05/08/2013
 * Modified   	:  05/08/2013
 * Description	:  Definition of the class Enseignant
 **************************************************************************/

import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ_ENSEIGNANT", sequenceName = "SEQ_ENSEIGNANT", initialValue = 1, allocationSize = 1)
public class Enseignant implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENSEIGNANT")
	int IdEnseignant;
	String NomEnseignant;
	String password;
	String diplome;
	int con;
	@Column(updatable=false)
	String dateRecrutement;
	String Grade;
	@Column(unique = true)
	String identifiantEnseignant;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idadmin", nullable = false)
	Administrateur administrateur;

	@OneToMany(cascade = { CascadeType.ALL },mappedBy="enseignant",fetch=FetchType.EAGER)
	List<Cours> cours = new ArrayList<Cours>();
	/*@OneToMany(mappedBy = "enseignant", cascade = { CascadeType.ALL })
	List<Communiquer> communiquer = new ArrayList<Communiquer>();*/

	public String getIdentifiant_enseignant() {
		return identifiantEnseignant;
	}

	public void setIdentifiant_enseignant(String identifiant_enseignant) {
		this.identifiantEnseignant = identifiant_enseignant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}
	
	public int getCon(){
		return con;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public int getId_Enseignant() {
		return IdEnseignant;
	}

	public void setId_Enseignant(int id_Enseignant) {
		IdEnseignant = id_Enseignant;
	}

	public String getNom_Enseignant() {
		return NomEnseignant;
	}

	public void setNom_Enseignant(String nom_Enseignant) {
		NomEnseignant = nom_Enseignant;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	/*public List<Communiquer> getCommuniquer() {
		return communiquer;
	}*/

	/*public void setCommuniquer(List<Communiquer> communiquer) {
		this.communiquer = communiquer;
	}*/

	public String getDateRecrutement() {
		return dateRecrutement;
	}

	public void setDateRecrutement(String dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	public void setCon(int c){
		this.con=c;
	}

	
} // End Class Enseignant


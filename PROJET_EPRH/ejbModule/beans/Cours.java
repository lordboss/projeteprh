package beans;


import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator (
	    name="SEQ_COURS",
	    sequenceName="SEQ_COURS",
	    initialValue=1,
	    allocationSize=1
	)
public  class Cours implements Serializable{ 
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_COURS")
    int IdCours;
    String IntituleCours;
	int Niveau;
    @Column(unique=true,nullable=false)
    String code_cours;
	int nbCredit;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_enseignant")
    Enseignant enseignant;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn (name="id_horaire",nullable=false)
    Horaire horaire;
    @ManyToOne(fetch=FetchType.EAGER)
    Salle salle;
	public int getId_Cours() {
		return IdCours;
	}

	public void setId_Cours(int id_Cours) {
		IdCours = id_Cours;
	}

	public String getCode_Cours() {
		return code_cours;
	}

	public void setCode_Cours(String code_cours) {
		this.code_cours = code_cours;
	}
 
	public int getNbCredit() {
		return nbCredit;
	}

	public void setNbCredit(int nbCredit) {
		this.nbCredit = nbCredit;
	}

	public String getIntitule_Cours() {
		return IntituleCours;
	}

	public void setIntitule_Cours(String intitule_Cours) {
		IntituleCours = intitule_Cours;
	}

	public int getNiveau() {
		return Niveau;
	}

	public void setNiveau(int niveau) {
		Niveau = niveau;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	@Override
	public String toString() {
		return "Cours [Id_Cours=" + IdCours + ", code_cours=" + code_cours
				+ ", Intitule_Cours=" + IntituleCours + ", Niveau=" + Niveau
				+ ", enseignant=" + enseignant + ", horaire=" + horaire + "]";
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	

} //End Class Cours


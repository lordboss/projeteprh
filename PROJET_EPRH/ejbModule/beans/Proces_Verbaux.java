package beans;



import java.awt.Image;
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
import javax.persistence.Column;

@Entity
@SequenceGenerator (
	    name="SEQ_PROCESVERBAUX",
	    sequenceName="SEQ_PROCESVERBAUX",
	    initialValue=1,
	    allocationSize=1
	)
public  class Proces_Verbaux implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_PROCESVERBAUX")
	int idPv;
	@Column(unique=true)
    String jourPv;
	String moisPv;
	String anneePv;
	String semestre;
	String nomFichier;
	String intitulePv;
	String heure;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="id_admin",nullable=false)
	Administrateur administrateur;
	public int getIdPv() {
		return idPv;
	}
	public void setIdPv(int idPv) {
		this.idPv = idPv;
	}
	
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getJourPv() {
		return jourPv;
	}
	public void setJourPv(String jourPv) {
		this.jourPv = jourPv;
	}
	public String getMoisPv() {
		return moisPv;
	}
	public void setMoisPv(String moisPv) {
		this.moisPv = moisPv;
	}
	public String getAnneePv() {
		return anneePv;
	}
	public void setAnneePv(String anneePv) {
		this.anneePv = anneePv;
	}
	public String getNomFichier() {
		return nomFichier;
	}
	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	public String getIntitulePv() {
		return intitulePv;
	}
	public void setIntitulePv(String intitulePv) {
		this.intitulePv = intitulePv;
	}
	public Administrateur getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	

} //End Class Proces_Verbaux


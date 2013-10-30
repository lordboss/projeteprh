package beans;



import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator (
	    name="SEQ_HORAIRE",
	    sequenceName="SEQ_HORAIRE",
	    initialValue=1,
	    allocationSize=1
	)
public  class Horaire implements Serializable{ 
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_HORAIRE")
	int IdHoraire;
	@Column(unique=true)
	String Heure;
	
	@OneToMany(mappedBy = "horaire", cascade = { CascadeType.ALL })
	List<Cours> cours=new ArrayList<Cours>();

	public int getId_Horaire() {
		return IdHoraire;
	}

	public void setId_Horaire(int id_Horaire) {
		IdHoraire = id_Horaire;
	}

	

	public String getHeure() {
		return Heure;
	}

	public void setHeure(String heure) {
		Heure = heure;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	
	
	
} //End Class Horraire


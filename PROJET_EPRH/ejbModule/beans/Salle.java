package beans;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "SEQ_SALLE", sequenceName = "SEQ_SALLE", initialValue = 1, allocationSize = 1)
public class Salle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALLE")
	int idSalle;
	@Column(unique=true)
	String codeSalle;
	String nomSalle;
	@OneToMany(mappedBy = "salle", cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	List<Cours> cours=new ArrayList<Cours>();
	public int getIdSalle() {
		return idSalle;
	}
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	public String getCodeSalle() {
		return codeSalle;
	}
	public void setCodeSalle(String codeSalle) {
		this.codeSalle = codeSalle;
	}
	public String getNomSalle() {
		return nomSalle;
	}
	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}
	public List<Cours> getCours() {
		return cours;
	}
	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}
	

}

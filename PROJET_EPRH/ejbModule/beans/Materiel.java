package beans;
import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@SequenceGenerator (
	    name="SEQ_MATERIEL",
	    sequenceName="SEQ_MATERIEL",
	    initialValue=1,
	    allocationSize=1
	)
public  class Materiel implements Serializable{ 
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_MATERIEL")
	int IdMateriel;
	String Nom;
	@Column(unique=true,nullable=false)
    String Code;
	int Quantite;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="id_admin",nullable=false)
	Administrateur administrateur;

	public int getId_Materiel() {
		return IdMateriel;
	}

	public void setId_Commande(int IdMateriel) {
		this.IdMateriel = IdMateriel;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String Nom) {
		this.Nom = Nom;
	}

	

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public int getQuantite() {
		return Quantite;
	}

	public void setQuantite(int Quantite) {
		this.Quantite = Quantite;
	}

	public Administrateur getAdmin() {
		return administrateur;
	}

	public void setAdmin(Administrateur admin) {
		this.administrateur = admin;
	}

	@Override
	public String toString() {
		return "Materiel [Id_Materiel=" + IdMateriel + ", Nom_Materiel="
				+ Nom + ", Code_Materiel=" + Code
				+ ", Quantite_Materiel=" + Quantite + ", admin=" + administrateur + "]";
	}
	
	
	

} 

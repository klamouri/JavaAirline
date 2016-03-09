package bean;


import java.io.Serializable;


import org.joda.time.DateTime;



@SuppressWarnings("serial")
public class Depart implements Serializable {
	
	private Vol numvol;
	private DateTime dateDepart;
	private Avion numav;
	private Utilisateur matricule;
	private Boolean moderation;
	
	
	public Vol getNumvol() {
	
		return numvol;
	}
	
	
	public DateTime getDateDepart() {
	
		return dateDepart;
	}
	
	
	public Avion getNumav() {
	
		return numav;
	}
	
	
	public Utilisateur getMatricule() {
	
		return matricule;
	}
	
	
	public Boolean getModeration() {
	
		return moderation;
	}
	
	
	public void setNumvol(Vol numvol) {
	
		this.numvol = numvol;
	}
	
	
	public void setDateDepart(DateTime dateDepart) {
	
		this.dateDepart = dateDepart;
	}
	
	
	public void setNumav(Avion numav) {
	
		this.numav = numav;
	}
	
	
	public void setMatricule(Utilisateur matricule) {
	
		this.matricule = matricule;
	}
	
	
	public void setModeration(Boolean moderation) {
	
		this.moderation = moderation;
	}
	
	
}

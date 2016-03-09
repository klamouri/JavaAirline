package bean;


import java.io.Serializable;


import org.joda.time.DateTime;



@SuppressWarnings("serial")
public class Reservation implements Serializable {
	
	private Vol vol;
	private Utilisateur utilisateur;
	private Boolean assurance;
	private DateTime dateDepart;
	
	
	public Vol getVol() {
	
		return vol;
	}
	
	
	public Utilisateur getUtilisateur() {
	
		return utilisateur;
	}
	
	
	public void setVol(Vol vol) {
	
		this.vol = vol;
	}
	
	
	public void setUtilisateur(Utilisateur utilisateur) {
	
		this.utilisateur = utilisateur;
	}
	
	
	public Boolean getAssurance() {
	
		return assurance;
	}
	
	
	public void setAssurance(Boolean assurance) {
	
		this.assurance = assurance;
	}
	
	
	public DateTime getDateDepart() {
	
		return dateDepart;
	}
	
	
	public void setDateDepart(DateTime dateDepart) {
	
		this.dateDepart = dateDepart;
	}
	
	
}

package bean;


import java.io.Serializable;


import org.joda.time.DateTime;



@SuppressWarnings("serial")
public class Vol implements Serializable {
	
	private String numvol;
	private DateTime heureDepart;
	private DateTime heureArrivee;
	private String villeDepart;
	private String villeArrivee;
	
	
	public String getNumvol() {
	
		return numvol;
	}
	
	
	public DateTime getHeureDepart() {
	
		return heureDepart;
	}
	
	
	public DateTime getHeureArrivee() {
	
		return heureArrivee;
	}
	
	
	public String getVilleDepart() {
	
		return villeDepart;
	}
	
	
	public String getVilleArrivee() {
	
		return villeArrivee;
	}
	
	
	public void setNumvol(String numvol) {
	
		this.numvol = numvol;
	}
	
	
	public void setHeureDepart(DateTime heureDepart) {
	
		this.heureDepart = heureDepart;
	}
	
	
	public void setHeureArrivee(DateTime heureArrivee) {
	
		this.heureArrivee = heureArrivee;
	}
	
	
	public void setVilleDepart(String villeDepart) {
	
		this.villeDepart = villeDepart;
	}
	
	
	public void setVilleArrivee(String villeArrivee) {
	
		this.villeArrivee = villeArrivee;
	}
	
	
}

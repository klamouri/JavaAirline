package bean;


import java.io.Serializable;



@SuppressWarnings("serial")
public class Avion implements Serializable {
	
	private Integer numav;
	private Integer capacite;
	private String type;
	private String entrepot;
	
	
	public Integer getNumav() {
	
		return numav;
	}
	
	
	public Integer getCapacite() {
	
		return capacite;
	}
	
	
	public String getType() {
	
		return type;
	}
	
	
	public String getEntrepot() {
	
		return entrepot;
	}
	
	
	public void setNumav(Integer numav) {
	
		this.numav = numav;
	}
	
	
	public void setCapacite(Integer capacite) {
	
		this.capacite = capacite;
	}
	
	
	public void setType(String type) {
	
		this.type = type;
	}
	
	
	public void setEntrepot(String entrepot) {
	
		this.entrepot = entrepot;
	}
	
	
}

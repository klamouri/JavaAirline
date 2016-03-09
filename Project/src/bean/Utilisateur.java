package bean;


import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
public class Utilisateur implements Serializable {
	
	private String nom;
	private String login;
	private String mdp;
	private Integer idConnexion;
	private Integer idSpecialisation;
	
	
	private String ville;
	private Integer age;
	private Integer salaire;
	
	private String typeSpecialisation;
	
	// Nom a afficher + Nom du contrôleur associé...
	private String[][] services;
	
	private List<Reservation> reservations;
	private List<Depart> departs;
	private List<Depart> moderations;
	
	
	public String getNom() {
	
		return nom;
	}
	
	
	public Integer getIdConnexion() {
	
		return idConnexion;
	}
	
	
	public Integer getIdSpecialisation() {
	
		return idSpecialisation;
	}
	
	
	public String getTypeSpecialisation() {
	
		return typeSpecialisation;
	}
	
	
	public String[][] getServices() {
	
		return services;
	}
	
	
	public List<Reservation> getReservations() {
	
		return reservations;
	}
	
	
	public void setNom(String nom) {
	
		this.nom = nom;
	}
	
	
	public void setIdConnexion(Integer idConnexion) {
	
		this.idConnexion = idConnexion;
	}
	
	
	public void setIdSpecialisation(Integer idSpecialisation) {
	
		this.idSpecialisation = idSpecialisation;
	}
	
	
	public void setTypeSpecialisation(String typeSpecialisation) {
	
		this.typeSpecialisation = typeSpecialisation;
	}
	
	
	public void setServices(String[][] services) {
	
		this.services = services;
	}
	
	
	public void setReservations(List<Reservation> reservations) {
	
		this.reservations = reservations;
	}
	
	
	public String getMdp() {
	
		return mdp;
	}
	
	
	public void setMdp(String mdp) {
	
		this.mdp = mdp;
	}
	
	
	public String getLogin() {
	
		return login;
	}
	
	
	public void setLogin(String login) {
	
		this.login = login;
	}
	
	
	public String getVille() {
	
		return ville;
	}
	
	
	public void setVille(String ville) {
	
		this.ville = ville;
	}
	
	
	public Integer getAge() {
	
		return age;
	}
	
	
	public void setAge(Integer age) {
	
		this.age = age;
	}
	
	
	public Integer getSalaire() {
	
		return salaire;
	}
	
	
	public void setSalaire(Integer salaire) {
	
		this.salaire = salaire;
	}
	
	
	public List<Depart> getDeparts() {
	
		return departs;
	}
	
	
	public void setDeparts(List<Depart> departs) {
	
		this.departs = departs;
	}


	public List<Depart> getModerations() {
	
		return moderations;
	}


	public void setModerations(List<Depart> moderations) {
	
		this.moderations = moderations;
	}
	
	
}
package dao.interfaces;


import java.util.List;


import org.joda.time.DateTime;


import bean.Depart;
import bean.Utilisateur;



public interface DepartDao {
	
	List<Depart> trouver(String villeDep, String villeArr, DateTime date);
	
	
	// Depart trouver(String Numvol, DateTime DateDepart);
	
	
	void creer(Depart depart);
	
	
	void update(Depart depart);
	
	
	Depart trouver(String Numvol, DateTime DateDepart, Utilisateur u);
	
	
	List<Depart> trouver();
	
	
	// List<Depart> trouver(String villeDep, String villeArr, DateTime date,
	// Utilisateur u);
	
}

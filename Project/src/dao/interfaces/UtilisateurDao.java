package dao.interfaces;


import java.util.List;


import bean.Utilisateur;



public interface UtilisateurDao {
	
	void creer(Utilisateur utilisateur);
	
	
	void update(Utilisateur utilisateur);
	
	
	Utilisateur trouver(String email);
	
	
	List<Utilisateur> trouverPilote();
	
	
}

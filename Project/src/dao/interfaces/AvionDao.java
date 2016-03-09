package dao.interfaces;


import java.util.List;


import bean.Avion;



public interface AvionDao {
	
	List<Avion> trouver();
	
	
	Avion trouver(Integer Numav);
	
	
	void creer(Avion avion);
	
	
	void update(Avion avion);
}

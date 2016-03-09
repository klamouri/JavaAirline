package dao.interfaces;


import bean.Vol;



public interface VolDao {
	
	Vol trouver(String Numvol);
	
	
	void creer(Vol vol);
	
	
	void update(Vol vol);
	
	
	void delete(Vol vol);
}

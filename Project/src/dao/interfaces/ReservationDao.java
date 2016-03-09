package dao.interfaces;


import org.joda.time.DateTime;


import bean.Reservation;
import bean.Utilisateur;



public interface ReservationDao {
	
	Reservation trouver(Utilisateur Numab, String numvol, DateTime DateDepart);
	
	
	void creer(Reservation reservation);
	
	
	void update(Reservation reservation);
	
	
	void delete(Reservation reservation);
}

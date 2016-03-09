package dao.Implementation;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.joda.time.DateTime;


import bean.Reservation;
import bean.Utilisateur;
import dao.DAOFabrique;
import dao.interfaces.ReservationDao;



public class ReservationDaoImpl implements ReservationDao {
	
	private final DAOFabrique daoFabrique;
	
	
	public ReservationDaoImpl(DAOFabrique instance) {
	
		this.daoFabrique = instance;
	}
	
	
	// Recherche d'un bean avec ses identifants primaires
	@Override
	public Reservation trouver(Utilisateur Numab, String numvol,
			DateTime DateDepart) {
	
		Reservation r = new Reservation();
		if (!Numab.getTypeSpecialisation().equals("passager"))
			return r;
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		
		
		try {
			String Query =
					"SELECT * FROM Reservation "
							+ "WHERE Numab = ? AND Numvol = ? AND DateDepart = ?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setInt(1, Numab.getIdSpecialisation());
			creation.setString(2, numvol);
			creation.setDate(3, new Date(DateDepart.getMillis()));
			res = creation.executeQuery();
			r.setUtilisateur(Numab);
			
			r.setDateDepart(DateDepart);
			while (res.next()) {
				
				r.setVol(new VolDaoImpl(DAOFabrique.getInstance())
						.trouver(numvol));
				r.setAssurance(res.getString(4).equals("Y") ? true : false);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
			if (creation != null)
				creation.close();
			if (res != null)
				res.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	
	// Création d'une réservation Dans la BDD à partir de son bean
	@Override
	public void creer(Reservation reservation) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		try {
			String Query =
					"INSERT INTO Reservation "
							+ "Reservation(Numab, Numvol, DateDepart, Assurance) "
							+ "VALUES(?,?,?,?)";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setInt(1, reservation.getUtilisateur()
					.getIdSpecialisation());
			creation.setString(2, reservation.getVol().getNumvol());
			creation.setDate(3, new Date(reservation.getDateDepart()
					.getMillis()));
			creation.setString(4, reservation.getAssurance() ? "Y" : "N");
			creation.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
			if (creation != null)
				creation.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Mise à jour d'une réservation dans la BDD à partir de son bean
	
	@Override
	public void update(Reservation reservation) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		try {
			String Query =
					"UPDATE Reservation SET  Assurance=? "
							+ "WHERE Numab=? AND Numvol=? AND DateDepart=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setString(1, reservation.getAssurance() ? "Y" : "N");
			creation.setInt(2, reservation.getUtilisateur()
					.getIdSpecialisation());
			creation.setString(3, reservation.getVol().getNumvol());
			creation.setDate(4, new Date(reservation.getDateDepart()
					.getMillis()));
			
			creation.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
			if (creation != null)
				creation.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	// Suppression d'une Réservation à partir de son bean
	@Override
	public void delete(Reservation reservation) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		
		try {
			String Query =
					"DELETE FROM Reservation "
							+ "WHERE Numab = ? AND Numvol = ? AND DateDepart = ?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setInt(1, reservation.getUtilisateur()
					.getIdSpecialisation());
			creation.setString(2, reservation.getVol().getNumvol());
			creation.setDate(3, new Date(reservation.getDateDepart()
					.getMillis()));
			creation.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
			if (creation != null)
				creation.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

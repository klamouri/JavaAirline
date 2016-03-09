package dao.Implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;


import org.joda.time.DateTime;


import bean.Vol;
import dao.DAOFabrique;
import dao.interfaces.VolDao;



public class VolDaoImpl implements VolDao {
	
	private final DAOFabrique daoFabrique;
	
	
	public VolDaoImpl(DAOFabrique instance) {
	
		this.daoFabrique = instance;
	}
	
	
	// Recherche d'un vol dans la bdd puis extraction dans un bean
	@Override
	public Vol trouver(String Numvol) {
	
		Vol v = new Vol();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query = "SELECT * FROM VOL WHERE Numvol=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setString(1, Numvol);
			res = creation.executeQuery();
			
			while (res.next()) {
				v.setNumvol(Numvol);
				v.setHeureDepart(new DateTime(res.getTime(2).getTime()));
				v.setHeureArrivee(new DateTime(res.getTime(3).getTime()));
				v.setVilleDepart(res.getString(4));
				v.setVilleArrivee(res.getString(5));
				
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
		
		return v;
	}
	
	
	// Création d'un Vol dans la BDD à partir d'un bean
	@Override
	public void creer(Vol vol) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		try {
			String Query =
					"insert into vol (Numvol, HeureDepart, HeureArrivee, VilleDepart, VilleArrivee) "
							+ "values(?,?,?,?,?)";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setString(1, vol.getNumvol());
			creation.setTime(2, new Time(vol.getHeureDepart().getMillis()));
			creation.setTime(3, new Time(vol.getHeureArrivee().getMillis()));
			creation.setString(4, vol.getVilleDepart());
			creation.setString(5, vol.getVilleArrivee());
			
			
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
	
	
	// Mise à jour d'un vol dans la BDD à partir de son bean
	@Override
	public void update(Vol vol) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		try {
			String Query =
					"UPDATE vol SET HeureDepart=?, HeureArrivee=?, "
							+ "VilleDepart=?, " + "VilleArrivee=? "
							+ "WHERE Numvol=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setTime(1, new Time(vol.getHeureDepart().getMillis()));
			creation.setTime(2, new Time(vol.getHeureArrivee().getMillis()));
			creation.setString(3, vol.getVilleDepart());
			creation.setString(4, vol.getVilleArrivee());
			creation.setString(5, vol.getNumvol());
			
			creation.execute();
			if (connection != null)
				connection.close();
			if (creation != null)
				creation.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Suppression d'un Vol à partir de son bean
	@Override
	public void delete(Vol vol) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		try {
			String Query = "DELETE FROM vol WHERE Numvol=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setString(1, vol.getNumvol());
			
			creation.execute();
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

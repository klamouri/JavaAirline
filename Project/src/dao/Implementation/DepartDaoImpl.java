package dao.Implementation;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.joda.time.DateTime;


import bean.Depart;
import bean.Utilisateur;
import dao.DAOFabrique;
import dao.interfaces.DepartDao;



public class DepartDaoImpl implements DepartDao {
	
	private final DAOFabrique daoFabrique;
	
	
	public DepartDaoImpl(DAOFabrique instance) {
	
		this.daoFabrique = instance;
	}
	
	
	// Recherche d'un départ à partir de ses identifiants uniques
	@Override
	public Depart trouver(String Numvol, DateTime DateDepart, Utilisateur u) {
	
		Depart d = new Depart();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query =
					"SELECT * FROM Depart D, Utilisateur U  WHERE D.Matricule=U.IdPilote AND D.Numvol=? AND D.DateDepart=?";
			connection = daoFabrique.getConnection();
			AvionDaoImpl a = new AvionDaoImpl(DAOFabrique.getInstance());
			creation = connection.prepareStatement(Query);
			creation.setString(1, Numvol);
			creation.setDate(2, new Date(DateDepart.getMillis()));
			res = creation.executeQuery();
			while (res.next()) {
				d.setNumvol(daoFabrique.getVolDao().trouver(
						res.getString("numVol")));
				d.setDateDepart(DateDepart);
				d.setNumav(a.trouver(res.getInt("numAv")));
				d.setMatricule(u);
				d.setModeration(res.getString("Moderation").equals("Y") ? true
						: false);
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
		
		
		return d;
	}
	
	
	// Création d'un départ dans la BDD à partir de son bean
	@Override
	public void creer(Depart depart) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		
		try {
			String Query =
					"insert into depart (Numvol, DateDepart, Numav, Matricule, Moderation) "
							+ "values(?,?,?,?,?)";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setString(1, depart.getNumvol().getNumvol());
			creation.setDate(2, new Date(depart.getDateDepart().getMillis()));
			creation.setInt(3, depart.getNumav().getNumav());
			creation.setInt(4, depart.getMatricule().getIdSpecialisation());
			creation.setString(5, depart.getModeration() ? "Y" : "N");
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
	
	
	// Mise à jour d'un départ dans la BDD à partir de son bean
	@Override
	public void update(Depart depart) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		
		try {
			String Query =
					"UPDATE depart"
							+ " SET Numav=?, Matricule=?, Moderation=? "
							+ "WHERE Numvol=? AND DateDepart=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setInt(1, depart.getNumav().getNumav());
			creation.setInt(2, depart.getMatricule().getIdSpecialisation());
			creation.setString(3, depart.getModeration() ? "Y" : "N");
			creation.setString(4, depart.getNumvol().getNumvol());
			creation.setDate(5, new Date(depart.getDateDepart().getMillis()));
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
	
	
	// Recherche de tout les départs à partir d'une ville de départ, d'arrivée et
	// d'une date
	@Override
	public List<Depart>
			trouver(String villeDep, String villeArr, DateTime date) {
	
		List<Depart> d = new ArrayList<Depart>();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query =
					"SELECT d.numVol, d.DateDepart, u.login FROM Depart D, Vol v, Utilisateur u "
							+ "WHERE d.numVol=v.numVol AND "
							+ "u.IdPilote=D.Matricule " + "AND d.DateDepart=? "
							+ "AND v.VilleDepart=? " + "AND v.VilleArrivee=?";
			connection = daoFabrique.getConnection();
			creation = connection.prepareStatement(Query);
			creation.setDate(1, new Date(date.getMillis()));
			creation.setString(2, villeDep);
			creation.setString(3, villeArr);
			res = creation.executeQuery();
			while (res.next())
				d.add(trouver(res.getString("numVol"), new DateTime(res
						.getDate("DateDepart").getTime()), daoFabrique
						.getUtilisateurDao().trouver(res.getString("login"))));
			
			
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
		
		
		return d;
	}
	
	
	// Recherche de tout les départs
	@Override
	public List<Depart> trouver() {
	
		List<Depart> d = new ArrayList<Depart>();
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query =
					"SELECT * FROM Depart d, Utilisateur u "
							+ "WHERE D.Matricule=U.idPilote";
			connection = daoFabrique.getConnection();
			AvionDaoImpl a = new AvionDaoImpl(DAOFabrique.getInstance());
			creation = connection.prepareStatement(Query);
			res = creation.executeQuery();
			while (res.next()) {
				Depart dep = new Depart();
				dep.setNumvol(daoFabrique.getVolDao().trouver(
						res.getString("numVol")));
				dep.setDateDepart(new DateTime(res.getDate("DateDepart")
						.getTime()));
				dep.setNumav(a.trouver(res.getInt("numAv")));
				dep.setMatricule(daoFabrique.getUtilisateurDao().trouver(
						res.getString("login")));
				dep.setModeration(res.getString("moderation").equals("Y") ? true
						: false);
				d.add(dep);
			}
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
		
		return d;
	}
}

package dao.Implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.Avion;
import dao.DAOFabrique;
import dao.interfaces.AvionDao;



public class AvionDaoImpl implements AvionDao {
	
	private final DAOFabrique daoFabrique;
	
	
	public AvionDaoImpl(DAOFabrique instance) {
	
		this.daoFabrique = instance;
	}
	
	
	// Recherche d'un objet dans la base de données à partir de son identifiant
	// unique
	@Override
	public Avion trouver(Integer Numav) {
	
		Avion a = new Avion();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query = "SELECT * FROM Avion WHERE Numav=?";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			creation.setInt(1, Numav);
			res = creation.executeQuery();
			while (res.next()) {
				a.setNumav(Numav);
				a.setCapacite(res.getInt(2));
				a.setType(res.getString(3));
				a.setEntrepot(res.getString(4));
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
		
		return a;
	}
	
	
	// Création de l'objet dans la BDD à partir de son Bean
	@Override
	public void creer(Avion avion) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		
		try {
			String Query =
					"insert into avion (Numav, Capacite, Type, Entrepot) "
							+ "values(seq_avion.nextval,?,?,?)";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			
			creation.setInt(1, avion.getCapacite());
			creation.setString(2, avion.getType());
			creation.setString(3, avion.getEntrepot());
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
	
	
	// Update d'un avion dans la BDD a partir de son bean
	@Override
	public void update(Avion avion) {
	
		Connection connection = null;
		PreparedStatement creation = null;
		
		try {
			String Query =
					"UPDATE AVION SET Capacite=?, Type=?, Entrepot=? "
							+ "WHERE Numav=?";
			
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			
			creation.setInt(1, avion.getCapacite());
			creation.setString(2, avion.getType());
			creation.setString(3, avion.getEntrepot());
			creation.setInt(4, avion.getNumav());
			
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
	
	
	// Rercherche de tout les avions
	@Override
	public List<Avion> trouver() {
	
		List<Avion> l = new ArrayList<Avion>();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query = "SELECT * FROM Avion";
			connection = daoFabrique.getConnection();
			
			creation = connection.prepareStatement(Query);
			res = creation.executeQuery();
			while (res.next()) {
				Avion a = new Avion();
				a.setNumav(res.getInt(1));
				a.setCapacite(res.getInt(2));
				a.setType(res.getString(3));
				a.setEntrepot(res.getString(4));
				l.add(a);
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
		
		return l;
	}
}

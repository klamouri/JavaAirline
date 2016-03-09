package dao.Implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.joda.time.DateTime;


import bean.Depart;
import bean.Reservation;
import bean.Utilisateur;
import dao.DAOFabrique;
import dao.interfaces.UtilisateurDao;



public class UtilisateurDaoImpl implements UtilisateurDao {
	
	private final DAOFabrique daoFabrique;
	
	
	public UtilisateurDaoImpl(DAOFabrique instance) {
	
		this.daoFabrique = instance;
	}
	
	
	@Override
	public void creer(Utilisateur utilisateur) {
	
		Connection connection = null;
		PreparedStatement creationSpecialisation = null;
		PreparedStatement creationUtilisateur = null;
		PreparedStatement getIdSpe = null;
		ResultSet res = null;
		
		try {
			connection = daoFabrique.getConnection();
			
			String QuerySpecialisation;
			Integer idSpecialisation = null;
			
			//
			// Création de la spécialisation
			//
			
			if (utilisateur.getTypeSpecialisation().equals("pilote")) {
				getIdSpe =
						connection
								.prepareStatement("select seq_pilote.nextval from dual");
				res = getIdSpe.executeQuery();
				while (res.next())
					idSpecialisation = res.getInt(1);
				QuerySpecialisation =
						"insert into pilote "
								+ "(Matricule, Nom, Ville, Age, Salaire) "
								+ "values(?,?,?,?,?)";
				creationSpecialisation =
						connection.prepareStatement(QuerySpecialisation);
				creationSpecialisation.setInt(1, idSpecialisation);
				creationSpecialisation.setString(2, utilisateur.getNom());
				creationSpecialisation.setString(3, utilisateur.getVille());
				creationSpecialisation.setInt(4, utilisateur.getAge());
				creationSpecialisation.setInt(5, utilisateur.getSalaire());
				creationSpecialisation.execute();
				
			}
			
			else if (utilisateur.getTypeSpecialisation().equals("passager")) {
				getIdSpe =
						connection
								.prepareStatement("select seq_passager.nextval from dual");
				res = getIdSpe.executeQuery();
				while (res.next())
					idSpecialisation = res.getInt(1);
				
				
				QuerySpecialisation =
						"insert into passager (Numab, Nomab) " + "values(?,?)";
				creationSpecialisation =
						connection.prepareStatement(QuerySpecialisation);
				creationSpecialisation.setInt(1, idSpecialisation);
				creationSpecialisation.setString(2, utilisateur.getNom());
				creationSpecialisation.execute();
			}
			
			//
			// Création de l'utilisateur
			//
			
			String QueryUtilisateur =
					"insert into utilisateur (NumUti, login, mdp, idPilote, idPassager) "
							+ "values(seq_utilisateur.nextval,? ,? ,?,?)";
			creationUtilisateur = connection.prepareStatement(QueryUtilisateur);
			
			creationUtilisateur.setString(1, utilisateur.getLogin());
			creationUtilisateur.setString(2, utilisateur.getMdp());
			
			
			if (utilisateur.getTypeSpecialisation().equals("pilote")) {
				
				
				creationUtilisateur.setInt(3, idSpecialisation);
				creationUtilisateur.setNull(4, java.sql.Types.INTEGER);
			}
			else if (utilisateur.getTypeSpecialisation().equals("admin")) {
				creationUtilisateur.setNull(3, java.sql.Types.INTEGER);
				creationUtilisateur.setNull(4, java.sql.Types.INTEGER);
			}
			else {// Passager
				creationUtilisateur.setNull(3, java.sql.Types.INTEGER);
				creationUtilisateur.setInt(4, idSpecialisation);
			}
			creationUtilisateur.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			// Fermeture des connexions
			if (connection != null)
				connection.close();
			if (creationSpecialisation != null)
				creationSpecialisation.close();
			if (creationUtilisateur != null)
				creationUtilisateur.close();
			if (getIdSpe != null)
				getIdSpe.close();
			if (res != null)
				res.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update(Utilisateur utilisateur) {
	
		Connection connection = null;
		PreparedStatement creationSpecialisation = null;
		try {
			connection = daoFabrique.getConnection();
			
			String QuerySpecialisation;
			
			//
			// Création de la spécialisation
			//
			
			if (utilisateur.getTypeSpecialisation().equals("pilote")) {
				
				QuerySpecialisation =
						"UPDATE pilote SET Nom = ?, Ville = ?, Age = ?,"
								+ "Salaire = ? WHERE Matricule = ?";
				
				creationSpecialisation =
						connection.prepareStatement(QuerySpecialisation);
				creationSpecialisation.setString(1, utilisateur.getNom());
				creationSpecialisation.setString(2, utilisateur.getVille());
				creationSpecialisation.setInt(3, utilisateur.getAge());
				creationSpecialisation.setInt(4, utilisateur.getSalaire());
				creationSpecialisation.setInt(5,
						utilisateur.getIdSpecialisation());
				creationSpecialisation.execute();
				
			}
			
			else if (utilisateur.getTypeSpecialisation().equals("passager")) {
				
				
				QuerySpecialisation =
						"UPDATE passager SET Nomab = ? WHERE Numab = ?";
				creationSpecialisation =
						connection.prepareStatement(QuerySpecialisation);
				
				creationSpecialisation.setString(1, utilisateur.getNom());
				creationSpecialisation.setInt(2,
						utilisateur.getIdSpecialisation());
				creationSpecialisation.execute();
			}
			
			//
			// Création de l'utilisateur
			//
			creationSpecialisation.close();
			String QueryUtilisateur =
					"UPDATE utilisateur "
							+ "SET login = ?, mdp = ?, idPilote = ?, "
							+ "idPassager = ? " + "WHERE NumUti = ?";
			creationSpecialisation =
					connection.prepareStatement(QueryUtilisateur);
			
			creationSpecialisation.setString(1, utilisateur.getLogin());
			creationSpecialisation.setString(2, utilisateur.getMdp());
			
			if (utilisateur.getTypeSpecialisation().equals("pilote")) {
				creationSpecialisation.setInt(3,
						utilisateur.getIdSpecialisation());
				creationSpecialisation.setNull(4, java.sql.Types.INTEGER);
			}
			else if (utilisateur.getTypeSpecialisation().equals("admin")) {
				creationSpecialisation.setNull(3, java.sql.Types.INTEGER);
				creationSpecialisation.setNull(4, java.sql.Types.INTEGER);
			}
			else {// Passager
				creationSpecialisation.setNull(3, java.sql.Types.INTEGER);
				creationSpecialisation.setInt(4,
						utilisateur.getIdSpecialisation());
			}
			creationSpecialisation.setInt(5, utilisateur.getIdConnexion());
			creationSpecialisation.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			// Fermeture des connexions
			if (connection != null)
				connection.close();
			if (creationSpecialisation != null)
				creationSpecialisation.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public Utilisateur trouver(String email) {
	
		Connection connection = null;
		PreparedStatement utilisateur = null;
		PreparedStatement specialisation = null;
		Utilisateur u = new Utilisateur();
		ResultSet res = null;
		ResultSet resSpe = null;
		try {
			connection = daoFabrique.getConnection();
			String QueryUtilisateur = "SELECT * FROM utilisateur WHERE login=?";
			utilisateur = connection.prepareStatement(QueryUtilisateur);
			utilisateur.setString(1, email);
			res = utilisateur.executeQuery();
			
			while (res.next()) {
				
				u.setIdConnexion(res.getInt(1));
				u.setLogin(res.getString(2));
				u.setMdp(res.getString(3));
				
				if (res.getObject(4) != null) {
					ArrayList<Depart> dep = new ArrayList<Depart>();
					u.setIdSpecialisation(res.getInt(4));
					u.setTypeSpecialisation("pilote");
					String QuerySpecialisation =
							"SELECT * FROM pilote WHERE Matricule=?";
					specialisation =
							connection.prepareStatement(QuerySpecialisation);
					specialisation.setInt(1, u.getIdSpecialisation());
					resSpe = specialisation.executeQuery();
					while (resSpe.next()) {
						u.setNom(resSpe.getString(2));
						u.setVille(resSpe.getString(3));
						u.setAge(resSpe.getInt(4));
						u.setSalaire(resSpe.getInt(5));
					}
					utilisateur.close();
					res.close();
					resSpe.close();
					specialisation.close();
					
					
					String QueryListe =
							"SELECT Numvol, DateDepart" + " FROM Depart "
									+ "WHERE Matricule=? ORDER BY 2,1";
					utilisateur = connection.prepareStatement(QueryListe);
					utilisateur.setInt(1, u.getIdSpecialisation());
					res = utilisateur.executeQuery();
					
					while (res.next())
						dep.add(daoFabrique.getDepartDao().trouver(
								res.getString(1),
								new DateTime(res.getDate(2).getTime()), u));
					u.setDeparts(dep);
					String[][] services = new String[3][2];
					services[0][1] = "PLANNING";
					services[0][0] = "/Planning";
					services[1][1] = "CHANGER MOT DE PASSE";
					services[1][0] = "/MotDePasse";
					services[2][1] = "DECONNEXION";
					services[2][0] = "/Deconnexion";
					u.setServices(services);
					
				}
				else if (res.getObject(5) != null) {
					u.setIdSpecialisation(res.getInt(5));
					u.setTypeSpecialisation("passager");
					String QuerySpecialisation =
							"SELECT * FROM passager WHERE Numab=?";
					specialisation =
							connection.prepareStatement(QuerySpecialisation);
					specialisation.setInt(1, u.getIdSpecialisation());
					resSpe = specialisation.executeQuery();
					while (resSpe.next())
						u.setNom(resSpe.getString(2));
					
					ArrayList<Reservation> reserv =
							new ArrayList<Reservation>();
					String QueryListe =
							"SELECT numvol, DateDepart FROM Reservation "
									+ "WHERE Numab=? ORDER BY 2,1";
					utilisateur = connection.prepareStatement(QueryListe);
					utilisateur.setInt(1, u.getIdSpecialisation());
					res = utilisateur.executeQuery();
					while (res.next())
						reserv.add(daoFabrique.getReservationDao().trouver(u,
								res.getString(1),
								new DateTime(res.getDate(2).getTime())));
					u.setReservations(reserv);
					String[][] services = new String[4][2];
					services[0][1] = "PLANNING";
					services[0][0] = "/Planning";
					services[1][1] = "RESERVER UN VOL";
					services[1][0] = "/Reservation";
					services[2][1] = "CHANGER MOT DE PASSE";
					services[2][0] = "/MotDePasse";
					services[3][1] = "DECONNEXION";
					services[3][0] = "/Deconnexion";
					u.setServices(services);
				}
				else {
					u.setTypeSpecialisation("admin");
					
					String QueryListe =
							"SELECT d.Numvol, d.DateDepart, u.login FROM Depart d, utilisateur U WHERE d.Moderation='Y' AND d.Matricule=u.IdPilote ORDER BY 2,1";
					utilisateur = connection.prepareStatement(QueryListe);
					res = utilisateur.executeQuery();
					ArrayList<Depart> dep = new ArrayList<Depart>();
					while (res.next())
						dep.add(daoFabrique.getDepartDao().trouver(
								res.getString(1),
								new DateTime(res.getDate(2).getTime()),
								daoFabrique.getUtilisateurDao().trouver(
										res.getString(3))));
					u.setModerations(dep);
					
					
					String[][] services = new String[4][2];
					services[0][1] = "MODERER DEMANDE PILOTE";
					services[0][0] = "/Planning";
					services[1][1] = "AJOUTER UN DEPART";
					services[1][0] = "/AjoutDepart";
					services[2][1] = "SUPPRIMER UN DEPART";
					services[2][0] = "/SupprimerDepart";
					services[3][1] = "DECONNEXION";
					services[3][0] = "/Deconnexion";
					u.setServices(services);
					
					
				}
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			// Fermeture des connexions
			if (connection != null)
				connection.close();
			if (utilisateur != null)
				utilisateur.close();
			if (res != null)
				res.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	@Override
	public List<Utilisateur> trouverPilote() {
	
		List<Utilisateur> u = new ArrayList<Utilisateur>();
		
		Connection connection = null;
		PreparedStatement creation = null;
		ResultSet res = null;
		try {
			String Query =
					"SELECT login FROM Utilisateur WHERE IdPilote IS NOT NULL";
			connection = daoFabrique.getConnection();
			creation = connection.prepareStatement(Query);
			res = creation.executeQuery();
			while (res.next())
				u.add(trouver(res.getString("login")));
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
		
		return u;
	}
}

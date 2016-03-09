package dao;


import java.sql.Connection;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import dao.Implementation.AvionDaoImpl;
import dao.Implementation.DepartDaoImpl;
import dao.Implementation.ReservationDaoImpl;
import dao.Implementation.UtilisateurDaoImpl;
import dao.Implementation.VolDaoImpl;
import dao.interfaces.AvionDao;
import dao.interfaces.DepartDao;
import dao.interfaces.ReservationDao;
import dao.interfaces.UtilisateurDao;
import dao.interfaces.VolDao;



public class DAOFabrique {
	
	
	private static DAOFabrique instance;
	private static DataSource dataSource;
	
	
	private DAOFabrique(DataSource dataSource) {
	
		DAOFabrique.dataSource = dataSource;
	}
	
	
	private static void init() {
	
		try {
			// Obtention du DataSource à l'origine du pool de connection géré
			// par
			// Tomcat
			
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/testdb");
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		instance = new DAOFabrique(dataSource);
	}
	
	
	public static DAOFabrique getInstance() {
	
		if (instance == null)
			// Double vérification pour l'initilisation pour ne pas créer de
			// soucis de thread-safety
			synchronized (DAOFabrique.class) {
				if (instance == null)
					init();
				return instance;
			}
		else
			return instance;
		
		
	}
	
	
	public Connection getConnection() throws SQLException {
	
		return dataSource.getConnection();
	}
	
	
	public AvionDao getAvionDao() {
	
		return new AvionDaoImpl(instance);
	}
	
	
	public DepartDao getDepartDao() {
	
		return new DepartDaoImpl(instance);
	}
	
	
	public ReservationDao getReservationDao() {
	
		return new ReservationDaoImpl(instance);
	}
	
	
	public UtilisateurDao getUtilisateurDao() {
	
		return new UtilisateurDaoImpl(instance);
	}
	
	
	public VolDao getVolDao() {
	
		return new VolDaoImpl(instance);
	}
	
}
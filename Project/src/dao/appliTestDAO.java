package dao;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.joda.time.DateTime;


import bean.Utilisateur;
import bean.Vol;
import dao.interfaces.UtilisateurDao;
import dao.interfaces.VolDao;



/**
 * @author Karim & Alexy Servlet de test pour tester les requêtes SQL Pour
 *         qu'elles marchent il faut penser à desactiver le filtre
 */
public class appliTestDAO extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		PrintWriter out = resp.getWriter();
		
		
		out.println(req.getParameter("numvol"));
		out.println(req.getParameter("pilote"));
		out.println("\n\n\n\n");
		out.println("\n\n\n\n");
		out.println("\n\n\n\n");
		out.println("\n\n\n\n");
		
		
		out.println(DAOFabrique.getInstance().getDepartDao()
				.trouver("New_York", "Paris", new DateTime(2001, 01, 25, 0, 0))
				.get(0).getMatricule().getNom());
		
		
		out.println("\n\n\n\n");
		
		
		int aa = 0;
		try {
			PreparedStatement pre =
					DAOFabrique
							.getInstance()
							.getConnection()
							.prepareStatement(
									"select seq_pilote.nextval from dual");
			ResultSet res = pre.executeQuery();
			while (res.next())
				aa = res.getInt(1);
			out.println(aa);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DAOFabrique dao = DAOFabrique.getInstance();
		out.println("\n\nTEST DES UTILISATEURS\n");
		UtilisateurDao uDao = dao.getUtilisateurDao();
		Utilisateur u2 = uDao.trouver("Admin");
		// out.println(uDao.getNextVal("seq_passager.nextval"));
		out.println(u2.getNom());
		out.println(u2.getLogin());
		out.println(u2.getMdp());
		out.println(u2.getIdConnexion());
		out.println(u2.getIdSpecialisation());
		out.println(u2.getVille());
		out.println(u2.getAge());
		out.println(u2.getSalaire());
		out.println(u2.getTypeSpecialisation());
		
		out.println(u2.getReservations());
		
		out.println(u2.getDeparts());
		out.println(u2.getModerations());
		
		out.println("\n\nTEST DES RESERVATIONS\n");
		// ReservationDao rDao = dao.getReservationDao();
		// Utilisateur u = new Utilisateur();
		// u.setIdConnexion(1);
		// u.setIdSpecialisation(1);
		// u.setTypeSpecialisation("passager");
		// Reservation r =
		// rDao.trouver(u, "AF333", new DateTime(2001, 12, 25, 0, 0));
		// out.println(r.getAssurance());
		// r.setAssurance(true);
		// rDao.update(r);
		// r = rDao.trouver(u, "AF333", new DateTime(2001, 12, 25, 0, 0));
		// out.println(r.getAssurance());
		//
		//
		out.println("\n\nTEST DES VOLS\n");
		VolDao vDao = dao.getVolDao();
		Vol v = vDao.trouver("AF900");
		out.println(v.getHeureDepart());
		out.println(v.getHeureArrivee());
		out.println(v.getVilleArrivee());
		
		v.setHeureArrivee(new DateTime());
		vDao.update(v);
		v = vDao.trouver("AF900");
		out.println(v.getHeureDepart());
		out.println(v.getHeureArrivee());
		out.println(v.getVilleArrivee());
		//
		// v.setHeureArrivee(new DateTime());
		// v.setNumvol("KARIM2");
		// // vDao.creer(v);
		// v = vDao.trouver("KARIM2");
		// out.println(v.getHeureDepart());
		// out.println(v.getHeureArrivee());
		// out.println(v.getVilleArrivee());
		//
		//
		// out.println("\n\nTEST DES DEPARTS\n");
		// DepartDao dDao = dao.getDepartDao();
		// Depart d = dDao.trouver("AF421", new DateTime(2001, 12, 19, 0, 0));
		// out.println(d.getMatricule());
		// out.println(d.getDateDepart());
		// out.println(d.getModeration());
		//
		// d.setModeration(true);
		// dDao.update(d);
		// d = dDao.trouver("AF421", new DateTime(2001, 12, 19, 0, 0));
		// out.println(d.getModeration());
		//
		// d.setModeration(true);
		// DateTime date = new DateTime();
		// d.setDateDepart(date);
		// // dDao.creer(d);
		// d = dDao.trouver("AF421", date);
		// out.println(d.getModeration());
		//
		//
		// out.println("\n\nTEST DES AVIONS\n");
		// AvionDao aDao = dao.getAvionDao();
		// Avion a = aDao.trouver(1);
		// out.println(a.getNumav());
		// out.println(a.getCapacite());
		// out.println(a.getType());
		// out.println(a.getEntrepot());
		// a.setType("Karim");
		// aDao.creer(a);
		// a = aDao.trouver(1);
		// out.println(a.getNumav());
		// out.println(a.getCapacite());
		// out.println(a.getType());
		// out.println(a.getEntrepot());
		//
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doGet(req, resp);
	};
}

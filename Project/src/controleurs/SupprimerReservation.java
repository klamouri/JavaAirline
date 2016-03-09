package controleurs;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.joda.time.DateTime;


import bean.Reservation;
import bean.Utilisateur;
import dao.DAOFabrique;



public class SupprimerReservation extends HttpServlet {
	
	private static final String J_DATE = "jDate";
	private static final String NUM_VOL = "numVol";
	private static final String A_DATE = "aDate";
	private static final String M_DATE = "mDate";
	private static final long serialVersionUID = 7336137279900288361L;
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	private static final String CONTROLEUR_PLANNING = "/Planning";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		// Si l'utilisateur est un passager on lui permet de supprimer une de
		// ses réservations
		if (u.getTypeSpecialisation().equals("passager")) {
			
			Reservation r = new Reservation();
			DAOFabrique dao = DAOFabrique.getInstance();
			String jDateS = req.getParameter(J_DATE);
			String mDateS = req.getParameter(M_DATE);
			String aDateS = req.getParameter(A_DATE);
			String numVol = req.getParameter(NUM_VOL);
			if (jDateS != null && mDateS != null && aDateS != null
					&& numVol != null) {
				Integer jDate = Integer.parseInt(jDateS);
				Integer mDate = Integer.parseInt(mDateS);
				Integer aDate = Integer.parseInt(aDateS);
				
				// On met les critères dans le bean reservation que l'on souhaite
				// supprimer
				r.setUtilisateur(u);
				r.setVol(dao.getVolDao().trouver(numVol));
				r.setDateDepart(new DateTime(aDate, mDate, jDate, 0, 0, 0));
				
				// Supression à partir du bean de critères
				dao.getReservationDao().delete(r);
				
				// Mise a jour de l'utilisateur en cours
				session.setAttribute(ATTR_UTILISATEUR, dao.getUtilisateurDao()
						.trouver(u.getLogin()));
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_PLANNING);
				
			}
			else
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		}
		else
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		
	}
	
	
}

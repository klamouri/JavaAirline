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



@SuppressWarnings("serial")
public class AjoutReservation extends HttpServlet {
	
	private static final String J_DATE = "jDate";
	private static final String NUM_VOL = "numVol";
	private static final String A_DATE = "aDate";
	private static final String M_DATE = "mDate";
	private static final String ASSURANCE = "assurance";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "/Accueil";
	private static final String CONTROLEUR_PLANNING = "/Planning";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		if (u.getTypeSpecialisation().equals("passager")) {
			DAOFabrique dao = DAOFabrique.getInstance();
			
			String jDateS = req.getParameter(J_DATE);
			String mDateS = req.getParameter(M_DATE);
			String aDateS = req.getParameter(A_DATE);
			String numVol = req.getParameter(NUM_VOL);
			String assuranceS = req.getParameter(ASSURANCE);
			if (jDateS != null && mDateS != null && aDateS != null
					&& numVol != null && assuranceS != null) {
				
				Integer jDate = Integer.parseInt(jDateS);
				Integer mDate = Integer.parseInt(mDateS);
				Integer aDate = Integer.parseInt(aDateS);
				Boolean assurance = assuranceS.equals("true") ? true : false;
				
				// Affectation du bean à sauvegarder
				Reservation reservation = new Reservation();
				reservation.setAssurance(assurance);
				reservation.setDateDepart(new DateTime(aDate, mDate, jDate, 0,
						0));
				reservation.setUtilisateur((Utilisateur) session
						.getAttribute(ATTR_UTILISATEUR));
				reservation.setVol(DAOFabrique.getInstance().getVolDao()
						.trouver(numVol));
				
				// Creation de la réservation persistante
				DAOFabrique.getInstance().getReservationDao()
						.creer(reservation);
				
				// Mise a jour de l'utilisateur en cours
				session.setAttribute(ATTR_UTILISATEUR, dao.getUtilisateurDao()
						.trouver(u.getLogin()));
				
				// Redirection vers le planning
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_PLANNING);
				
			}
			else
				// Si un paramètre nécessaire est manquant redirection vers
				// l'accueil
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		}
		else
			// Si l'utilisateur n'est pas un passager on le redirige vers son
			// accueil
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
	}
}
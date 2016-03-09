package controleurs;


import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.joda.time.DateTime;


import bean.Depart;
import bean.Utilisateur;
import dao.DAOFabrique;



@SuppressWarnings("serial")
public class SupprimerDepart extends HttpServlet {
	
	private static final String LISTE_DEPART = "listeDepart";
	private static final String J_DATE = "jDate";
	private static final String NUM_VOL = "numVol";
	private static final String A_DATE = "aDate";
	private static final String M_DATE = "mDate";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	private static final String CONTROLEUR_PLANNING = "/Planning";
	private static final String VUE_ADMIN =
			"/WEB-INF/Vues/SupprimerDepartAdmin.jsp";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		// Si l'utilisateur est un pilote celui-ci peut demander à être
		// désaffecté d'un vol
		if (u.getTypeSpecialisation().equals("pilote")) {
			
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
				
				
				Depart d = new Depart();
				d =
						dao.getDepartDao().trouver(numVol,
								new DateTime(aDate, mDate, jDate, 0, 0, 0), u);
				
				// On inverse la demande de modération comme ça si le pilote
				// s'est trompé il peut rectifier sa demande avant le passage
				// d'un admin
				d.setModeration(!d.getModeration());
				dao.getDepartDao().update(d);
				// Mise a jour de l'utilisateur en cours, puis redirection
				session.setAttribute(ATTR_UTILISATEUR, dao.getUtilisateurDao()
						.trouver(u.getLogin()));
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_PLANNING);
				
			}
			else
				resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		}
		// Si l'utilisateur est un admin on lui liste tout les départs pour
		// qu'il choisisse celui qu'il souhaite supprimer
		else if (u.getTypeSpecialisation().equals("admin")) {
			
			List<Depart> listeDepart =
					DAOFabrique.getInstance().getDepartDao().trouver();
			
			// On met la liste dans le scope de la requête puis on le transfère
			// vers la vue affichant les vols
			req.setAttribute(LISTE_DEPART, listeDepart);
			this.getServletContext().getRequestDispatcher(VUE_ADMIN)
					.forward(req, resp);
		}
		else
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		
	}
}

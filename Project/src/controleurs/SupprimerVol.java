package controleurs;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Utilisateur;
import bean.Vol;
import dao.DAOFabrique;



@SuppressWarnings("serial")
public class SupprimerVol extends HttpServlet {
	
	private static final String NUM_VOL = "numVol";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	private static final String CONTROLEUR_PLANNING = "/Planning";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		if (u.getTypeSpecialisation().equals("admin")) {
			DAOFabrique dao = DAOFabrique.getInstance();
			String numVol = req.getParameter(NUM_VOL);
			if (numVol != null) {
				
				// Recherche du vol à supprimer
				Vol v = dao.getVolDao().trouver(numVol);
				// Suppression de celui-ci
				dao.getVolDao().delete(v);
				
				// Mise a jour de l'utiisateur en cours
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

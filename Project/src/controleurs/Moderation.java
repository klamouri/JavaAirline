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
public class Moderation extends HttpServlet {
	
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	private static final String ATTR_LISTE = "listePilote";
	private static final String ATTR_NUMVOL = "numvol";
	private static final String VUE_MODERATION = "/WEB-INF/Vues/Moderation.jsp";
	private static final String ATTR_A_DATE = "aDate";
	private static final String ATTR_M_DATE = "mDate";
	private static final String ATTR_J_DATE = "jDate";
	private static final String ATTR_LOGIN = "pilote";
	private static final String CONTROLEUR_PLANNING = "/Planning";
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		String numVol = request.getParameter(ATTR_NUMVOL);
		String jDateS = request.getParameter(ATTR_J_DATE);
		String mDateS = request.getParameter(ATTR_M_DATE);
		String aDateS = request.getParameter(ATTR_A_DATE);
		
		// Si l'utilisateur est un admin et que les paramètres dont on a besoin
		// sont présents on peut lui afficher sa page de modération
		if (u.getTypeSpecialisation().equals("admin") && numVol != null
				&& jDateS != null && mDateS != null && aDateS != null) {
			List<Utilisateur> listePilote =
					DAOFabrique.getInstance().getUtilisateurDao()
							.trouverPilote();
			request.setAttribute(ATTR_NUMVOL, numVol);
			request.setAttribute(ATTR_J_DATE, jDateS);
			request.setAttribute(ATTR_M_DATE, mDateS);
			request.setAttribute(ATTR_A_DATE, aDateS);
			request.setAttribute(ATTR_LISTE, listePilote);
			this.getServletContext().getRequestDispatcher(VUE_MODERATION)
					.forward(request, response);
			
		}
		// Sinon on lui réaffiche son planning pour qu'il choisisse un vol à
		// modérer
		else
			response.sendRedirect(request.getContextPath()
					+ CONTROLEUR_REDIRECTION);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		// Si l'utlisateur est un admin on peut modifier les attributs du départ
		if (u.getTypeSpecialisation().equals("admin")) {
			
			DAOFabrique dao = DAOFabrique.getInstance();
			String jDateS = req.getParameter(ATTR_J_DATE);
			String mDateS = req.getParameter(ATTR_M_DATE);
			String aDateS = req.getParameter(ATTR_A_DATE);
			String numVol = req.getParameter(ATTR_NUMVOL);
			String login = req.getParameter(ATTR_LOGIN);
			if (jDateS != null && mDateS != null && aDateS != null
					&& numVol != null && login != null) {
				Integer jDate = Integer.parseInt(jDateS);
				Integer mDate = Integer.parseInt(mDateS);
				Integer aDate = Integer.parseInt(aDateS);
				
				// Recuperation de l'ancien départ puis mise à jour de celui-ci
				Depart d;
				d =
						dao.getDepartDao().trouver(numVol,
								new DateTime(aDate, mDate, jDate, 0, 0, 0), u);
				d.setMatricule(dao.getUtilisateurDao().trouver(login));
				d.setModeration(!d.getModeration());
				dao.getDepartDao().update(d);
				
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

package controleurs.bases;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Utilisateur;
import dao.DAOFabrique;



@SuppressWarnings("serial")
public class MotDePasse extends HttpServlet {
	
	private static final String MDP_OLD = "mdpOld";
	private static final String MDP3 = "mdp";
	private static final String MDP22 = "mdp2";
	private static final String REDIRECT_PLANNING = "/Planning";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String VUE = "/WEB-INF/Vues/MotDePasse.jsp";
	private static final String CONTROLEUR_REDIRECTION = "";
	private static final String ATTR_UTIL = "utilisateur";
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		if (u.getTypeSpecialisation().equals("passager")
				|| u.getTypeSpecialisation().equals("pilote"))
			this.getServletContext().getRequestDispatcher(VUE)
					.forward(request, response);
		else
			this.getServletContext().getRequestDispatcher(REDIRECT_PLANNING)
					.forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		if (req.getParameter(MDP_OLD) != null && req.getParameter(MDP3) != null
				&& req.getParameter(MDP22) != null) {
			String mdpOld = req.getParameter(MDP_OLD);
			String mdp = req.getParameter(MDP3);
			String mdp2 = req.getParameter(MDP22);
			if (u.getMdp().equals(mdpOld) && mdp.equals(mdp2)) {
				u.setMdp(mdp);
				DAOFabrique.getInstance().getUtilisateurDao().update(u);
				session.setAttribute(ATTR_UTIL, DAOFabrique.getInstance()
						.getUtilisateurDao().trouver(u.getLogin()));
			}
		}
		resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		
		
	}
}

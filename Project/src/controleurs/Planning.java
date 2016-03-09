package controleurs;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Utilisateur;
import dao.DAOFabrique;



@SuppressWarnings("serial")
public class Planning extends HttpServlet {
	
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String VUE_PLANNING_UTILISATEUR =
			"/WEB-INF/Vues/PlanningPassager.jsp";
	private static final String VUE_PLANNING_PILOTE =
			"/WEB-INF/Vues/PlanningPilote.jsp";
	private static final String VUE_PLANNING_ADMIN =
			"/WEB-INF/Vues/PlanningAdmin.jsp";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		DAOFabrique dao = DAOFabrique.getInstance();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		// Rafraichissement de l'utilisateur (Doute sur l'utilité)
		dao.getUtilisateurDao().trouver(u.getLogin());
		session.setAttribute(ATTR_UTILISATEUR, u);
		
		// Si l'utilisateur est un passager on le redirige vers sa vue
		if (u.getTypeSpecialisation().equals("passager"))
			this.getServletContext()
					.getRequestDispatcher(VUE_PLANNING_UTILISATEUR)
					.forward(req, resp);
		// Si l'utilisateur est un pilote on le redirige vers sa vue
		else if (u.getTypeSpecialisation().equals("pilote"))
			this.getServletContext().getRequestDispatcher(VUE_PLANNING_PILOTE)
					.forward(req, resp);
		// Si l'utilisateur est un admin on le redirige vers sa vue.
		else if (u.getTypeSpecialisation().equals("admin"))
			this.getServletContext().getRequestDispatcher(VUE_PLANNING_ADMIN)
					.forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
	}
}

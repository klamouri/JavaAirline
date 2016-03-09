package controleurs.bases;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Utilisateur;
import dao.DAOFabrique;



public class Connexion extends HttpServlet {
	
	private static final long serialVersionUID = 4111019794157911532L;
	private static final String VUE = "/VuesPublic/index.jsp";
	private static final String REDIRECT_PLANNING = "/Planning";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute(ATTR_UTILISATEUR) == null)
			this.getServletContext().getRequestDispatcher(VUE)
					.forward(request, response);
		else
			this.getServletContext().getRequestDispatcher(REDIRECT_PLANNING)
					.forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession(true);
		String mail = req.getParameter("identifiant");
		String mdp = req.getParameter("mdp");
		if (mail.equals("") || mdp.equals(""))
			resp.sendRedirect(req.getContextPath() + VUE);
		else {
			Utilisateur u =
					DAOFabrique.getInstance().getUtilisateurDao().trouver(mail);
			if (u.getIdConnexion() == null)
				resp.sendRedirect(req.getContextPath() + VUE);
			else if (u.getMdp().equals(mdp)) {
				session.setAttribute(ATTR_UTILISATEUR, u);
				resp.sendRedirect(req.getContextPath() + REDIRECT_PLANNING);
			}
			else
				resp.sendRedirect(req.getContextPath() + VUE);
		}
	}
}

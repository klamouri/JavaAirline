package controleurs.bases;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Accueil extends HttpServlet {
	
	private static final long serialVersionUID = 4111019794157911532L;
	// private static final String VUE = "/VuesPublic/index.jsp";
	private static final String REDIRECT_PLANNING = "/Planning";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute(ATTR_UTILISATEUR) != null)
			this.getServletContext().getRequestDispatcher(REDIRECT_PLANNING)
					.forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		
	}
}

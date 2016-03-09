package controleurs.bases;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Utilisateur;
import dao.DAOFabrique;



public class Inscription extends HttpServlet {
	
	private static final long serialVersionUID = 7176530158227740973L;
	private static final String VUE = "/VuesPublic/inscription.jsp";
	private static final String REDIRECT_PLANNING = "/Planning";
	private static final String REDIRECT_CONNEXION = "/Connexion";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
	
		
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
	
		String login;
		String mdp;
		String nom;
		String ville;
		String age;
		String salaire;
		String pilote;
		
		login = req.getParameter("login");
		mdp = req.getParameter("mdp");
		nom = req.getParameter("nom");
		ville = req.getParameter("ville");
		age = req.getParameter("age");
		salaire = req.getParameter("salaire");
		pilote = req.getParameter("pilote");
		
		
		if (pilote != null) {
			
			if (login.equals("") || mdp.equals("") || nom.equals("")
					|| ville.equals("") || age.equals("") || salaire.equals(""))
				
				resp.sendRedirect(req.getContextPath() + VUE);
			
			// Création d'un pilote
			else {
				Utilisateur u = new Utilisateur();
				u.setTypeSpecialisation("pilote");
				u.setLogin(login);
				u.setMdp(mdp);
				u.setNom(nom);
				u.setVille(ville);
				u.setAge(Integer.parseInt(age));
				u.setSalaire(Integer.parseInt(salaire));
				DAOFabrique.getInstance().getUtilisateurDao().creer(u);
				resp.sendRedirect(req.getContextPath() + REDIRECT_CONNEXION);
			}
			
		}
		else if (login == null || mdp == null || nom == null)
			resp.sendRedirect(req.getContextPath() + VUE);
		
		// Création d'un passager
		else {
			Utilisateur u = new Utilisateur();
			u.setTypeSpecialisation("passager");
			u.setLogin(login);
			u.setMdp(mdp);
			u.setNom(nom);
			DAOFabrique.getInstance().getUtilisateurDao().creer(u);
			resp.sendRedirect(req.getContextPath() + REDIRECT_CONNEXION);
		}
		
		
	}
	
}
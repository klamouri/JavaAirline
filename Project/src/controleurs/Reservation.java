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
public class Reservation extends HttpServlet {
	
	private static final String ATTR_LIST = "liste";
	public final static String VUE_FORM = "/WEB-INF/Vues/ReservationForm.jsp";
	public final static String VILLE_DEP = "villeDep";
	public final static String VILLE_ARR = "villeArr";
	public final static String J_DEP = "jDate";
	public final static String M_DEP = "mDate";
	public final static String A_DEP = "aDate";
	private static final String VUE_DEPARTS =
			"/WEB-INF/Vues/ReservationResp.jsp";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		if (u.getTypeSpecialisation().equals("passager"))
			// Affichage du formulaire à afficher pour que l'utilisateur puisse
			// choisir une réservation
			this.getServletContext().getRequestDispatcher(VUE_FORM)
					.forward(req, resp);
		else
			// Si ce n'est pas un passager on le redirige vers l'accueil
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String VilleDep = req.getParameter(VILLE_DEP);
		String VilleArr = req.getParameter(VILLE_ARR);
		String jDate = req.getParameter(J_DEP);
		String mDate = req.getParameter(M_DEP);
		String aDate = req.getParameter(A_DEP);
		List<Depart> list;
		HttpSession session = req.getSession();
		
		if (VilleDep != null && VilleArr != null && jDate != null
				&& mDate != null && aDate != null) {
			// Recherche des départs correspondants aux critères
			list =
					DAOFabrique
							.getInstance()
							.getDepartDao()
							.trouver(
									VilleDep,
									VilleArr,
									new DateTime(Integer.parseInt(aDate),
											Integer.parseInt(mDate), Integer
													.parseInt(jDate), 0, 0));
			// Dépôt de la liste dans le scope de la requête.
			session.setAttribute(ATTR_LIST, list);
			// Transfert du scope request et affichage de la vue de depart à
			// afficher pour que l'utilisateur fasse son choix
			this.getServletContext().getRequestDispatcher(VUE_DEPARTS)
					.forward(req, resp);
		}
		
		
	}
}

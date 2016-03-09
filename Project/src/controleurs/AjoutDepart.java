package controleurs;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.joda.time.DateTime;


import bean.Avion;
import bean.Depart;
import bean.Utilisateur;
import bean.Vol;
import dao.DAOFabrique;



@SuppressWarnings("serial")
public class AjoutDepart extends HttpServlet {
	
	// get
	private static final String ATTR_LISTE_PILOTE = "listePilote";
	private static final String ATTR_LISTE_AVION = "listeAvion";
	
	// post
	private static final String ATTR_NUMVOL = "numvol";
	private static final String ATTR_A_DATE = "aDate";
	private static final String ATTR_M_DATE = "mDate";
	private static final String ATTR_J_DATE = "jDate";
	private static final String ATTR_LOGIN = "login";
	private static final String ATTR_HDATEDEP = "hDateDep";
	private static final String ATTR_MDATEDEP = "MinDateDep";
	private static final String ATTR_HDATEARR = "hDateArr";
	private static final String ATTR_MDATEARR = "MinDateArr";
	private static final String ATTR_VILLE_DEP = "villeDep";
	private static final String ATTR_VILLE_ARR = "villeArr";
	private static final String ATTR_AVION = "avion";
	
	// commun
	private static final String VUE_AJOUT_DEPART =
			"/WEB-INF/Vues/AjoutDepart.jsp";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String CONTROLEUR_REDIRECTION = "";
	
	
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
	
		HttpSession session = req.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute(ATTR_UTILISATEUR);
		
		if (u.getTypeSpecialisation().equals("admin")) {
			// Recherche des pilotes et des avions pour créer un départ
			// Puis ajout des attributs dans la requête puis transfert de
			// celle-ci vers la vue.
			req.setAttribute(ATTR_LISTE_PILOTE, DAOFabrique.getInstance()
					.getUtilisateurDao().trouverPilote());
			req.setAttribute(ATTR_LISTE_AVION, DAOFabrique.getInstance()
					.getAvionDao().trouver());
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_DEPART)
					.forward(req, resp);
		}
		else
			// Si l'utilisateur n'est pas admin, on le redirige vers son accueil
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		
	};
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String numVol = req.getParameter(ATTR_NUMVOL);
		String aDateS = req.getParameter(ATTR_A_DATE);
		String mDateS = req.getParameter(ATTR_M_DATE);
		String jDateS = req.getParameter(ATTR_J_DATE);
		String login = req.getParameter(ATTR_LOGIN);
		String minDateDepS = req.getParameter(ATTR_MDATEDEP);
		String hDateDepS = req.getParameter(ATTR_HDATEDEP);
		String minDateArrS = req.getParameter(ATTR_MDATEARR);
		String hDateArrS = req.getParameter(ATTR_HDATEARR);
		String villeArr = req.getParameter(ATTR_VILLE_ARR);
		String villeDep = req.getParameter(ATTR_VILLE_DEP);
		String avionS = req.getParameter(ATTR_AVION);
		
		if (numVol == null || aDateS == null || mDateS == null
				|| jDateS == null || login == null || minDateDepS == null
				|| hDateDepS == null || minDateArrS == null
				|| hDateArrS == null || villeArr == null || villeDep == null
				|| avionS == null)
			// Si un champ est vide on réaffiche la page
			resp.sendRedirect(req.getContextPath() + VUE_AJOUT_DEPART);
		else {
			int avion = Integer.parseInt(avionS);
			int aDate = Integer.parseInt(aDateS);
			int jDate = Integer.parseInt(jDateS);
			int mDate = Integer.parseInt(mDateS);
			int hDateDep = Integer.parseInt(hDateDepS);
			int minDateDep = Integer.parseInt(minDateDepS);
			int hDateArr = Integer.parseInt(hDateArrS);
			int minDateArr = Integer.parseInt(minDateArrS);
			
			// Recherche de l'avion, et du pilote concerné par la création
			Avion a = DAOFabrique.getInstance().getAvionDao().trouver(avion);
			Utilisateur pilote =
					DAOFabrique.getInstance().getUtilisateurDao()
							.trouver(login);
			
			// Création du vol
			Vol v = new Vol();
			v.setNumvol(numVol);
			v.setHeureDepart(new DateTime(1970, 1, 1, hDateDep, minDateDep));
			v.setHeureArrivee(new DateTime(1970, 1, 1, hDateArr, minDateArr));
			v.setVilleDepart(villeDep);
			v.setVilleArrivee(villeArr);
			DAOFabrique.getInstance().getVolDao().creer(v);
			
			// Création du départ concerné par le vol
			Depart d = new Depart();
			d.setMatricule(pilote);
			d.setNumvol(v);
			d.setModeration(false);
			d.setNumav(a);
			d.setDateDepart(new DateTime(aDate, mDate, jDate, 0, 0));
			DAOFabrique.getInstance().getDepartDao().creer(d);
			resp.sendRedirect(req.getContextPath() + CONTROLEUR_REDIRECTION);
		}
		
	}
}

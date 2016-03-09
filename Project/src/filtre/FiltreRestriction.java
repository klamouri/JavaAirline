package filtre;


import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * @author Karim & Alexy
 * @Brief Filtre qui gère l'accès aux pages publiques pour qu'un non membre ne
 *        puisse pas acceder aux fonctionnalités du site.
 */
public class FiltreRestriction implements Filter {
	
	public static final String ACCES_CONNEXION = "/Connexion";
	public static final String ACCES_INSCRIPTION = "/Inscription";
	public static final String ATT_SESSION_USER = "utilisateur";
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	
	}
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
	
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		// Non-filtrage des pages publiques (Style, inclusions, inscriptions)
		
		
		String chemin =
				request.getRequestURI().substring(
						request.getContextPath().length());
		if (chemin.startsWith("/inc") || chemin.startsWith("/styles")
				|| chemin.startsWith("/VuesPublic")) {
			chain.doFilter(request, response);
			return;
		}
		if (chemin.startsWith(ACCES_INSCRIPTION)) {
			chain.doFilter(request, response);
			return;
		}
		// Récupération de la session depuis la requête
		HttpSession session = request.getSession();
		
		
		/**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		
		
		if (session.getAttribute(ATT_SESSION_USER) == null)
			// Redirection vers la page publique de connexion
			request.getRequestDispatcher(ACCES_CONNEXION).forward(request,
					response);
		else
			// Affichage de la page restreinte
			
			chain.doFilter(request, response);
		
	}
	
	
	@Override
	public void destroy() {
	
	}
}
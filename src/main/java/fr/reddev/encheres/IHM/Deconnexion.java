/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.IHM;

/**
 * @author REDDEV
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Deconnexion Servlet implementation class Deconnexion
 */
@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Deconnecte un utilisateur
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupère la session dan sla requete
		HttpSession session = request.getSession();
		// Détruit la session
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Pseudo")) {
					Cookie cookiePass = new Cookie("Pseudo", cookie.getValue());
					cookiePass.setMaxAge(0);
					response.addCookie(cookiePass); ;

				}
				if (cookie.getName().equals("MDP")) {
					Cookie cookiePass = new Cookie("MDP", cookie.getValue());
					cookiePass.setMaxAge(0);
					response.addCookie(cookiePass); ;
					
				}

			}
		}
		/// TODO supprimer les cookies
		
		
		// redirection sur la page de Home en déconnecter
		response.sendRedirect("http://localhost:8080/ENI-Encheres");
	}

	/**
	 * Aucun post vers cette page
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

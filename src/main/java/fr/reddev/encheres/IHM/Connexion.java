/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.IHM;

/**
 * @author REDDEV
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

/**
 * Connexion Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		String mdpcookie = "";
		String pseudocookie = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Pseudo")) {
					pseudocookie = cookie.getValue();

				}
				if (cookie.getName().equals("MDP")) {
					mdpcookie = cookie.getValue();
				}

			}
		}
		try {
			Utilisateur user = new UserManager().ConnexionCookies(pseudocookie, mdpcookie);
			if (user != null) {
				// Création d'une session
				HttpSession session = request.getSession();
				// Set dans la session l'attribut "utilisateur"
				session.setAttribute("utilisateur", user);
				// redirection sur la page Home en mode connecter
				request.setAttribute("titlePage", "Liste des enchères");
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				request.getRequestDispatcher("WEB-INF/jsp/pages/Connexion.jsp").forward(request, response);

			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Connexion d'un utilisateur
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		UserManager manager = new UserManager();
		Utilisateur utilisateur;
		RequestDispatcher rd;

		String password = request.getParameter("MDP");

		String sha3Hex = UserManager.hashPwd(password);
		String pseudo = request.getParameter("Pseudo");
		String sesouvenir = "";
		sesouvenir = request.getParameter("connection-remember");
		if (sesouvenir != null) {
			Cookie cookiePseudo = new Cookie("Pseudo", pseudo);
			cookiePseudo.setMaxAge(604800);
			response.addCookie(cookiePseudo);
			Cookie cookiePass = new Cookie("MDP", sha3Hex);
			cookiePass.setMaxAge(604800);
			response.addCookie(cookiePass);
		}

		try {
			// essai connexion
			try {
				utilisateur = manager.connexion(request.getParameter("Pseudo"), request.getParameter("MDP"));
				// OK
				if (utilisateur != null && !request.getParameter("Pseudo").equals("")
						&& !request.getParameter("MDP").equals("")) {
					// Création d'une session
					HttpSession session = request.getSession();
					// Set dans la session l'attribut "utilisateur"
					session.setAttribute("utilisateur", utilisateur);
					// redirection sur la page Home en mode connecter
					request.setAttribute("titlePage", "Liste des enchères");
					response.sendRedirect(request.getContextPath() + "/");
				} else {
					// PAS OK si erreurs identifiants
					exceptions.ajouterErreur(MSG_BLL.ID_MDP_KO);
					throw exceptions;
				}
			} catch (DALException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			// set dans la requete la liste d'erreurs a la jsp
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			request.setAttribute("titlePage", "Connexion");
			// redirection sur la page de connexion
			rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Connexion.jsp");
			rd.forward(request, response);
		}
	}
}

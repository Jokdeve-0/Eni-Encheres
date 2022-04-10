/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.IHM;

/**
 * @author REDDEV
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

/**
 * Connexion
 * Servlet implementation class Connexion
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
		
		request.setAttribute("titlePage", "Connexion");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * Connexion d'un utilisateur
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		UserManager manager = new UserManager();
		Utilisateur utilisateur;
		RequestDispatcher rd;

		try {
			// essai connexion
			utilisateur = manager.connexion(request.getParameter("Pseudo"), request.getParameter("MDP"));
			// OK
<<<<<<< HEAD
			if (utilisateur != null && !request.getParameter("Pseudo").equals("") && !request.getParameter("MDP").equals("")) {
=======
			if (utilisateur != null) {
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				// Création d'une session
				HttpSession session = request.getSession();
				// Set dans la session l'attribut "utilisateur"
				session.setAttribute("utilisateur", utilisateur);
				// redirection sur la page Home en mode connecter
				request.setAttribute("titlePage", "Liste des enchères");
				response.sendRedirect(request.getContextPath()+"/");
			} else {
				// PAS OK si erreurs identifiants
				exceptions.ajouterErreur(MSG_BLL.ID_MDP_KO);
				throw exceptions;
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

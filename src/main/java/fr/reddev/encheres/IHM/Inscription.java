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
 * Inscription
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
<<<<<<< HEAD
=======
		
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
		request.setAttribute("titlePage", "Inscription");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * Inscription d'un profil
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		UserManager manager = new UserManager();
		BusinessException exceptions = new BusinessException();
		Utilisateur utilisateur = null;

		// Compare les passwords
		if (request.getParameter("mdp").equals(request.getParameter("mdpConfirme"))) {
			// Creation d'un nouveau profil
			utilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"),
					request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
					request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"),
					UserManager.hashPwd(request.getParameter("mdp")), 250, false);
			// Validation du formulaire
			exceptions = manager.validateUtilisateur(utilisateur);
			// Si le formulaire est valide
			if (!exceptions.hasErreurs()) {
				// Enregistrement du nouveau profil dans la BDD
				exceptions = manager.createUtilisateur(utilisateur, exceptions);
			}
		} else
			// Si les passwords ne correspondent pas.
			exceptions.ajouterErreur(MSG_BLL.ERROR_MDP_NO_EQUALS);
		try {
			// si tout est OK
			if (!exceptions.hasErreurs()) {
				// On connecte le nouvel utilisateur
				Utilisateur nouvelUtilisateur = manager.connexion(utilisateur.getPseudo(), request.getParameter("mdp"));
				// Création d'une session
				HttpSession session = request.getSession();
				// Set dans la session l'attribut "utilisateur"
				session.setAttribute("utilisateur", nouvelUtilisateur);
				request.setAttribute("titlePage", "Accueil");
				// redirection sur la page Home en mode connecter
				response.sendRedirect("http://localhost:8080/ENI-Encheres");
			} else {
				// Erreur si l'enregistrement n'a pas été éffectuer
				exceptions.ajouterErreur(MSG_BLL.ERROR_CREATE_UTILISATEUR);
				throw exceptions;
			}
		} catch (BusinessException e) {
			// set dans la requete la liste d'erreurs a la jsp
<<<<<<< HEAD
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
=======
			request.setAttribute("listeErreur", exceptions.getListeCodesErreur());
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			// renvoi sur la page d'inscription
			doGet(request, response);
		}

	}
	
}

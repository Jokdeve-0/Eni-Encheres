package fr.reddev.encheres.IHM;

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
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("titlePage", "Connexion");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		UserManager manager = new UserManager();
		Utilisateur utilisateur;
		RequestDispatcher rd;
		
		String pseudo = request.getParameter("Pseudo" );
		System.out.println(pseudo);
		String  mdp = request.getParameter("MDP" );
		System.out.println(mdp);
		

		
			utilisateur = manager.connexion(pseudo,mdp);
			if(utilisateur != null) {
				HttpSession session =  request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				System.out.println(utilisateur.toString());
				
				request.setAttribute("titlePage", "Liste des enchères");
				rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		
			}else {
				exceptions.ajouterErreur(MSG_BLL.ID_MDP_KO);
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
				request.setAttribute("titlePage", "Connexion");
				rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Connexion.jsp");
			}
			rd.forward(request, response);
	}

}

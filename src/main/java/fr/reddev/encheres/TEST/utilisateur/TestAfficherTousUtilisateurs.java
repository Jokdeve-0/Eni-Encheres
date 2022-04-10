package fr.reddev.encheres.TEST.utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Utilisateur;

/**
 * Servlet implementation class TestAfficherTousUtilisateurs
 */
@WebServlet("/test/TestAfficherTousUtilisateurs")
public class TestAfficherTousUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set l'encodage de la request en UTF-8
		request.setCharacterEncoding("UTF-8");
		List<Utilisateur> catalogueUtilisateurs = new UserManager().AfficherTousUtilisateurs();
		System.out.println(catalogueUtilisateurs);
		// Redirection sur la jsp Home
		request.setAttribute("titlePage", "Accueil");
		RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/jsp/pages/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

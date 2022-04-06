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

/**
 * Accueil
 * Servlet implementation class Home
 */
@WebServlet({ "/Home", "/" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * En arrivant sur le site (Affichage différent ( *dans la Home.jsp en fonction que si l'utilisateur est connecté ou non ) 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("titlePage", "Accueil");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * Aucun post vers cette page
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

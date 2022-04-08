package fr.reddev.encheres.IHM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BusinessException;


/**
 * Servlet implementation class VenteArticle
 */
@WebServlet("/VendreUnArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("titlePage", "VenteArticle");
	     RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/VendreUnArticle.jsp");
         rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		request.setCharacterEncoding("UTF-8");
		Articles_vendusManager artManager = new Articles_vendusManager();
		System.out.println(request.getSession().getAttribute("utilisateur"));
		HttpSession session = request.getSession();
		
		Utilisateur curr_user = (Utilisateur) session.getAttribute("utilisateur");
			
        // New Articles
        Articles_vendus av = new Articles_vendus(
                request.getParameter("name"),
                request.getParameter("story"),               
                java.sql.Date.valueOf( 
                		LocalDate.parse(request.getParameter("date_debut"),
                				DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                java.sql.Date.valueOf(
                		LocalDate.parse(request.getParameter("date_fin"),
                				DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                !request.getParameter("howmuch").equals("") ? Integer.valueOf(request.getParameter("howmuch")) : 0,
                null,
                Integer.valueOf(curr_user.getno_utilisateur()),
                1,
                "CR",
                curr_user.getPseudo()
        );
          try {
				artManager.creerArticle(av);
			} catch (BusinessException e) {
				e.printStackTrace();
				exceptions.ajouterErreur(0);
				// TODO penser a creer le message d'erreur creation utilisateur echoué.
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
				e.printStackTrace();
			} 
         //TODO la redirection est encore a décider en attendant on renvoie sur Home
          response.sendRedirect(request.getContextPath()+"/Home");
	}
}

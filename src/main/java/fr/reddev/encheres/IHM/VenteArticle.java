package fr.reddev.encheres.IHM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.BLL.CategorieManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;


/**
 * Servlet implementation class VenteArticle
 */
@WebServlet("/VendreUnArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		try {
			// Récupération des catégories dans la BDD
			List<Categorie> categorie = new CategorieManager().getCategories();
			// Ajoute la liste des catégories dans la requete
			request.setAttribute("categorie", categorie);
		} catch (BLLException e) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_CATEGORIES);
			e.printStackTrace();
		}
		if (exceptions.hasErreurs()) {
			// set dans la requete la liste d'erreurs pour la jsp
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
		}
		
		request.setAttribute("titlePage", "Article");
	     RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/VendreUnArticle.jsp");
        rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		System.out.println("doGet");
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
				exceptions = artManager.creerArticle(av);
			} catch (BusinessException e) {
				e.printStackTrace();
				// TODO penser a creer le message d'erreur creation utilisateur echoué.
				exceptions.ajouterErreur(0);
				e.printStackTrace();
			} 
          if (exceptions.hasErreurs()) {
  			// set dans la requete la liste d'erreurs pour la jsp
  			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
  		}
          
         //TODO la redirection est encore a décider en attendant on renvoie sur Home
          response.sendRedirect(request.getContextPath()+"/Home");
	}
}

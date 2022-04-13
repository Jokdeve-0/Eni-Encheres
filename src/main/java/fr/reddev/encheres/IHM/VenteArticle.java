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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.BLL.CategorieManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

/**
 * Page article - Création d'un article Servlet implementation class
 * VenteArticle
 */
@WebServlet("/VendreUnArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SET L'ENCODAGE DE LA REQUEST EN UTF-8
		request.setCharacterEncoding("UTF-8");

		BusinessException exceptions = new BusinessException();

		// Récupération des catégories dans la BDD
			List<Categorie> categorie;

				try {
					categorie = new CategorieManager().getCategories();
					request.setAttribute("categorie", categorie);
				} catch (DALException | SQLException e) {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SET L'ENCODAGE DE LA REQUEST EN UTF-8
		request.setCharacterEncoding("UTF-8");

		Articles_vendusManager articleMG = new Articles_vendusManager();
		// Utilisateur courant dans la session
		Utilisateur curr_user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// Nouvelle article à créer
		Articles_vendus newArticle = new Articles_vendus(request.getParameter("name"), request.getParameter("story"),
				java.sql.Date.valueOf(
						LocalDate.parse(request.getParameter("date_debut"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
				java.sql.Date.valueOf(
						LocalDate.parse(request.getParameter("date_fin"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
				!request.getParameter("howmuch").equals("") ? Integer.valueOf(request.getParameter("howmuch")) : 0,
				null, Integer.valueOf(curr_user.getno_utilisateur()), 1, "CR", curr_user.getPseudo());
		// Id de l'article pour le renvoyer dans l'url pour l'affichage
		Integer no_article = null;
		// Validation de l'article
		BusinessException exceptions = articleMG.validateArticleVendu(newArticle);
		try {
			if (!exceptions.hasErreurs()) {
				// Insère l'article et récupère son id
				try {
					no_article = articleMG.creerArticle(newArticle);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO creer message de confirmation et set dans la
				// requete#####################################
			} else {
				throw exceptions;
			}
		} catch (BLLException e) {
			System.err.println(e);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
		}
		request.setAttribute("titlePage", "Article");
		response.sendRedirect("http://localhost:8080/ENI-Encheres/Article?id=" + no_article);
	}

}

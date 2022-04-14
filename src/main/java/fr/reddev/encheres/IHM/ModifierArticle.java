package fr.reddev.encheres.IHM;

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
 * Servlet implementation class ModifierArticle
 */
@WebServlet("/ModifierArticle")
public class ModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Articles_vendus article = null;

		if (!request.getParameter("idArticle").equals("") && request.getParameter("idArticle") != null) {
			// recuperation de l'article
			try {
				article = new Articles_vendusManager()
						.getArticleById(Integer.parseInt(request.getParameter("idArticle")));
				request.setAttribute("article", article);
			} catch (DALException | SQLException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Récupération des catégories dans la BDD
			List<Categorie> categorie;
			try {
				categorie = new CategorieManager().getCategories();
				request.setAttribute("categorie", categorie);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/Error500");
			}
		}
		request.setAttribute("titlePage", "Modifier Article");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/ModifierArticle.jsp");
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
		Articles_vendus newArticle = null;

		Articles_vendusManager articleMG = new Articles_vendusManager();
		// Utilisateur courant dans la session
		Utilisateur curr_user = (Utilisateur) request.getSession().getAttribute("utilisateur");

		if (!request.getParameter("idArticle").equals("") && request.getParameter("idArticle") != null) {
			// Nouvelle article à créer
			newArticle = new Articles_vendus(Integer.parseInt(request.getParameter("idArticle")),
					request.getParameter("name").toLowerCase(), request.getParameter("story").toLowerCase(),
					java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_debut"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
					java.sql.Date.valueOf(LocalDate.parse(request.getParameter("date_fin"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
					!request.getParameter("howmuch").equals("") ? Integer.valueOf(request.getParameter("howmuch")) : 0,
					null, Integer.valueOf(curr_user.getno_utilisateur()), 1, "CR", curr_user.getPseudo());

			// Validation de l'article
			BusinessException exceptions = articleMG.validateArticleVendu(newArticle);
			try {
				if (!exceptions.hasErreurs()) {
					// Insère l'article et récupère son id

					try {
						articleMG.updateArticle(newArticle);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// TODO creer message de confirmation et set dans la
					// requete#####################################
				} else {
					throw exceptions;
				}
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
		}
		request.getSession().setAttribute("idArticle", newArticle.getNo_article());
		request.setAttribute("titlePage", "Article");
		response.sendRedirect(request.getContextPath()+"/Article?idArticle="+ newArticle.getNo_article());
//		request.getRequestDispatcher("WEB-INF/jsp/pages/Article.jsp").forward(request, response);

	}

}

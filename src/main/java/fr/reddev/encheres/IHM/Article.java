package fr.reddev.encheres.IHM;

import java.io.IOException;
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
import fr.reddev.encheres.BLL.EncheresManager;
import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;

/**
 * Servlet implementation class Article
 */
@WebServlet("/Article")
public class Article extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		UserManager userMG = new UserManager();
		EncheresManager enchereMG =  new EncheresManager();
		List<Categorie> categorie = null;
		Articles_vendus  article = null;
		Utilisateur vendeur = null;
		Utilisateur curr_user = null;
		Utilisateur bestEncherisseur = null;
		Encheres curr_enchere = null;
		HttpSession session = request.getSession();
		
		curr_user = (Utilisateur) session.getAttribute("utilisateur");
		if(curr_user != null) {			
			curr_user = userMG.GetUtilisateur(curr_user.getno_utilisateur());
			request.setAttribute("utilisateur", curr_user);
		}
		
		if(!request.getParameter("id").equals("") && request.getParameter("id") != null) {
			try {
				// recuperation de l'article
				article = new Articles_vendusManager().getArticleById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("article", article);
			} catch (NumberFormatException e) {
				// TODO Message erreur
				e.printStackTrace();
			} catch (BLLException e) {
				// TODO Message erreur
				e.printStackTrace();
			}
			try {
				// Récupération des catégories dans la BDD
				categorie = new CategorieManager().getCategories();
				// Ajoute la liste des catégories dans la requete
				request.setAttribute("categorie", categorie);
			} catch (BLLException e) {
				//TODO erreur
//				exceptions.ajouterErreur(0); 
				e.printStackTrace();
			}
			// Récupération du vendeur dans la BDD
			vendeur = userMG.GetUtilisateur(article.getNo_utilisateur());
			// Ajoute le vendeur dans la requete
			request.setAttribute("vendeur", vendeur);
			
			//Récupération de l'enchere si elle existe 
			try {
				curr_enchere = enchereMG.rechercheEnchere(article.getNo_article());
				request.setAttribute("enchere", curr_enchere);
			} catch (BLLException e) {
				// TODO Erreur
				e.printStackTrace();
			}
			if(curr_enchere != null) {
				bestEncherisseur = userMG.GetUtilisateur(curr_enchere.getNo_utilisateur());
				request.setAttribute("bestEnchere", bestEncherisseur);
//			boolean etatEnchere = article.getDate_debut_encheres().before(Date.valueOf(LocalDate.now().plusDays(1))) && article.getDate_fin_encheres().after(Date.valueOf(LocalDate.now()));
				boolean etatEnchere = enchereMG.etatEnCours(article);
				request.setAttribute("etatEnchere", etatEnchere);
			}
			
			
			
		}else {
			//TODO new error no id article
			System.out.println("error####################");
		}

		request.setAttribute("titlePage", "Article");
	     RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Article.jsp");
         rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

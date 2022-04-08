/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.IHM;

/**
 * @author REDDEV
 */
import java.io.IOException;
import java.util.ArrayList;
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
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;

/**
 * Accueil
 * Servlet implementation class Home
 */
@WebServlet({ "/Home", "/" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		// Set l'encodage de la request en UTF-8
		request.setCharacterEncoding("UTF-8");
		try {
			// Récupération du catalogue d'articles dans la BDD
			List<Articles_vendus> catalogue = new Articles_vendusManager().getCatalogue();
			// Ajoute le catalogue d'articles dans la requete
			request.setAttribute("catalogue", catalogue);
		} catch (BLLException e) {
			exceptions.ajouterErreur(0);
// TODO ajouter code erreurs
			e.printStackTrace();
		}
		try {
			// Récupération des catégories dans la BDD
			List<Categorie> categorie = new CategorieManager().getCategories();
			// Ajoute la liste des catégories dans la requete
			request.setAttribute("categorie", categorie);
		} catch (BLLException e) {
			exceptions.ajouterErreur(0);
// TODO ajouter code erreurs
			e.printStackTrace();
		}
		if(exceptions.hasErreurs()) {
			// set dans la requete la liste d'erreurs pour la jsp
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
		}
		// Redirection sur la jsp Home
		request.setAttribute("titlePage", "Accueil");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessException exceptions = new BusinessException();
		List<Categorie> categorie =null;
		List<Articles_vendus> allCatalogueByName = null;
		List<Articles_vendus> catalogueByName = null;
		List<Articles_vendus> catalogueByNameAndCategorie = new ArrayList<>();
		Categorie CategorieRechercher = null;
		RequestDispatcher rqtDispatcher;
		//---------------------------------------------------//
		// SET L'ENCODAGE DE LA REQUEST EN UTF-8
		request.setCharacterEncoding("UTF-8");
		//---------------------------------------------------//
		// TOUTES LES CATÉGORIES DE LA BDD
		try {
			categorie = new CategorieManager().getCategories();
			// Ajoute a la request les catégories pour l'affichage du select (HTML)
			request.setAttribute("categorie", categorie);
		} catch (BLLException e) {
			exceptions.ajouterErreur(0);
			// TODO ajouter code erreurs
			e.printStackTrace();
		}
		//---------------------------------------------------//
		//SEARCH METHOD 
		// Si on reçoit la catégorie "Toutes" et : *1 ou *2
		if(request.getParameter("categorie").equals("Toutes")) {
			// *1 : Si l'utilisateur n'a pas entré de valeur dans la recherche
			if(request.getParameter("search").strip().length() == 0) {
				// Redirection vers la method doGet qui retourne le catalogue entier
				doGet(request, response);
			}
			// *2 : Si l'utilisateur a entré une valeur dans la recherche
			else {
				try {
					// Récupération de tous les articles qui contient la valeur de la recherche
					allCatalogueByName = new Articles_vendusManager().getCatalogueByName(request.getParameter("search"));
					// Trie et récupère les articles de la catégories demandée
					catalogueByName = new ArrayList<>(); 
					//	 Boucle sur le catalogue de tous les articles qui contient la valeur de la recherche
					for(Articles_vendus article : allCatalogueByName) { 
						//	Boucle sur la liste des catégories 
						for(Categorie cat : categorie) {
							// Si article est de la catégorie demandée, on l'ajoute dans le catalogue By Name
							if(article.getNo_categorie()== cat.getNo_categorie()) {
								catalogueByName.add(article);
							}
						}
					}
				}  catch (BLLException e) {
					exceptions.ajouterErreur(0);
					// TODO ajouter code erreurs
					e.printStackTrace();
				}
				
			}
			if(exceptions.hasErreurs()) {
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
			// Ajoute a la request le catalogue des résultats de la recherche
			request.setAttribute("catalogue", catalogueByName);
			// Redirection vers la page d'affichage des articles (HOME)
			request.setAttribute("titlePage", "Accueil");
			rqtDispatcher = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
			rqtDispatcher.forward(request, response);
		}
		// Si on ne reçoit pas la catégorie "Toutes"
		// c'est à dire que l'on va reçevoir n'importe quelles catégories existantes et : *1 et *2 , *3 ou *4
		else {
			try {
				// *1 Récupère la catégorie recherchée
				CategorieRechercher = new CategorieManager().getCatalogueLibelle(request.getParameter("categorie")).get(0);
			}  catch (BLLException e) {
				exceptions.ajouterErreur(0);
				// TODO ajouter code erreurs
				e.printStackTrace();
			}
			// *2 Récupère le catalogue avec tous les articles de la catégories demandée
			try {
				catalogueByName = new Articles_vendusManager().getCatalogueCategorie(CategorieRechercher.getNo_categorie());
			}  catch (BLLException e) {
				exceptions.ajouterErreur(0);
				// TODO ajouter code erreurs
				e.printStackTrace();
			}
			// *3 : Si l'utilisateur n'a pas entré de valeur dans la recherche
			if(request.getParameter("search").strip().length() == 0) {
				// Ajoute a la request le catalogue avec tous les articles de la catégories demandée
				request.setAttribute("catalogue", catalogueByName);
			}
			// *4 : Si l'utilisateur a entré une valeur dans la recherche
			else {
				try {
					// Récupération des articles contient la valeur de la recherche
					catalogueByName = new Articles_vendusManager().getCatalogueByName( request.getParameter("search"));
				}  catch (BLLException e) {
					exceptions.ajouterErreur(0);
					// TODO ajouter code erreurs
					e.printStackTrace();
				}
				//	 Boucle sur le catalogue de tous les articles qui contient la valeur de la recherche
				for(Articles_vendus article : catalogueByName) { 
						// Si article est de la catégorie demandée, on l'ajoute dans le catalogue By Name
						if( String.valueOf(article.getNo_categorie()).equals( String.valueOf(CategorieRechercher.getNo_categorie()) ) ) {
							catalogueByNameAndCategorie.add(article);
						}
				}
				// Ajoute a la request le catalogue avec tous les articles de la catégories demandée
				request.setAttribute("catalogue", catalogueByNameAndCategorie);
			}
			if(exceptions.hasErreurs()) {
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
			// Affiche la page Home
			request.setAttribute("titlePage", "Accueil");
			rqtDispatcher = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
			rqtDispatcher.forward(request, response);
		}
	}
}
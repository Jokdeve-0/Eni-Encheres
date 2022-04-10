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
<<<<<<< HEAD
import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.BO.Utilisateur;
=======
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Categorie;
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

/**
<<<<<<< HEAD
 * Accueil Servlet implementation class Home
=======
 * Accueil
 * Servlet implementation class Home
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
 */
@WebServlet({ "/Home", "/" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
<<<<<<< HEAD
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		Articles_vendusManager artMG = new Articles_vendusManager();
		List<Utilisateur> users = new UserManager().AfficherTousUtilisateurs();
		request.setAttribute("users",users);
		// Set l'encodage de la request en UTF-8
		request.setCharacterEncoding("UTF-8");
		try {
			//trie des articles ou l'etat de enchere est ouverte
			List<Articles_vendus> catalogueEnchere = artMG.getEnchereOuverte(artMG.getCatalogue());
			// Ajoute le catalogue d'articles encours dans la requete
			request.setAttribute("catalogue", catalogueEnchere);
=======
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();
		// Set l'encodage de la request en UTF-8
		request.setCharacterEncoding("UTF-8");
		try {
			// Récupération du catalogue d'articles dans la BDD
			List<Articles_vendus> catalogue = new Articles_vendusManager().getCatalogue();
			// Ajoute le catalogue d'articles dans la requete
			request.setAttribute("catalogue", catalogue);
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
		} catch (BLLException e) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
			e.printStackTrace();
		}
		try {
			// Récupération des catégories dans la BDD
			List<Categorie> categorie = new CategorieManager().getCategories();
			// Ajoute la liste des catégories dans la requete
			request.setAttribute("categorie", categorie);
		} catch (BLLException e) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_CATEGORIES);
			e.printStackTrace();
		}
<<<<<<< HEAD
		
		if (exceptions.hasErreurs()) {
=======
		if(exceptions.hasErreurs()) {
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
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
<<<<<<< HEAD
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BusinessException exceptions = new BusinessException();
		List<Categorie> categorie = null;
=======
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessException exceptions = new BusinessException();
		List<Categorie> categorie =null;
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
		List<Articles_vendus> allCatalogueByName = null;
		List<Articles_vendus> catalogueByName = null;
		List<Articles_vendus> catalogueByNameAndCategorie = new ArrayList<>();
		Categorie CategorieRechercher = null;
		RequestDispatcher rqtDispatcher;
<<<<<<< HEAD
		// ---------------------------------------------------//
		// SET L'ENCODAGE DE LA REQUEST EN UTF-8
		request.setCharacterEncoding("UTF-8");
		// ---------------------------------------------------//
=======
		//---------------------------------------------------//
		// SET L'ENCODAGE DE LA REQUEST EN UTF-8
		request.setCharacterEncoding("UTF-8");
		//---------------------------------------------------//
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
		// TOUTES LES CATÉGORIES DE LA BDD
		try {
			categorie = new CategorieManager().getCategories();
			// Ajoute a la request les catégories pour l'affichage du select (HTML)
			request.setAttribute("categorie", categorie);
		} catch (BLLException e) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_CATEGORIES);
			e.printStackTrace();
		}
<<<<<<< HEAD
		// ---------------------------------------------------//
		// SEARCH METHOD
		// Si on reçoit la catégorie "Toutes" et : *1 ou *2
		if (request.getParameter("categorie").equals("Toutes")) {
			// *1 : Si l'utilisateur n'a pas entré de valeur dans la recherche
			if (request.getParameter("search").strip().length() == 0) {

				try {
					// Récupération du catalogue d'articles dans la BDD
					List<Articles_vendus> catalogue = new Articles_vendusManager().getCatalogue();
					// Ajoute le catalogue d'articles dans la requete
					request.setAttribute("catalogue", catalogue);
				} catch (BLLException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
					e.printStackTrace();
				}

=======
		//---------------------------------------------------//
		//SEARCH METHOD 
		// Si on reçoit la catégorie "Toutes" et : *1 ou *2
		if(request.getParameter("categorie").equals("Toutes")) {
			// *1 : Si l'utilisateur n'a pas entré de valeur dans la recherche
			if(request.getParameter("search").strip().length() == 0) {
				// Redirection vers la method doGet qui retourne le catalogue entier
				doGet(request, response);
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
			}
			// *2 : Si l'utilisateur a entré une valeur dans la recherche
			else {
				try {
					// Récupération de tous les articles qui contient la valeur de la recherche
<<<<<<< HEAD
					allCatalogueByName = new Articles_vendusManager()
							.getCatalogueByName(request.getParameter("search"));
					// Trie et récupère les articles de la catégories demandée
					catalogueByName = new ArrayList<>();
					// Boucle sur le catalogue de tous les articles qui contient la valeur de la
					// recherche
					for (Articles_vendus article : allCatalogueByName) {
						// Boucle sur la liste des catégories
						for (Categorie cat : categorie) {
							// Si article est de la catégorie demandée, on l'ajoute dans le catalogue By
							// Name
							if (article.getNo_categorie() == cat.getNo_categorie()) {
=======
					allCatalogueByName = new Articles_vendusManager().getCatalogueByName(request.getParameter("search"));
					// Trie et récupère les articles de la catégories demandée
					catalogueByName = new ArrayList<>(); 
					//	 Boucle sur le catalogue de tous les articles qui contient la valeur de la recherche
					for(Articles_vendus article : allCatalogueByName) { 
						//	Boucle sur la liste des catégories 
						for(Categorie cat : categorie) {
							// Si article est de la catégorie demandée, on l'ajoute dans le catalogue By Name
							if(article.getNo_categorie()== cat.getNo_categorie()) {
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
								catalogueByName.add(article);
							}
						}
					}
<<<<<<< HEAD
					// Ajoute a la request le catalogue des résultats de la recherche
					request.setAttribute("catalogue", catalogueByName);
				} catch (BLLException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
					e.printStackTrace();
				}

			}
			if (exceptions.hasErreurs()) {
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}

		}
		// Si on ne reçoit pas la catégorie "Toutes"
		// c'est à dire que l'on va reçevoir n'importe quelles catégories existantes et
		// : *1 et *2 , *3 ou *4
		else {
			try {
				// *1 Récupère la catégorie recherchée
				CategorieRechercher = new CategorieManager().getCatalogueLibelle(request.getParameter("categorie"))
						.get(0);
			} catch (BLLException e) {
=======
				}  catch (BLLException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
					e.printStackTrace();
				}
				
			}
			if(exceptions.hasErreurs()) {
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
			// Ajoute a la request le catalogue des résultats de la recherche
			request.setAttribute("catalogue", catalogueByName);
		}
		// Si on ne reçoit pas la catégorie "Toutes"
		// c'est à dire que l'on va reçevoir n'importe quelles catégories existantes et : *1 et *2 , *3 ou *4
		else {
			try {
				// *1 Récupère la catégorie recherchée
				CategorieRechercher = new CategorieManager().getCatalogueLibelle(request.getParameter("categorie")).get(0);
			}  catch (BLLException e) {
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_CATEGORIES);
				e.printStackTrace();
			}
			// *2 Récupère le catalogue avec tous les articles de la catégories demandée
			try {
<<<<<<< HEAD
				catalogueByName = new Articles_vendusManager()
						.getCatalogueCategorie(CategorieRechercher.getNo_categorie());
			} catch (BLLException e) {
=======
				catalogueByName = new Articles_vendusManager().getCatalogueCategorie(CategorieRechercher.getNo_categorie());
			}  catch (BLLException e) {
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
				e.printStackTrace();
			}
			// *3 : Si l'utilisateur n'a pas entré de valeur dans la recherche
<<<<<<< HEAD
			if (request.getParameter("search").strip().length() == 0) {
				// Ajoute a la request le catalogue avec tous les articles de la catégories
				// demandée
=======
			if(request.getParameter("search").strip().length() == 0) {
				// Ajoute a la request le catalogue avec tous les articles de la catégories demandée
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				request.setAttribute("catalogue", catalogueByName);
			}
			// *4 : Si l'utilisateur a entré une valeur dans la recherche
			else {
				try {
					// Récupération des articles contient la valeur de la recherche
<<<<<<< HEAD
					catalogueByName = new Articles_vendusManager().getCatalogueByName(request.getParameter("search"));
				} catch (BLLException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
					e.printStackTrace();
				}
				// Boucle sur le catalogue de tous les articles qui contient la valeur de la
				// recherche
				for (Articles_vendus article : catalogueByName) {
					// Si article est de la catégorie demandée, on l'ajoute dans le catalogue By
					// Name
					if (String.valueOf(article.getNo_categorie())
							.equals(String.valueOf(CategorieRechercher.getNo_categorie()))) {
						catalogueByNameAndCategorie.add(article);
					}
				}
				// Ajoute a la request le catalogue avec tous les articles de la catégories
				// demandée
				request.setAttribute("catalogue", catalogueByNameAndCategorie);
			}
			if (exceptions.hasErreurs()) {
=======
					catalogueByName = new Articles_vendusManager().getCatalogueByName( request.getParameter("search"));
				}  catch (BLLException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
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
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
		}
		// Affiche la page Home
		request.setAttribute("titlePage", "Accueil");
		rqtDispatcher = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		rqtDispatcher.forward(request, response);
	}
}
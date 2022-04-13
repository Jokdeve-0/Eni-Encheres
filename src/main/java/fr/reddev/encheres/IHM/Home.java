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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.Administration;
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
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

/**
 * Accueil Servlet implementation class Home
 */
@WebServlet({ "/Home", "/" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETUP ADMINISTARATION
		BusinessException exceptions = new BusinessException();
		try {
			Administration.setUp(request, response);
		} catch (BLLException e1) {
			exceptions.ajouterErreur(0);// erreur de mise en place
			e1.printStackTrace();
		}
		// LES UTILISATEURS
		List<Utilisateur> users = null;
		try {
			users = new UserManager().AfficherTousUtilisateurs();
			request.setAttribute("users", users);
		} catch (DALException | SQLException e2) {
			exceptions.ajouterErreur(0);// erreur de mise en place
			e2.printStackTrace();
		}
		// LES CATEGORIES
		Articles_vendusManager artMG = new Articles_vendusManager();
		List<Categorie> categorie = null;

		try {
			categorie = new CategorieManager().getCategories();
		} catch (DALException | SQLException e5) {
			exceptions.ajouterErreur(0);// erreur de mise en place
			e5.printStackTrace();
		}
		request.setAttribute("categorie", categorie);

		// LES ARTICLES ENCHERES OUVERTES
		List<Articles_vendus> catalogueEncheres = null;
		try {
			catalogueEncheres = artMG.getEnchereOuverte(artMG.getCatalogue());
			request.setAttribute("catalogue", catalogueEncheres);
		} catch (Exception e4) {
			exceptions.ajouterErreur(0);// erreur de mise en place
			e4.printStackTrace();
		}
		if (exceptions.hasErreurs()) {
			request.getSession().setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			response.sendRedirect(request.getContextPath() + "/Error500");
		}
		// REDIRECTION SUR LA JSP HOME
		request.setAttribute("titlePage", "Accueil");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETUP ADMINISTARATION
		BusinessException exceptions = new BusinessException();
		try {
			Administration.setUp(request, response);
		} catch (BLLException e1) {
			exceptions.ajouterErreur(0);// erreur de mise en place
			e1.printStackTrace();
		}
		// INITIALISATION DES MANAGER
		Articles_vendusManager artMG = new Articles_vendusManager();
		CategorieManager categoriesMG = new CategorieManager();
		EncheresManager enchereMG = new EncheresManager();
		// LES CATEGORIES
		List<Categorie> categorie = null;
		try {
			categorie = categoriesMG.getCategories();
			request.setAttribute("categorie", categorie);/// #########################################################
		} catch (DALException | SQLException e4) {
			e4.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/Error500");
		}
		// LES ARTICLES
		List<Articles_vendus> catalogue = null;
		try {
			catalogue = artMG.getCatalogue();
			request.setAttribute("catalogue", catalogue);
		} catch (Exception e4) {
			e4.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/Error500");
		}
		// LES ENCHERES
		List<Encheres> catalogueEnchere = null;
		try {
			catalogueEnchere = enchereMG.getAllEncheres();
			request.setAttribute("catalogue", catalogueEnchere);
		} catch (DALException | SQLException e2) {
			e2.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/Error500");
		}
		// USER CURRENT
		Utilisateur userCurrent = null;
		userCurrent = (Utilisateur) request.getSession().getAttribute("utilisateur");

		// (affichage)PARAMETRE DES CHECKBOXS
		String[] names = request.getParameterValues("e-achats");
		List<String> list = null;
		if (names != null) {
			list = Arrays.asList(names);
			for (String val : list) {
				System.out.println(val);
			}
		}
		// 6 fonctions
		List<Articles_vendus> aRetourner = new ArrayList<>();
		if (userCurrent != null && list != null) {
			try {
				aRetourner = artMG.getUserAchats(list, catalogue, userCurrent, catalogueEnchere);
			} catch (BLLException | DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		

		List<Articles_vendus> catalogueARenvoyer = new ArrayList<>();
		// Si on reçoit la catégorie "Toutes" et : *1 ou *2
		if (request.getParameter("categorie").equals("Toutes")) {
			// *1 : Si l'utilisateur n'a pas entré de valeur dans la recherche
			if (request.getParameter("search").strip().length() == 0) {
				// Récupération du catalogue d'articles dans la BDD
				if (aRetourner.size() != 0) {
					catalogueARenvoyer = aRetourner;
				} else {
					try {
						for(Articles_vendus article : artMG.getCatalogue()) {			
							if(!article.getEtat_vente().equals("TR") && !article.getEtat_vente().equals("RE")) {
								catalogueARenvoyer.add(article);
							}
						}
					} catch (BLLException | DALException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// *2 : Si l'utilisateur a entré une valeur dans la recherche
			} else {
				// Récupération de tous les articles qui contient la valeur de la recherche
				try {
					if (aRetourner.size() != 0) {
						for (Articles_vendus article : aRetourner) {
							if (article.toString().contains(request.getParameter("search").trim())) {
								catalogueARenvoyer.add(article);
							}
						}
					} else {
						catalogueARenvoyer = artMG.getCatalogueByName(request.getParameter("search"));
					}

				} catch (BLLException | DALException e) {
					exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
					e.printStackTrace();
				}
			}
			if (exceptions.hasErreurs()) {
				// set dans la requete la liste d'erreurs pour la jsp
				request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			}
			// Si on ne reçoit pas la catégorie "Toutes"
			// c'est à dire que l'on va reçevoir n'importe quelles catégories existantes et
			// : *1 et *2 , *3 ou *4
		} else {
			Categorie CategorieRechercher = null;
			try {
				CategorieRechercher = new CategorieManager().getCatalogueLibelle(request.getParameter("categorie"))
						.get(0);
				// *1 Récupère la catégorie recherchée
				if (aRetourner.size() != 0) {
					for (Articles_vendus article : aRetourner) {
						if (article.getNo_categorie() == CategorieRechercher.getNo_categorie()) {
							catalogueARenvoyer.add(article);
						}
					}
				} else {
					// *2 Récupère le catalogue avec tous les articles de la catégories demandée
					for (Articles_vendus article : artMG.getCatalogueCategorie(CategorieRechercher.getNo_categorie())) {
						catalogueARenvoyer.add(article);
					}
				}

				// si param
				if (aRetourner.size() != 0) {
					for (Articles_vendus article : aRetourner) {
						if (article.toString().contains(request.getParameter("search").trim())) {
							catalogueARenvoyer.add(article);
						}
					}
				} else {
					for (Articles_vendus article : artMG.getCatalogueCategorie(CategorieRechercher.getNo_categorie())) {
						if (article.toString().contains(request.getParameter("search").trim())) {
							catalogueARenvoyer.add(article);
						}
					}
				}
			} catch (DALException | BLLException e) {
				exceptions.ajouterErreur(MSG_BLL.ERROR_ZERO_ENCHERES);
				e.printStackTrace();
			}
		}
		if (exceptions.hasErreurs()) {
			// set dans la requete la liste d'erreurs pour la jsp
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
		}
		request.setAttribute("catalogue", catalogueARenvoyer);
		// Affiche la page Home
		RequestDispatcher rqtDispatcher;
		request.setAttribute("titlePage", "Accueil");
		rqtDispatcher = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
		rqtDispatcher.forward(request, response);
	}
}
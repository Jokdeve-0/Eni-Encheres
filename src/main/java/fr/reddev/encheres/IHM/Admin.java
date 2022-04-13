package fr.reddev.encheres.IHM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.CategorieManager;
import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exceptions = new BusinessException();

				// Récupérer les utilisateurs

					List<Utilisateur> user;
					try {
						user = new UserManager().AfficherTousUtilisateurs();
						request.setAttribute("listUser", user);
					} catch (DALException | SQLException e) {
						exceptions.ajouterErreur(0);// erreur de mise en place
						e.printStackTrace();
					}

				// Récupérer les catégories
					List<Categorie> categorie;
					try {
						categorie = new CategorieManager().getCategories();
						request.setAttribute("categorie", categorie);
					} catch (DALException | SQLException e1) {
						exceptions.ajouterErreur(0);// erreur de mise en place
						e1.printStackTrace();
					}

		request.setAttribute("titlePage", "Administrateur");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Administrateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

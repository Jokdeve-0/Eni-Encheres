package fr.reddev.encheres.IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.CategorieManager;
import fr.reddev.encheres.Exception.DALException;

/**
 * Servlet implementation class AdminAjoutCategorie
 */
@WebServlet("/AdminAjoutCategorie")
public class AdminAjoutCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titlePage", "Administrateur");
		response.sendRedirect(request.getContextPath()+"/Admin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager manager = new CategorieManager();
		String cat = (String) request.getParameter("categorie");
			  try {
				manager.ajouterCategorie(cat);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		request.setAttribute("titlePage", "Administrateur");
		response.sendRedirect(request.getContextPath()+"/Admin");
	}

}

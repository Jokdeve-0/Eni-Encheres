package fr.reddev.encheres.IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.CategorieManager;

/**
 * Servlet implementation class AdminSuppressionCategorie
 */
@WebServlet("/AdminSuppressionCategorie")
public class AdminSuppressionCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager manager = new CategorieManager();
		String id = (String) request.getParameter("id");
		System.out.println(id);
		try {
			manager.deleteCategorie(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("titlePage", "Administrateur");
		response.sendRedirect(request.getContextPath()+"/Admin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

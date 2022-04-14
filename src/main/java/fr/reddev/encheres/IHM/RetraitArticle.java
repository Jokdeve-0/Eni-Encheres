package fr.reddev.encheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.Administration;
import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.Exception.DALException;

/**
 * Servlet implementation class RetraitArticle
 */
@WebServlet("/RetraitArticle")
public class RetraitArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Articles_vendusManager artMG = new Articles_vendusManager();
		// SETUP ADMINISTARATION
			try {
				Administration.setUp(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/Error500");
			}
			Integer no_article = null;
		if (!request.getParameter("idArticle").equals("") && request.getParameter("idArticle") != null) {
			try {
				no_article = Integer.parseInt(request.getParameter("idArticle"));
				artMG.retraitArticle(no_article);
			} catch (NumberFormatException | DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("idArticle", no_article);
		request.setAttribute("titlePage", "Article");
		response.sendRedirect(request.getContextPath()+"/Article?idArticle="+no_article);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

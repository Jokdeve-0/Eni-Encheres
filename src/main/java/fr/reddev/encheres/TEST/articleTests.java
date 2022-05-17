/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.TEST;

/**
 * @author JOKDEVE-LOOPER
 *
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.ArticleManager;
import fr.reddev.encheres.BO.Article;

/**
 * Servlet implementation class InsertArticle
 */
@WebServlet("/test/article")
public class articleTests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	ArticleManager managerA = ArticleManager.getInstance();
	Article article = Mocks.article;
	Article articleModify = Mocks.articleModify;
	Article articleSelected;
	List<Article> ArticleCatalog;
	int IdArticle = 0;
	// INSERT
	try {
	    IdArticle = managerA.registerANewArticle(article);
	    request.setAttribute("testInsert", true);
	} catch (Exception e) {
	    request.setAttribute("testInsert", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// UPDATE
	try {
	    articleModify.setId_article(IdArticle);
	    managerA.modifyArticle(articleModify);
	    request.setAttribute("testUpdate", true);
	} catch (Exception e) {
	    request.setAttribute("testUpdate", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// SELECTBYID
	try {
	    articleSelected = managerA.selectArticle(IdArticle);
	    request.setAttribute("testSelectById", true);
	    request.setAttribute("resultSelectId", articleSelected);
	} catch (Exception e) {
	    request.setAttribute("testSelectById", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// SELECTALL
	try {
	    ArticleCatalog = managerA.selectArticleCatalog();
	    request.setAttribute("testSelectAll", true);
	    request.setAttribute("resultSelectAll", ArticleCatalog);
	} catch (Exception e) {
	    request.setAttribute("testSelectAll", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// DELETE
	try {
	    managerA.removeArticleFromCatalog(IdArticle);
	    request.setAttribute("testDelete", true);
	} catch (Exception e) {
	    request.setAttribute("testDelete", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// REDIRECTION SUR LA JSP HOME
	request.setAttribute("titlePage", "Accueil");
	request.getRequestDispatcher("../WEB-INF/jsp/tests/userTest.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}

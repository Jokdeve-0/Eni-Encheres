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

import fr.reddev.encheres.BLL.CategoryManager;
import fr.reddev.encheres.BO.Category;

/**
 * Servlet implementation class InsertUser
 */
@WebServlet("/test/category")
public class categoryTests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	CategoryManager managerC = CategoryManager.getInstance();
	Category category = Mocks.category;
	Category categoryModify = Mocks.categoryModify;
	List<Category> ListCategory;
	Category categorySelected;	

	int idCategory = 0;
	//INSERT
	try {
	    idCategory = managerC.registerANewCategory(category);
	    request.setAttribute("testInsert", true);
	} catch (Exception e) {
	    request.setAttribute("testInsert", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//UPDATE
	try {
	    categoryModify.setId_category(idCategory);
	    managerC.modifyACategory(categoryModify);
	    request.setAttribute("testUpdate", true);
	} catch (Exception e) {
	    request.setAttribute("testUpdate", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//SELECTBYID
	try {
	    categorySelected = managerC.selectACategory(idCategory);
	    request.setAttribute("testSelectById", true);
	    request.setAttribute("resultSelectId", categorySelected);
	} catch (Exception e) {
	    request.setAttribute("testSelectById", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//SELECTALL
	try {
	    ListCategory = managerC.selectListCategory();
	    request.setAttribute("testSelectAll", true);
	    request.setAttribute("resultSelectAll", ListCategory);
	} catch (Exception e) {
	    request.setAttribute("testSelectAll", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//DELETE
	try {
	    managerC.removeCategoryFromList(idCategory);
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

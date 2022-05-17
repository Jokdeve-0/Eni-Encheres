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

import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.User;

/**
 * Servlet implementation class InsertUser
 */
@WebServlet("/test/user")
public class userTests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	UserManager managerU = UserManager.getInstance();
	User user = Mocks.user;
	User userModify = Mocks.userModify;
	User userSelected;
	List<User> usersRegister;
	int idUser = 0;
	//INSERT
	try {
	    idUser = managerU.registerANewUser(user);
	    request.setAttribute("testInsert", true);
	} catch (Exception e) {
	    request.setAttribute("testInsert", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//UPDATE
	try {
	    userModify.setId_user(idUser);
	    managerU.modifyAUser(userModify);
	    request.setAttribute("testUpdate", true);
	} catch (Exception e) {
	    request.setAttribute("testUpdate", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//SELECTBYID
	try {
	    userSelected = managerU.selectAUser(idUser);
	    request.setAttribute("testSelectById", true);
	    request.setAttribute("resultSelectId", userSelected);
	} catch (Exception e) {
	    request.setAttribute("testSelectById", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//SELECTALL
	try {
	    usersRegister = managerU.selectRegister();
	    request.setAttribute("testSelectAll", true);
	    request.setAttribute("resultSelectAll", usersRegister);
	} catch (Exception e) {
	    request.setAttribute("testSelectAll", false);
	    System.out.println(e.getLocalizedMessage());
	}
	//DELETE
	try {
	    managerU.removeUserFromRegister(idUser);
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

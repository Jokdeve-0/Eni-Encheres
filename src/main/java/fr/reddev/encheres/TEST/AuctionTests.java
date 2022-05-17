package fr.reddev.encheres.TEST;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.ArticleManager;
import fr.reddev.encheres.BLL.AuctionManager;
import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Article;
import fr.reddev.encheres.BO.Auction;
import fr.reddev.encheres.BO.User;

/**
 * Servlet implementation class AuctionTests
 */
@WebServlet("test/auction")
public class AuctionTests extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	AuctionManager managerA = AuctionManager.getInstance();
	UserManager managerU = UserManager.getInstance();
	ArticleManager managerAr = ArticleManager.getInstance();
	User user = Mocks.user;
	Article article = Mocks.article;
	Auction auction = Mocks.auction;
	Auction auctionModify = Mocks.auctionModify;
	Auction auctionSelected;
	List<Auction> AuctionByArticle;
	List<Auction> AuctionCatalog;
	int idUser = 0;
	int idArticle = 0;
	// BEFOREEACH
	idUser = managerU.registerANewUser(user);
	article.setIdUser(idUser);
	idArticle = managerAr.registerANewArticle(article);
	auction.setId_user(idUser);
	auction.setId_article(idArticle);
	auctionModify.setId_user(idUser);
	auctionModify.setId_article(idArticle);
	// INSERT
	try {
	    managerA.registerANewAuction(auction);
	    request.setAttribute("testInsert", true);
	} catch (Exception e) {
	    request.setAttribute("testInsert", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// UPDATE
	try {
	    managerA.modifyAuction(auctionModify);
	    request.setAttribute("testUpdate", true);
	} catch (Exception e) {
	    request.setAttribute("testUpdate", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// SELECTBYID
	try {
	    AuctionByArticle = managerA.retrieveByArticle(idArticle);
	    request.setAttribute("testSelectById", true);
	    request.setAttribute("resultSelectId", AuctionByArticle);
	} catch (Exception e) {
	    request.setAttribute("testSelectById", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// SELECTALL
	try {
	    AuctionCatalog = managerA.selectAuctionCatalog(AuctionByArticle);
	    request.setAttribute("testSelectAll", true);
	    request.setAttribute("resultSelectAll", AuctionCatalog);
	} catch (Exception e) {
	    request.setAttribute("testSelectAll", false);
	    System.out.println(e.getLocalizedMessage());
	}
	// DELETE
	try {
	    managerA.removeAuctionFromCatalog(IdAuction);
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
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}

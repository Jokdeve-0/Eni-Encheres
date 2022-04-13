/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.TEST;

/**
 * @author REDDEV
 */
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;

/**
 * Servlet implementation class AddArticle
 */
@WebServlet("/test/AddArticle")
public class TestAjouterArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessException exceptions = new BusinessException();
		request.setCharacterEncoding("UTF-8");
		Articles_vendusManager artManager = new Articles_vendusManager();
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate trip_start = LocalDate.parse("2099-01-01",dtf);
		LocalDate trip_end = LocalDate.parse("2099-12-31",dtf);


//		RÉEL COMMANDE AVEC UN UTILISATEUR CONNECTÉ
//		{Utilisateur curr_user = (Utilisateur) request.getAttribute("utilisateur");}
		
// 	MOCK UTILISATEUR FAKE 
		Utilisateur curr_user = new Utilisateur(
				1,"Jokdeve","Looper","Arts","jokdeve@gmail.com","0123456789","test_rue","59250","test_ville","test",250,true,true
				);

        // New Articles
        Articles_vendus av = new Articles_vendus(
                "Article test",
                "Une court déscription de l'article",               
                java.sql.Date.valueOf( trip_start),
                java.sql.Date.valueOf(trip_end),
                200,
                null, curr_user.getno_utilisateur(),   1, "CR", curr_user.getPseudo()
        );
       
		try {
			artManager.creerArticle(av);
		} catch (BLLException | DALException e) {
			exceptions.ajouterErreur(0);
			// TODO penser a creer le message d'erreur creation utilisateur echoué.
			request.setAttribute("listeCodesErreur", exceptions.getListeCodesErreur());
			e.printStackTrace();
		}
			
         
		response.sendRedirect(request.getContextPath()+"/Home");
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("titlePage", "Accueil");
		 RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/pages/Home.jsp");
         rd.forward(request, response);
	
	}

}

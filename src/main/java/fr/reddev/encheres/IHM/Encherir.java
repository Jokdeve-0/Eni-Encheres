package fr.reddev.encheres.IHM;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.apt.util.Util;

import fr.reddev.encheres.BLL.Articles_vendusManager;
import fr.reddev.encheres.BLL.EncheresManager;
import fr.reddev.encheres.BLL.UserManager;
import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Encherir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessException exceptions = new BusinessException();
		EncheresManager enchereMG = new EncheresManager();
		UserManager userMG = new UserManager();
		Utilisateur encherisseur = new Utilisateur();
		Articles_vendus article = null;
		Encheres curr_enchere = null;
		if(!request.getParameter("id").equals("") && request.getParameter("id") != null) {
			try {
				// recuperation de l'article
				article = new Articles_vendusManager().getArticleById(Integer.parseInt(request.getParameter("id")));
//				request.setAttribute("article", article);
			} catch (NumberFormatException e) {
				// TODO Message erreur
				e.printStackTrace();
			} catch (BLLException e) {
				// TODO Message erreur
				e.printStackTrace();
			}
			//Récupération de l'enchere si elle existe 
			try {
			curr_enchere = enchereMG.rechercheEnchere(article.getNo_article());
//			request.setAttribute("enchere", curr_enchere);
			} catch (BLLException e) {
				// TODO Erreur
				e.printStackTrace();
			}			
			//Récupération  des infos de l'encherisseur
			encherisseur = userMG.GetUtilisateur(((Utilisateur) request.getSession().getAttribute("utilisateur")).getno_utilisateur());
//			// debiter le cerdit
//			encherisseur.setCredit(encherisseur.getCredit() - Integer.parseInt(request.getParameter("howmuch")));
//			// MAJ de l'utilisateur
//			userMG.modifierProfil(encherisseur);
//			request.setAttribute("utilisateur", encherisseur);
			try {
				//Ajoute l'enchere
				encherisseur = enchereMG.ajouterEnchere(curr_enchere, encherisseur,Integer.parseInt(request.getParameter("howmuch")),article );
				// MAJ de l'utilisateur dans la session
				request.getSession().setAttribute("utilisateur", encherisseur);
				request.setAttribute("utilisateur", encherisseur);				
				request.setAttribute("bestEnchere", encherisseur);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//debiter l'utilisateur
		}
		request.setAttribute("titlePage", "Article");
	     response.sendRedirect("http://localhost:8080/ENI-Encheres/Article?id="+article.getNo_article());
	}
}

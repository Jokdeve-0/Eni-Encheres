/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.BLL;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author REDDEV
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.BLLException;

public class Administration {

	public Administration() {
		super();
	}

	public void Authentification(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Utilisateur userCurrent = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (userCurrent == null) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
		}
	}
	
	public static void setUp(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().setAttribute("listeCodesErreur", new ArrayList<>());
		request.getSession().setAttribute("listeCodesMessage", new ArrayList<>());
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/Error500");
			}
	}

}

/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.BLL;

import java.io.IOException;

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
	
	public static void setUp(HttpServletRequest request, HttpServletResponse response) throws BLLException, IOException {
		request.setAttribute("listeCodesErreur", null);
		request.setAttribute("listeMessage", null);
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/Error500");
			}
	}

}

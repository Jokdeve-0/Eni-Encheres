/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.DAL;

/**
 * @author REDDEV
 */
import fr.reddev.encheres.DAL.JDBC.UtilisateurDaoImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDaoImpl();
    }
	
	
}

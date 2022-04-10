/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.DAL;

/**
 * @author REDDEV
 */
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.DALException;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	public Utilisateur selectByLogin(String login) throws  DALException;
	public boolean UniquePseudoMail(String pseudo, String email) throws DALException;
}

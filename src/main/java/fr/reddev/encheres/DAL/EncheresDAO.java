/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.DAL;

/**
 * @author REDDEV
 */

import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.Exception.DALException;

public interface EncheresDAO extends DAO<Encheres> {

	public Encheres recuprerMaxEnchere(int idArticle) throws DALException;

	public Encheres selectByUser(int idArticle, Integer no_utilisateur) throws DALException;
}

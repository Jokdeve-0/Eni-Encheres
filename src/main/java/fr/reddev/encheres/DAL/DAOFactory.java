/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL;

import fr.reddev.encheres.DAL.JDBC.ArticleImpl;
import fr.reddev.encheres.DAL.JDBC.AuctionImpl;
import fr.reddev.encheres.DAL.JDBC.CategoryImpl;
/**
 * @author JOKDEVE-LOOPER
 *
 */
import fr.reddev.encheres.DAL.JDBC.UserImpl;

public class DAOFactory {
    public static UserDAO getUserDAO() {
	return new UserImpl();
    }

    public static ArticleDAO getArticleDAO() {
	return new ArticleImpl();
    }

    public static CategoryDAO getCategoryDAO() {
	return new CategoryImpl();
    }

    public static AuctionDAO getAuctionDAO() {
	return new AuctionImpl();
    }
}

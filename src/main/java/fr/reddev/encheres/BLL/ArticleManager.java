/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BLL;

/**
 * @author JOKDEVE-LOOPER
 *
 */
import java.sql.SQLException;
import java.util.List;

import fr.reddev.encheres.BO.Article;
import fr.reddev.encheres.DAL.ArticleDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.DALException;

public class ArticleManager {

    /**
     * instance Manager
     */
    private static ArticleManager instance;
    private ArticleDAO articleDao = DAOFactory.getArticleDAO();

    /**
     * @return Instance instance of manager
     */
    public static ArticleManager getInstance() {
	if (instance == null) {
	    instance = new ArticleManager();
	}
	return instance;
    }

    /**
     * Inserting a article into the database
     * 
     * @param article new article
     * @throws SQLException
     * @throws DALException
     */
    public int registerANewArticle(Article article) throws DALException {
	return articleDao.insertArticle(article);
    }

    /**
     * Update a article in the database
     * 
     * @param article article modify
     * @throws SQLException
     * @throws DALException
     */
    public void modifyArticle(Article article) throws DALException {
	articleDao.update(article);
    }

    /**
     * Select a article from the database
     * 
     * @param id id article
     * @throws DALException
     */
    public Article selectArticle(Integer id) throws DALException {
	return articleDao.selectById(id);
    }

    /**
     * Select all articles in the database
     * 
     * @return A register of articles
     * @throws DALException
     */
    public List<Article> selectArticleCatalog() throws DALException {
	return articleDao.selectAll();
    }

    /**
     * delete article from database
     * 
     * @param id id article
     * @throws DALException
     */
    public void removeArticleFromCatalog(int id) throws DALException {
	articleDao.delete(id);
    }

}

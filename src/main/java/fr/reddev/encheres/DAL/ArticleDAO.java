/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL;

import java.util.List;

import fr.reddev.encheres.BO.Article;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public interface ArticleDAO extends DAO <Article> {

    /**
     * @param article
     * @return
     * @throws DALException
     */
    public Integer insertArticle(Article article) throws DALException;

    /**
     * @param article
     * @throws DALException
     */
    public void setStateArticle(Article article) throws DALException;

    /**
     * @param id
     * @return
     * @throws DALException
     */
    public List<Article> selectCategorie(int id) throws DALException;

    /**
     * @param keyword
     * @return
     * @throws DALException
     */
    public List<Article> selectArticlesByKeyword(String keyword) throws DALException;

    /**
     * @param id
     * @throws DALException
     */
    void deliveryArticle(int id) throws DALException;

}

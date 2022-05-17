/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Article;
import fr.reddev.encheres.BO.Delivery;
import fr.reddev.encheres.BO.User;
import fr.reddev.encheres.DAL.ArticleDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class ArticleImpl implements ArticleDAO {

    @Override
    public Article selectById(int id) throws DALException {
	Article article = null;
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_BY_ID_ARTICLE);
	    pstmt.setInt(1, id);
	    pstmt.executeQuery();
	    ResultSet rs = pstmt.getResultSet();
	    if (rs.next()) {
		article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
			rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
			rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"),
			rs.getString("etat_vente"), rs.getString("vendeur"));
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - selectById()\n" + e.getLocalizedMessage() + "\n");
	}
	return article;
    }

    @Override
    public List<Article> selectAll() throws DALException {
	List<Article> articlesCatalog = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_ALL_ARTICLES);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
			rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
			rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
			rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("vendeur"));
		articlesCatalog.add(article);
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - selectAll()\n" + e.getLocalizedMessage() + "\n");
	}
	return articlesCatalog;
    }

    @Override
    public Integer insertArticle(Article article) throws DALException {
	// INSERT UN ARTICLE EN 2 ÉTAPES
	Integer idArticle = null;
	try {// INSERT UN ARTICLE
	    PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_ARTICLE,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    stmt.setString(1, article.getName());
	    stmt.setString(2, article.getDescription());
	    stmt.setDate(3, article.getAuctionStartDate());
	    stmt.setDate(4, article.getAuctionEndDate());
	    stmt.setInt(5, article.getStartedPrice() != 0 ? article.getStartedPrice() : 0);
	    stmt.setInt(6, 0);
	    stmt.setInt(7, article.getIdUser());
	    stmt.setInt(8, article.getIdCategory());
	    stmt.setString(9, article.getStateSale());
	    stmt.setString(10, article.getSeller());
	    stmt.executeUpdate();
	    ResultSet rs = stmt.getGeneratedKeys();
	    while (rs.next()) {
		article.setId_article(idArticle);
		idArticle = rs.getInt(1);
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - insertArticle-article()\n" + e.getLocalizedMessage() + "\n");
	}
	try {// INSERT UN RETRAIT POUR L'ARTICLE
	    User user = DAOFactory.getUserDAO().selectById(article.getIdUser());
	    Delivery delivery = new Delivery(idArticle, user.getStreet(), user.getPostal_code(), user.getCity());
	    PreparedStatement stmt2 = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_DELIVERY);
	    stmt2.setInt(1, delivery.getId_article());
	    stmt2.setString(2, delivery.getStreet());
	    stmt2.setString(3, delivery.getPostal_code());
	    stmt2.setString(4, delivery.getCity());
	    stmt2.executeUpdate();
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - insertArticle-delivery()\n" + e.getLocalizedMessage() + "\n");
	}
	return idArticle;
    }

    @Override
    public void setStateArticle(Article article) throws DALException {
	try {
	    PreparedStatement pstmt3 = JdbcTools.getConnection()
		    .prepareStatement("UPDATE ARTICLES_VENDUS  set etat_vente =?  Where no_article = ?");
	    pstmt3.setString(1, article.getStateSale());
	    pstmt3.setInt(2, article.getIdUser());
	    pstmt3.executeUpdate();
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - setStateArticle()\n" + e.getLocalizedMessage() + "\n");
	}

    }

    @Override
    public void delete(int id) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.DELETE_ARTICLE);
	    pstmt.setInt(1, id);
	    pstmt.executeUpdate();
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - delete()\n" + e.getLocalizedMessage() + "\n");
	}

    }

    @Override
    public List<Article> selectCategorie(int id) throws DALException {
	List<Article> articlesCatalog = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_BY_CATEGORIE);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
			rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
			rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
			rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("vendeur"));
		articlesCatalog.add(article);
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - selectCategorie()\n" + e.getLocalizedMessage() + "\n");
	}
	return articlesCatalog;
    }

    @Override
    public List<Article> selectArticlesByKeyword(String keyword) throws DALException {
	List<Article> articlesCatalog = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection()
		    .prepareStatement(SQLRequest.SELECT_ALL_BY_KEYWORD + keyword + "%'");
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Article article = new Article(rs.getInt("no_article"), rs.getString("nom_article"),
			rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
			rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
			rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("vendeur"));
		articlesCatalog.add(article);
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException(
		    "ERROR : ArticleImpl - selectArticlesByKeyword()\n" + e.getLocalizedMessage() + "\n");
	}
	return articlesCatalog;
    }

    @Override
    public void insert(Article data) throws DALException {
	// INUTILE
    }

    @Override
    public void update(Article article) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.UPDATE_ARTICLE);
	    pstmt.setString(1, article.getName());
	    pstmt.setString(2, article.getDescription());
	    pstmt.setDate(3, article.getAuctionStartDate());
	    pstmt.setDate(4, article.getAuctionEndDate());
	    pstmt.setInt(5, article.getStartedPrice());
	    pstmt.setInt(6, 0);
	    pstmt.setInt(7, article.getIdUser());
	    pstmt.setInt(8, article.getIdCategory());
	    pstmt.setString(9, article.getStateSale());
	    pstmt.setString(10, article.getSeller());
	    // where
	    pstmt.setInt(11, article.getId_article());
	    int rows = pstmt.executeUpdate();
	    // check Article Delivery
	    if (rows < 1) {
		throw new DALException("WARNING : ArticleImpl - update()\nNo modification has been made in database\n");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - update()\n" + e.getLocalizedMessage() + "\n");
	}
    }

    @Override
    public void deliveryArticle(int id) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.DELIVERED_ARTICLE);
	    pstmt.setInt(1, id);
	    // execute
	    int rows = pstmt.executeUpdate();
	    // check 1 utilisateur modifier
	    if (rows < 1) {
		throw new DALException(
			"ERROR : ArticleImpl - deliveryArticle()\nNo modification has been made in database\n");
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : ArticleImpl - deliveryArticle()\n" + e.getLocalizedMessage() + "\n");
	}

    }

}

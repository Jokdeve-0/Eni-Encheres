package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.Articles_vendusDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.DAL.UtilisateurDAO;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;

public class Articles_vendusDaoImpl implements Articles_vendusDAO  {
	
	public static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (a.no_utilisateur = u.no_utilisateur)";
	public static final String SELECT_CATEGORIES = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN CATEGORIES c ON (a.no_categorie = c.no_categorie) WHERE c.no_categorie = ?";
	
	@Override
	public Articles_vendus selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Articles_vendus> selectAll() throws DALException {
		List<Articles_vendus> listArticles = new ArrayList<>();
		Articles_vendus article;
		try {
			Connection cnx =JdbcTools.getConnection(); 
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				UtilisateurDAO userDao = DAOFactory.getUtilisateurDAO();
				Utilisateur user = userDao.selectById(rs.getInt("no_utilisateur"));
					article = new Articles_vendus(
							rs.getInt("no_article"), rs.getString("nom_article"),
							rs.getString("description"), rs.getDate("date_debut_encheres"),
							rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),
							rs.getInt("no_categorie"),"CR", user.getPseudo());
					listArticles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return listArticles;
	}

	@Override
	public void update(Articles_vendus data) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void insert(Articles_vendus articles) throws DALException {
	    try {    Connection cnx = JdbcTools.getConnection();
        String INSERT = "INSERT INTO ARTICLES_VENDUS " +
                "(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie, etat_vente, vendeur"
                + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, articles.getNom_article());
        stmt.setString(2, articles.getDescription());
        stmt.setDate(3, articles.getDate_debut_encheres());
        stmt.setDate(4, articles.getDate_fin_encheres());
        stmt.setInt(5, articles.getPrix_initial() !=null ? articles.getPrix_initial() : 0);
        stmt.setInt(6, articles.getPrix_vente() !=null ? articles.getPrix_vente() : 0);
        stmt.setInt(7, articles.getNo_utilisateur());
        stmt.setInt(8, articles.getNo_categorie());
        stmt.setString(9, articles.getEtat_vente());
        stmt.setString(10, articles.getVendeur());

        
        System.out.println(stmt);
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            articles.setNo_article(rs.getInt(1));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new DALException("Erreur lors de la requêtte insert");
    }		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerArticle(Articles_vendus article) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	public List<Articles_vendus> selectCategorie(int id) throws DALException {
		List<Articles_vendus> listArticles = new ArrayList<>();
		Articles_vendus article;
		try {
			Connection cnx =JdbcTools.getConnection(); 
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIES);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
					article = new Articles_vendus(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
							rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie"),rs.getString("etat_vente") , rs.getString("vendeur"));

				listArticles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return listArticles;
	}

	public List<Articles_vendus> selectAllByName(String nom) throws DALException {
		String SELECT_ALL_BY_NAME = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (a.no_utilisateur = u.no_utilisateur) WHERE nom_article LIKE '%"+ nom+"%'";
		List<Articles_vendus> listArticles = new ArrayList<>();
		Articles_vendus article;
		try {
			Connection cnx =JdbcTools.getConnection(); 
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BY_NAME);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
					article = new Articles_vendus(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
							rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
							rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie"),rs.getString("etat_vente") , rs.getString("vendeur") );
				listArticles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return listArticles;
	}
	
}

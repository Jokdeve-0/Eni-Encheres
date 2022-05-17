/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Category;
import fr.reddev.encheres.DAL.CategoryDAO;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class CategoryImpl implements CategoryDAO {

    public void insert(Category category) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_CATEGORY);
	    pstmt.setString(1, category.getWording());
	    pstmt.executeUpdate();
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - insert()\n" + e.getLocalizedMessage() + "\n");
	}
    }
    
    public void update(Category category) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.UPDATE_CATEGORY);
	    pstmt.setString(1, category.getWording());
	    pstmt.setInt(2, category.getId_category());
	    pstmt.executeUpdate();
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - update()\n" + e.getLocalizedMessage() + "\n");
	}
    }
    
    public Category selectById(int id) throws DALException {
	Category category = null;
	try {
	    Connection cnx = JdbcTools.getConnection();
	    PreparedStatement pstmt = cnx.prepareStatement(SQLRequest.SELECT_BY_ID_CATEGORY);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		category = new Category(rs.getInt("no_categorie"), rs.getString("libelle"));
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - selectById()\n" + e.getLocalizedMessage() + "\n");
	}
	return category;
    }
    
    public List<Category> selectAll() throws DALException {
	List<Category> listCategory = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_CATEGORIES);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Category category = new Category(rs.getInt("no_categorie"), rs.getString("libelle"));
		listCategory.add(category);
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - selectAll()\n" + e.getLocalizedMessage() + "\n");
	}
	return listCategory;
    }
    
    public List<Category> selectByWording(String Word) throws DALException {
	List<Category> listCategories = new ArrayList<>();
	try {
	    Connection cnx = JdbcTools.getConnection();
	    PreparedStatement pstmt = cnx.prepareStatement(SQLRequest.SELECT_ALL_BY_KEYWORD);
	    pstmt.setString(1, Word);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Category category = new Category(rs.getInt("no_category"), rs.getString("libelle"));
		listCategories.add(category);
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - selectByWording()\n" + e.getLocalizedMessage() + "\n");
	}
	return listCategories;
    }

    public void delete(int id) throws DALException {
	try {
	    Connection cnx = JdbcTools.getConnection();
	    PreparedStatement pstmt = cnx.prepareStatement(SQLRequest.DELETE_CATEGORY);
	    pstmt.setInt(1, id);
	    pstmt.executeUpdate();
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - delete()\n" + e.getLocalizedMessage() + "\n");
	}
    }
    
    public int insertAndReturnIdCategory(Category category) throws DALException {
	int id = 0;
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_CATEGORY,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    pstmt.setString(1, category.getWording());
	    pstmt.executeUpdate();
	    ResultSet rs = pstmt.getGeneratedKeys();
	    while (rs.next()) {
		id = rs.getInt(1);
		category.setId_category(id);
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : CategoryImpl - insertAndReturnIdCategory()\n" + e.getLocalizedMessage() + "\n");
	}
	return id;
    }

}

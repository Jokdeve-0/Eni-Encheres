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

import fr.reddev.encheres.BO.User;
import fr.reddev.encheres.DAL.UserDAO;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class UserImpl implements UserDAO {
    
    @Override
    public int insertForTest(User user) throws DALException {
	int id = 0;
	try {
	    PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_USER,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    stmt.setString(1, user.getAlias());
	    stmt.setString(2, user.getLastname());
	    stmt.setString(3, user.getFirstname());
	    stmt.setString(4, user.getEmail());
	    stmt.setString(5, user.getPhone());
	    stmt.setString(6, user.getStreet());
	    stmt.setString(7, user.getPostal_code());
	    stmt.setString(8, user.getCity());
	    stmt.setString(9, user.getPassword());
	    stmt.setInt(10, user.getCredit());
	    stmt.setBoolean(11, user.isAdministrator());
	    stmt.setBoolean(12, user.isActive());
	    stmt.executeUpdate();
	    ResultSet rs = stmt.getGeneratedKeys();
	    if (rs.next()) {
		id = rs.getInt(1);
		user.setId_user(rs.getInt(1));
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - insertForTest()\n" + e.getLocalizedMessage() + "\n");
	}
	return id;
    }
    
    @Override
    public void insert(User user) throws DALException {
	try {
	    PreparedStatement stmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_USER,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    stmt.setString(1, user.getAlias());
	    stmt.setString(2, user.getLastname());
	    stmt.setString(3, user.getFirstname());
	    stmt.setString(4, user.getEmail());
	    stmt.setString(5, user.getPhone());
	    stmt.setString(6, user.getStreet());
	    stmt.setString(7, user.getPostal_code());
	    stmt.setString(8, user.getCity());
	    stmt.setString(9, user.getPassword());
	    stmt.setInt(10, user.getCredit());
	    stmt.setBoolean(11, user.isAdministrator());
	    stmt.setBoolean(12, user.isActive());
	    stmt.executeUpdate();
	    ResultSet rs = stmt.getGeneratedKeys();
	    if (rs.next()) {
		user.setId_user(rs.getInt(1));
	    }
	} catch (Exception e) {
//		e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - insert()\n" + e.getLocalizedMessage() + "\n");
	}
    }

    @Override
    public void update(User user) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.UPDATE_USER);
	    pstmt.setString(1, user.getAlias());
	    pstmt.setString(2, user.getLastname());
	    pstmt.setString(3, user.getFirstname());
	    pstmt.setString(4, user.getEmail());
	    pstmt.setString(5, user.getPhone());
	    pstmt.setString(6, user.getStreet());
	    pstmt.setString(7, user.getPostal_code());
	    pstmt.setString(8, user.getCity());
	    pstmt.setInt(9, user.getCredit());
	    pstmt.setString(10, user.getPassword());
	    pstmt.setBoolean(11, user.isAdministrator());
	    pstmt.setBoolean(12, user.isActive());
	    // where
	    pstmt.setInt(13, user.getId_user());
	    int rows = pstmt.executeUpdate();
	    // check 1 user modify
	    if (rows < 1) {
		throw new DALException("*!* ERROR *!* \nAucune modification n'a été éffectuées dans la BDD.");
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - update()\n" + e.getLocalizedMessage() + "\n");
	}
    }

    @Override
    public User selectById(int id) throws DALException {
	User user = null;
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_BY_ID_USER);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
		user = new User(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
			rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
			rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
			rs.getInt("credit"), rs.getBoolean("administrateur"), rs.getBoolean("active"));
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - selectById()\n" + e.getLocalizedMessage() + "\n");
	}
	return user;
    }

    @Override
    public List<User> selectAll() throws DALException {
	List<User> UsersRegister = new ArrayList<>();
	User user;
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_ALL_USER);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		user = new User(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
			rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
			rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
			rs.getInt("credit"), rs.getBoolean("administrateur"), rs.getBoolean("active"));
		UsersRegister.add(user);
	    }
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - selectAll()\n" + e.getLocalizedMessage() + "\n");
	}
	return UsersRegister;
    }

    @Override
    public void delete(int id) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.DELETE_USER);
	    pstmt.setInt(1, id);
	    pstmt.executeUpdate();
	} catch (Exception e) {
//	    e.printStackTrace();
	    throw new DALException("ERROR : UserImpl - delete()\n" + e.getLocalizedMessage() + "\n");
	}
    }

}

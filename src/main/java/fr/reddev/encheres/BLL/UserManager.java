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

import fr.reddev.encheres.BO.User;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.DAL.UserDAO;
import fr.reddev.encheres.Exception.DALException;

public class UserManager {

    /**
     * instance Manager
     */
    private static UserManager instance;
    private UserDAO userDao =DAOFactory.getUserDAO();

    /**
     * @return Instance instance of manager
     */
    public static UserManager getInstance() {
	if (instance == null) {
	    instance = new UserManager();
	}
	return instance;
    }

    /**
     * Inserting a user into the database
     * @param user new user
     * @throws SQLException 
     * @throws DALException 
     */
    public int registerANewUser(User user) throws DALException {
	return userDao.insertForTest(user);
    }

    /**
     * Update a user in the database
     * @param user user modify
     * @throws SQLException 
     * @throws DALException 
     */
    public void modifyAUser(User user) throws DALException{
	userDao.update(user);
    }

    /**
     * Select a user from the database
     * @param id id user
     * @throws DALException 
     */
    public User selectAUser(Integer id) throws DALException {
	return userDao.selectById(id);	
    }

    /**
     * Select all users in the database
     * @return A register of users
     * @throws DALException 
     */
    public List<User> selectRegister() throws DALException {
	return userDao.selectAll();
    }

    /**
     * delete user from database
     * @param id id user
     * @throws DALException 
     */
    public void removeUserFromRegister(int id) throws DALException {
	userDao.delete(id);	
    }

}

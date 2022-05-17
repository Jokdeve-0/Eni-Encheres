/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BLL;

import java.util.List;

import fr.reddev.encheres.BO.Category;
import fr.reddev.encheres.DAL.CategoryDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class CategoryManager {

    /**
     * instance Manager
     */
    private static CategoryManager instance;
    private CategoryDAO categoryDao = DAOFactory.getCategoryDAO();

    /**
     * @return Instance instance of manager
     */
    public static CategoryManager getInstance() {
	if (instance == null) {
	    instance = new CategoryManager();
	}
	return instance;
    }

    /**
     * @param category
     * @return
     * @throws DALException 
     */
    public int registerANewCategory(Category category) throws DALException {
	return categoryDao.insertAndReturnIdCategory(category);
    }

    /**
     * @param categoryModify
     * @throws DALException 
     */
    public void modifyACategory(Category categoryModify) throws DALException {
	categoryDao.update(categoryModify);
    }

    /**
     * @param idCategory
     * @return
     * @throws DALException 
     */
    public Category selectACategory(int idCategory) throws DALException {
	return categoryDao.selectById(idCategory);
    }

    /**
     * @return
     * @throws DALException
     */
    public List<Category> selectListCategory() throws DALException {
	return categoryDao.selectAll();
    }

    /**
     * @param idCategory
     * @throws DALException 
     */
    public void removeCategoryFromList(int idCategory) throws DALException {
	categoryDao.delete(idCategory);
	
    }
}

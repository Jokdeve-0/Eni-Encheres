/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL;


import java.util.List;

import fr.reddev.encheres.BO.Category;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public interface CategoryDAO extends DAO <Category> {

    public List<Category> selectByWording(String Word) throws DALException;
    public int insertAndReturnIdCategory(Category category) throws DALException;
}

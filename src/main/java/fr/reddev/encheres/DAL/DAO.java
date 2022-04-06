/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.DAL;

/**
 * @author REDDEV
 */
import java.util.List;

import fr.reddev.encheres.Exception.DALException;

public interface DAO<T> {
	public T selectById(int id) throws DALException;
	public List<T> selectAll() throws DALException;
	public void update(T data) throws DALException;
	public void insert(T data) throws DALException;
	public void delete(int id) throws DALException;
}

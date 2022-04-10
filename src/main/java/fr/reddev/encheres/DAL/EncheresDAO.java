package fr.reddev.encheres.DAL;

import fr.reddev.encheres.BO.Encheres;

<<<<<<< HEAD
 public interface EncheresDAO extends DAO<Encheres>{

=======
import fr.reddev.encheres.Exception.BusinessException;

 public interface EncheresDAO extends DAO<Encheres>{
	public void insertEnchere(Encheres enchere) throws BusinessException;
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
}

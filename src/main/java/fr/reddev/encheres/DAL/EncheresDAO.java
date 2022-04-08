package fr.reddev.encheres.DAL;

import fr.reddev.encheres.BO.Encheres;

import fr.reddev.encheres.Exception.BusinessException;

 public interface EncheresDAO extends DAO<Encheres>{
	public void insertEnchere(Encheres enchere) throws BusinessException;
}

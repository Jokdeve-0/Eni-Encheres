package fr.reddev.encheres.DAL;

import java.util.List;

import fr.reddev.encheres.BO.Articles_vendus;
<<<<<<< HEAD
import fr.reddev.encheres.Exception.DALException;

public interface Articles_vendusDAO extends DAO<Articles_vendus>{
=======
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;

public interface Articles_vendusDAO extends DAO<Articles_vendus>{
	public void creerArticle(Articles_vendus article) throws BusinessException;
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
	public List<Articles_vendus> selectCategorie(int id) throws DALException;
	public List<Articles_vendus> selectAllByName(String nom) throws DALException;
}

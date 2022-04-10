package fr.reddev.encheres.DAL;

import java.util.List;

import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.Exception.DALException;

public interface Articles_vendusDAO extends DAO<Articles_vendus>{
	public List<Articles_vendus> selectCategorie(int id) throws DALException;
	public List<Articles_vendus> selectAllByName(String nom) throws DALException;
}

package fr.reddev.encheres.DAL;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.DALException;

public interface UtilisateurDAO extends DAO<Utilisateur> {


	public Utilisateur selectByLogin(String login) throws  DALException;
}

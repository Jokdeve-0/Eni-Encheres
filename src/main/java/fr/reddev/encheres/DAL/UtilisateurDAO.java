package fr.reddev.encheres.DAL;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.Exception.DALException;

public interface UtilisateurDAO extends DAO<Utilisateur> {

	public Utilisateur connexion(String pseudo, String mdp) throws DALException;
	public Utilisateur selectByLogin(String login) throws  DALException;
}

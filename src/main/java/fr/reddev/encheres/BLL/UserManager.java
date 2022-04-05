package fr.reddev.encheres.BLL;


import java.security.MessageDigest;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.DAL.UtilisateurDAO;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

public class UserManager {

	
	private static UserManager instance;
	
	public UserManager() {
		super();
	}
	
	public static UserManager getInstance() {
		if (instance==null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public  Utilisateur connexion(String identifiant, String mdp) {
		BusinessException exceptions = new BusinessException();
		
		UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
			Utilisateur utilisateur = new Utilisateur();
			boolean verification = false;

			try {
				utilisateur = utilisateurDao.selectByLogin(identifiant);
				if (utilisateur != null) {
//					byte[] aByteArray = mdp.getBytes(Charset.forName("UTF-8"));
					byte[] aByteArray = mdp.getBytes();
			//		System.out.println(aByteArray.toString());
					verification =  MessageDigest.isEqual(aByteArray, utilisateur.getMot_de_passe().getBytes());
		//			System.out.println(verification);
					if (!verification){
							exceptions.ajouterErreur(MSG_BLL.ID_MDP_KO);
							throw exceptions;
					}
				}
			} catch (DALException | BusinessException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		
			return utilisateur;
	}

}

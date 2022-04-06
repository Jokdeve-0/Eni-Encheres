/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.BLL;

/**
 * @author REDDEV
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.DAL.UtilisateurDAO;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

public class UserManager {
	
	/**
	 * instance Manager
	 */
	private static UserManager instance; 
	
	/**
	 * Constructeur
	 */
	public UserManager() {
		super();
	}

	/**
	 * @return Instance
	 */
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	/**
	 * Connexion
	 * @param pseudo  envoyé par ( jsp) l'utilisateur
	 * @param mdp  envoyé par ( jsp) l'utilisateur
	 * @return  Si tout est OK on renvoi un utilisateur , sinon utilisateur = null
	 * @throws BusinessException
	 */
	public Utilisateur connexion(String pseudo, String mdp) throws BusinessException {
		BusinessException exceptions = new BusinessException();
		UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
		Utilisateur utilisateur = new Utilisateur();
		boolean verification = false;
		try {
			// Tente de récupérer l'utilisateur avec l'identififiant si il existe
			utilisateur = utilisateurDao.selectByLogin(pseudo);
		} catch (DALException e) {
			// si un problème survient de la DAL dans selectByLogin()
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
			if (utilisateur != null) {
				// Si l'utilisateur existe => on compare si le password reçu correspond à celui de l'utilisateur trouvé
				verification = UserManager.hashPwd(mdp).equals(utilisateur.getMot_de_passe());
				if (!verification) {
					//  Si le password est incorrect => on ajoute le message d'erreur et on lève l'exception
					exceptions.ajouterErreur(MSG_BLL.ID_MDP_KO);
					throw exceptions;
				}
				// Si le password est correct => on retourne l'utilisateur trouvé
			}
		return utilisateur;
	}

	/**
	 * Créer un nouvel utilisateur
	 * @param utilisateur Nouveau utilisateur à enregistrer 
	 * @param exceptions BLL
	 * @return la liste des exceptions 
	 */
	public BusinessException createUtilisateur(Utilisateur utilisateur, BusinessException exceptions) {
		UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
		try {
			// Vérifie si l'email ou le pseudo existe déjà dans la BDD
			if (!utilisateurDao.UniquePseudoMail(utilisateur.getPseudo(), utilisateur.getEmail()))
				// Si oui =>  on ajouter un message d'erreur
				exceptions.ajouterErreur(MSG_BLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
			else
				// Si non => on enregistre dans la BDD
				utilisateurDao.insert(utilisateur);
		} catch (DALException e) {
			// si un problème survient de la DAL dans UniquePseudoMail()
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return exceptions;
	}

	/**
	 * Validation du formulaire d'enregistrement
	 * @param utilisateur utilisateur a créer
	 * @return  la liste des exceptions 
	 */
	public BusinessException validateUtilisateur(Utilisateur utilisateur) {
		String pseudoValidationRegEx = "[A-Za-z0-9]+";
		// le regex de validation de mail viens de ce site : https://emailregex.com/
		String emailValidationRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
		// Ce regexp gere seulement les numéros de téléphone francais.
		String telephoneNumberValidationRegEx = "^0[1-9][0-9]{8}$";
		BusinessException BusinessException = new BusinessException();
		if (utilisateur.getPseudo().length() > 30) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_PSEUDO_UTILISATEUR);
		}
		if (!Pattern.matches(pseudoValidationRegEx, utilisateur.getPseudo())) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_PSEUDO_NOT_SPECIAL_CHAR);
		}
		if (utilisateur.getNom().length() > 30) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_NOM_UTILISATEUR);
		}
		if (utilisateur.getPrenom().length() > 30) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_PRENOM_UTILISATEUR);
		}
		if (utilisateur.getEmail().length() > 40) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_EMAIL_UTILISATEUR);
		}
		if (!Pattern.matches(emailValidationRegEx, utilisateur.getEmail())) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_FORMAT_EMAIL_UTILISATEUR);
		}
		if (utilisateur.getTelephone().length() > 10) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_TELEPHONE_UTILISATEUR);
		}
		if (!Pattern.matches(telephoneNumberValidationRegEx, utilisateur.getTelephone())) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_FORMAT_TELEPHONE_UTILISATEUR);
		}
		if (utilisateur.getRue().length() > 60) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_RUE_UTILISATEUR);
		}
		if (utilisateur.getCode_postal().length() > 5) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_CODE_POSTAL_UTILISATEUR);
		}
		if (utilisateur.getVille().length() > 30) {
			BusinessException.ajouterErreur(MSG_BLL.ERROR_LENGTH_VILLE_UTILISATEUR);
		}
		return BusinessException;
	}

	/**
	 * Supprimer un utilisateur
	 * @param id no_utilisateur courrant
	 */
	public void deleteUser(Integer id) {
		UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
		try {
			// tente de supprimer l'utilisateur
			utilisateurDao.delete(id);
		} catch (DALException e) {
			// si un problème survient de la DAL
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Modifier un profil
	 * @param utilisateur Nouveau utilisateur créé avec les modifications
	 */
	public void modifierProfil(Utilisateur utilisateur) {
		UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
		try {
			// Tente de modifier
			utilisateurDao.update(utilisateur);
		} catch (DALException e) {
			// si un problème survient de la DAL 
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Hash le password en SHA-256
	 * @param chaine  le password
	 * @return le password crypté
	 */
	public static String hashPwd(String chaine) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(chaine.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}

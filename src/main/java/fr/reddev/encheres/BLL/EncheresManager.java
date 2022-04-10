package fr.reddev.encheres.BLL;

import java.sql.Date;
import java.time.LocalDate;

import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.DAL.EncheresDAO;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.MessageConfException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

public class EncheresManager {
	public Utilisateur ajouterEnchere(Encheres enchere,Utilisateur encherisseur, int somme, Articles_vendus article) throws BusinessException {
		EncheresDAO enchereDao = DAOFactory.getEncheresDAO();
		BusinessException exceptions = new BusinessException();
		MessageConfException messages = new MessageConfException();
		Encheres newEnchere = new Encheres(encherisseur.getno_utilisateur(),article.getNo_article(),Date.valueOf(LocalDate.now()), somme);	
		Utilisateur userMAJ = null;

		if(enchere != null) {
			if(validerSoldeSuffisant(enchere, encherisseur.getCredit())) {
				if(enchereSuperieurAncienne(enchere.getMontant_enchere(),somme)) {
				
					//	credit l'ancien
					recrediterUtilisateur(enchere.getMontant_enchere(),enchere.getNo_utilisateur());
					//debit le nouveau
					userMAJ = debiterUtilisateur(somme,newEnchere.getNo_utilisateur());
					//ajouter l'enchere
					try {
						enchereDao.update(newEnchere);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}else {
					exceptions.ajouterErreur(0);
					// TODO pas de message
				}
			}else {
				exceptions.ajouterErreur(MSG_BLL.NOMBRE_DE_POINTS_INSUFFISANT);
				// TODO  pas de message
			}
		}else {
			// si il y n'a pas d'enchere en cours
			if(valideSuperieurAuPrix(article,somme)) {
				//debit l'utilisateur
				userMAJ = debiterUtilisateur(somme,newEnchere.getNo_utilisateur());
				try {
					//ajoute une nouvelle enchere
					enchereDao.insert(newEnchere);
					// envoie message de confirmation
				} catch (DALException e) {
					// TODO message DAL console 
					e.printStackTrace();
				}
			}else {
				exceptions.ajouterErreur(0);
				// TODO message somme inferieur au prix initial
			}
			
		}

		if (exceptions.hasErreurs()) {
				throw exceptions;
		}
		return userMAJ;
	}
	
	private Utilisateur debiterUtilisateur(int somme, Integer no_utilisateur) {
		UserManager userMG = new UserManager();
		Utilisateur user = userMG.GetUtilisateur(no_utilisateur);
		user.setCredit(user.getCredit() - somme);
		userMG.modifierProfil(user);
		return user;
	}

	private void recrediterUtilisateur(Integer montant_enchere, Integer no_utilisateur) {
		UserManager userMG = new UserManager();
		Utilisateur oldUser = userMG.GetUtilisateur(no_utilisateur);
		oldUser.setCredit(oldUser.getCredit() + montant_enchere);
		userMG.modifierProfil(oldUser);
	}

	private boolean valideSuperieurAuPrix(Articles_vendus article, int somme) {
		boolean valid= false;
		if(article.getPrix_initial() < somme) {
			valid = true;
		}
		return valid;
	}

	// Méthodes privées
	

	private boolean validerSoldeSuffisant(Encheres enchere, Integer credit) {
		boolean valid = false;
		if (credit > enchere.getMontant_enchere()) {
			valid = true;
		}
		return valid;
	}
	public boolean enchereSuperieurAncienne(Integer AncienMontant, int NouveauMontant) {
		boolean verif = false;
		if (NouveauMontant > AncienMontant) {
			verif = true;
		}
		return verif;
	}

	public Encheres rechercheEnchere(Integer no_article) throws BLLException {
		EncheresDAO enchereDao = DAOFactory.getEncheresDAO();
		Encheres enchere = new Encheres();
		try {
			enchere = enchereDao.selectById(no_article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(
					"Erreur lors de la récupération des catégories par libellé {CategorieManager - L38 - getCatalogueLibelle()}");
		}
		return enchere;

	}


	public boolean etatEnCours(Articles_vendus article) {
		boolean etat = false;
		if(article.getDate_debut_encheres().before(Date.valueOf(LocalDate.now().plusDays(1))) &&
			article.getDate_fin_encheres().after(Date.valueOf(LocalDate.now()))) {
			etat = true;
		}
		return etat;
	}
	
}

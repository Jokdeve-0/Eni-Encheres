/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.BLL;

/**
 * @author REDDEV
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Articles_vendus;
import fr.reddev.encheres.DAL.Articles_vendusDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

public class Articles_vendusManager {

	private static Articles_vendusDAO articleDao;

	public Articles_vendusManager() {
		articleDao = DAOFactory.getArticles_vendusDAO();
	}

	/**
	 * Créer un article
	 * @param articleVendu
	 * @throws BusinessException
	 */
	public BusinessException creerArticle(Articles_vendus articleVendu) throws BusinessException{
		BusinessException exceptions = validateArticleVendu(articleVendu);
		if (!exceptions.hasErreurs()) {
			try {
				articleDao.insert(articleVendu);
			} catch (DALException e) {
				// TODO Message DAL
				e.printStackTrace();
			}
		} else {
			exceptions.ajouterErreur(0);
			// TODO Ecrire Message d'erreur
		}
		return exceptions;
	}

	/**
	 * Validation du formulaire de création d'un article
	 * @param articleVendu
	 * @return
	 */
	private BusinessException validateArticleVendu(Articles_vendus articleVendu) {
		LocalDate date_debut = articleVendu.getDate_debut_encheres().toLocalDate();
		LocalDate date_fin = articleVendu.getDate_fin_encheres().toLocalDate();

		BusinessException exceptions = new BusinessException();
		if (articleVendu.getNom_article().length() > 30) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_LENGTH_NOM_ARTICLE);
		}
		if (articleVendu.getDescription().length() > 300) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_LENGTH_DESCRIPTION_ARTICLE);
		}

		if (articleVendu.getDate_debut_encheres().after(articleVendu.getDate_fin_encheres())) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_START_DATE_AFTER_END_DATE);
		}
		if (date_debut.isBefore(LocalDate.now().plusDays(1)) || !date_fin.isAfter(date_debut)) {
			exceptions.ajouterErreur(MSG_BLL.ERROR_DATE_BEFORE_TODAY);
		}
		return exceptions;

	}

	/**
	 * @return
	 * @throws BLLException
	 */
	public List<Articles_vendus> getCatalogue() throws BLLException {
		List<Articles_vendus> articles = null;
		try {
			articles = articleDao.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Une erreur s'est produite lors de la récupération du catalogue");
		}
		return articles;
	}
	/**
	 * @param id
	 * @return
	 * @throws BLLException
	 */
	public List<Articles_vendus> getCatalogueCategorie(int id) throws BLLException {
		List<Articles_vendus> articles = null;
		try {
			articles = articleDao.selectCategorie(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Une erreur s'est produite lors de la récupération du catalogue par catégories");
		}
		return articles;
	}
	/**
	 * @param nom
	 * @return
	 * @throws BLLException
	 */
	public List<Articles_vendus> getCatalogueByName(String nom) throws BLLException{
		List<Articles_vendus> articles = null;
		try {
			articles = articleDao.selectAllByName(nom);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Une erreur s'est produite lors de la récupération du catalogue par recherche");
		}
		return articles;
	}
	
	public Articles_vendus getArticleById(int id) throws BLLException {
		Articles_vendus articles = null;
		try {
			articles = articleDao.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Une erreur s'est produite lors de la récupération du catalogue");
		}
		return articles;
	}

	public List<Articles_vendus> getEnchereOuverte(List<Articles_vendus> catalogue) {
		List<Articles_vendus> enchereOuverte = new ArrayList<>();
		EncheresManager enchereMG = new EncheresManager();
		Articles_vendusManager artMG = new Articles_vendusManager();
		if(catalogue != null ) {
			for(Articles_vendus art : catalogue) {
				try {
					if(!art.getEtat_vente().equals("TR") && !art.getEtat_vente().equals("RE") && enchereMG.etatEnCours(art) ) {
						//enchere ouverte
						if(art.getEtat_vente().equals("CR" )) {
							//change l'etat
							art.setEtat_vente("EC");
							//MAJ dans la BDD
							artMG.ModifierEtat(art);
							//Ajoute a la liste des encheres ouvertes
							enchereOuverte.add(art);
						}else {
							//etat = "EC" => Ajoute a la liste des encheres ouvertes
							enchereOuverte.add(art);
						}
					}else {
						//verification est MAJ de l'article
						if(art.getEtat_vente().equals("CR") || art.getEtat_vente().equals("EC") ) {
							//change l'etat
							art.setEtat_vente("TR");
							//MAJ dans la BDD
							artMG.ModifierEtat(art);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return enchereOuverte;
	}

	private void ModifierEtat(Articles_vendus art) {
		try {
			articleDao.update(art);
		} catch (DALException e) {
			// TODO Erreur message
			e.printStackTrace();
		}
		
	}

}

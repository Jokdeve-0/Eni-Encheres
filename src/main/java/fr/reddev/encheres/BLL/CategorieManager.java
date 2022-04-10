package fr.reddev.encheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.DAL.CategorieDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.BLLException;
import fr.reddev.encheres.Exception.DALException;

public class CategorieManager {
	private static CategorieDAO daoCategories;

	public CategorieManager() {
		daoCategories = DAOFactory.getCategorieDAO();
	}

	public List<Categorie> getCategories() throws BLLException {
		List<Categorie> list = new ArrayList<>();
		try {
			list = daoCategories.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur lors de la récupération des catégories {CategorieManager - L26 - getCategories()}");
		}
		return list;
	}

	public List<Categorie> getCatalogueLibelle(String libelle) throws BLLException {
		List<Categorie> list = new ArrayList<>();
		try {
			list = daoCategories.selectByLibelle(libelle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur lors de la récupération des catégories par libellé {CategorieManager - L38 - getCatalogueLibelle()}");
		}
		return list;
	}
}

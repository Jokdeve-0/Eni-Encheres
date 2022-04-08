package fr.reddev.encheres.BLL;

import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_BLL;

public class EncheresManager {
	public void ajouterEnchere(Encheres enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		validerSoldeSuffisant(enchere, businessException);
		if (!businessException.hasErreurs()) {
			try {
				DAOFactory.getEncheresDAO(). insert(enchere);
			} catch (DALException e) {
				
				e.printStackTrace();
			}
		} else {
			throw businessException;
		}
	}
	
	// Méthodes privées
	
	private void validerSoldeSuffisant(Encheres enchere, BusinessException businessException) {
		if (enchere.getUtilisateur().getCredit() < enchere.getMontantEnchere()) {
			businessException.ajouterErreur(MSG_BLL.NOMBRE_DE_POINTS_INSUFFISANT);//pas de message
		}
	}
	
}


/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BLL;

import java.sql.SQLException;
import java.util.List;

import fr.reddev.encheres.BO.Auction;
import fr.reddev.encheres.DAL.AuctionDAO;
import fr.reddev.encheres.DAL.DAOFactory;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class AuctionManager {

    /**
     * instance Manager
     */
    private static AuctionManager instance;
    private AuctionDAO auctionDao = DAOFactory.getAuctionDAO();

    /**
     * @return Instance instance of manager
     */
    public static AuctionManager getInstance() {
	if (instance == null) {
	    instance = new AuctionManager();
	}
	return instance;
    }

    /**
     * @param auction
     * @return
     * @throws DALException
     */
    public void registerANewAuction(Auction auction) throws DALException {
	auctionDao.insert(auction);
    }

    /**
     * @param auctionModify
     * @throws DALException
     */
    public void modifyAuction(Auction auctionModify) throws DALException {
	auctionDao.update(auctionModify);

    }

    /**
     * @return
     * @throws SQLException
     * @throws DALException
     */
    public List<Auction> retrieveByArticle(int idArticle) throws DALException {
	return auctionDao.selectByIdArticle(idArticle);
    }

    /**
     * @return
     * @throws DALException
     */
    public Auction selectAuctionCatalog(Auction auction) throws DALException {
	return auctionDao.selectByUserAndArticle(auction);
    }
}

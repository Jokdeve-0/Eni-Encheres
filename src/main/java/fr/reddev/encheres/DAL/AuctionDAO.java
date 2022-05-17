/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL;

import java.util.List;

import fr.reddev.encheres.BO.Auction;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public interface AuctionDAO extends DAO<Auction> {

	public Auction getHighestAuction(int idArticle) throws DALException;

	public List<Auction>  selectByIdArticle(int id) throws DALException;

	public List<Auction> selectAllAuctionWithDatasUsers() throws DALException;

	public Auction selectByUserAndArticle(Auction auction) throws DALException;

}

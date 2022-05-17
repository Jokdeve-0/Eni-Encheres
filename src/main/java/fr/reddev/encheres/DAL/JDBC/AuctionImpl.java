/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Auction;
import fr.reddev.encheres.DAL.AuctionDAO;
import fr.reddev.encheres.Exception.DALException;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class AuctionImpl implements AuctionDAO {

    @Override
    public void insert(Auction auction) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.INSERT_AUCTION);
	    pstmt.setInt(1, auction.getId_user());
	    pstmt.setInt(2, auction.getId_article());
	    pstmt.setDate(3, Date.valueOf(LocalDate.now()));
	    pstmt.setInt(4, auction.getAuction_amount());
	    pstmt.executeUpdate();
	} catch (Exception e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - insert()\n" + e.getLocalizedMessage() + "\n");
	}
    }

    @Override
    public void update(Auction auction) throws DALException {
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.UPDATE_AUCTION);
	    pstmt.setDate(1, auction.getAuction_date());
	    pstmt.setInt(2, auction.getAuction_amount());
	    pstmt.setInt(3, auction.getId_user());
	    pstmt.executeUpdate();
	} catch (SQLException e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - update()\n" + e.getLocalizedMessage() + "\n");
	}
    }

    @Override
    public Auction selectById(int id) throws DALException {
	return null;
    }

    @Override
    public List<Auction> selectAll() throws DALException {
	List<Auction> Auction = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_ALL_AUCTION);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Auction auction = new Auction(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
			rs.getDate("date_auction"), rs.getInt("montant_auction"), rs.getString("pseudo"));
		Auction.add(auction);
	    }
	} catch (SQLException e) {
//			e.printStackTrace();
	    throw new DALException("AuctionDaoImpl - selectAll() ");
	}
	return Auction;
    }

    @Override
    public void delete(int id) throws DALException {
    }

    @Override
    public List<Auction> selectByIdArticle(int idArticle) throws DALException {
	List<Auction> list = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_BY_ID_AUCTION);
	    pstmt.setInt(1, idArticle);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Auction auction = new Auction(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
			rs.getDate("date_auction"), rs.getInt("montant_auction"), rs.getString("pseudo"));
		list.add(auction);
	    }
	} catch (Exception e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - selectByIdSpec()\n" + e.getLocalizedMessage() + "\n");
	}
	return list;
    }

    @Override
    public List<Auction> selectAllAuctionWithDatasUsers() throws DALException {
	List<Auction> listAuctions = new ArrayList<>();
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_AUCTION_USER);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
		Auction auction = new Auction(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
			rs.getDate("date_auction"), rs.getInt("montant_auction"), rs.getString("pseudo"));
		listAuctions.add(auction);
	    }
	} catch (SQLException e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - selectByAuction()\n" + e.getLocalizedMessage() + "\n");
	}
	return listAuctions;
    }

    @Override
    public Auction selectByUserAndArticle(Auction auction) throws DALException {
	Auction auctionReturn = null;
	try {
	    Connection cnx = JdbcTools.getConnection();
	    PreparedStatement pstmt;
	    pstmt = cnx.prepareStatement(SQLRequest.SELECT_AUCTION_BY_USER_AND_ARTICLE);
	    pstmt.setInt(1, auction.getId_article());
	    pstmt.setInt(2, auction.getId_user());
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
		auctionReturn = new Auction(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
			rs.getDate("date_auction"), rs.getInt("montant_auction"), rs.getString("pseudo"));
	    }
	} catch (SQLException e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - selectByUser()\n" + e.getLocalizedMessage() + "\n");
	}
	return auctionReturn;
    }

    @Override
    public Auction getHighestAuction(int idArticle) throws DALException {

	Auction auction = null;
	try {
	    PreparedStatement pstmt = JdbcTools.getConnection().prepareStatement(SQLRequest.SELECT_BY_HIGHTEST_AUCTION);
	    pstmt.setInt(1, idArticle);
	    pstmt.setInt(2, idArticle);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
		auction = new Auction(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_auction"),
			rs.getInt("montant_auction"), rs.getString("pseudo"));
	    }
	} catch (SQLException e) {
//		    e.printStackTrace();
	    throw new DALException("ERROR : AuctionImpl - getHighestAuction()\n" + e.getLocalizedMessage() + "\n");
	}
	return auction;
    }

}

/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BO;

import java.sql.Date;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class Auction {
    private Integer id_user;
    private Integer id_article;
    private Date auction_date;
    private Integer auction_amount;
    private String alias;
    
    /**
     * @param id_user
     * @param id_article
     * @param auction_date
     * @param auction_amount
     * @param alias
     */
    public Auction(Integer id_user, Integer id_article, Date auction_date, Integer auction_amount, String alias) {
	super();
	this.id_user = id_user;
	this.id_article = id_article;
	this.auction_date = auction_date;
	this.auction_amount = auction_amount;
	this.alias = alias;
    }

    /**
     * @return the id_user
     */
    public Integer getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    /**
     * @return the id_article
     */
    public Integer getId_article() {
        return id_article;
    }

    /**
     * @param id_article the id_article to set
     */
    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }

    /**
     * @return the auction_date
     */
    public Date getAuction_date() {
        return auction_date;
    }

    /**
     * @param auction_date the auction_date to set
     */
    public void setAuction_date(Date auction_date) {
        this.auction_date = auction_date;
    }

    /**
     * @return the auction_amount
     */
    public Integer getAuction_amount() {
        return auction_amount;
    }

    /**
     * @param auction_amount the auction_amount to set
     */
    public void setAuction_amount(Integer auction_amount) {
        this.auction_amount = auction_amount;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
	return "Auction [getId_user()=" + getId_user() + ", getId_article()=" + getId_article() + ", getAuction_date()="
		+ getAuction_date() + ", getAuction_amount()=" + getAuction_amount() + ", getAlias()=" + getAlias()
		+ "]";
    }
   
}

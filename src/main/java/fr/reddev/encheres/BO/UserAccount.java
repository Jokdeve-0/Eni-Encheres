/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BO;

import java.util.List;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class UserAccount extends User {

    private List<Article> articleToSold;
    private List<Auction> auctions;

    /**
     * @param id_user
     * @param alias
     * @param lastname
     * @param firstname
     * @param email
     * @param phone
     * @param street
     * @param postal_code
     * @param city
     * @param password
     * @param credit
     * @param administrator
     * @param active
     * @param articleToSold
     */
    public UserAccount(Integer id_user, String alias, String lastname, String firstname, String email, String phone,
	    String street, String postal_code, String city, String password, Integer credit, boolean administrator,
	    boolean active,List<Article> articleToSold, List<Auction> auctions) {
	super(id_user, alias, lastname, firstname, email, phone, street, postal_code, city, password, credit,
		administrator, active);
	this.setArticleToSold(articleToSold);
	this.setAuctions(auctions);
    }

    /**
     * @return the articleToSold
     */
    public List<Article> getArticleToSold() {
	return articleToSold;
    }

    /**
     * @param articleToSold the articleToSold to set
     */
    public void setArticleToSold(List<Article> articleToSold) {
	this.articleToSold = articleToSold;
    }

    /**
     * @return the auctions
     */
    public List<Auction> getAuctions() {
	return auctions;
    }

    /**
     * @param auctions the auctions to set
     */
    public void setAuctions(List<Auction> auctions) {
	this.auctions = auctions;
    }    
    
}

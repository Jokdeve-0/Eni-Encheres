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
public class Article {
    private Integer id_article;
    private String name;
    private String description;
    private Date auctionStartDate;
    private Date auctionEndDate;
    private Integer startedPrice;
    private Integer finalPrice;
    private Integer idUser;
    private Integer idCategory;
    private String stateSale;
    private String seller;
    
    /**
     * @param idArticle
     * @param name
     * @param description
     * @param auctionStartDate
     * @param auctionEndDate
     * @param startedPrice
     * @param finalPrice
     * @param idUser
     * @param idCategory
     * @param stateSale
     * @param seller
     */
    public Article(Integer id_article, String name, String description, Date auctionStartDate, Date auctionEndDate,
	    Integer startedPrice, Integer finalPrice, Integer idUser, Integer idCategory, String stateSale, String seller) {
	super();
	this.id_article = id_article;
	this.name = name;
	this.description = description;
	this.auctionStartDate = auctionStartDate;
	this.auctionEndDate = auctionEndDate;
	this.startedPrice = startedPrice;
	this.finalPrice = finalPrice;
	this.idUser = idUser;
	this.idCategory = idCategory;
	this.stateSale = stateSale;
	this.seller = seller;
    }

    /**
     * @param id_article
     * @param name
     * @param description
     * @param auctionStartDate
     * @param auctionEndDate
     * @param startedPrice
     * @param finalPrice
     * @param idUser
     * @param idCategory
     * @param stateSale
     * @param seller
     */
    public Article(String name, String description, Date auctionStartDate, Date auctionEndDate,
	    Integer startedPrice, Integer finalPrice, Integer idUser, Integer idCategory, String stateSale, String seller) {
	this( null,name,  description,  auctionStartDate,  auctionEndDate, startedPrice,  finalPrice,  idUser,  idCategory,  stateSale,seller);
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the auctionStartDate
     */
    public Date getAuctionStartDate() {
        return auctionStartDate;
    }

    /**
     * @param auctionStartDate the auctionStartDate to set
     */
    public void setAuctionStartDate(Date auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    /**
     * @return the auctionEndDate
     */
    public Date getAuctionEndDate() {
        return auctionEndDate;
    }

    /**
     * @param auctionEndDate the auctionEndDate to set
     */
    public void setAuctionEndDate(Date auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    /**
     * @return the startedPrice
     */
    public Integer getStartedPrice() {
        return startedPrice;
    }

    /**
     * @param startedPrice the startedPrice to set
     */
    public void setStartedPrice(Integer startedPrice) {
        this.startedPrice = startedPrice;
    }

    /**
     * @return the finalPrice
     */
    public Integer getFinalPrice() {
        return finalPrice;
    }

    /**
     * @param finalPrice the finalPrice to set
     */
    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the idCategorie
     */
    public Integer getIdCategory() {
        return idCategory;
    }

    /**
     * @param idCategorie the idCategorie to set
     */
    public void setIdCategory(Integer idCategorie) {
        this.idCategory = idCategorie;
    }

    /**
     * @return the stateSale
     */
    public String getStateSale() {
        return stateSale;
    }

    /**
     * @param stateSale the stateSale to set
     */
    public void setStateSale(String stateSale) {
        this.stateSale = stateSale;
    }

    /**
     * @return the seller
     */
    public String getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
	return "Article [getIdArticle()=" + getId_article() + ", getName()=" + getName() + ", getDescription()="
		+ getDescription() + ", getAuctionStartDate()=" + getAuctionStartDate() + ", getAuctionEndDate()="
		+ getAuctionEndDate() + ", getStartedPrice()=" + getStartedPrice() + ", getFinalPrice()="
		+ getFinalPrice() + ", getIdUser()=" + getIdUser() + ", getIdCategorie()=" + getIdCategory()
		+ ", getStateSale()=" + getStateSale() + "]";
    }

    
}

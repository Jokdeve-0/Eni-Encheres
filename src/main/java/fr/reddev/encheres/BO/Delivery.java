/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.BO;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class Delivery {

    private int id_article;
    private String street;
    private String postal_code;
    private String city;
    
    /**
     * @param id_article
     * @param street
     * @param postal_code
     * @param city
     */
    public Delivery(int id_article, String street, String postal_code, String city) {
	super();
	this.id_article = id_article;
	this.street = street;
	this.postal_code = postal_code;
	this.city = city;
    }

    /**
     * @return the id_article
     */
    public int getId_article() {
        return id_article;
    }

    /**
     * @param id_article the id_article to set
     */
    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the postal_code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * @param postal_code the postal_code to set
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
	return "Delivery [getId_article()=" + getId_article() + ", getStreet()=" + getStreet() + ", getPostal_code()="
		+ getPostal_code() + ", getCity()=" + getCity() + "]";
    }
    
}

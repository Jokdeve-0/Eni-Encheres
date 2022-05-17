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
public class Category {

	private Integer id_category;
	private String wording;
	/**
	 * @param id_category
	 * @param wording
	 */
	public Category(Integer id_category, String wording) {
	    super();
	    this.id_category = id_category;
	    this.wording = wording;
	}
	/**
	 * @param id_category
	 * @param wording
	 */
	public Category(String wording) {
	    super();
	    this.wording = wording;
	}
	/**
	 * @return the id_category
	 */
	public Integer getId_category() {
	    return id_category;
	}
	/**
	 * @param id_category the id_category to set
	 */
	public void setId_category(Integer id_category) {
	    this.id_category = id_category;
	}
	/**
	 * @return the wording
	 */
	public String getWording() {
	    return wording;
	}
	/**
	 * @param wording the wording to set
	 */
	public void setWording(String wording) {
	    this.wording = wording;
	}
	@Override
	public String toString() {
	    return "Categorie [getId_category()=" + getId_category() + ", getWording()=" + getWording() + "]";
	}
	
}

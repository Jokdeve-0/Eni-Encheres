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
public class User {

    private Integer id_user;
    private String alias;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;
    private String street;
    private String postal_code;
    private String city;
    private String password;
    private Integer credit;
    private boolean administrator;
    private boolean active;
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
     */
    public User(Integer id_user, String alias, String lastname, String firstname, String email, String phone,
	    String street, String postal_code, String city, String password, Integer credit, boolean administrator,
	    boolean active) {
	super();
	this.id_user = id_user;
	this.alias = alias;
	this.lastname = lastname;
	this.firstname = firstname;
	this.email = email;
	this.phone = phone;
	this.street = street;
	this.postal_code = postal_code;
	this.city = city;
	this.password = password;
	this.credit = credit;
	this.administrator = administrator;
	this.active = active;
    }
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
     */
    public User( String alias, String lastname, String firstname, String email, String phone,
	    String street, String postal_code, String city, String password, Integer credit, boolean administrator,
	    boolean active) {
	this(null, alias,  lastname,  firstname,  email,  phone, street,  postal_code,  city,  password,  credit,  administrator, active);
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
    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the credit
     */
    public Integer getCredit() {
        return credit;
    }
    /**
     * @param credit the credit to set
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
    /**
     * @return the administrator
     */
    public boolean isAdministrator() {
        return administrator;
    }
    /**
     * @param administrator the administrator to set
     */
    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    @Override
    public String toString() {
	return "User [getId_user()=" + getId_user() + ", getAlias()=" + getAlias() + ", getLastname()=" + getLastname()
		+ ", getFirstname()=" + getFirstname() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone()
		+ ", getStreet()=" + getStreet() + ", getPostal_code()=" + getPostal_code() + ", getCity()=" + getCity()
		+ ", getPassword()=" + getPassword() + ", getCredit()=" + getCredit() + ", isAdministrator()="
		+ isAdministrator() + ", isActive()=" + isActive() + "]";
    }
    


}

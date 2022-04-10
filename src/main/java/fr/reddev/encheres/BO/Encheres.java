package fr.reddev.encheres.BO;

<<<<<<< HEAD
import java.sql.Date;

public class Encheres {
	private Integer no_utilisateur;
	private Integer no_article;
	private Date date_enchere;
	private Integer montant_enchere;
	
	
	public Encheres() {
		super();
	}
	
	public Encheres(Integer no_utilisateur, Integer no_article, Date date_enchere, Integer montant_enchere) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
	}

	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}

	public Date getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(Date date_enchere) {
		this.date_enchere = date_enchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	@Override
	public String toString() {
		return "Encheres [no_utilisateur=" + no_utilisateur + ", no_article=" + no_article + ", date_enchere="
				+ date_enchere + ", montant_enchere=" + montant_enchere + "]";
=======
import java.time.LocalDate;

public class Encheres {
	private Utilisateur utilisateur;
	private Articles_vendus article;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	public Encheres() {
		super();
		utilisateur = new Utilisateur();
		article = new Articles_vendus();
	}
	
	// Constructeur pour l'affichage du détail d'un article
	public Encheres(int idUtilisateur, String pseudo, int montantEnchere) {
		this();
		this.utilisateur.setno_utilisateur(idUtilisateur);
		this.utilisateur.setPseudo(pseudo);
		this.montantEnchere = montantEnchere;
	}

	public Encheres(Utilisateur utilisateur, Articles_vendus article, LocalDate dateEnchere, int montantEnchere) {
		this();
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the article
	 */
	public Articles_vendus getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Articles_vendus article) {
		this.article = article;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
	}
	
	
	
}


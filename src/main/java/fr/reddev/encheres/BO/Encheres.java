package fr.reddev.encheres.BO;

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
	}
	
	
	
}


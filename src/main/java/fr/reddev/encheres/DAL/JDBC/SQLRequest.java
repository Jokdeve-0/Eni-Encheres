/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.DAL.JDBC;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class SQLRequest {
//    	USER
    public static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe ,credit,administrateur,active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    public static final String UPDATE_USER = "UPDATE UTILISATEURS SET  pseudo=? , nom=?  , prenom=? , email=? , telephone=? , rue=? , code_postal=? , ville=? , credit=?, mot_de_passe=?, administrateur=?, active=? WHERE no_utilisateur = ?";
    public static final String SELECT_BY_ID_USER = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
    public static final String SELECT_ALL_USER = "SELECT * FROM UTILISATEURS";
    public static final String DELETE_USER = "DELETE FROM UTILISATEURS where no_utilisateur = ?";

//    ARTICLE
    public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur, no_categorie, etat_vente, vendeur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_ARTICLE = " UPDATE ARTICLES_VENDUS  set nom_article=? ,description=? ,date_debut_encheres=?, date_fin_encheres=? , prix_initial=? , prix_vente=? , no_utilisateur=? , no_categorie=? , etat_vente=? , vendeur=? WHERE no_article= ?";
    public static final String SELECT_BY_ID_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article =?";
    public static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (a.no_utilisateur = u.no_utilisateur) ORDER BY no_article DESC";
    public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
    public static final String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN CATEGORIES c ON (a.no_categorie = c.no_categorie) WHERE c.no_categorie = ?";
    public static final String SELECT_ALL_BY_KEYWORD = "SELECT * FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (a.no_utilisateur = u.no_utilisateur) WHERE nom_article LIKE '%";
    public static final String DELIVERED_ARTICLE = " UPDATE ARTICLES_VENDUS  set  etat_vente='DE'  WHERE no_article= ?";

//    DELIVERY
    public static final String INSERT_DELIVERY = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES(?,?,?,?)";

//    CATEGORY
    public static final String INSERT_CATEGORY = "INSERT INTO CATEGORIES VALUES (?)";
    public static final String UPDATE_CATEGORY = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    public static final String SELECT_BY_ID_CATEGORY = "SELECT * FROM CATEGORIES WHERE no_categorie=?";
    public static final String DELETE_CATEGORY = "DELETE FROM CATEGORIES WHERE no_categorie = ?";
    public static final String SELECT_CATEGORIES = "SELECT * FROM CATEGORIES";
    public static final String SELECT_BY_WORDING = "SELECT * FROM CATEGORIES WHERE libelle LIKE ?";

//    AUCTION
    public static final String INSERT_AUCTION= "INSERT INTO ENCHERES VALUES (?, ?, ?, ?)";
    public static final String UPDATE_AUCTION = "UPDATE ENCHERES SET   date_enchere=?, montant_enchere = ? WHERE no_utilisateur=? ";
    public static final String SELECT_BY_ID_AUCTION = "SELECT * FROM ENCHERES WHERE no_article = ?";
    public static final String SELECT_ALL_AUCTION = "SELECT * FROM ENCHERES";
    public static final String SELECT_AUCTION_BY_USER_AND_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ? and no_utilisateur =?";
    public static final String SELECT_BY_HIGHTEST_AUCTION = "SELECT * FROM ENCHERES WHERE no_article = ? AND montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ?)";
    public static final String SELECT_AUCTION_USER= "SELECT ENCHERES.no_utilisateur, no_article, date_enchere,montant_enchere, UTILISATEURS.pseudo FROM ENCHERES INNER JOIN UTILISATEURS ON ( ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur )  ORDER BY montant_enchere DESC ";
}

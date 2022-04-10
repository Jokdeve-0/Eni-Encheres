package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.sql.SQLException;
=======
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958
import java.time.LocalDate;
import java.util.List;

import fr.reddev.encheres.BO.Encheres;
import fr.reddev.encheres.DAL.EncheresDAO;
<<<<<<< HEAD
import fr.reddev.encheres.Exception.DALException;

public class EncheresDaoImpl implements EncheresDAO{
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_article = ?";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET  no_utilisateur = ?, date_enchere=?, montant_enchere = ? WHERE no_article =? ";


		@Override
		public Encheres selectById(int id) throws DALException {
			Encheres enchere =null;
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt;
			
			try {
				pstmt = cnx.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
						enchere=new Encheres(rs.getInt("no_utilisateur"),
								rs.getInt("no_article"),
								rs.getDate("date_enchere"),
								rs.getInt("montant_enchere"));
						
				}
			} catch (SQLException e) {
				//  remonter l'erreur dal
				e.printStackTrace();
			}
		
			
			
			return enchere;
		}

		@Override
		public List<Encheres> selectAll() throws DALException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void insert(Encheres enchere) throws DALException{
			
			try {
				Connection cnx = JdbcTools.getConnection();
				
				try {
					PreparedStatement	pstmt = cnx.prepareStatement(INSERT_ENCHERE);
					pstmt.setInt(1, enchere.getNo_utilisateur());
					pstmt.setInt(2, enchere.getNo_article());
					pstmt.setDate(3, Date.valueOf(LocalDate.now()));
					pstmt.setInt(4, enchere.getMontant_enchere());		
					pstmt.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DALException("Echec à l'insert de l'enchere");
//				BusinessException businessException = new BusinessException();
//				businessException.ajouterErreur(MSG_DAL.INSERT_ENCHERE_ERREUR);
//				throw businessException;
			}
					
		}

		@Override
		public void delete(int id) throws DALException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(Encheres enchere) throws DALException {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt3;
			try {
				pstmt3 = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt3.setInt(1, enchere.getNo_utilisateur());
				pstmt3.setDate(2, enchere.getDate_enchere());
				pstmt3.setInt(3, enchere.getMontant_enchere());
				pstmt3.setInt(4, enchere.getNo_article());
				pstmt3.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
=======
import fr.reddev.encheres.Exception.BusinessException;
import fr.reddev.encheres.Exception.DALException;
import fr.reddev.encheres.Exception.CodesMessages.MSG_DAL;

public class EncheresDaoImpl implements EncheresDAO{
private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?, ?, ?, ?);";
	
	// requêtes utilitaires et de vérification

	private static final String DERNIERE_ENCHERE_ARTICLE_ID = "SELECT no_utilisateur, montant_enchere" + 
			"	FROM ENCHERES" + 
			"	WHERE no_article = ? AND montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ?);";
	
	private static final String CREDITER_UTILISATEUR_ID = "UPDATE UTILISATEURS SET credit += ? WHERE no_utilisateur = ?";
	
	private static final String DEBITER_UTILISATEUR_ID = "UPDATE UTILISATEURS SET credit -= ? WHERE no_utilisateur = ?";

	@Override
	public void insertEnchere(Encheres enchere) throws BusinessException {
	try {
			Connection cnx = JdbcTools.getConnection();
			
			try {
				int idMeilleurEncherisseur = 0;
				int montantMeilleurEncherisseur = 0;
				
				PreparedStatement pstmt = cnx.prepareStatement(DEBITER_UTILISATEUR_ID);
				pstmt.setInt(1, enchere.getMontantEnchere());
				pstmt.setInt(2, enchere.getUtilisateur().getno_utilisateur());
				pstmt.executeUpdate();

				
				pstmt = cnx.prepareStatement(DERNIERE_ENCHERE_ARTICLE_ID);
				pstmt.setInt(1, enchere.getArticle().getNo_article());
				pstmt.setInt(2, enchere.getArticle().getNo_article());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					idMeilleurEncherisseur = rs.getInt("no_utilisateur");
					montantMeilleurEncherisseur = rs.getInt("montant_enchere");
				}
	
				
				if (idMeilleurEncherisseur != 0) {
					pstmt = cnx.prepareStatement(CREDITER_UTILISATEUR_ID);
					pstmt.setInt(1, montantMeilleurEncherisseur);
					pstmt.setInt(2, idMeilleurEncherisseur);
					pstmt.executeUpdate();
		
				}
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, enchere.getUtilisateur().getno_utilisateur());
				pstmt.setInt(2, enchere.getArticle().getNo_article());
				pstmt.setDate(3, Date.valueOf(LocalDate.now()));
				pstmt.setInt(4, enchere.getMontantEnchere());		
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(MSG_DAL.INSERT_ENCHERE_ERREUR);
			throw businessException;
		}
		
	}

	@Override
	public Encheres selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Encheres> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Encheres data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Encheres data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
>>>>>>> 7b0875c12920df25e51724e34e7883470d4d5958


}

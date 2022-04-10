/**
 * PROJET ENI-ENCHERES
 * 
 */
package fr.reddev.encheres.DAL.JDBC;

/**
 * @author REDDEV
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.UtilisateurDAO;
import fr.reddev.encheres.Exception.DALException;

public class UtilisateurDaoImpl implements UtilisateurDAO {

	public static final String SELECT_BY_LOGIN = "SELECT no_utilisateur,nom,pseudo,prenom,email,telephone,rue,code_postal,ville,mot_de_passe ,credit,administrateur FROM UTILISATEURS where pseudo = ?";
	public static final String DELETE_USER = "DELETE FROM UTILISATEURS where no_utilisateur = ?";
	public static final String UPDATE_USER = "UPDATE UTILISATEURS SET  pseudo=? , nom=?  , prenom=? , email=? , telephone=? , rue=? , code_postal=? , ville=? , credit=?, mot_de_passe=? WHERE no_utilisateur = ?";
	public static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe ,credit,administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	public static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
	
	
	@Override
	public Utilisateur selectByLogin(String login) throws DALException {
		Utilisateur utilisateur = null;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Identifiant ou mot de passe invalide");
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectById(int id) throws DALException {
		Utilisateur utilisateur = null;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Identifiant ou mot de passe invalide");
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> catalogueUtilisateurs = new ArrayList<>();
		Utilisateur utilisateur;
		try {
			Connection cnx =JdbcTools.getConnection(); 
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
					utilisateur = new Utilisateur(
							rs.getInt("no_utilisateur"), rs.getString("pseudo"),
							rs.getString("nom"), rs.getString("prenom"),
							rs.getString("email"), rs.getString("telephone"),
							rs.getString("rue"),rs.getString("code_postal"),
							rs.getString("ville"),rs.getString("mot_de_passe"),
							rs.getInt("credit"),rs.getBoolean("administrateur"));
					catalogueUtilisateurs.add(utilisateur);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return catalogueUtilisateurs;
	}

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
			// Attrs
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCode_postal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setInt(9, utilisateur.getCredit());
			pstmt.setString(10, utilisateur.getMot_de_passe());
			// where
			pstmt.setInt(11, utilisateur.getno_utilisateur());
			// execute
			int rows = pstmt.executeUpdate();
			// check 1 utilisateur modifier
			if (rows < 1) {
				throw new DALException("Aucune modification n'a été éffectuées dans la BDD.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(
					" Une erreur est intervenue lors de la mise a jour du profil { DAOIMPL L.71 - update() } \nErreur : "
							+ e);
		}

	}

	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCode_postal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMot_de_passe());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.getAdministrateur());
			System.out.println(stmt);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setno_utilisateur(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur lors de la requêtte insert");
		}
	}

	@Override
	public void delete(int id) throws DALException {
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean UniquePseudoMail(String pseudo, String email) throws DALException {
		boolean isUnique = true;

		try {
			Connection cnx = JdbcTools.getConnection();

			String uniquepseudomail = "SELECT * FROM UTILISATEURS " + "WHERE pseudo LIKE ? OR email LIKE ?;";
			PreparedStatement stmt = cnx.prepareStatement(uniquepseudomail);
			stmt.setString(1, pseudo);
			stmt.setString(2, email);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				isUnique = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Votre compte existe déjà");

		}
		return isUnique;
	}

}

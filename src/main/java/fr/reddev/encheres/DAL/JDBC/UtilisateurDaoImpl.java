package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.reddev.encheres.BO.Utilisateur;
import fr.reddev.encheres.DAL.UtilisateurDAO;
import fr.reddev.encheres.Exception.DALException;

public class UtilisateurDaoImpl implements UtilisateurDAO {

	public static final String SELECT_BY_LOGIN ="SELECT no_utilisateur,nom,pseudo,prenom,email,telephone,rue,code_postal,ville,mot_de_passe ,credit,administrateur FROM UTILISATEURS where pseudo = ?";

	
@Override
	public Utilisateur selectByLogin(String login) throws  DALException {

	
	Utilisateur utilisateur = null; 
	try {			
		Connection cnx =JdbcTools.getConnection(); 
		PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
		pstmt.setString(1, login);	
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			utilisateur= new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("nom"), rs.getString("pseudo"), rs.getString("prenom"), rs.getString("email"),rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"),rs.getByte("administrateur"));
		}
	}catch (Exception e) {
		e.printStackTrace();
		throw new DALException("Identifiant ou mot de passe invalide");
	}
	return utilisateur;
	}

@Override
public Utilisateur selectById(int id) throws DALException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Utilisateur> selectAll() throws DALException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void update(Utilisateur data) throws DALException {
	// TODO Auto-generated method stub
	
}

@Override
public void insert(Utilisateur data) throws DALException {
	// TODO Auto-generated method stub
	
}

@Override
public void delete(int id) throws DALException {
	// TODO Auto-generated method stub
	
}

@Override
public Utilisateur connexion(String pseudo, String mdp) throws DALException {
	// TODO Auto-generated method stub
	return null;
}
}

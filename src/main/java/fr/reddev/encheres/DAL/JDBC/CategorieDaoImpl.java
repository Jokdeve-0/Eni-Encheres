package fr.reddev.encheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.reddev.encheres.BO.Categorie;
import fr.reddev.encheres.DAL.CategorieDAO;
import fr.reddev.encheres.Exception.DALException;

public class CategorieDaoImpl implements CategorieDAO {

	public static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	public static final String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle LIKE ?";

	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				listCategorie.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return listCategorie;
	}

	@Override
	public Categorie selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Categorie data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Categorie data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub

	}

	public List<Categorie> selectByLibelle(String libelle) throws DALException {
		List<Categorie> listCategorie = new ArrayList<>();
		Categorie categorie;
		try {
			Connection cnx = JdbcTools.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

				listCategorie.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("\nERREUR : Sur la sélection de toutes les données.\n\nMESSAGE : " + e);
		}
		return listCategorie;
	}

	@Override
	public List<Categorie> getCatalogue() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}

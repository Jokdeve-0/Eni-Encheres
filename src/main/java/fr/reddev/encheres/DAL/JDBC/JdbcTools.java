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
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import fr.reddev.encheres.Exception.DALException;

public class JdbcTools {
	private static Connection connection;

	public static Connection getConnection() throws DALException  {
		if (connection == null) {
			try {
				connection = ((DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool_cnx")).getConnection();
			} catch (Exception e) {
//				e.printStackTrace();
				throw new DALException("ERROR : JdbcTools - getConnection()\n"
					+ e.getLocalizedMessage() + "\n");
			}
		}
		return connection;
	}
}

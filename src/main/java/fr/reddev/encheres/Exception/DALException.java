/**
 * Project ENI-ENCHERES
 * CDA TEAMS REDDEV
 * VERSION REFACTOR BY JOKDEVE-LOOPER
 */
package fr.reddev.encheres.Exception;

/**
 * @author JOKDEVE-LOOPER
 *
 */
public class DALException extends Exception {

	private static final long serialVersionUID = 1L;

	public DALException(String message) {
		super(message);
	}

	@Override
	public String toString() {
	    return ""
	    	+ "##########################################################\n"
	    	+ "DALEXCEPTION [" + getLocalizedMessage() + "]\n"
	    	+ "##########################################################\n";
	}

}

package fr.reddev.encheres.Exception;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;
	public DALException() {
		super();
	}
	public DALException(String message) {
		super(message);
	}
	@Override
	public String toString() {
		return "Couche DAL => DALException : \n" + super.getMessage();
	}
}
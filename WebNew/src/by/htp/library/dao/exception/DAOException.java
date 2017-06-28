package by.htp.library.dao.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 8797956540255244445L;
	public DAOException(){
		super();
	}
	public DAOException (String message){
		super (message);
	}
	public DAOException (Exception e){
		super (e);
	}
	public DAOException (String message, Exception e){
		super (message,e);
	}
}



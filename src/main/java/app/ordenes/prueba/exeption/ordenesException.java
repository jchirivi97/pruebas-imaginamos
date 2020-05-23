package app.ordenes.prueba.exeption;

public class ordenesException extends Exception{
	
	
	public static final String USER_NO_FOUND = "usuario no existe";
	public static final String USER_EXIST = "usuario ya existe";
	
	
	public ordenesException() {
		super();
	}
	
	public 	ordenesException (String message) {
		super(message);
	}
}

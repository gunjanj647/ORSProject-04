package in.co.rays.proj4.exception;


/**
 * Database Exception that occurs when there is 
 * exception in SQL syntax and other exceptions.
 * 
 * @authorGunjan jain
 * @version 1.0
 */
public class DatabaseException extends Exception {
	
	public DatabaseException(String msg) {
		super(msg);
	}
}

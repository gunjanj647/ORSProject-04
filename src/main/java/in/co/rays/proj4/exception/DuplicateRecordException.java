package in.co.rays.proj4.exception;


/**
 * Duplicate Record Exception occurs when 
 * duplicate record is being inserted into database.
 * 
 * @authorGunjan jain
 * @version 1.0
 */
public class DuplicateRecordException extends Exception {
	
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}

package propertytycoongame;

public class DuplicateException extends Exception{
	public DuplicateException(String message) {
		// TODO Auto-generated constructor stub
		super("Duplicate" + message);
	}
}

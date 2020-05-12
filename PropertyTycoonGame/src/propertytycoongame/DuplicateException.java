package propertytycoongame;

/**
 * DuplicateException
 * .
 * 
 * Documented by Haotian Jiao
 * 
 * @author Haotian Jiao
 */
public class DuplicateException extends Exception {

    /**
     * Constructor for DuplicateException.
     *
     * @param message A message that need to be thrown
     */
    public DuplicateException(String message) {
        super("Duplicate" + message);
    }
}

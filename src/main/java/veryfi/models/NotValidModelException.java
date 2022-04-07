package veryfi.models;

/**
 * Not Valid Model Exception
 */
public class NotValidModelException extends Exception {

    /**
     * When model is not valid convert to Json throws this exception.
     * @param errorMessage error when model is not valid
     */
    public NotValidModelException(String errorMessage) {
        super(errorMessage);
    }
}

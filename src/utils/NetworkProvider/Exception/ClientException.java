package utils.NetworkProvider.Exception;

/**
 * Class ClientException
 */
public class ClientException extends Exception{
    /**
     * @param errorMessage
     * @param error
     */
    public ClientException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}

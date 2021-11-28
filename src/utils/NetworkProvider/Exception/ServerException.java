package utils.NetworkProvider.Exception;

/**
 * Class ServerException
 */
public class ServerException extends Exception {

    /**
     * @param errorMessage
     * @param error
     */
    public ServerException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}

package utils.NetworkProvider;

import java.io.IOException;

import utils.NetworkProvider.Exception.ServerException;
import utils.NetworkProvider.Model.ConnectionConfig;
import utils.NetworkProvider.Model.Direction;

/**
 * Interface ServerInterface
 */
public interface ServerInterface {
    /**
     * @param connectionConfig
     * @throws ServerException
     */
    public void hello(ConnectionConfig connectionConfig) throws ServerException;

    /**
     * @param username
     * @throws ServerException
     */
    public void login(String username) throws ServerException;

    /**
     * @param direction
     * @throws ServerException
     */
    public void move(Direction direction) throws ServerException;

    /**
     * @throws ServerException
     */
    public void exit() throws ServerException;
}

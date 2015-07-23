package io.leaf.service;

/**
 * Date: 2015.07.23.
 * Time: 21:37
 *
 * @author Andras Toth
 */
public class ServiceInitializationException extends Exception {

    public ServiceInitializationException() {
        super();
    }

    public ServiceInitializationException(String message) {
        super(message);
    }

    public ServiceInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceInitializationException(Throwable cause) {
        super(cause);
    }
}

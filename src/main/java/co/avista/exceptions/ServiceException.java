package co.avista.exceptions;

public class ServiceException extends Exception {

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }
}

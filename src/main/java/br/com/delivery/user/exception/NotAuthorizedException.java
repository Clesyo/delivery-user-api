package br.com.delivery.user.exception;

public class NotAuthorizedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}

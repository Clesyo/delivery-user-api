package br.com.delivery.user.exception;

public class InvalidUserException  extends RuntimeException {

	private static final long serialVersionUID = -4469124915364232189L;

	private String field;

	public InvalidUserException() {
		super();
	}

	public InvalidUserException(String field, String message) {
		super(message);
		this.field = field;
	}

	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}

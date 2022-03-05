package br.com.delivery.user.exception;

public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = -4469124915364232189L;

	private String field;

	public InvalidException() {
		super();
	}

	public InvalidException(String field, String message) {
		super(message);
		this.field = field;
	}

	public InvalidException(String message) {
		super(message);
	}

	public InvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}

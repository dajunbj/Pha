package src.web.exctption;

public class PhaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhaException() {
	}

	public PhaException(String message) {
		super(message);
	}

	public PhaException(String message, Throwable cause) {
		super(message, cause);
	}
}

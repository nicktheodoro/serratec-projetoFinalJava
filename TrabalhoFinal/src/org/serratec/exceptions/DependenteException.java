package org.serratec.exceptions;

public class DependenteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7519336446107692072L;

	public DependenteException() {
		super("Dependente não pode ter mais do que 18 anos.");
	}
}
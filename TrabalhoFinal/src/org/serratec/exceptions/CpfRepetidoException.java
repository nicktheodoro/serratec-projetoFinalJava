package org.serratec.exceptions;

public class CpfRepetidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7826809138936049087L;

	public CpfRepetidoException() {
		super("Este CPF j� foi cadastrado no sistema.");
	}
}

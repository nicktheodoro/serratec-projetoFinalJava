package org.serratec.excecoes;

public class DependenteException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7519336446107692072L;

	public DependenteException() {
        super("Todo dependente tem que ser menor que 18 anos.");
    }
}
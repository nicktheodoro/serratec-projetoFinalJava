package org.serratec.excecoes;

public class CpfRepetidoException extends Exception {
	public CpfRepetidoException() {
		super("Este CPF já foi cadastrado no sistema.");
	}
}

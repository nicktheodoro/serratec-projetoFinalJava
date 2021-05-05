package org.serratec.entidades;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.serratec.excecoes.CpfRepetidoException;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;
	protected static List<String> cpfsCadastrados = new ArrayList<String>();

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) throws CpfRepetidoException {
		this.nome = nome;
		this.dataNascimento = dataNascimento;

		if (cpfsCadastrados.contains(cpf)) {
			throw new CpfRepetidoException();
		} else {
			this.cpf = cpf;
			Pessoa.cpfsCadastrados.add(cpf);
		}
	}
}
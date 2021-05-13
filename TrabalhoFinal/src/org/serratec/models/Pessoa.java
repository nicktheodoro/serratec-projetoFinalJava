package org.serratec.models;

import java.time.LocalDate;
import java.util.List;

import org.serratec.exceptions.CpfException;

import java.util.ArrayList;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;
	protected static List<String> cpfsCadastrados = new ArrayList<String>();

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) throws CpfException {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		
		if(cpf.length() != 11) {
			throw new CpfException("O CPF deve possuir apenas algarismos e ter 11 digitos");
		}

		if (cpfsCadastrados.contains(cpf)) {
			throw new CpfException("Este CPF já foi cadastrado no sistema.");
		} else {
			this.cpf = cpf;
			Pessoa.cpfsCadastrados.add(cpf);
		}
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	
}
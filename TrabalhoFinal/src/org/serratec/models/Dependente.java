package org.serratec.models;

import java.time.LocalDate;
import java.time.Period;

import org.serratec.enums.TipoParentesco;
import org.serratec.exceptions.CpfRepetidoException;
import org.serratec.exceptions.DependenteException;

public class Dependente extends Pessoa {
	private TipoParentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, TipoParentesco parentesco)
			throws CpfRepetidoException, DependenteException {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;

		if (this.isDependenteMaiorDezoito(dataNascimento)) {
			throw new DependenteException();
		}

	}

	private int definirIdade(LocalDate dataNascimento) {
		LocalDate hoje = LocalDate.now();

		return Period.between(dataNascimento, hoje).getYears();
	}

	private boolean isDependenteMaiorDezoito(LocalDate dataNascimento) {
		return this.definirIdade(dataNascimento) > 17;
	}

	public TipoParentesco getParentesco() {
		return parentesco;
	}
}
package org.serratec.entidades;

import java.time.LocalDate;

import org.serratec.excecoes.CpfRepetidoException;
import org.serratec.excecoes.DependenteException;

public class Dependente extends Pessoa {
	private TipoParentesco parentesco;
	private int idade;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, TipoParentesco parentesco)
			throws CpfRepetidoException, DependenteException {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;

		if (isDependentUnderEighteenYearsOld(dataNascimento)) {
			this.idade = defineIdade(dataNascimento);
		}
	}

	private int defineIdade(LocalDate dataNascimento) {
		LocalDate hoje = LocalDate.now();
		int idade = hoje.getYear() - dataNascimento.getYear();

		if (hoje.getMonthValue() < dataNascimento.getMonthValue()) {
			idade--;
		} else {
			if (hoje.getMonthValue() == dataNascimento.getMonthValue()
					&& hoje.getDayOfMonth() < dataNascimento.getDayOfMonth()) {
				idade--;
			}
		}

		return idade;
	}

	private boolean isDependentUnderEighteenYearsOld(LocalDate dataNascimento) throws DependenteException {
		if (this.defineIdade(dataNascimento) > 18) {
			throw new DependenteException();
		}

		return true;
	}
}
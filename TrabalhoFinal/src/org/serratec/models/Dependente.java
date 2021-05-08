package org.serratec.models;

import java.time.LocalDate;

import org.serratec.enums.TipoParentesco;
import org.serratec.exceptions.CpfRepetidoException;
import org.serratec.exceptions.DependenteException;

public class Dependente extends Pessoa {
	private TipoParentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, TipoParentesco parentesco)
			throws CpfRepetidoException, DependenteException {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		
		if(isDependenteMaiorDezoito(dataNascimento)) {
			throw new DependenteException();
		}
	}
	
	private int defineIdade(LocalDate dataNascimento) {
		LocalDate hoje = LocalDate.now();
		int idade = hoje.getYear() - dataNascimento.getYear();
		
		if(hoje.getMonthValue() < dataNascimento.getMonthValue()) {
			idade--;
		} else {
			if(hoje.getMonthValue() == dataNascimento.getMonthValue()
				&& hoje.getDayOfMonth() < dataNascimento.getDayOfMonth()) {
					idade--;
			}
		}	
		return idade;
	}
	
	private boolean isDependenteMaiorDezoito(LocalDate dataNascimento) {
		return this.defineIdade(dataNascimento) > 18;
	}

	public TipoParentesco getParentesco() {
		return parentesco;
	}
}
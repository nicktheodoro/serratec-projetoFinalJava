package org.serratec.entidades;

import java.time.LocalDate;

import org.serratec.enums.TipoParentesco;
import org.serratec.excecoes.CpfRepetidoException;
import org.serratec.excecoes.DependenteException;

public class Dependente extends Pessoa {
	private TipoParentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, TipoParentesco parentesco)
			throws CpfRepetidoException, DependenteException {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		
		if(isDependentOverEighteenYearsOld(dataNascimento)) {
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
	
	private boolean isDependentOverEighteenYearsOld(LocalDate dataNascimento) {
		return this.defineIdade(dataNascimento) > 18;
	}
}
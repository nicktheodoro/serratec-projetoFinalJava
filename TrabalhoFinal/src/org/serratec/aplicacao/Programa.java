package org.serratec.aplicacao;

import java.time.LocalDate;

import org.serratec.entidades.Dependente;
import org.serratec.entidades.Funcionario;
import org.serratec.entidades.TipoParentesco;
import org.serratec.excecoes.CpfRepetidoException;
import org.serratec.excecoes.DependenteException;

public class Programa {

	public static void main(String[] args) throws CpfRepetidoException, DependenteException {
		Dependente dependente1 = new Dependente("Mateus", "22345678990", LocalDate.of(2021, 06, 26), TipoParentesco.FILHO);
		Dependente dependente2 = new Dependente("Mateus2", "32345678990", LocalDate.of(2020, 06, 26), TipoParentesco.SOBRINHO);
		Dependente dependente3 = new Dependente("Mateus2", "42345678990", LocalDate.of(2020, 06, 26), TipoParentesco.SOBRINHO);
		
		Dependente[] dependentesDoLucas = { dependente1, dependente2, dependente3 };
		 
		Funcionario funcionario1 = new Funcionario("Lucas Cruz", "12345678990", LocalDate.of(1997, 06, 26), 4300, dependentesDoLucas);
		
		funcionario1.calculaDescontoInss();
		funcionario1.calculaDeducaoDependentes();
		funcionario1.calculaDescontoIR();
		funcionario1.calculaSalarioLiquido();
		
		System.out.println(funcionario1.getSalarioLiquido());
	}

}
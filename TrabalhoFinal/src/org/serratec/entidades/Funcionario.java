package org.serratec.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.services.Calculos;

public class Funcionario extends Calculos {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;
	protected static List<String> cpfsCadastrados = new ArrayList<String>();
	
	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto,
			Dependente[] dependentes){
		
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;
	}

	public Funcionario(double salarioBruto, double descontoInss, double descontoIR, double salarioLiquido,
			Dependente[] dependentes, double deducaoDependentes) {
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
		this.dependentes = dependentes;
		this.deducaoDependentes = deducaoDependentes;
	}

	
}

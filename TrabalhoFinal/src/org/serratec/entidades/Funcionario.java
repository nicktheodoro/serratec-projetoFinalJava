package org.serratec.entidades;

import java.time.LocalDate;

import org.serratec.services.CalculadoraFinanceira;
import org.serratec.excecoes.CpfRepetidoException;
import java.util.List;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private List<Dependente> dependentes;
	CalculadoraFinanceira calculadora = new CalculadoraFinanceira();;
	private double salarioLiquido;
	private double descontoInss;
	private double descontoIr;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto,
			List<Dependente> dependentes) throws CpfRepetidoException {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;
	}
	
	public void calcularSalarioLiquido() {
		this.salarioLiquido = calculadora.calculaSalarioLiquido(this.salarioBruto, this.dependentes);
		this.descontoInss = calculadora.getDescontoInss();
		this.descontoIr = calculadora.getDescontoIR();
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public double getDescontoIr() {
		return descontoIr;
	}

	@Override
	public String toString() {
		return "Nome: " + nome 
				+ "\nCPF: " + cpf 
				+ "\nINSS: " + String.format("%.2f", descontoInss) 
				+ "\nIR: " + String.format("%.2f", descontoIr) 
				+ "\nSalário Líquido: " + String.format("%.2f", salarioLiquido)
				+ "\nDedução Dependetes: " + String.format("%.2f", calculadora.getDeducaoDependentes())
				+ "\n";
	}
}
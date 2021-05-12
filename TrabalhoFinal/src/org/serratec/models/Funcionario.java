package org.serratec.models;

import java.time.LocalDate;

import org.serratec.exceptions.CpfRepetidoException;
import org.serratec.services.CalculadoraFinanceira;

import java.util.List;

public class Funcionario extends Pessoa {
	CalculadoraFinanceira calculadora = new CalculadoraFinanceira();
	private List<Dependente> dependentes;
	private double salarioBruto;
	private double salarioLiquido;
	private double descontoInss;
	private double descontoIr;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto,
			List<Dependente> dependentes) throws CpfRepetidoException {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;
	}

	public void calcularFinancas() {
		this.salarioLiquido = calculadora.calcularSalarioLiquido(this.salarioBruto, this.dependentes);
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
		return this.nome + ";" + this.cpf + ";" + String.format("%.2f", this.descontoInss) + ";"
				+ String.format("%.2f", this.descontoIr) + ";" + String.format("%.2f", this.salarioLiquido);
	}
}
package org.serratec.services;

import java.util.List;

import org.serratec.entidades.Dependente;

public class CalculadoraFinanceira {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	List<Dependente> dependentes;
	private double deducaoDependentes;

	public double calculaSalarioLiquido(double salarioBruto, List<Dependente> dependentes) {
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;
	
		calculaDescontoInss();
		calculaDeducaoDependentes();
		calculaDescontoIR();

		return (this.salarioBruto - this.descontoInss - this.descontoIR);
	}

	private double calculaDescontoInss() {
		if (this.salarioBruto <= 1100) {
			return descontoInss = this.salarioBruto * 7.5 / 100;
		} else if (this.salarioBruto <= 2203.48) {
			return descontoInss = (this.salarioBruto * 9 / 100) - 16.5;
		} else if (this.salarioBruto <= 3305.22) {
			return descontoInss = (this.salarioBruto * 12 / 100) - 82.61;
		} else if (this.salarioBruto <= 6433.57) {
			return descontoInss = (this.salarioBruto * 14 / 100) - 148.72;
		} else {
			return descontoInss = 751.97;
		}
	}

	private double calculaDeducaoDependentes() {
		return this.deducaoDependentes = dependentes.size() * 189.59;
	}

	private double calculaDescontoIR() {
		double descontoIR = this.salarioBruto - this.deducaoDependentes - this.descontoInss;

		if (descontoIR <= 1903.98) {
			return this.descontoIR;
		} else if (descontoIR <= 2826.65) {
			return this.descontoIR = (descontoIR * 7.5 / 100) - 142.80;
		} else if (descontoIR <= 3751.05) {
			return this.descontoIR = (descontoIR * 15 / 100) - 354.80;
		} else if (descontoIR <= 4664.68) {
			return this.descontoIR = (descontoIR * 22.5 / 100) - 636.13;
		} else {
			return this.descontoIR = (descontoIR * 27.5 / 100) - 869.36;
		}
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public double getDeducaoDependentes() {
		return deducaoDependentes;
	}
	
	
}

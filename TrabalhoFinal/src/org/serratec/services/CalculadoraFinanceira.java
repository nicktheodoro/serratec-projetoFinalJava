package org.serratec.services;

import java.util.List;

import org.serratec.interfaces.ICalculadoraFinanceira;
import org.serratec.models.Dependente;

public class CalculadoraFinanceira extends DadosAliquota implements ICalculadoraFinanceira {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private List<Dependente> dependentes;
	private double deducaoDependentes;

	public double calcularSalarioLiquido(double salarioBruto, List<Dependente> dependentes) {
		this.salarioBruto = salarioBruto;
		this.dependentes = dependentes;

		this.calcularDescontoInss();
		this.calcularDeducaoDependentes();
		this.calcularDescontoIR();

		return (this.salarioBruto - this.descontoInss - this.descontoIR);
	}

	public double calcularDescontoInss() {
		if (this.salarioBruto <= ALIQUOTA_INSS_ISENTO) {
			return descontoInss = this.salarioBruto * 7.5 / 100;
		} else if (this.salarioBruto <= ALIQUOTA_INSS_TAXA_1) {
			return descontoInss = (this.salarioBruto * 9 / 100) - 16.5;
		} else if (this.salarioBruto <= ALIQUOTA_INSS_TAXA_2) {
			return descontoInss = (this.salarioBruto * 12 / 100) - 82.61;
		} else if (this.salarioBruto <= ALIQUOTA_INSS_TAXA_3) {
			return descontoInss = (this.salarioBruto * 14 / 100) - 148.72;
		} else {
			return descontoInss = ALIQUOTA_INSS_TAXA_4;
		}
	}

	public double calcularDescontoIR() {
		double descontoIR = this.salarioBruto - this.deducaoDependentes - this.descontoInss;

		if (descontoIR <= ALIQUOTA_IR_ISENTO) {
			return this.descontoIR;
		} else if (descontoIR <= ALIQUOTA_IR_TAXA_1) {
			return this.descontoIR = (descontoIR * 7.5 / 100) - 142.80;
		} else if (descontoIR <= ALIQUOTA_IR_TAXA_2) {
			return this.descontoIR = (descontoIR * 15 / 100) - 354.80;
		} else if (descontoIR <= ALIQUOTA_IR_TAXA_3) {
			return this.descontoIR = (descontoIR * 22.5 / 100) - 636.13;
		} else {
			return this.descontoIR = (descontoIR * 27.5 / 100) - 869.36;
		}
	}

	public double calcularDeducaoDependentes() {
		return this.deducaoDependentes = this.dependentes.size() * 189.59;
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

package org.serratec.interfaces;

import java.util.List;

import org.serratec.models.Dependente;

public interface ICalculadoraFinanceira {

	double calcularSalarioLiquido(double salarioBruto, List<Dependente> dependentes);

	double calcularDescontoInss();

	double calcularDeducaoDependentes();

	double calcularDescontoIR();
}

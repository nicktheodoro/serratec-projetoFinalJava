package org.serratec.app;

import org.serratec.models.Funcionario;
import org.serratec.services.Leitor;

public class Programa {
	public static void main(String[] args) {
		String caminhoArquivo = "//home//devcinzento//Downloads//entrada.csv";
		Leitor leitura = new Leitor(caminhoArquivo);

		for (Funcionario funcionario : leitura.getFuncionarios()) {
			funcionario.calcularSalarioLiquido();
			System.out.println(funcionario);
		}
	}
}
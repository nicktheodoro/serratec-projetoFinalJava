package org.serratec.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.serratec.models.Funcionario;

public class Escritora {
	private final String CAMINHO_DO_ARQUIVO = "//home//devcinzento//Downloads//entrada.csv";
	private final String NOME_DO_ARQUIVO = "saida";
	private Leitora entrada;

	public Escritora(Leitora entrada) {
		this.entrada = entrada;
	}

	public void escreveCsv() throws IOException {

		FileWriter csvWriter = new FileWriter(CAMINHO_DO_ARQUIVO + NOME_DO_ARQUIVO + ".csv");

		for (Funcionario funcionario : entrada.getFuncionarios()) {
			String[] atributos;
			List<String> linha;

			funcionario.calcularSalarioLiquido();

			atributos = funcionario.toString().split(";");
			linha = Arrays.asList(atributos[0], atributos[1], atributos[2], atributos[3], atributos[4]);

			csvWriter.append(String.join(";", linha));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}
}
package org.serratec.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.serratec.interfaces.IEscritora;
import org.serratec.models.Funcionario;

public class Escritora implements IEscritora {
	private final String CAMINHO_DO_ARQUIVO = "//home//devcinzento//Downloads//";
	private final String NOME_DO_ARQUIVO = "saida";
	private Leitora entrada;

	public Escritora(Leitora entrada) {
		this.entrada = entrada;
	}

	@Override
	public void escreveCsv() throws IOException {

		FileWriter csvFWriter = new FileWriter(CAMINHO_DO_ARQUIVO + NOME_DO_ARQUIVO + ".csv");
		BufferedWriter csvBWriter = new BufferedWriter(csvFWriter);

		for (Funcionario funcionario : entrada.getFuncionarios()) {

			String[] atributos;
			List<String> linha;

			funcionario.calcularSalarioLiquido();

			atributos = funcionario.toString().split(";");
			linha = Arrays.asList(atributos[0], atributos[1], atributos[2], atributos[3], atributos[4]);

			csvBWriter.append(String.join(";", linha));
			csvBWriter.append("\n");
		}

		csvBWriter.flush();
		csvBWriter.close();
	}
}
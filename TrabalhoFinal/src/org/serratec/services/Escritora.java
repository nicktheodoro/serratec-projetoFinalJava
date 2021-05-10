package org.serratec.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.serratec.interfaces.IEscritora;
import org.serratec.models.Funcionario;

public class Escritora implements IEscritora, AutoCloseable {
	private final String CAMINHO_DO_ARQUIVO = "files" + File.separator;
	private final String NOME_DO_ARQUIVO_SAIDA = "saida.csv";
	private Leitora entrada;
	private FileWriter csvFWriter;
	private BufferedWriter csvBWriter;

	public Escritora(Leitora entrada) {
		this.entrada = entrada;
	}

	public void escreverCsv() {

		try {
			this.csvFWriter = new FileWriter(CAMINHO_DO_ARQUIVO + NOME_DO_ARQUIVO_SAIDA);
			this.csvBWriter = new BufferedWriter(csvFWriter);

			for (Funcionario funcionario : entrada.getFuncionarios()) {
				this.gerarCsv(funcionario);
			}
		} catch (IOException e) {
			System.out.println("Falha ao escrever arquivo.");
		}
	}
	
	private void gerarCsv(Funcionario funcionario) throws IOException {
		String[] atributos;
		List<String> linha;

		funcionario.calcularSalarioLiquido();

		atributos = funcionario.toString().split(";");
		linha = Arrays.asList(atributos[0], atributos[1], atributos[2], atributos[3], atributos[4]);

		this.csvBWriter.append(String.join(";", linha));
		this.csvBWriter.append("\n");
	}

	@Override
	public void close() {
		try {
			this.csvBWriter.flush();
			this.csvBWriter.close();
		} catch (IOException e) {
			System.out.println("Falha ao fechar leitora.");
		}
	}
}
package org.serratec.app;

import java.io.File;
import java.util.Scanner;

import org.serratec.services.Escritora;
import org.serratec.services.Leitora;

public class Programa {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Ol�!\n\nAs entradas e sa�das deste " + "programa s�o direcionadas � pasta 'files'\n"
				+ "que pode ser encontrada dentro do diret�rio do projeto."
				+ "\n\nPara come�ar, mova um arquivo .csv para essa pasta " + "e entre com os dados abaixo.\n");

		System.out.print("1. Nome do arquivo de entrada" + " (ex.: entrada): ");
		String nomeEntrada = input.nextLine();

		System.out.print("2. Nome que o arquivo de sa�da " + "ter� (ex.: saida): ");
		String nomeSaida = input.nextLine();
		
		input.close();

		System.out.println("\n");

		final String CAMINHO_ARQUIVO_ENTRADA = "files" + File.separator + nomeEntrada + ".csv";
		final String CAMINHO_ARQUIVO_SAIDA = "files" + File.separator + nomeSaida + ".csv";

		try (
				Leitora leitura = new Leitora(CAMINHO_ARQUIVO_ENTRADA);
				Escritora escrita = new Escritora(leitura, CAMINHO_ARQUIVO_SAIDA)
		) {
			escrita.escreverCsv();
			System.out.println("Arquivo gerado!");
		}
	}
}

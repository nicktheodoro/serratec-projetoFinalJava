package org.serratec.app;

import java.io.File;

import org.serratec.services.Escritora;
import org.serratec.services.Leitora;

public class Programa {
	public static void main(String[] args) {
		final String CAMINHO_DO_ARQUIVO = "files" + File.separator + "entrada.csv";

		try(
			Leitora leitura = new Leitora(CAMINHO_DO_ARQUIVO);
			Escritora escrita = new Escritora(leitura)
			) {
			escrita.escreverCsv();
			System.out.println("Arquivo gerado!");
		}
	}
}
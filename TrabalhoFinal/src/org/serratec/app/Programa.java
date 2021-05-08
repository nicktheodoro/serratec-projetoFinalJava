package org.serratec.app;

import java.io.IOException;

import org.serratec.services.Escritora;
import org.serratec.services.Leitora;

public class Programa {
	public static void main(String[] args) {
		final String CAMINHO_DO_ARQUIVO = "//home//devcinzento//Downloads//entrada.csv";
		
		Leitora leitura = new Leitora(CAMINHO_DO_ARQUIVO);
		Escritora escrita = new Escritora(leitura);	
		
		try {
			escrita.escreveCsv();
			System.out.println("Arquivo gerado!");
		} catch (IOException e) {
			System.out.println("Erro na gravação do arquivo.");
			e.printStackTrace();
		}
	}
}
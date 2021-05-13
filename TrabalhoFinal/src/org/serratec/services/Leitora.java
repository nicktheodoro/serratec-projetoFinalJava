package org.serratec.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.enums.TipoParentesco;
import org.serratec.exceptions.CpfException;
import org.serratec.exceptions.DependenteException;
import org.serratec.interfaces.ILeitora;
import org.serratec.models.Dependente;
import org.serratec.models.Funcionario;

public class Leitora implements ILeitora, AutoCloseable {

	private final String filho = "FILHO";
	private final String sobrinho = "SOBRINHO";
	private final String outros = "OUTROS";

	private String caminhoArquivo;
	private FileReader arquivo;
	private BufferedReader lerArquivo;
	private List<Funcionario> funcionarios;
	private TipoParentesco tipo = null;

	public Leitora(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		this.iniciarLeitora();
	}

	public void iniciarLeitora() {
		try {
			this.arquivo = new FileReader(this.caminhoArquivo);
			this.lerArquivo = new BufferedReader(this.arquivo);

			this.funcionarios = lerCsv();

			this.arquivo.close();
			this.lerArquivo.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo informado n√£o foi encontrado.");
			e.getMessage();
		} catch (IOException e) {
			System.out.println("Formato errado de arquivo.");
			e.getMessage();
		} catch (NumberFormatException e) {
			System.out.println("O formato do arquivo est· incorreto");
			e.printStackTrace();
		} catch (CpfException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public List<Funcionario> lerCsv()
			throws IOException, NumberFormatException, CpfException, DependenteException {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		List<Dependente> dependentesDeFuncionario = new ArrayList<Dependente>();
		Funcionario funcionario = null;

		for (String linha = this.lerArquivo.readLine(); linha != null; linha = this.lerArquivo.readLine()) {
			String[] campo;

			if (linha.isEmpty()) {
				funcionarios.add(funcionario);
				funcionario = null;
				dependentesDeFuncionario = new ArrayList<Dependente>();
				continue;
			}

			campo = linha.split(";");
			
			this.obterParentesco(campo[3]);

			if (this.isFuncionario(campo)) {
				funcionario = new Funcionario(campo[0], campo[1], this.obterDataNascimento(campo[2]),
						Double.parseDouble(campo[3]), dependentesDeFuncionario);
			} else {
				dependentesDeFuncionario
						.add(new Dependente(campo[0], campo[1], this.obterDataNascimento(campo[2]), tipo));
			}
		}

		if(funcionario != null) {
			funcionarios.add(funcionario);
		}

		return funcionarios;
	}

	private boolean isFuncionario(String[] campo) {
		return !(campo[3].equals(filho) || campo[3].equals(sobrinho) || campo[3].equals(outros));
	}

	private LocalDate obterDataNascimento(String campo) {
		int ano = Integer.parseInt(campo.substring(0, 4));
		int mes = Integer.parseInt(campo.substring(4, 6));
		int dia = Integer.parseInt(campo.substring(6, 8));

		return LocalDate.of(ano, mes, dia);
	}

	private void obterParentesco(String campo) {
		switch (campo) {
		case filho:
			tipo = TipoParentesco.FILHO;
			break;
		case sobrinho:
			tipo = TipoParentesco.SOBRINHO;
			break;
		case outros:
			tipo = TipoParentesco.OUTROS;
			break;
		default:
			break;
		}
	}

	@Override
	public void close() {
		try {
			this.lerArquivo.close();
		} catch (IOException e) {
			System.out.println("Falha ao fechar leitora");
		}
	}
}

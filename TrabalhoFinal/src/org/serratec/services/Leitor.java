package org.serratec.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.entidades.Dependente;
import org.serratec.entidades.Funcionario;
import org.serratec.enums.TipoParentesco;
import org.serratec.excecoes.CpfRepetidoException;
import org.serratec.excecoes.DependenteException;

public class Leitor {
	private String caminhoArquivo;
	private FileReader arquivo;
	private static BufferedReader lerArquivo;
	private List<Funcionario> funcionarios;

	public Leitor(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;

		try {
			this.arquivo = new FileReader(this.caminhoArquivo);
			Leitor.lerArquivo = new BufferedReader(this.arquivo);

			this.funcionarios = lerCsv();

			this.arquivo.close();
			Leitor.lerArquivo.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo informado não foi encontrado.");
			e.getMessage();
		} catch (IOException e) {
			System.out.println("Formato errado de arquivo.");
			e.getMessage();
		} catch (NumberFormatException e) {
			System.out.println("O formato da data está incorreto.");
			e.printStackTrace();
		} catch (CpfRepetidoException e) {
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

	private List<Funcionario> lerCsv()
			throws IOException, NumberFormatException, CpfRepetidoException, DependenteException {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		List<Dependente> dependentesDeFuncionario = new ArrayList<Dependente>();
		Funcionario funcionario = null;
		Dependente dependente = null;
		TipoParentesco tipo = null;

		for (String linha = Leitor.lerArquivo.readLine(); linha != null; linha = Leitor.lerArquivo.readLine()) {
			String[] campo;
			int ano, mes, dia;

			if (linha.equals("")) {
				funcionarios.add(funcionario);
				dependentesDeFuncionario = new ArrayList<Dependente>();
				continue;
			}

			campo = linha.split(";");
			ano = Integer.parseInt(campo[2].substring(0, 4));
			mes = Integer.parseInt(campo[2].substring(4, 6));
			dia = Integer.parseInt(campo[2].substring(6, 8));

			switch (campo[3]) {
			case "FILHO":
				tipo = TipoParentesco.FILHO;
				break;
			case "SOBRINHO":
				tipo = TipoParentesco.SOBRINHO;
				break;
			case "OUTROS":
				tipo = TipoParentesco.OUTROS;
				break;
			default:
				break;
			}

			if (!(campo[3].equals("FILHO") || campo[3].equals("SOBRINHO") || campo[3].equals("OUTROS"))) {
				funcionario = new Funcionario(campo[0], campo[1], LocalDate.of(ano, mes, dia),
						Double.parseDouble(campo[3]), dependentesDeFuncionario);
			} else {
				dependente = new Dependente(campo[0], campo[1], LocalDate.of(ano, mes, dia), tipo);
				dependentesDeFuncionario.add(dependente);
			}
		}

		// adiciona último funcionario à lista de funcionarios
		funcionarios.add(funcionario);

		return funcionarios;
	}
}
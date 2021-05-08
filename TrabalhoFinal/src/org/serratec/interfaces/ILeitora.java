package org.serratec.interfaces;

import java.io.IOException;
import java.util.List;

import org.serratec.exceptions.CpfRepetidoException;
import org.serratec.exceptions.DependenteException;
import org.serratec.models.Funcionario;

public interface ILeitora {
	List<Funcionario> lerCsv() throws IOException, NumberFormatException, CpfRepetidoException, DependenteException;
}

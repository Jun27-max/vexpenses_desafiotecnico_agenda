package vexpenses_desafiotecnico_agenda.controller;

import java.util.ArrayList;
import java.util.Optional;

import vexpenses_desafiotecnico_agenda.model.Contato;

public class GerenciadorContatos {

	private ArrayList<Contato> contatos;
	private static int prox = 0;

	public GerenciadorContatos() {
		contatos = new ArrayList<>();
	}

	public boolean inserir(Contato contato) {
		if (contato != null) {
			contato.setCodigo(++prox);
			contatos.add(contato);
			return true;
		}
		return false;
	}

	// Retorna os contatos inseridos
	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	public boolean remover(int codigo) {
		Optional<Contato> contato = buscarPeloCodigo(codigo);// Encapsulando o retorno
		if (contato.isPresent()) {
			contatos.remove(contato.get());// Remove o índice especificado
			return true;
		}
		return false;
	}

	public Optional<Contato> buscarPeloCodigo(int codigo) {
		Contato contato = null;
		for (Contato elemento : contatos) {
			if (elemento.getCodigo() == codigo) {// Retorna o elemento do índice especificado
				contato = elemento;
			}
		}
		return Optional.ofNullable(contato);// Se o valor estiver presente, retorna Optional com o valor, senão retorna
											// vazio
	}

	public boolean editar(Contato contato) {
		if (contatos.contains(contato)) {// verifica se existe contato
			contatos.set(contatos.indexOf(contato), contato);
			return true;
		}
		return false;
	}

}

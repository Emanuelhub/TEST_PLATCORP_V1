package br.com.api.exceptions;

public class ClienteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1398075499727159749L;

	public ClienteNotFoundException(Long id) {
		super("Não foi encontrado Cliente com id " + id);
	}
}

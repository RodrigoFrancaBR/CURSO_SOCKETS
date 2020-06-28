package br.com.franca;

import java.io.IOException;
import java.net.Socket;

public class TestaServidorSocket {
	public static void main(String[] args) {
		ServidorSocket servidor = new ServidorSocket();
		System.out.println("Aguardando conexão...");
		try {
			servidor.criarServidorSocket(12345);
			Socket socket = servidor.aguardarConexao();
			System.out.println("Cliente conectado.");
			servidor.tratarConexao(socket);
			System.out.println("Cliente finalizado.");
		} catch (IOException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		}
	}
}

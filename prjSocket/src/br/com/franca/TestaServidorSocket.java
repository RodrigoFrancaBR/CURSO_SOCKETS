package br.com.franca;

import java.io.IOException;

public class TestaServidorSocket {
	
	public static void main(String[] args) {
		
		try {
			ServidorSocket servidor = new ServidorSocket(12345);
			servidor.tratarConexao(servidor.getServerSocket());
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		
		
		/*try {
			servidor.criarServidorSocket(12345);

			while (true) {
				Socket socket = servidor.aguardarConexao();
				System.out.println("Cliente conectado.");
				
				new TrataConexao(socket);

				System.out.println("Cliente finalizado.");
			}
		} catch (IOException e) {
			
		}*/
	}
}

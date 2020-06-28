package br.com.franca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	private ServerSocket serverSocket;

	public ServidorSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
		System.out.println("Servidor Socket criado na porta: " + porta + " com sucesso!");
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Socket aguardarConexao() throws IOException {
		System.out.println("Aguardando cliente se conectar");
		return serverSocket.accept();
	}

	public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}

	/*
	 * private void enviarMsg(Object o, ObjectOutputStream out) throws
	 * IOException { out.writeObject(o); out.flush(); }
	 */

	public void tratarConexao(ServerSocket serverSocket) throws IOException {
		System.out.println("Aguardando mensagem do cliente.");

		// The server do a loop here to accept all connection initiated by the
		// client application.
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				new TrataConexao(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * try {
		 * 
		 * ObjectOutputStream output = new
		 * ObjectOutputStream(socket.getOutputStream()); ObjectInputStream input
		 * = new ObjectInputStream(socket.getInputStream());
		 * 
		 * String msg = input.readUTF(); System.out.println(
		 * "Mensagem recebida: " + msg);
		 * 
		 * output.writeUTF("HELLO WORLD!");
		 * 
		 * output.flush(); input.close(); output.close();
		 * 
		 * } catch (IOException e) { System.out.println(
		 * "Problema no tratamento da conexão com o cliente: " +
		 * socket.getInetAddress()); System.out.println("Erro: " +
		 * e.getMessage()); throw e; } finally { fecharSocket(socket); }
		 */

	}

}

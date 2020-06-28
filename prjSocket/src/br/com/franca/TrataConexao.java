package br.com.franca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TrataConexao implements Runnable {

	private Socket socket;

	public TrataConexao(Socket socket) {
		this.socket = socket;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			// Read a message sent by client application
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			String mensagem = (String) entrada.readObject();
			System.out.println("Mensagem recebida com sucesso: " + mensagem);

			// Send a response information to the client application
			ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
			saida.writeObject("Olá cliente do ip: " + socket.getInetAddress() + " o servidor recebeu sua mensagem.");

			entrada.close();
			saida.close();
			socket.close();

			System.out.println("Aguardando um novo cliente");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}

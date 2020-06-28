package br.com.franca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente2 {
	private String host;
	private int porta;
	private static Socket socket;

	public Cliente2(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}	
	
	public void criarConexao() {
		
		System.out.println("Tentando criar uma conexao.");
		
		try {
			socket = new Socket(host, porta);
			System.out.println("Conexao criada com o host: " + host + ":" + porta + " com sucesso");
		} catch (IOException e) {
			System.out.println("Erro ao criar a conexao: " + e);
			Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public static void escreverMensagenParaServidor(ObjectOutputStream saida) {
		System.out.println("Tentando enviar mensagen parao servidor.");
		// String resultado ="Hello";
		try {
			saida.writeObject("Olá Servidor, Eu sou seu cliente2.");
			// output.writeUTF(resultado);
			saida.flush();
			System.out.println("Mensagen enviada com sucesso para o servidor.");
		} catch (IOException e) {
			System.out.println("Erro ao enviar mensagen para o servidor: " + e);
			Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public static String lerMensagemDoServidor(ObjectInputStream entrada) {		
		System.out.println("Tentando obter mensagen do servidor.");
		String mensagem = "Sem mensagem";
		try {
			mensagem =  (String) entrada.readObject();
			System.out.println("Mensagen obtida com sucesso do servidor.");			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Erro ao obter mensagen do servidor: " + e);
			Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, e);			
		}
		return mensagem;
	}
	
/*
	public void criarConexao() {
		System.out.println("Tentando criar uma conexao.");
		try {
			socket = new Socket(host, porta);
			System.out.println("Conexao criada com sucesso.");
		} catch (IOException e) {
			System.out.println("Erro ao criar a conexao: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static String lerMensagemDoServidor(ObjectInputStream input) {
		System.out.println("Tentando obter mensagen do servidor.");
		String mensagen = "sem mensagens";
		try {
			mensagen = input.readUTF();
		} catch (IOException e) {
			System.out.println("Erro ao obter mensagen do servidor: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
		System.out.println("Mensagen obtida com sucesso do servidor.");
		return mensagen;
	}

	public static void escreverMensagenParaServidor(ObjectOutputStream output) {
		System.out.println("Tentando enviar mensagen parao servidor.");
		String resultado ="Hello";
		try {
			// Scanner teclado = new Scanner(System.in);
			output.writeUTF(resultado);
			output.flush();

			System.out.println("Mensagen enviada com sucesso para o servidor.");
		} catch (IOException e) {
			System.out.println("Erro ao enviar mensagen para o servidor: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}*/

	public static void main(String[] args) {
		
		try {
	        // Create a connection to the server socket on the server application
			new Cliente2("localhost", 12345).criarConexao();
	        // InetAddress host = InetAddress.getLocalHost();
	        // Socket socket = new Socket(host.getHostName(), 7777);

	        // Send a message to the client application
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			
			
			Cliente2.escreverMensagenParaServidor(output);
	        // ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	        // oos.writeObject("Hello There...");

	        // Read and display the response message sent by server application
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	        // ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	        String mensagemDoServidor = Cliente2.lerMensagemDoServidor(input);
			// String message = (String) ois.readObject();
	        System.out.println(mensagemDoServidor);
	        input.close();
	        output.close();
	        // ois.close();
	        // oos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		/*new Cliente("localhost", 12345).criarConexao();

		String mensagenDoServidor = "sem mensagen";

		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
			
			Cliente.escreverMensagenParaServidor(output);
			
			mensagenDoServidor = Cliente.lerMensagemDoServidor(input);
			System.out.println(mensagenDoServidor);

			input.close();
			output.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Erro no método main: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}*/
	}
}

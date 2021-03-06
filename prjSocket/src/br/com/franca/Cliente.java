package br.com.franca;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	private String host;
	private int porta;
	private static Socket socket;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

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

	public static String lerMensagemDoServidor(InputStream input) {
		// public static String lerMensagemDoServidor(ObjectInputStream input) {
		System.out.println("Tentando obter mensagen do servidor.");
		String mensagen = "sem mensagens";
		try {
			Scanner s = new Scanner(input);
	        while (s.hasNextLine()) {
	            mensagen=s.nextLine();
	        }
			// mensagen = input.readUTF();
		// } catch (IOException e) {
		} catch (Exception e) {
			System.out.println("Erro ao obter mensagen do servidor: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
		System.out.println("Mensagen obtida com sucesso do servidor.");
		return mensagen;
	}

	public static void escreverMensagenParaServidor(OutputStream output) {
		// public static void escreverMensagenParaServidor(ObjectOutputStream output) {
		System.out.println("Tentando enviar mensagen parao servidor.");
		// String resultado ="Hello";
		try {
			Scanner teclado = new Scanner(System.in);
			 PrintStream saida = new PrintStream(output);
			// output.writeUTF(resultado);
			// output.flush();
			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());				
			}
			System.out.println("Mensagen enviada com sucesso para o servidor.");
		} catch (Exception e) {
		// } catch (IOException e) {
			System.out.println("Erro ao enviar mensagen para o servidor: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static void main(String[] args) {

		new Cliente("localhost", 12345).criarConexao();

		String mensagenDoServidor = "sem mensagen";

		try {
			// ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			// ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
							        		
			// Cliente.escreverMensagenParaServidor(output);
			Cliente.escreverMensagenParaServidor(socket.getOutputStream());
					
			// mensagenDoServidor = Cliente.lerMensagemDoServidor(input);
			mensagenDoServidor = Cliente.lerMensagemDoServidor(socket.getInputStream());
			System.out.println(mensagenDoServidor);
			
			// input.close();
			// output.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Erro no m�todo main: " + e);
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}

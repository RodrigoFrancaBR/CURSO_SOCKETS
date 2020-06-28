package br.com.franca;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorSocket {

	private ServerSocket serverSocket;

	public void criarServidorSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	public Socket aguardarConexao() throws IOException {
		return serverSocket.accept();
	}

	public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}

/*	private void enviarMsg(Object o, ObjectOutputStream out) throws IOException {
		out.writeObject(o);
		out.flush();
	}*/
	
	 public void tratarConexao(Socket socket) throws IOException {         
	        
	        try {

	        	// ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	            // ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	            
	            System.out.println("Tratando...");
	            
	            Scanner scanner = new Scanner(socket.getInputStream());
	            PrintStream ps = new PrintStream(socket.getOutputStream());
	        	while (scanner.hasNextLine()) {
	        		System.out.println("Mensagem recebida: " + scanner.nextLine());
	        		ps.println("Enviando Mensagem: HelloWorld");
	        	}
	        	
	        	scanner.close();
	        	
	            // String msg = input.readUTF();
	            // System.out.println("Mensagem recebida: " + msg);
	            
	            // output.writeUTF("HELLO WORLD!");
	            
	            // output.flush();
	            // input.close();
	            // output.close();
	            
	        } catch (IOException e) {
	            System.out.println("Problema no tratamento da conexão com o cliente: "+socket.getInetAddress());
	            System.out.println("Erro: " + e.getMessage());
	            throw e;
	        }finally{
	            fecharSocket(socket);
	        }

	    }
}

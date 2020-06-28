package br.com.franca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket2 {

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
	        // * Cliente ------SOCKET-----servidor
	        //protocolo da aplicação
	        /*
	        4 - Tratar a conversação entre cliente e 
	         servidor (tratar protocolo);
	         */
	           
	        
	        try {
	            /* 3 - Criar streams de entrada e saída;*/
	         
	            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
	            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	            
	            /*protocolo
	                Cliente --> HELLO
	                Server <---- HELLO WORLD!
	            */
	            /*4 - Tratar a conversação entre cliente e 
	         servidor (tratar protocolo);*/
	            
	            System.out.println("Tratando...");
	            String msg = input.readUTF();
	            System.out.println("Mensagem recebida: " + msg);
	            output.writeUTF("HELLO WORLD!");
	            output.flush();//cambio do rádio amador
	            
	            //4.2 - Fechar streams de entrada e saída
	            input.close();
	            output.close();
	        } catch (IOException e) {
	            //tratamento de falhas
	            System.out.println("Problema no tratamento da conexão com o cliente: "+socket.getInetAddress());
	            System.out.println("Erro: " + e.getMessage());
	            throw e;
	        }finally{
	            //final do tratamento do protocolo
	            /*4.1 - Fechar socket de comunicação entre servidor/cliente*/
	            fecharSocket(socket);
	        }

	    }
}

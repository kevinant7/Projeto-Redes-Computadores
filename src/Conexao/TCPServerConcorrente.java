package Conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerConcorrente {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            try {
                System.out.println("Esperando conexão");
                Socket no = serverSocket.accept();
                System.out.println("Conexao aceita");

                ThreadAtendimento thread = new ThreadAtendimento(no);
                thread.start();
            } catch (Exception e) {
             throw new Exception(e.fillInStackTrace());
            }
        }
    }
}

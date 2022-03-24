package Conexao;

import java.io.*;
import java.net.Socket;

public class TCP_Client {
    public static void main(String[] args) throws IOException {
        //Tenta criar uma conexao com o host remoto "127.0.0.1" na porta 9000
        Socket client = new Socket("127.0.0.1", 9000);

        //cria a cadeia de saida (escrite) de informacao do socket
        OutputStream os = client.getOutputStream();
        DataOutputStream writer = new DataOutputStream(os);

        //cria a cadeia de entrada (leitura) de informacao do socket
        InputStreamReader is = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(is);

        //cria um buffer que le informacoes do teclado
        BufferedReader inFromUser = new BufferedReader((new InputStreamReader(System.in)));

        //leitura do teclado
        String texto = inFromUser.readLine();

        //escrita do socket (envio de informacoes ao host remoto)
        writer.writeBytes(texto + "\n");

        //leitura do socket (recebimento de informacoes do host remoto)
        String response = reader.readLine();
        System.out.println("DoServidor:" + response);

        //fechamento do canal
        client.close();
    }
}

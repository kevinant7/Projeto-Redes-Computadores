package Conexao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadAtendimento extends Thread {
    private final Socket no;

    public ThreadAtendimento(Socket node) {
        no = node;
    }

    public void run() {
        try {
            InputStreamReader is = new InputStreamReader(no.getInputStream());
            BufferedReader reader = new BufferedReader(is);

            OutputStream os = no.getOutputStream();
            DataOutputStream writer = new DataOutputStream(os);

            String texto = reader.readLine();

            writer.writeBytes(texto.toUpperCase() + "\n");
        } catch
        (Exception e) {
            e.printStackTrace();
        }
    }
}
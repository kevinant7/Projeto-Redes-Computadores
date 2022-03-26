package UDP;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;


public class Client_Sender {
    public static void main(String[] args) throws IOException {

        HashMap<Integer, String> opcoes = new HashMap<>();
        opcoes.put(1, "Lento");

        while (true) {
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                String host = "127.0.0.1";
                String mensagemASerEnviada;
                int identificador = 0;


                InetAddress ipAdress = InetAddress.getByName(host);

                System.out.println("Sender");

                byte[] sendData = new byte[1024];
                //sendData = "Sou um cliente".getBytes();

                Scanner lerTeclado = new Scanner(System.in);
                mensagemASerEnviada = lerTeclado.next();

                sendData = mensagemASerEnviada.getBytes();

                String mensagemEnviada = new String(sendData);
                System.out.println("Mensagem enviada para o receiver: " + mensagemEnviada + "\n");

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAdress, 9876);
                clientSocket.send(sendPacket);

                System.out.println("Opções de envio: \n" +
                        "1. Lento \n" +
                        "2. Perda \n" +
                        "3. Fora de ordem \n" +
                        "4. Duplicada \n" +
                        "5. Normal"
                );

                int opcaoEnvio = lerTeclado.nextInt();

                System.out.println("Sender. Opcao escolhida : " + opcaoEnvio + "enviada como " + opcoes.get(opcaoEnvio));

                System.out.println("Mensagem " + mensagemEnviada);

                byte[] recBuffer = new byte[1024];
                DatagramPacket recPkt = new DatagramPacket(recBuffer, recBuffer.length);

                clientSocket.receive(recPkt);
                String informacao = new String(recPkt.getData(), recPkt.getOffset(), recPkt.getLength());
                System.out.println("'Recebido do servidor: " + informacao);

                clientSocket.close();

            } catch (IOException ignored) {
            }
        }
    }
}
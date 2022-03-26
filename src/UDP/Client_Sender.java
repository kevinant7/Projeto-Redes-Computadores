package UDP;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client_Sender {
    public static int entradanointervalo() {
        Scanner entrada = new Scanner(System.in);
        int numero = entrada.nextInt();
        if (numero >= 6 || numero <=0) {
            System.out.println("Valor invalido. Tente novamente");
            entradanointervalo();
        } else
            return numero;
        return numero;
    }

    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> opcoes = new HashMap<>();
        ArrayList<String> entradas = new ArrayList<>();
        opcoes.put(1, "Lento");
        opcoes.put(2, "Perda");
        opcoes.put(3, "Fora de ordem");
        opcoes.put(4, "Duplicada");
        opcoes.put(5, "Fora de ordem]");


        while (true) {
            try {
                DatagramSocket clientSocket = new DatagramSocket();
                String host = "127.0.0.1";
                String mensagemASerEnviada;

                InetAddress ipAdress = InetAddress.getByName(host);

                System.out.println("Sender inicio");

                Scanner lerTeclado = new Scanner(System.in);
                mensagemASerEnviada = lerTeclado.next();
                entradas.add(mensagemASerEnviada);

                byte[] sendData = new byte[1024];
                sendData = mensagemASerEnviada.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAdress, 9876);
                clientSocket.send(sendPacket);

                String mensagemEnviada = new String(sendData);
                System.out.println("Mensagem enviada para o receiver: " + mensagemEnviada + "\n");
                System.out.println("""
                        Opções de envio:\s
                        1. Lento\s
                        2. Perda\s
                        3. Fora de ordem\s
                        4. Duplicada\s
                        5. Normal\n"""
                );

                int opcaoEnvio = entradanointervalo();
                String mensagemObtidaPelasEntradas = "\nOpcao escolhida " + opcaoEnvio + " enviada como " + opcoes.get(opcaoEnvio) + " com id " + entradas.indexOf(mensagemEnviada);

                byte [] sendDataByte;
                sendDataByte = mensagemObtidaPelasEntradas.getBytes();
                DatagramPacket sendDataObtida = new DatagramPacket(sendDataByte, sendDataByte.length, ipAdress, 9876);
                clientSocket.send(sendDataObtida);

                System.out.println(mensagemObtidaPelasEntradas);

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

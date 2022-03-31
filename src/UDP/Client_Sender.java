package UDP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client_Sender {

    private final static int PORT = 9876;
    public static String host = "127.0.0.1";
    static String buf;

//    public static String EntradaNoIntervalo(String valorInserido) {
//        Scanner entrada = new Scanner(System.in);
//        valorInserido = entrada.next();
//        int numeroInserido = Integer.parseInt(valorInserido);
//        if (numeroInserido >= 6 || numeroInserido <= 0) {
//            System.out.println("Valor invalido. Tente novamente");
//            return EntradaNoIntervalo(valorInserido);
//        } else
//            System.out.println("Numero inserido no intervalo possível");
//        return valorInserido;
//    }
//
//    public static String LeEntradausuario() {
//        String mensagemASerEnviada;
//
//        Scanner lerTeclado = new Scanner(System.in);
//        mensagemASerEnviada = lerTeclado.next();
//        return mensagemASerEnviada;
//    }
//
//
//    public static void enviaMensagem(String mensagemASerEnviada, DatagramSocket datagramSocket) throws IOException {
//        String host = "127.0.0.1";
//        InetAddress ipAdress = InetAddress.getByName(host);
//
//        DatagramSocket clientSocket = new DatagramSocket();
//        byte[] sendData = new byte[1024];
//        sendData = mensagemASerEnviada.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAdress, 9876);
//        clientSocket.send(sendPacket);
//    }
//
//    public static String recebeMensagem(DatagramSocket datagramSocket) throws IOException {
//        String host = "127.0.0.1";
//
//        byte[] recBuffer = new byte[1024];
//        DatagramPacket receivedPackage = new DatagramPacket(recBuffer, recBuffer.length);
//        datagramSocket.receive(receivedPackage);
//
//        String informacaoRecebida = new String(receivedPackage.getData(), receivedPackage.getOffset(), receivedPackage.getLength());
//        return informacaoRecebida;
//    }

    public static void main(String[] args) throws IOException {

        //Cria socket
        DatagramSocket clientSocket = new DatagramSocket();

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        //Seta o ip
        InetAddress ip = InetAddress.getByName(host);

        //Cliente rodando
        System.out.println("Cliente rodando");

        //Create datapackage
        //DatagramPacket(Byte[] buf, int  length, Inetddress address, int port
        //Client.connet(ip, port)

        while ((buf = entrada.readLine()) != null) {

            //Cria dataPackage
            byte[] sendBuf = buf.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, ip, PORT);

            //Envia pacote
            clientSocket.send(sendPacket);

            //Recebe mensagem
            byte[] receiveBuf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
            clientSocket.receive(receivePacket);

            //Imprime mensagem recebida
            byte[] bufReceive = receivePacket.getData();
            int len = receivePacket.getLength();
            String resultado = new String(bufReceive, 0, len);
            System.out.println(resultado);
        }
        //Fecha socket
        clientSocket.close();
        entrada.close();


//        int contador = 0;
//        ArrayList<String> entradas = new ArrayList<>();
//        ArrayList<String> opcaoEscolhida = new ArrayList<>();
//        String host = "127.0.0.1";
//        InetAddress ipAdress = InetAddress.getByName(host);
//        DatagramSocket clientSocket = new DatagramSocket();
//        Socket socket = new Socket(host, 9876);
//        System.out.println("Client iniciou");
//
//        HashMap<Integer, String> opcoes = new HashMap<>();
//        opcoes.put(1, "Lento");
//        opcoes.put(2, "Perda");
//        opcoes.put(3, "Fora de ordem");
//        opcoes.put(4, "Duplicada");
//        opcoes.put(5, "Fora de ordem");
//
//        while (true) {
//            entradas.add(LeEntradausuario());
//            System.out.println("""
//                    Opções de envio:\s
//                    1. Lento\s
//                    2. Perda\s
//                    3. Fora de ordem\s
//                    4. Duplicada\s
//                    5. Normal\n"""
//            );
//            opcaoEscolhida.add(LeEntradausuario());
//            String mensagemASerEnviada = "Mensagem " + entradas.get(contador) + " enviada como " + opcoes.get(Integer.parseInt(opcaoEscolhida.get(contador))) + " com id " + contador;
//            enviaMensagem(mensagemASerEnviada, clientSocket);
//            contador++;
//            System.out.println(recebeMensagem(clientSocket));
//            clientSocket.setSoTimeout(1000);
//            clientSocket.close();
    }
}


package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server_Receiver extends Thread {

    private static final int PORT = 9876;
    public DatagramSocket datagramSocket;
    public int port;
    public String info;
    public InetAddress ip;

    public Server_Receiver(DatagramSocket datagramSocket, DatagramPacket datagramPacket, byte[] infoBytes) {
        this.datagramSocket = datagramSocket;
        this.info = new String(infoBytes, 0, datagramPacket.getLength());
        this.port = datagramPacket.getPort();
        this.ip = datagramPacket.getAddress();
    }

//    public static String leEntradausuario() {
//        String mensagemASerEnviada;
//
//        Scanner lerTeclado = new Scanner(System.in);
//        mensagemASerEnviada = lerTeclado.next();
//        return mensagemASerEnviada;
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
//        System.out.println(informacaoRecebida);
//        return "";
//    }
//
//    public static void enviaMensagem(String mensagemASerEnviada, DatagramSocket datagramSocket) throws
//            IOException {
//        String host = "127.0.0.1";
//        InetAddress ipAdress = InetAddress.getByName(host);
//
//        DatagramSocket serverSocket = new DatagramSocket();
//        byte[] sendData = new byte[1024];
//        sendData = mensagemASerEnviada.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAdress, 9876);
//        serverSocket.send(sendPacket);
//
//        String mensagemEnviada = new String(sendData);
//        //System.out.println("Mensagem enviada para o Sender: " + mensagemEnviada + "\n");
//    }

    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket(PORT);
        byte[] infoBytes = new byte[1024];
        System.out.println("Server esta rodando");

        while (true) {
            try {
                 DatagramPacket datagramPacket = new DatagramPacket(infoBytes, infoBytes.length);

                //Recebe dados
                datagramSocket.receive(datagramPacket);

                //Após receber inicia thread
                Mensagem thread = new Mensagem(datagramPacket, datagramSocket, infoBytes);
                thread.start();
            } catch (Exception e) {
                throw new Exception(e.fillInStackTrace());
            }
        }
    }
}

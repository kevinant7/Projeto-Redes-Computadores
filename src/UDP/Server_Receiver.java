package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server_Receiver {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            while (true) {
                byte[] recBuffer = new byte[1024];

                System.out.println("Server inicio");

                DatagramPacket recPkt = new DatagramPacket(recBuffer, recBuffer.length);
                serverSocket.receive(recPkt);
                String informacao = new String(recPkt.getData(), recPkt.getOffset(), recPkt.getLength());
                System.out.println("Mensagem recebida do sender: " + informacao);

                byte[] sendBuf = new byte[1024];
                sendBuf = "Sou o servidor".getBytes();

                InetAddress ipAddress = recPkt.getAddress();
                int port = recPkt.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, ipAddress, port);
                serverSocket.send(sendPacket);

                DatagramPacket recPkt2 = new DatagramPacket(recBuffer, recBuffer.length);
                serverSocket.receive(recPkt2);
                String informacao2 = new String(recPkt2.getData(), recPkt2.getOffset(), recPkt2.getLength());
                System.out.println("Mensagem recebida do sender: " + informacao2);
                System.out.println("Mensagem enviada pelo servidor: " + new String(sendPacket.getData()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

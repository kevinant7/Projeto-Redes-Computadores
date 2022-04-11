package UDP;

import java.net.*;

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

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        InetAddress ipAdress = InetAddress.getByName(host);
        DatagramSocket serverSocket = new DatagramSocket(PORT);
        byte[] infoBytes = new byte[1024];
        byte[] infoBytes2 = new byte[1024];
        System.out.println("Server esta rodando");

        while (true) {
            try {
                DatagramPacket receiveData = new DatagramPacket(infoBytes, infoBytes.length);
                DatagramPacket receiveData2 = new DatagramPacket(infoBytes2, infoBytes2.length);

                //Recebe dados
                serverSocket.receive(receiveData);
                serverSocket.receive(receiveData2);

                //Após receber inicia thread
                Mensagem thread = new Mensagem(receiveData, receiveData2, serverSocket, infoBytes, infoBytes2);
                thread.start();

                //Recebe dados
                serverSocket.receive(receiveData);

            } catch (Exception e) {
                throw new Exception(e.fillInStackTrace());
            }
        }
    }
}

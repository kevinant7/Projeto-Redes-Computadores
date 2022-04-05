package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Mensagem extends Thread {

    private static final int PORT = 9876;
    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacket;
    public byte[] infoBytes;
    public String info;
    public int port;
    public InetAddress ip;
    public static int contador = 0;

    public Mensagem(DatagramPacket datagramPacket, DatagramSocket datagramSocket, byte[] infoBytes) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
        this.infoBytes = infoBytes;
        this.info = new String(infoBytes, 0, datagramPacket.getLength());
        this.port = datagramPacket.getPort();
        this.ip = datagramPacket.getAddress();
    }

    @Override
    public void run() {

        super.run();

        ArrayList<String> mensagem = new ArrayList<>();
        ArrayList<String> opcao = new ArrayList<>();

        //Recebe a mensagem

        mensagem.add(info);

        System.out.println(info);
        System.out.println(mensagem.get(0));

        //Recebe a opcao
        //opcao.add(info);

        // System.out.println("Ciente-" + ip + "port=" + port + "say: Opcao " + opcao.get(contador));

        byte[] buf = ("Recebido").getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, port);

        try {
            datagramSocket.send(sendPacket);
        } catch
        (Exception e) {
            e.printStackTrace();
        }
    }
}

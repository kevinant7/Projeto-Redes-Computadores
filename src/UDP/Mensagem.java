package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Mensagem extends Thread {

    private static final int PORT = 9876;
    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacket;
    public byte[] infoBytes;
    public String info;
    public int port;
    public InetAddress ip;

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

        System.out.println("Ciente-" + ip + "port=" + port + "say: " + info);

        //Responde com a mensagem recebida
        byte[] echobuf = ("Server recebeu: " + info).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(echobuf, echobuf.length, ip, port);

        try {
            datagramSocket.send(sendPacket);
        } catch
        (Exception e) {
            e.printStackTrace();
        }
    }
}

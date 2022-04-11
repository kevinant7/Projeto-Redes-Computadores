package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Mensagem extends Thread {

    private static final int PORT = 9876;

    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacket;
    public DatagramPacket datagramPacket2;
    public byte[] infoBytes;
    public byte[] infoBytes2;
    public String info;
    public String info2;
    public int port;
    public InetAddress ip;
    public static int contador = 0;

    public Mensagem(DatagramPacket datagramPacket, DatagramPacket datagramPacket2, DatagramSocket datagramSocket, byte[] infoBytes, byte[] infoBytes2) {
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
        this.datagramPacket2 = datagramPacket2;
        this.infoBytes = infoBytes;
        this.infoBytes2 = infoBytes2;
        this.info = new String(infoBytes, 0, datagramPacket.getLength());
        this.info2 = new String(infoBytes2, 0, datagramPacket2.getLength());
        this.port = datagramPacket.getPort();
        this.ip = datagramPacket.getAddress();
    }

    @Override
    public void run() {

        super.run();

        ArrayList<String> mensagem = new ArrayList<>();
        ArrayList<String> opcao = new ArrayList<>();


        byte[] buf = ("Recebido").getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, port);

        //Recebe a mensagem

        mensagem.add(info);

        //System.out.println(info);
        System.out.println(mensagem.get(contador));


        opcao.add(info2);

        System.out.println(opcao.get(contador));

        //System.out.println("Ciente" + + port + "say: Opcao " + opcao.get(contador));

        if (contador % 2 == 0) {
            //Faz nada
        } else {
            switch (mensagem.get(contador)) {
                case "1":
                    System.out.println("Lento");
                    try {
                        try {
                            datagramSocket.setSoTimeout(5000);
                            byte[] receiveBuf = new byte[1024];
                            DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                            datagramSocket.receive(receivePacket);
                        } catch (SocketTimeoutException ignored) {
                        }
                        datagramSocket.send(sendPacket);
                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    //case "2":
                    //    try {
                    //        clientSocket.setSoTimeout(5000);
                    //        byte[] receiveBuf = new byte[1024];
                    //        DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                    //        clientSocket.receive(receivePacket);
                    //        System.out.println("Mensagem id " + contador + " recebida pelo receiver");
                    //    } catch (SocketTimeoutException e) {
                    //        System.out.println("Perda de pacote");
                    //    }
                    //    break;
                case "3":
                    System.out.println("Fora de ordem");
                case "4":
                    System.out.println("Duplicada");
                    //case "5":
                    //    clientSocket.setSoTimeout(5000);
                    //    byte[] receiveBuf = new byte[1024];
                    //    DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                    //    clientSocket.receive(receivePacket);
                    //    System.out.println("Mensagem id " + contador + " recebida pelo receiver");
                    //    try {
                    //        datagramSocket.setSoTimeout(5000);
                    //        datagramSocket.send(sendPacket);
                    //    } catch
                    //    (Exception e) {
                    //        e.printStackTrace();
                    //        contador++;
                    //
                    //  }
            }
            contador++;
        }
    }
}

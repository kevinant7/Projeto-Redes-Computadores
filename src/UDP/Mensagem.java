package UDP;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Mensagem extends Thread {

    public DatagramSocket datagramSocket;
    public DatagramPacket mensagem;
    public DatagramPacket opcaoEscolhida;
    public byte[] infoBytes;
    public byte[] infoBytes2;
    public String mensagemRecebida;
    public String opcaoEscolhidaRecebida;
    public int port;
    public InetAddress ip;
    public static int contador = 0;

    public Mensagem(DatagramPacket mensagem, DatagramPacket opcaoEscolhida, DatagramSocket datagramSocket, byte[] infoBytes, byte[] infoBytes2) {
        this.datagramSocket = datagramSocket;
        this.mensagem = mensagem;
        this.opcaoEscolhida = opcaoEscolhida;
        this.infoBytes = infoBytes;
        this.infoBytes2 = infoBytes2;
        this.mensagemRecebida = new String(infoBytes, 0, mensagem.getLength());
        this.opcaoEscolhidaRecebida = new String(infoBytes2, 0, opcaoEscolhida.getLength());
        this.port = mensagem.getPort();
        this.ip = mensagem.getAddress();
    }

    @Override
    public void run() {
        super.run();

        ArrayList<String> mensagem = new ArrayList<>();
        ArrayList<String> opcao = new ArrayList<>();

        byte[] buf = ("Recebido").getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, port);

        //Recebe a mensagem
        mensagem.add(mensagemRecebida);

        //Recebe a opcao escolhida
        opcao.add(opcaoEscolhidaRecebida);

        switch (opcaoEscolhidaRecebida) {

            //Responsavel pela mensagem 1. Lenta
            case "1":
                System.out.println("Mensagem id " + contador + " recebida na ordem, entregando para a camada de aplicação.");
                try {
                    this.datagramSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contador++;
                break;

            //Responsável pela mensagem 2.Perda
            case "2":
                contador++;
                break;

            //Responsavel pela mensagem 3.fora de ordem
            case "3":
                System.out.println("Mensagem id " + contador + " recebida fora de ordem, ainda não recebidos os identificadores [lista de identificadores não recebidos]");
                contador++;
                break;

            //Responsavel pela mensagem 4.duplicada
            case "4":
                System.out.println("Mensagem id " + contador + " recebida de forma duplicada");
                byte[] receiveByte = new byte[1024];
                DatagramPacket receive = new DatagramPacket(receiveByte, receiveByte.length);
                try {
                    datagramSocket.setSoTimeout(10000);
                    datagramSocket.receive(receive);
                    datagramSocket.receive(receive);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    this.datagramSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contador++;
                break;

            case "5":
                System.out.println("Mensagem id " + contador + " recebida na ordem, entregando para a camada de aplicação.");
                byte[] sendNormal = new byte[1024];
                DatagramPacket sendNormalMessage = new DatagramPacket(sendNormal, sendNormal.length, ip, port);
                try {
                    datagramSocket.send(sendNormalMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                contador++;
                break;
        }
    }
}



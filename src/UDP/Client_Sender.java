package UDP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Client_Sender {

    private final static int PORT = 9876;
    public static String host = "127.0.0.1";
    static String buf;
    public static int contador = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<String> mensagemUsuario = new ArrayList<>();
        ArrayList<String> opcaoEscolhida = new ArrayList<>();

        //Cria Array com identificadore das mensagens a serem reenviadas
        ArrayList<Integer> indiceMensagensPerdias = new ArrayList<>();

        HashMap<String, String> opcoes = new HashMap<>();
        opcoes.put("1", "Lento");
        opcoes.put("2", "Perda");
        opcoes.put("3", "Fora de ordem");
        opcoes.put("4", "Duplicada");
        opcoes.put("5", "Normal");

        //Cria socket
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        //Seta o ip
        InetAddress ip = InetAddress.getByName(host);

        //Opcoes que usuario pode escolher
        while ((buf = entrada.readLine()) != null) {
            mensagemUsuario.add(buf);

            //Envia mensagem
            byte[] mensagem = buf.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(mensagem, mensagem.length, ip, PORT);
            clientSocket.send(sendPacket);

            //Mostra opcoes
            System.out.println("Opcoes de envio: " +
                    "\n 1. Lento" +
                    "\n 2. Perda" +
                    "\n 3. Fora de ordem" +
                    "\n 4. Duplicada" +
                    "\n 5. Normal"
            );

            //Le opcao escolhida
            String numeroEscolhido = entrada.readLine();
            opcaoEscolhida.add(numeroEscolhido);

            //Envia a opcao
            byte[] sendOpcaoByte = opcaoEscolhida.get(contador).getBytes();
            DatagramPacket sendOpcao = new DatagramPacket(sendOpcaoByte, sendOpcaoByte.length, ip, PORT);
            clientSocket.send(sendOpcao);

            //Formata mensagem e imprime no console
            String mensagemFormatada = "Mensagem " + mensagemUsuario.get(contador) + " enviada como " + opcoes.get(opcaoEscolhida.get(contador)) + " com id " + contador;
            System.out.println(mensagemFormatada);

            //Envia e recebe recebe Resposta
            switch (opcaoEscolhida.get(contador)) {
                //Responsavel pela mensagem 1. Lenta
                case "1":
                    clientSocket.setSoTimeout(10000);
                    byte[] receiveBuf1 = new byte[1024];
                    DatagramPacket receivePacket1 = new DatagramPacket(receiveBuf1, receiveBuf1.length);
                    clientSocket.receive(receivePacket1);
                    System.out.println("Mensagem id " + contador + " recebida pelo receiver");
                    break;

                //Responsável pela perda de mensagem. Usando temporizador e timeout
                case "2":
                    try {
                        clientSocket.setSoTimeout(6000);
                        byte[] receiveBuf = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                        clientSocket.receive(receivePacket);
                        System.out.println("Mensagem id " + contador + " recebida pelo receiver");
                    } catch (SocketTimeoutException e) {
                        indiceMensagensPerdias.add(contador);
                        System.out.println(contador);
                    }
                    break;

                //Responsavel pela mensagem fora de ordem
                case "3":
                    break;

                //Responsavel pela mensagem 4.duplicada
                case "4":
                    byte[] sendDuplicadaByte = numeroEscolhido.getBytes();
                    DatagramPacket sendDuplicada = new DatagramPacket(sendDuplicadaByte, sendDuplicadaByte.length, ip, PORT);
                    clientSocket.wait(5000);
                    clientSocket.send(sendDuplicada);
                    clientSocket.send(sendDuplicada);
                    break;

                //Responsavel pelas mensagens 5. normmal
                case "5":
                    byte[] receiveNormalByte = new byte[1024];
                    DatagramPacket receiveNormal = new DatagramPacket(receiveNormalByte, receiveNormalByte.length);
                    clientSocket.receive(receiveNormal);
                    System.out.println("Mensagem id " + contador + " recebida pelo receiver");
                    break;
            }
            //Contador de entrada
            contador++;
        }
        //Fecha socket
        clientSocket.close();
        entrada.close();
    }
}


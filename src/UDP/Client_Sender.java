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

    public String EntradaNoIntervalo(String valorInserido) {
        int numeroInserido = Integer.parseInt(valorInserido);
        if (numeroInserido >= 6 || numeroInserido <= 0) {
            System.out.println("Valor invalido. Tente novamente");
            return EntradaNoIntervalo(valorInserido);
        } else
            System.out.println("Numero inserido no intervalo possível");
        return valorInserido;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> mensagemUsuario = new ArrayList<>();
        ArrayList<String> opcaoEscolhida = new ArrayList<>();

        HashMap<String, String> opcoes = new HashMap<>();
        opcoes.put("1", "Lento");
        opcoes.put("2", "Perda");
        opcoes.put("3", "Fora de ordem");
        opcoes.put("4", "Duplicada");
        opcoes.put("5", "Fora de ordem");

        //Cria socket
        DatagramSocket clientSocket = new DatagramSocket();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        //Seta o ip
        InetAddress ip = InetAddress.getByName(host);

        //Cliente rodando
        //System.out.println("Cliente rodando");

        //Opcoes que usuario pode escolher
        while ((buf = entrada.readLine()) != null) {
            mensagemUsuario.add(buf);

            //Envia mensagem
            byte[] mensagem = buf.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(mensagem, mensagem.length, ip, PORT);
            clientSocket.send(sendPacket);

            //Mostra opcoes
            System.out.println("""
                    Opções de envio:\s
                    1. Lento\s
                    2. Perda\s
                    3. Fora de ordem\s
                    4. Duplicada\s
                    5. Normal
                    """
            );

            //Le opcao escolhida
            String numeroEscolhido = entrada.readLine();
            opcaoEscolhida.add(numeroEscolhido);

            //Envia a opcao
            byte[] sendOpcaoByte = numeroEscolhido.getBytes();
            DatagramPacket sendOpcao = new DatagramPacket(sendOpcaoByte, sendOpcaoByte.length, ip, PORT);
            clientSocket.send(sendOpcao);

            //Formata mensagem
            String mensagemFormatada = "Mensagem " + mensagemUsuario.get(contador) + " enviada como " + opcoes.get(opcaoEscolhida.get(contador)) + " com id " + contador;
            System.out.println(mensagemFormatada);

            //Recebe Resposta
            try {
                clientSocket.setSoTimeout(5000);
                byte[] receiveBuf = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                clientSocket.receive(receivePacket);
                System.out.println("Mensagem id " + contador + " recebida pelo receiver");
            } catch (SocketTimeoutException e) {
                System.out.println("Reenviar o pacote");
            }

            //Imprime mensagem recebida
            //byte[] bufReceive = receivePacket.getData();
            //int len = receivePacket.getLength();
            //String resultado = new String(bufReceive, 0, len);
            //System.out.println(resultado);

            //Contador de entrada
            contador++;
        }
        //Fecha socket
        clientSocket.close();
        entrada.close();
    }
}


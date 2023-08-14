import java.net.*; // Biblioteca para trabalhar com sockets
import java.io.*; // Biblioteca para trabalhar com buffers

class UDPClient {
    public static void main(String args[]) throws Exception {
        // Criação do socket UDP sem definir a porta
        DatagramSocket clientSocket = new DatagramSocket();

        // Obtenção do IP do servidor: no código ou pelo usuário
        // InetAddress IPAddress =
        InetAddress.getByName("");
        System.out.println("Digite o IP do servidor:");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        InetAddress IPAddress = InetAddress.getByName(inFromUser.readLine());

        // Criação dos vetores de bytes para enviar e receber dados para/do o servidor
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        // Obtenção da sentença para enviar ao servidor
        System.out.println("Digite o texto a ser enviado para o servidor:");
        String sentence = inFromUser.readLine();

        // Conversão da sentença para bytes
        sendData = sentence.getBytes();

        // Criação do pacote de envio ao servidor
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, 9876);

        // Envio do pacote ao servidor
        clientSocket.send(sendPacket);

        // Criação do pacote de recebimento da resposta do servidor
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);

        // Recebimento da resposta do servidor
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("RESPOSTA DO SERVIDOR:" +
                modifiedSentence);

        // Fechamento do socket do cliente
        clientSocket.close();

    }
}
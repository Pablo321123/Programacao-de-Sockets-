import java.net.*; // Biblioteca para trabalhar com sockets

class UDPServer {
    public static void main(String args[]) throws Exception {
        // Criação do socket UDP no servidor, indicando a porta
        DatagramSocket serverSocket = new DatagramSocket(9876);

        // Criação dos vetores de bytes para receber e enviar dados do /para o cliente
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        System.out.println("Executando...");

        // Loop infinito para executar o código no servidor
        while (true) {
            
            // Criação da variável para receber o pacote de requisição do cliente
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Recebimento do pacote do cliente
            serverSocket.receive(receivePacket);

            // Tratamento dos dados recebidos do cliente: sentença, IP e porta do cliente
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            // Conversão da sentença para letras maiúsculafs
            String capitalizedSentence = sentence.toUpperCase();

            // Conversão da sentença para bytes
            sendData = capitalizedSentence.getBytes();

            // Criação do pacote de resposta para o cliente
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);
            

            System.out.println(capitalizedSentence);

            // Envio do pacote de resposta para o cliente
            serverSocket.send(sendPacket);

        }
    }
}
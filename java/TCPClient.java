import java.io.*;
import java.net.*;

// Biblioteca para trabalhar com buffers
// Biblioteca para trabalhar com sockets

class TCPClient {
    public static void main(String argv[]) throws Exception {
        // Criação do socket TCP do cliente, indicando o IP e a porta do servidor
        Socket clientSocket = new Socket("", 6789);
        // Buffer para escrita dos dados para o servidor
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        // Buffer para leitura dos dados enviados pelo servidor
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // Obtenção da sentença para enviar ao servidor
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite o texto a ser enviado para o servidor:");
        String sentence = inFromUser.readLine();
        // Envio da sentença ao servidor
        outToServer.writeBytes(sentence + '\n');
        // Leitura da resposta enviada pelo servidor
        String modifiedSentence = inFromServer.readLine();
        System.out.println("RESPOSTA DO SERVIDOR: " + modifiedSentence);

        // Fechamento do socket do cliente
        clientSocket.close();

    }
}
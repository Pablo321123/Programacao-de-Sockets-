import java.io.*;
import java.net.*;

// Biblioteca para trabalhar com buffers
// Biblioteca para trabalhar com sockets
class TCPServer {
    public static void main(String argv[]) throws Exception {
        // Criação do socket de conexão TCP no servidor, indicando a porta
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            // Criação do socket de dados a partir do estabelecimento de uma conexão
            Socket connectionSocket = welcomeSocket.accept();
            // Buffer para leitura dos dados enviados pelo cliente
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            // Buffer para escrita dos dados para o cliente
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            // Leitura dos dados enviados pelo cliente
            String clientSentence = inFromClient.readLine();
            // Conversão da sentença para letras maiúsculas
            String capitalizedSentence = clientSentence.toUpperCase()
                    + '\n';

            // Envio da resposta ao cliente
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
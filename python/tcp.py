import socket


class TCPClient:
    def __init__(self) -> None:
        self.serverIP = ""
        self.server_port = 6789

    def connectServer(self):
        self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client_socket.connect((self.serverIP, self.server_port))

    def sendMessage(self, msg):
        message = msg
        self.client_socket.send(message.encode())

        response = self.client_socket.recv(1024).decode()
        print(f"RESPOSTA DO SERVIDOR: {response}")

    def closeSection(self):
        self.client_socket.close()


class TCPServer:
    def __init__(self) -> None:
        self.serverIP = ""
        self.server_port = 6789

    def go_up_server(self):
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server_socket.bind((self.serverIP, self.server_port))
        self.server_socket.listen(5)

        print(f"Servidor está online na porta: {self.server_port}")

        while True:
            client_socket, client_address = self.server_socket.accept()
            print(f"Conectado em: {client_address}")

            message = client_socket.recv(1024).decode()
            print(f"MESSAGEM RECEBIDA: {message}")

            response = "Mensagem Recebida!"
            client_socket.send(response.encode())

            client_socket.close


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
        self.client_socket.sendall(
            b"M" + len(message).to_bytes(4, "big") + message.encode()
        )

        self.serverResponse()

    def sendImage(self, path):
        with open(path, "rb") as image_file:
            image_data = image_file.read()

        self.client_socket.sendall(
            b"I" + len(image_data).to_bytes(8, "big") + image_data
        )
        self.serverResponse()

    def serverResponse(self):
        response = self.client_socket.recv(1024).decode()
        print(f"RESPOSTA DO SERVIDOR: {response}")

    def closeSection(self):
        self.client_socket.close()


class TCPServer:
    def __init__(self) -> None:
        self.serverIP = ""
        self.server_port = 6789

    def receiveSimpleMessage(self, length):
        return self.client_socket.recv(length).decode()

    def receiveImage(self, legth):
        image_data = b""
        remaining_bytes = legth

        while remaining_bytes > 0:
            chunk = self.client_socket.recv(min(remaining_bytes, 1024))

            if not chunk:
                break
            else:
                image_data += chunk
                remaining_bytes -= len(chunk)

        return image_data

    def go_up_server(self):
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server_socket.bind((self.serverIP, self.server_port))
        self.server_socket.listen(5)

        print(f"Servidor está online na porta: {self.server_port}")

        while True:
            self.client_socket, self.client_address = self.server_socket.accept()
            print(f"Conectado em: {self.client_address}")

            # message = self.client_socket.recv(1024).decode()

            message_type = self.client_socket.recv(1).decode()

            # Recebimendo e distinção do tipo da mensagem
            #Se for M -> Mensagem em texto comum
            if message_type == "M":
                message_legth = int.from_bytes(self.client_socket.recv(4), "big")
                message = self.receiveSimpleMessage(message_legth)
                response = f"MESSAGEM RECEBIDA: {message}!"

            # Se for I -> Imagem
            elif message_type == "I":
                image_length = int.from_bytes(self.client_socket.recv(8), "big")
                image_data = self.receiveImage(image_length)

                with open("received_image.jpg", "wb") as image_file:
                    image_file.write(image_data)

                response = "IMAGEM RECEBIDA!"
            else:
                response = "NENHUM DADO RECEBIDO NO SERVIDOR!"

            self.client_socket.send(response.encode())

            self.client_socket.close

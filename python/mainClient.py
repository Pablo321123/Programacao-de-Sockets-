from tcp import TCPClient

if __name__ == "__main__":
    tcpClient = TCPClient()
    
    tcpClient.connectServer()
    tcpClient.sendMessage("MAQUINA 1 - WINDOWS")
    
    tcpClient.closeSection()
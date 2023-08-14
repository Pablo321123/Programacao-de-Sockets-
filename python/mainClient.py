from tcp import TCPClient

if __name__ == "__main__":
    tcpClient = TCPClient()
    
    tcpClient.connectServer()
    tcpClient.sendMessage("COLEEEE")
    
    tcpClient.closeSection()
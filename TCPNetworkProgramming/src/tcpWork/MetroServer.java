package tcpWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    MetroCardBank bnk = null;
    private ServerSocket servSock = null;
    private int serverPort = -1;

    public MetroServer(int port) {
        this.bnk = new MetroCardBank();
        this.serverPort = port;
    }

    @Override
    public void run() {
        try {
            this.servSock = new ServerSocket(serverPort);
            System.out.println("Metro Server started");
            while (true) {
                System.out.println("New Client Waiting...");
                Socket sock = servSock.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(this.getBnk(), sock);
                ch.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                servSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Metro Server stopped");
        }
    }

    public MetroCardBank getBnk() {
        return bnk;
    }

    public void setBnk(MetroCardBank bnk) {
        this.bnk = bnk;
    }

    public ServerSocket getServSock() {
        return servSock;
    }

    public void setServSock(ServerSocket servSock) {
        this.servSock = servSock;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }

}

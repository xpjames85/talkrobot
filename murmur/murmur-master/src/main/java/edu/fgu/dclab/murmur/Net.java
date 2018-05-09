package edu.fgu.dclab.murmur;

import java.io.IOException;
import java.net.Socket;

public class Net {
    private Socket clientSocket;

    public boolean connect(String ip, int port) {
        try {
            this.clientSocket = new Socket(ip, port);
        }
        catch (IOException e) {
            System.out.println("Connection refused.");
        }

        return isConnected();
    }

    public void connectSink(MessageSink sink) {
        try {
            new Thread(new NetReader(
                this.clientSocket.getInputStream(),
                sink
            )).start();
        }
        catch (IOException e) {
            System.out.println("connectSink failed.");
        }
    }

    public void connectSource(MessageSource source) {
        try {
            source.connectSink(new NetWriter(
                this.clientSocket.getOutputStream()
            ));
        }
        catch (IOException e) {
            System.out.println("connectSource failed.");
        }
    }

    public boolean isConnected() {
        return !((this.clientSocket == null) || (this.clientSocket.isClosed()));
    }
}

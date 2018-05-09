package edu.fgu.dclab.murmur;

import edu.fgu.dclab.Message;

import java.io.*;

public class NetReader implements Runnable {
    private ObjectInputStream in;
    private MessageSink out;

    public NetReader(InputStream in, MessageSink out) {
        try {
            this.in = new ObjectInputStream(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.out = out;
    }

    @Override
    public void run() {
        Message message = null;

        try {
            while ((message = (Message)this.in.readObject()) != null) {
                this.out.writeMessage(message);
            }
        }
        catch (IOException e) {
            System.out.println("NetReader I/O Exception");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

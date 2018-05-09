package edu.fgu.dclab.murmur;

import edu.fgu.dclab.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class NetWriter implements MessageSink {
    private ObjectOutputStream out;

    public NetWriter(OutputStream out) {
        try {
            this.out = new ObjectOutputStream(out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(Message message) {
        try {
            this.out.writeObject(message);
            this.out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

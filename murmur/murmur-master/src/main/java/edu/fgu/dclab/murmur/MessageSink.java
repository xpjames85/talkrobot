package edu.fgu.dclab.murmur;

import edu.fgu.dclab.Message;

public interface MessageSink {
    void writeMessage(Message message);
}

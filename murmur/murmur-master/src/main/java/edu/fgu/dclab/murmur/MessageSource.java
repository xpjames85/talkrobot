package edu.fgu.dclab.murmur;

import edu.fgu.dclab.Message;

public interface MessageSource {
    Message readMessage();

    void connectSink(MessageSink sink);
}

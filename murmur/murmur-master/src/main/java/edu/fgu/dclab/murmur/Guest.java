package edu.fgu.dclab.murmur;

import edu.fgu.dclab.ChatMessage;
import edu.fgu.dclab.LoginMessage;
import edu.fgu.dclab.Message;
import edu.fgu.dclab.RoomMessage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.text.MessageFormat;

public class Guest implements MessageSink, MessageSource {
    private final int LOGINING = 0;
    private final int CHATTING = 1;

    private String id = "";
    private SceneChat scene = null;
    private int state = LOGINING;

    private MessageSink sink = null;

    public Guest(SceneChat scene) {
        this.scene = scene;

        scene.setOnButtonSend(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                readMessage();
            }
        });
    }

    @Override
    public Message readMessage() {
        Message message = null;

        switch (this.state) {
            case CHATTING:
                message = new ChatMessage(
                    this.id,
                    this.scene.readMessage()
                );

                break;

            case LOGINING:
                this.id = this.scene.readMessage();

                message = new LoginMessage(
                    this.id,
                    ""
                );

                this.state = CHATTING;

                break;

            default:
        }

        this.sink.writeMessage(message);

        return message;
    }

    private void process(Message message) {
        switch (message.getType()) {
            case Message.CHAT:
                String chat = MessageFormat.format(
                    "{0} 講：{1}",
                    message.getSource(),
                    ((ChatMessage) message).MESSAGE
                );

                scene.writeMessage(chat);

                break;

            case Message.ROOM_STATE:
                scene.updateStatusBar(
                    ((RoomMessage)message).NUMBER_OF_GUESTS,
                    ((RoomMessage)message).ROOM_NUMBER
                );

                break;

            default:
        }
    }

    @Override
    public void connectSink(MessageSink sink) {
        this.sink = sink;
    }

    @Override
    public void writeMessage(Message message) {
        process(message);
    }
}

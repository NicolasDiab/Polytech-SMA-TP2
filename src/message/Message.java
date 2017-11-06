package message;

//import model.agents.Agent;

import agent.Agent;

public class Message {
    private Agent sender;
    private Agent receiver;
    private String message;
    private long timestamp;
    private TypeMessage typeMessage;

    public Message(Agent sender, Agent receiver, String message, TypeMessage type, long timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timestamp = timestamp;
        this.setTypeMessage(type);
    }

    public Agent getSender() {
        return sender;
    }

    public void setSender(Agent sender) {
        this.sender = sender;
    }

    public Agent getReceiver() {
        return receiver;
    }

    public void setReceiver(Agent receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}

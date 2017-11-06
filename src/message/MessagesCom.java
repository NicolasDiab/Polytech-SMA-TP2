package message;

import agent.Agent;

import java.util.HashMap;
import java.util.List;


//TODO A SUPPRIMER !!!!

public class MessagesCom {
    private static MessagesCom instance = null;

    private HashMap<Agent, List<Message>> hashmapMessages;

    public static MessagesCom getInstance(){
        if (instance == null){
            instance = new MessagesCom();
        }
        return instance;
    }

    private MessagesCom(){

    }

    public List<Message> readMyMessages(Agent agent) {
        if (this.hashmapMessages.containsKey(agent))
            return this.hashmapMessages.get(agent);
        return null;
    }

    public void sendMessage(Message message) {
        // add message in the sender's hashmap
        List<Message> senderMessages = this.readMyMessages(message.getSender());
        senderMessages.add(message);
        this.hashmapMessages.put(message.getSender(), senderMessages);

        // add message in the receiver's hashmap
        List<Message> receiverMessages = this.readMyMessages(message.getReceiver());
        receiverMessages.add(message);
        this.hashmapMessages.put(message.getReceiver(), receiverMessages);
    }

    public void clearMyMessages(Agent agent) {
        if (this.hashmapMessages.containsKey(agent))
            this.hashmapMessages.remove(agent);
    }
}

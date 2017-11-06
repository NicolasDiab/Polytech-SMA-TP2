package message;

import agent.Negociator;
import agent.Provider;
import service.PlaneTicket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Negociation {

    private static final int NEGOCIATIONS_DURATION = 6;

    private int nb_iterations_left;

    private List<Message> messages;

    private SimpleDateFormat startDate;
    private SimpleDateFormat endDate;

    private PlaneTicket planeTicket;

    private Negociator negociator;
    private Provider provider;

    public Negociation(PlaneTicket planeTicket, Negociator negociator, Provider provider) {
        this.planeTicket = planeTicket;
        this.negociator = negociator;
        this.provider = provider;

        this.nb_iterations_left = NEGOCIATIONS_DURATION;
        this.messages = new ArrayList<>();
        this.startDate = new SimpleDateFormat();
    }

    public int getNb_iterations_left() {
        return nb_iterations_left;
    }

    public void setNb_iterations_left(int nb_iterations_left) {
        this.nb_iterations_left = nb_iterations_left;
    }

    public static double getNegociationsDuration() {
        return NEGOCIATIONS_DURATION;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public SimpleDateFormat getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleDateFormat startDate) {
        this.startDate = startDate;
    }

    public SimpleDateFormat getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleDateFormat endDate) {
        this.endDate = endDate;
    }

    public PlaneTicket getPlaneTicket() {
        return planeTicket;
    }

    public void setPlaneTicket(PlaneTicket planeTicket) {
        this.planeTicket = planeTicket;
    }

    public Negociator getNegociator() {
        return negociator;
    }

    public void setNegociator(Negociator negociator) {
        this.negociator = negociator;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}

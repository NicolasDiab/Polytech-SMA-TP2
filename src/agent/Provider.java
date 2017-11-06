package agent;

import message.Negociation;
import service.PlaneTicket;

import java.util.HashMap;
import java.util.List;

public class Provider extends Agent {
    private List<PlaneTicket> planeTickets;

    private List<Negociator> negociators;
    private HashMap<Constraint, String> constraints;
    private List<Negociation> negociations;

    @Override
    public void run() {

    }

    public Provider(List<PlaneTicket> planeTickets, List<Negociator> negociators, HashMap<Constraint, String> constraints,
                    List<Negociation> negociations) {
        this.planeTickets = planeTickets;
        this.negociators = negociators;
        this.constraints = constraints;
        this.negociations = negociations;
    }

    public List<Negociation> getNegociations() {
        return negociations;
    }

    public void setNegociations(List<Negociation> negociations) {
        this.negociations = negociations;
    }

    public List<PlaneTicket> getPlaneTickets() {
        return planeTickets;
    }

    public void setPlaneTickets(List<PlaneTicket> planeTickets) {
        this.planeTickets = planeTickets;
    }

    public List<Negociator> getNegociators() {
        return negociators;
    }

    public void setNegociators(List<Negociator> negociators) {
        this.negociators = negociators;
    }

    public HashMap<Constraint, String> getConstraints() {
        return constraints;
    }

    public void setConstraints(HashMap<Constraint, String> constraints) {
        this.constraints = constraints;
    }
}

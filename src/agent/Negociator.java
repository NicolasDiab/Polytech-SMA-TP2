package agent;

import message.Negociation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Negociator extends Agent {

    private List<Provider> providers;
    private HashMap<Constraint, String> constraints;
    private List<Negociation> negociations;

    @Override
    public void run() {

    }

    public Negociator(List<Provider> providers, HashMap<Constraint, String> constraints) {
        this.providers = providers;
        this.constraints = constraints;
        this.negociations = new ArrayList<>();
    }

    public List<Negociation> getNegociations() {
        return negociations;
    }

    public void setNegociations(List<Negociation> negociations) {
        this.negociations = negociations;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public HashMap<Constraint, String> getConstraints() {
        return constraints;
    }

    public void setConstraints(HashMap<Constraint, String> constraints) {
        this.constraints = constraints;
    }
}

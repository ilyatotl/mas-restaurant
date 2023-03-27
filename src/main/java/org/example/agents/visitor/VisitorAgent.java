package org.example.agents.visitor;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class VisitorAgent extends Agent {
    private final String name = "visitor";
    private final AID aid = getAID();

    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("Visitor agent " + getArguments()[1].toString() + " created");

        addBehaviour(new OrderCreator(getArguments()[0], getArguments()[1].toString()));
    }
}

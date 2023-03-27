package org.example.agents.operation;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class OperationAgent extends Agent {
    private final String name = "operation";
    public static AID aid;

    @Override
    protected void setup() {
        aid = getAID();
        AController.log.info("Operation " + getAID().getName() + " created");
        AController.addNewAgent(this, name);
    }
}

package org.example.agents.operation;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class OperationAgent extends Agent {
    private final String name = "operation";
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("operation agent added");
    }
}

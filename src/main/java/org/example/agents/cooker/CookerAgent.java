package org.example.agents.cooker;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class CookerAgent extends Agent {
    private final String name = "cooker";
    private final AID aid = getAID();
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("cooker agent added");
    }
}

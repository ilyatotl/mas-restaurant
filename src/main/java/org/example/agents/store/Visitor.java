package org.example.agents.store;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

public class Visitor extends Agent {
    private final String name = "visitor";
    private final AID aid = getAID();
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("visitor agent added");
    }
}

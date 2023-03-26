package org.example.agents.store;

import jade.core.Agent;
import jade.core.AID;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

public class StoreAgent extends Agent {
    private final String name = "store";
    public static AID aid;
    @Override
    protected void setup() {
        aid = getAID();
        AController.addNewAgent(this, name);
        System.out.println("Store agent " + getAID().getName() + " created");
    }
}

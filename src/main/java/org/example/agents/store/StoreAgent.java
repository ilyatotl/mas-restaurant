package org.example.agents.store;

import jade.core.Agent;
import jade.core.AID;

public class StoreAgent extends Agent {
    private final String name = "store";
    private final AID aid = getAID();
    protected void setup() {
        System.out.println("store agent added");
    }
}

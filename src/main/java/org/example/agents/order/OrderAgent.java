package org.example.agents.order;

import jade.core.AID;
import jade.core.Agent;

public class OrderAgent extends Agent {
    private final String name = "order";
    public static AID aid;

    @Override
    public void setup() {
        aid = getAID();
    }
}

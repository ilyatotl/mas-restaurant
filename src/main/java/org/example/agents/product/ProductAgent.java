package org.example.agents.product;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class ProductAgent extends Agent {
    private final String name = "product";
    private final AID aid = getAID();
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("product agent added");
    }
}
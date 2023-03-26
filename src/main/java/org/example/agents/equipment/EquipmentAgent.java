package org.example.agents.equipment;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class EquipmentAgent extends Agent {
    private final String name = "equipment";
    private final AID aid = getAID();
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("Equipment agent " + getAID().getName() + "created");
    }
}
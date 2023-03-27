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
<<<<<<< HEAD
        AController.log.info("Operation " + getAID().getName() + " created");
=======
        System.out.println("Operation agent for dish  " + getArguments()[1].toString() + " created");
>>>>>>> master
        AController.addNewAgent(this, name);

        addBehaviour(new SenderForCooking(getArguments()[0]));
    }
}

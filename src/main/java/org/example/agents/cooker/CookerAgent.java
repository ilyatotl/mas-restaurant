package org.example.agents.cooker;

import jade.core.AID;
import jade.core.Agent;
import org.example.AController;

public class CookerAgent extends Agent {
    private final String name = "cooker";
    public static AID aid;

    @Override
    protected void setup() {
        aid = getAID();
        AController.addNewAgent(this, name);
<<<<<<< HEAD
        AController.log.info("Cooker agent " + getAID().getName() + " created");
=======
        System.out.println("Cooker agent " + getArguments()[0].toString() + " with id " + getArguments()[1].toString() + " created");

        addBehaviour(new CookProducer(getArguments()));
>>>>>>> master
    }
}

package org.example.agents;

import jade.core.Agent;
import jade.core.AID;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

public class StoreAgent extends Agent {
    private final String name = "store";
    private final AID aid = getAID();
    @Override
    protected void setup() {
        AController.addNewAgent(this, name);
        System.out.println("store agent added");
        System.out.println(TJSONSerializer.getString("src/main/java/org/example/resources/product_types.txt"));
    }
}

package org.example.agents.equipment;

import com.google.gson.JsonObject;
import jade.core.AID;
import jade.core.Agent;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

public class EquipmentAgent extends Agent {
    private final String name = "equipment";
    public static AID aid;
    private String equipmentName;
    private String equipmentId;

    @Override
    protected void setup() {
        aid = getAID();
        AController.addNewAgent(this, name);

        JsonObject jsonEquipment = TJSONSerializer.getJsonFromString(getArguments()[0].toString());
        equipmentName = jsonEquipment.get("equip_name").getAsString();
        equipmentId = jsonEquipment.get("equip_id").getAsString();

        System.out.println("Equipment agent " + equipmentName + " with id " + equipmentId + " created");
    }
}
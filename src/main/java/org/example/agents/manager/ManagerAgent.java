package org.example.agents.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.AID;
import jade.core.Agent;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.cooker.CookerAgent;
import org.example.agents.equipment.EquipmentAgent;
import org.example.agents.store.StoreAgent;
import org.example.agents.visitor.VisitorAgent;

import java.util.HashMap;
import java.util.Map;

public class ManagerAgent extends Agent {
    private final String pathToProductTypes = "src/main/java/org/example/resources/product_types.txt";
    private final String pathToProducts = "src/main/java/org/example/resources/products.txt";
    private final String pathToVisitorOrders = "src/main/java/org/example/resources/visitor_orders.txt";
    private final String pathToCookers = "src/main/java/org/example/resources/cookers.txt";
    private final String pathToEquipment = "src/main/java/org/example/resources/equipment.txt";

    private final String name = "manager";
    private final AID aid = getAID();

    Map<String, String> visitors = new HashMap<>();
    Map<String, String> cookers = new HashMap<>();
    Map<String, String> equipment = new HashMap<>();

    @Override
    public void setup() {
        AController.addNewAgent(this, name);
        System.out.println("Manager agent " + aid.getName() + "created");

        createStore();
        createVisitors();
        createCookers();
        createEquipment();
    }

    private void createStore() {
        AController.addAgent(
                StoreAgent.class,
                "",
                new Object[]{TJSONSerializer.getString(pathToProductTypes), TJSONSerializer.getString(pathToProducts)}
        );
    }

    private void createVisitors() {
        JsonArray visitorsOrders = TJSONSerializer.getJson(pathToVisitorOrders).getAsJsonArray("visitors_orders");
        for (JsonElement jsonElement : visitorsOrders) {
            String visitorName = ((JsonObject)jsonElement).get("vis_name").getAsString();
            visitors.put(visitorName, AController.addAgent(VisitorAgent.class, visitorName, new Object[]{jsonElement}));
        }
    }

    private void createCookers() {
        JsonArray cooker = TJSONSerializer.getJson(pathToCookers).getAsJsonArray("cookers");
        for (JsonElement jsonElement : cooker) {
            String cookerName = ((JsonObject)jsonElement).get("cook_name").getAsString();
            cookers.put(cookerName, AController.addAgent(CookerAgent.class, cookerName, new Object[]{jsonElement}));
        }
    }

    private void createEquipment() {
        JsonArray equipments = TJSONSerializer.getJson(pathToEquipment).getAsJsonArray("equipment");
        for (JsonElement jsonElement : equipments) {
            String equipmentName = ((JsonObject)jsonElement).get("equip_name").getAsString();
            equipment.put(equipmentName, AController.addAgent(EquipmentAgent.class, equipmentName, new Object[]{jsonElement}));
        }
    }
}
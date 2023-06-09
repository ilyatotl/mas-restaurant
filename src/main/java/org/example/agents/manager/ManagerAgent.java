package org.example.agents.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.AID;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.cooker.CookerAgent;
import org.example.agents.equipment.EquipmentAgent;
import org.example.agents.store.StoreAgent;
import org.example.agents.visitor.VisitorAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerAgent extends jade.core.Agent {
    private final String pathToProductTypes = "src/main/java/org/example/resources/product_types.txt";
    private final String pathToProducts = "src/main/java/org/example/resources/products.txt";
    private final String pathToVisitorOrders = "src/main/java/org/example/resources/visitor_orders.txt";
    private final String pathToCookers = "src/main/java/org/example/resources/cookers.txt";
    private final String pathToEquipment = "src/main/java/org/example/resources/equipment.txt";

    private final String name = "manager";
    public static AID aid;

    Map<String, String> visitors = new HashMap<>();
    Map<String, String> equipment = new HashMap<>();

    List<AID> cookers = new ArrayList<>();
    List<String> cookersNames = new ArrayList<>();

    @Override
    public void setup() {
        aid = getAID();
<<<<<<< HEAD
        AController.log.info("Manager agent " + getAID().getName() + " created");
=======
        System.out.println("Manager agent created");
>>>>>>> master
        AController.addNewAgent(this, name);

        createStore();
        createVisitors();
        createCookers();
        createEquipment();

        addBehaviour(new MenuDispenser());
        addBehaviour(new ManagerOrderCreator());
        addBehaviour(new OperationManager(cookers, cookersNames));
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
            String visitorName = jsonElement.getAsJsonObject().get("vis_name").getAsString();
            visitors.put(visitorName, AController.addAgent(VisitorAgent.class, visitorName, new Object[]{jsonElement, visitorName}));
        }
    }

    private void createCookers() {
        JsonArray cooker = TJSONSerializer.getJson(pathToCookers).getAsJsonArray("cookers");
        for (JsonElement jsonElement : cooker) {
            String cookerName = jsonElement.getAsJsonObject().get("cook_name").getAsString();
            String cookerId = jsonElement.getAsJsonObject().get("cook_id").getAsString();

            cookers.add(new AID(AController.addAgent(CookerAgent.class, cookerName, new Object[]{cookerName, cookerId})));
            cookersNames.add(cookerName);
        }
    }

    private void createEquipment() {
        JsonArray equipments = TJSONSerializer.getJson(pathToEquipment).getAsJsonArray("equipment");
        for (JsonElement jsonElement : equipments) {
            String equipmentName = jsonElement.getAsJsonObject().get("equip_name").getAsString();
            equipment.put(equipmentName, AController.addAgent(EquipmentAgent.class, equipmentName, new Object[]{jsonElement}));
        }
    }
}
package org.example.agents.order;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import jade.core.AID;
import jade.core.Agent;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.operation.OperationAgent;

import java.util.HashMap;
import java.util.Map;

public class OrderAgent extends Agent {
    private final Map<String, AID> processes = new HashMap<>();
    private final String name = "order";
    public static AID aid;

    @Override
    public void setup() {
        aid = getAID();
        System.out.println("Order " + getAID().getName() + " created");
        AController.addNewAgent(this, name);

        createDishes();
    }

    private void createDishes() {
        JsonArray dishes = TJSONSerializer.getJsonFromString(getArguments()[0].toString()).getAsJsonArray("vis_ord_dishes");

        for (JsonElement dish : dishes) {
            String dishId = dish.getAsJsonObject().get("ord_dish_id").getAsString();
            String menuId = dish.getAsJsonObject().get("menu_dish").getAsString();

            System.out.println("Dish " + menuId + " was added with id " + dishId);

            processes.put(dishId, new AID(AController.addAgent(OperationAgent.class, dishId, new Object[]{dish})));
        }
    }
}

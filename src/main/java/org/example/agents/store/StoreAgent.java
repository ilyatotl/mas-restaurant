package org.example.agents.store;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.Agent;
import jade.core.AID;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

import java.util.HashMap;
import java.util.Map;

public class StoreAgent extends Agent {
    Map<String, JsonObject> products = new HashMap<>();
    private final String name = "store";
    public static AID aid;

    @Override
    protected void setup() {
        aid = getAID();
        System.out.println("Store agent created");
        AController.addNewAgent(this, name);
<<<<<<< HEAD
        AController.log.info("Store agent " + getAID().getName() + " created");
=======

        getProducts();

        addBehaviour(new ProductController(products));
    }

    private void getProducts() {
        JsonArray jsonProducts = TJSONSerializer.getJsonFromString(getArguments()[1].toString()).getAsJsonArray("products");
        String productIdes = "";
        for (JsonElement product : jsonProducts) {
            products.put(product.getAsJsonObject().get("prod_item_id").getAsString(), product.getAsJsonObject());
            productIdes += product.getAsJsonObject().get("prod_item_id").getAsString() + " ";
        }
        System.out.println("Products: " + productIdes + " added to the store");
>>>>>>> master
    }
}

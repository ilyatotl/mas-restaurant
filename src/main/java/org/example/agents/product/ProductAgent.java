package org.example.agents.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.AID;
import jade.core.Agent;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ProductAgent extends Agent {
    Map<String, JsonObject> products = new HashMap<>();
    private final String name = "product";
    public static AID aid;
    @Override
    protected void setup() {
        aid = getAID();
        System.out.println("Product agent " + getAID().getName() + " created");
        AController.addNewAgent(this, name);

        getProducts();
    }

    private void getProducts() {
        JsonArray jsonProducts = TJSONSerializer.getJsonFromString(getArguments()[1].toString()).getAsJsonArray("products");
        String productIdes = "";
        for (JsonElement product : jsonProducts) {
            products.put(product.getAsJsonObject().get("prod_item_id").getAsString(), product.getAsJsonObject());
            productIdes += product.getAsJsonObject().get("prod_item_id").getAsString() + " ";
        }
        System.out.println("Products: " + productIdes + " added to the store");
    }


}
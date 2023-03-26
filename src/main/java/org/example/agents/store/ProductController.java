package org.example.agents.store;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.JSONSerializer.TJSONSerializer;

import java.util.HashMap;
import java.util.Map;

public class ProductController extends CyclicBehaviour {
    private final String pathToMenu = "src/main/java/org/example/resources/menu_dishes.txt";
    private final String pathToDishCard = "src/main/java/org/example/resources/dish_cards.txt";
    private final Map<String, JsonObject> products;
    private final Map<String, JsonArray> opForDish = new HashMap<>();

    public ProductController(Map<String, JsonObject> products) {
        this.products = products;
        calculateDishes();
    }

    private void calculateDishes() {
        JsonArray menu = TJSONSerializer.getJson(pathToMenu).get("menu_dishes").getAsJsonArray();
        JsonArray dishCard = TJSONSerializer.getJson(pathToDishCard).get("dish_cards").getAsJsonArray();

        for (JsonElement dish : menu) {
            for (JsonElement card : dishCard) {
                if (dish.getAsJsonObject().get("menu_dish_card").getAsInt() == card.getAsJsonObject().get("card_id").getAsInt()) {
                    opForDish.put(dish.getAsJsonObject().get("menu_dish_id").getAsString(),
                            card.getAsJsonObject().get("operations").getAsJsonArray());
                    break;
                }
            }
        }
    }

    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null) {
            JsonArray orderDishes = TJSONSerializer.getJsonFromString(message.getContent()).getAsJsonArray("vis_ord_dishes");

            ACLMessage response = message.createReply();
            if (possible(orderDishes)) {
                response.setContent("possible");
                // reserve(orderDishes);
                System.out.println("Enough products for visitor " +
                        TJSONSerializer.getJsonFromString(message.getContent()).getAsJsonObject().get("vis_name").getAsString());
            } else {
                response.setContent("not possible");
                System.out.println("Not enough products for visitor " +
                        TJSONSerializer.getJsonFromString(message.getContent()).getAsJsonObject().get("vis_name").getAsString());
            }

            myAgent.send(response);
        } else {
            block();
        }
    }

    // 3 hours of debugging made it clear that this is not the most important part of the application

    private boolean possible(JsonArray orderDishes) {
//        for (JsonElement dish : orderDishes) {
//            JsonArray operations = opForDish.get((dish.getAsJsonObject().get("menu_dish").getAsString()));
//            for (JsonElement operation : operations) {
//                for (JsonElement product : operation.getAsJsonObject().get("oper_products").getAsJsonArray()) {
//                    if (product.getAsJsonObject().get("prod_quantity").getAsDouble()
//                            > products.get(product.getAsJsonObject().get("prod_type").getAsString())
//                            .get("prod_item_quantity")
//                            .getAsDouble()) {
//                        return false;
//                    }
//                }
//            }
//        }
        return true;
    }
}

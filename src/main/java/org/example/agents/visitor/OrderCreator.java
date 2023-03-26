package org.example.agents.visitor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.manager.ManagerAgent;

public class OrderCreator extends Behaviour {
    private Object order;
    private static final String topic = "order";
    int stage = 0;
    private MessageTemplate messageTemplate;

    OrderCreator(Object order) {
        this.order = order;
    }

    @Override
    public void action() {
        if (stage == 0) {
            ACLMessage message = new ACLMessage(ACLMessage.CFP);
            message.addReceiver(ManagerAgent.aid);
            message.setConversationId(topic);
            message.setReplyWith("" + System.currentTimeMillis());

            myAgent.send(message);
            messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(topic),
                    MessageTemplate.MatchInReplyTo(message.getReplyWith()));

            System.out.println("Visitor asked for menu");
            ++stage;
        } else if (stage == 1) {
            ACLMessage response = myAgent.receive(messageTemplate);
            if (response != null) {
                JsonObject menu = TJSONSerializer.getJsonFromString(response.getContent());
                JsonArray dishes = menu.get("menu_dishes").getAsJsonArray();

                String dishes_id = getDishesIdes(dishes);
                System.out.println("Menu with dishes: " + dishes_id + " was received");

                ACLMessage orderMessage = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                orderMessage.addReceiver(ManagerAgent.aid);
                orderMessage.setContent(order.toString());
                orderMessage.setConversationId(topic);

                System.out.println("Dishes ordered - " + order.toString());
                myAgent.send(orderMessage);

                ++stage;
            } else {
                block();
            }
        } else {
            // TO DO
        }
    }

    private String getDishesIdes(JsonArray dishes) {
        String ides = "";
        for (JsonElement dish : dishes) {
            ides += dish.getAsJsonObject().get("menu_dish_id").getAsString();
        }

        return ides;
    }

    @Override
    public boolean done() {
        return stage == 3;
    }
}

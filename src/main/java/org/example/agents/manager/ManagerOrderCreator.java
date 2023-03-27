package org.example.agents.manager;

import com.google.gson.JsonObject;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.AController;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.order.OrderAgent;

public class ManagerOrderCreator extends CyclicBehaviour {
    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null) {
            String order = message.getContent();
<<<<<<< HEAD
            AController.log.info("Manager receive from " + message.getSender().getName() + " order: " + order);
=======
            JsonObject jsonOrder = TJSONSerializer.getJsonFromString(order);
            System.out.println("Manager receive from " + jsonOrder.get("vis_name").getAsString() + " order: " + order);
>>>>>>> master

            AController.addAgent(OrderAgent.class,
                    message.getSender().getName(),
                    new Object[]{order, jsonOrder.get("vis_name").getAsString()});
        } else {
            block();
        }
    }
}

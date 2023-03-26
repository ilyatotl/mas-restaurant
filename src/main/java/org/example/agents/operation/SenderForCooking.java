package org.example.agents.operation;

import com.google.gson.JsonObject;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import org.example.JSONSerializer.TJSONSerializer;
import org.example.agents.manager.ManagerAgent;

public class SenderForCooking extends Behaviour {
    boolean send = false;
    private String ord_id;
    private String menu_id;


    public SenderForCooking(Object data) {
        JsonObject order = TJSONSerializer.getJsonFromString(data.toString());
        ord_id = order.get("ord_dish_id").getAsString();
        menu_id = order.get("menu_dish").getAsString();
    }

    @Override
    public void action() {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(ManagerAgent.aid);
        message.setContent(ord_id);
        myAgent.send(message);

        send = true;
    }

    @Override
    public boolean done() {
        return send;
    }
}

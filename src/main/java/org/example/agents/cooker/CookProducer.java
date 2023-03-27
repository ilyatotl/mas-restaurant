package org.example.agents.cooker;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.AController;

public class CookProducer extends CyclicBehaviour {
    private final String cookerName;
    private final String cookerId;

    public CookProducer(Object[] objects) {
        cookerName = objects[0].toString();
        cookerId = objects[1].toString();
    }


    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null) {
            String ordId = message.getContent();
            System.out.println("Cooker " + cookerName + " with id " + cookerId + " made order dish " + ordId);
        } else {
            block();
        }
    }
}

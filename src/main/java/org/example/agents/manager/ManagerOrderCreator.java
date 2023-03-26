package org.example.agents.manager;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.AController;
import org.example.agents.order.OrderAgent;

public class ManagerOrderCreator extends CyclicBehaviour {
    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null) {
            String order = message.getContent();
            System.out.println("Manager receive from " + message.getSender().getName() + " order: " + order);

            AController.addAgent(OrderAgent.class,
                    message.getSender().getName(),
                    new Object[]{order, message.getSender().getName()});
        } else {
            block();
        }
    }
}

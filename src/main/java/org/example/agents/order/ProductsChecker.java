package org.example.agents.order;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.agents.store.StoreAgent;

public class ProductsChecker extends Behaviour {

    private int stage = 0;
    private static final String topic = "order_products";
    private String products;
    private MessageTemplate messageTemplate;

    public ProductsChecker(String products) {
        this.products = products;
    }

    @Override
    public void action() {
        if (stage == 0) {
            ACLMessage message = new ACLMessage(ACLMessage.CFP);
            message.addReceiver(StoreAgent.aid);
            message.setContent(products);
            message.setReplyWith("" + System.currentTimeMillis());

            System.out.println("Order request about products" + " " + products);

            myAgent.send(message);
            messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(topic),
                    MessageTemplate.MatchInReplyTo(message.getReplyWith()));

            ++stage;
        } else if (stage == 1) {
            ACLMessage response = myAgent.receive(messageTemplate);
            if (response != null) {
                if (response.getContent().contentEquals("possible")) {
                    ++stage;
                } else {
                    stage = -1;
                }
            } else {
                block();
            }
        }
    }

    @Override
    public boolean done() {
        return stage == 2;
    }
}

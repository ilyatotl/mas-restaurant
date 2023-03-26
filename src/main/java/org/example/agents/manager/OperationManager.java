package org.example.agents.manager;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

public class OperationManager extends CyclicBehaviour {
    private int cookerNumber = 0;
    private final int cookerCount;
    List<AID> cookers;

    public OperationManager(List<AID> cookers) {
        cookerCount = cookers.size();
        this.cookers = cookers;
    }


    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage message = myAgent.receive(messageTemplate);

        if (message != null) {
            String ord_id = message.getContent();
            ACLMessage messageToCooker = new ACLMessage(ACLMessage.CFP);
            messageToCooker.addReceiver(cookers.get(cookerNumber));
            messageToCooker.setContent(ord_id);

            myAgent.send(messageToCooker);
            cookerNumber = (cookerNumber + 1) % cookerCount;

        } else {
            block();
        }
    }
}

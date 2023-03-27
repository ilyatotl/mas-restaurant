package org.example.agents.manager;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.example.JSONSerializer.TJSONSerializer;

public class MenuDispenser extends CyclicBehaviour {
    private final String pathToMenu = "src/main/java/org/example/resources/menu_dishes.txt";

    @Override
    public void action() {
        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage request = myAgent.receive(messageTemplate);

        if (request != null) {
            ACLMessage response = request.createReply();
            response.setPerformative(ACLMessage.PROPOSE);
            response.setContent(TJSONSerializer.getString(pathToMenu));
            System.out.println("Menu was send to visitor " + request.getContent());
            myAgent.send(response);
        } else {
            block();
        }
    }
}

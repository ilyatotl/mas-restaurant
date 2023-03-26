package org.example;

import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class AgentController {
    public static void adNewAgent(jade.core.Agent agent, String type) {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(agent.getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(type);
        serviceDescription.setName(agent.getName());
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(agent, dfAgentDescription);
        } catch (FIPAException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

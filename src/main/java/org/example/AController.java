package org.example;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import org.example.agents.manager.ManagerAgent;
import org.example.agents.store.StoreAgent;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AController {
    private static ContainerController containerController;
    public static Logger log = Logger.getLogger("Log");

    static {
        FileHandler fh;
        try {
            fh = new FileHandler("logs.txt");
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            log.info("My first log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AController() {
        final Runtime rt = Runtime.instance();
        final Profile p = new ProfileImpl();

        p.setParameter(Profile.MAIN_HOST, "localhost");
        p.setParameter(Profile.MAIN_PORT, "8081");
        p.setParameter(Profile.GUI, "true");

        containerController = rt.createMainContainer(p);
    }

    void start() {
        addAgent(ManagerAgent.class, "", null);
    }

    public static String addAgent(Class<?> clazz, String suffix, Object[] args) {
        try {
            AgentController agent =
                    containerController.createNewAgent(
                            MessageFormat.format("{0}{1}", clazz.getSimpleName(), suffix), clazz.getName(), args);
            agent.start();
            return agent.getName();
        } catch (StaleProxyException ex) {
            ex.printStackTrace(); // I prefer ff
        }
        return "";
    }
    public static void addNewAgent(jade.core.Agent agent, String type) {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(agent.getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(type);
        serviceDescription.setName(agent.getName());
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(agent, dfAgentDescription);
        } catch (FIPAException ex) {
            AController.log.info(ex.getMessage());
        }
    }
}

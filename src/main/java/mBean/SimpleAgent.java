package mBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.management.*;
import java.lang.management.ManagementFactory;

public class SimpleAgent {
    @Inject
    private PointCount pointCount;
    @Inject
    private Area area;

    public SimpleAgent(){

    }

    @PostConstruct
    public void startAgent(){
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName;
        try {
            objectName = new ObjectName("SimpleAgent:name=PointCount");
            if (!server.isRegistered(objectName)) {
                server.registerMBean(pointCount, objectName);
            }
            objectName = new ObjectName("SimpleAgent:name=Area");
            if (!server.isRegistered(objectName)){
                server.registerMBean(area, objectName);
            }

        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                 MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }
    public void logSimpleAgentStarted() {
        System.out.println("SimpleAgent.logSimpleAgentStarted");
    }

    public void startup(@Observes @Initialized(ApplicationScoped.class) Object context) {
        SimpleAgent a = new SimpleAgent();
        a.logSimpleAgentStarted();
    }
}

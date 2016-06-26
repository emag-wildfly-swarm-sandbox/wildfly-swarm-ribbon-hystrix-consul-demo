package wildflyswarm.ribbon_hystrix_consul.events;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.topology.TopologyArchive;

public class App {

  public static void main(String[] args) throws Exception {
    Container container = new Container(args);

    JAXRSArchive archive = ShrinkWrap.create(JAXRSArchive.class);
    archive.addPackage(App.class.getPackage());

    archive.as(TopologyArchive.class).advertise("events");

    container.start().deploy(archive);
  }

}

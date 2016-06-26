package wildflyswarm.ribbon_hystrix_consul.time;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/")
public class TimeController {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String now() {
    System.out.println("Accessed");
    return String.format("{\"now\":\"%s\"}", LocalDateTime.now().toString());
  }

}

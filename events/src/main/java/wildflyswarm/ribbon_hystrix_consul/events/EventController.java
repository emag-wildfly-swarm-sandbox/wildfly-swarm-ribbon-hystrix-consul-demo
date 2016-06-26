package wildflyswarm.ribbon_hystrix_consul.events;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import rx.Observable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/")
public class EventController {

  private static final List<Event> EVENTS = new ArrayList<>();

  private final TimeService time;

  public EventController() {
    this.time = TimeService.INSTANCE;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public void get(@Suspended AsyncResponse asyncResponse) {
    Event event = new Event();
    event.setName("GET");
    recordEvent(event, asyncResponse);
  }

  private void recordEvent(Event event, @Suspended AsyncResponse asyncResponse) {
    Observable<ByteBuf> obs = time.now().observe();
    obs.subscribe(
      result -> {
        try {
          System.out.println(result);
          ObjectMapper mapper = new ObjectMapper();
          ObjectReader reader = mapper.reader();
          JsonFactory factory = new JsonFactory();
          JsonParser parser = factory.createParser(new ByteBufInputStream(result));
          Map map = reader.readValue(parser, Map.class);
          event.setTimestamp(map);
          event.setId(EVENTS.size());
          EVENTS.add(event);
          asyncResponse.resume(EVENTS);
        } catch (IOException e) {
          System.err.println("ERROR: " + e.getLocalizedMessage());
          asyncResponse.resume(e);
        }
      },
      err -> {
        System.err.println("ERROR: " + err.getLocalizedMessage());
        asyncResponse.resume(err);
      });

    System.out.println("New event");
  }

}

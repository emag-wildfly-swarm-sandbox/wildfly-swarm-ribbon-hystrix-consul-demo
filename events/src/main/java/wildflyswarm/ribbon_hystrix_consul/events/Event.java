package wildflyswarm.ribbon_hystrix_consul.events;

import java.util.Map;

class Event {

  private int id;
  private String name;
  private Map timestamp;


  public Map getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Map timestamp) {
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

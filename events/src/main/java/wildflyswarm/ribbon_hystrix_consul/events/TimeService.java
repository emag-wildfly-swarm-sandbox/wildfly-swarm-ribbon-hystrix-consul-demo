package wildflyswarm.ribbon_hystrix_consul.events;

import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import io.netty.buffer.ByteBuf;

@ResourceGroup(name = "time")
public interface TimeService {

  TimeService INSTANCE = Ribbon.from(TimeService.class);

  @TemplateName("now")
  @Http(method = Http.HttpMethod.GET, uri = "/")
  @Hystrix(fallbackHandler = TimeFallbackHandler.class)
  RibbonRequest<ByteBuf> now();

}

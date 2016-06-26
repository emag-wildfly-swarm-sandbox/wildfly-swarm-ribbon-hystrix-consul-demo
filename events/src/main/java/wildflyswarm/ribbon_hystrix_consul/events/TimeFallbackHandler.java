package wildflyswarm.ribbon_hystrix_consul.events;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import rx.Observable;

import java.time.LocalDateTime;
import java.util.Map;

public class TimeFallbackHandler implements FallbackHandler<ByteBuf> {

  @Override
  public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
    String fallback = String.format("{\"now\":\"%s\"}", LocalDateTime.of(2016, 6, 27, 0, 0, 0, 0).toString());

    byte[] bytes = fallback.getBytes();
    ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
    byteBuf.writeBytes(bytes);

    return Observable.just(byteBuf);
  }

}

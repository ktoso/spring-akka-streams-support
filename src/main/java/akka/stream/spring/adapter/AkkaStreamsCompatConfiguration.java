package akka.stream.spring.adapter;

import akka.actor.ActorSystem;
import akka.event.Logging;
import org.springframework.core.ReactiveAdapterRegistry;

@Configuration
public class AkkaStreamsCompatConfiguration {

  @Autowired
  public RegisterAkkaStreams(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
    final ReactiveAdapterRegistry registry = requestMappingHandlerAdapter.getReactiveAdapterRegistry();

    final ActorSystem system = ActorSystem.create(Logging.simpleName(DemoApplication.class));
    new AkkaStreamsRegistrar(system).registerAdapters(registry);
  }
  
}



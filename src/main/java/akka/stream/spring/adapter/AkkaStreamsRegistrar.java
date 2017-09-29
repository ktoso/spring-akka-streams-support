package akka.stream.spring.adapter;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.util.Assert;

import static org.springframework.core.ReactiveTypeDescriptor.multiValue;

public class AkkaStreamsRegistrar {

  private final Materializer materializer;

  public AkkaStreamsRegistrar(ActorSystem system) {
    materializer = ActorMaterializer.create(system);
  }

  void registerAdapters(ReactiveAdapterRegistry registry) {
    Assert.notNull(registry, "registry must not be null");
    registry.registerReactiveType(
      multiValue(akka.stream.javadsl.Source.class, akka.stream.javadsl.Source::empty),
      source -> ((akka.stream.javadsl.Source<?, ?>) source).runWith(akka.stream.javadsl.Sink.asPublisher(AsPublisher.WITH_FANOUT), materializer),
      akka.stream.javadsl.Source::fromPublisher
    );
    
    registry.registerReactiveType(
      multiValue(akka.stream.scaladsl.Source.class, akka.stream.scaladsl.Source::empty),
      source -> ((akka.stream.scaladsl.Source<?, ?>) source).runWith(akka.stream.scaladsl.Sink.asPublisher(true), materializer),
      akka.stream.scaladsl.Source::fromPublisher
    );
    
//    registry.registerReactiveType(
//      singleRequiredValue(scala.concurrent.Future.class),
//      future -> Source.fromFuture(((scala.concurrent.Future<?>) future)).runWith(a),
//      RxReactiveStreams::toSingle
//    );
//    
//    registry.registerReactiveType(
//      noValue(rx.Completable.class, rx.Completable::complete),
//      source -> RxReactiveStreams.toPublisher((rx.Completable) source),
//      RxReactiveStreams::toCompletable
//    );
  }
}

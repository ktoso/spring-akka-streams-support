# spring-akka-streams-support

Makes this work:

```java

package com.example.demo;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.AsPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import akka.stream.javadsl.JavaFlowSupport;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.concurrent.Flow;

@RestController
public class SampleController {

  private final static ActorSystem system = ActorSystem.create("System");
  private final static Materializer mat = ActorMaterializer.create(system);

  private final Sink<String, Flow.Publisher<String>> asFlowPublisher =
    JavaFlowSupport.Sink.asPublisher(AsPublisher.WITH_FANOUT);

  @RequestMapping("/")
  public Source<String, NotUsed> index() {

    final Flow.Publisher<String> flowPub =
      Source.single("Hello world!").runWith(asFlowPublisher, mat);

    return JavaFlowSupport.Source.fromPublisher(flowPub);
  }

}


```

- It would allow spring users to easier get exposed to and use Akka Streams. "Pick people up where they are." as some people say.
  - Of course the best place to use Akka Streams is with Akka apps, not Spring apps, but it could lead to people "having a look at Akka" who otherwise wouldn't hm...
- Also proves the RS inter-op point.
- They don't really provide JDK9 types support yet, fun tweet opportunity ;-)

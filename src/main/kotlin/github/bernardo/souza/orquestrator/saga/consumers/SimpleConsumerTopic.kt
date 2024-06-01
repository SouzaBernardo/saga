package github.bernardo.souza.orquestrator.saga.consumers

import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component("simple-consumer-topic")
class SimpleConsumerTopic: Consumer<String> {

    override fun accept(t: String) {
        TODO("Not yet implemented")
    }

}



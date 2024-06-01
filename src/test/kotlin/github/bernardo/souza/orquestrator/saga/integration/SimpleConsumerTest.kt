package github.bernardo.souza.orquestrator.saga.integration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.binder.test.InputDestination
import org.springframework.cloud.stream.binder.test.OutputDestination
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration
import org.springframework.context.annotation.Import
import org.springframework.core.annotation.AliasFor
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.messaging.Message

import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Import(TestChannelBinderConfiguration::class)

class SimpleConsumerTest(
    @Autowired
    val kafkaTemplate: KafkaTemplate<String, Message<String>>
){

    companion object {
        @Container
        val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.0.0"))
    }

    @Test
    fun `test send message to topic`() {
        kafka.start()
        val message = MessageBuilder.withPayload("test").build()
        kafkaTemplate.send("simple-consumer-topic", message)
    }
}
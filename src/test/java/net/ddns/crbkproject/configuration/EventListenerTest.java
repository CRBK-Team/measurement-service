package net.ddns.crbkproject.configuration;

import net.ddns.crbkproject.infrastructure.MeasurementHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RabbitTestConfiguration.class)
@ActiveProfiles("test")
@AutoConfigureDataMongo
class EventListenerTest {

    @Autowired
    private TestRabbitTemplate template;

    @Autowired
    private RabbitListenerTestHarness harness;

    @Value("${spring.rabbitmq.measured-queue}")
    private String measuredQueue;

    private MeasurementHandler measurementHandler;

    @BeforeEach
    void setUp() {
        measurementHandler = harness.getSpy("measurement");
        assertThat(measurementHandler).isNotNull();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(measurementHandler);
    }

    @Test
    void testSendAndReceive() {
        // given
        String message = "{\n \"dev\":\"test-device\",\n \"sm\":50 \n}";

        // when
        this.template.convertSendAndReceive(measuredQueue, message);

        // then
        verify(measurementHandler, times(1)).handleMeasurement(message);
    }
}

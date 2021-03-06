package net.ddns.crbkproject.configuration;

import com.rabbitmq.client.Channel;
import net.ddns.crbkproject.domain.repository.SoilMoistureRepository;
import net.ddns.crbkproject.unit.InMemoryMongoSoilMoistureRepository;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

@TestConfiguration
@RabbitListenerTest(capture = true)
public class RabbitTestConfiguration {

    @Bean
    public TestRabbitTemplate template() {
        return new TestRabbitTemplate(connectionFactory());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        Connection connection = mock(Connection.class);
        Channel channel = mock(Channel.class);
        willReturn(connection).given(factory).createConnection();
        willReturn(channel).given(connection).createChannel(anyBoolean());
        given(channel.isOpen()).willReturn(true);
        return factory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    @Primary
    public SoilMoistureRepository soilMoistureRepository() {
        return new InMemoryMongoSoilMoistureRepository();
    }
}

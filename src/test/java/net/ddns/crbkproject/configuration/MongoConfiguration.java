package net.ddns.crbkproject.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

import static de.flapdoodle.embed.mongo.distribution.Version.Main;
import static de.flapdoodle.embed.process.runtime.Network.getFreeServerPort;
import static de.flapdoodle.embed.process.runtime.Network.localhostIsIPv6;

@TestConfiguration
public class MongoConfiguration {

    @Bean
    IMongodConfig mongodConfig() throws IOException {
        return new MongodConfigBuilder()
                .version(Main.PRODUCTION)
                .net(new Net(getFreeServerPort(), localhostIsIPv6()))
                .build();
    }

    @Bean(destroyMethod = "stop")
    public MongodExecutable mongodExecutable(IMongodConfig mongodConfig) {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        return starter.prepare(mongodConfig);
    }

    @Bean(destroyMethod = "stop")
    public MongodProcess mongodProcess(MongodExecutable mongodExecutable) throws IOException {
        return mongodExecutable.start();
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClient client = null;
        try {
            IMongodConfig mongodConfig = mongodConfig();
            mongodProcess(mongodExecutable(mongodConfig));
            client = MongoClients.create(new ConnectionString("mongodb:://localhost:" + mongodConfig.net().getPort()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}

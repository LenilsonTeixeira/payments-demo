package com.lteixeira.msorders.configuration;

import com.lteixeira.msorders.model.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.bootstrapServers:#{null}}")
    private String bootstrapServers;

    @Value("${kafka.clientId:#{null}}")
    private String clientId;

    @Value("${kafka.groupId:#{null}}")
    private String groupId;

    @Value("${kafka.topicPattern:#{null}}")
    private String topicPattern;

    @Bean
    public ReceiverOptions<String, Message> receiverOptions() {

        Map<String, Object> configuration = new HashMap<>();
        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configuration.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        configuration.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configuration.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Message.class);
        configuration.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        configuration.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        configuration.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        configuration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configuration.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, 1000);

        ReceiverOptions<String, Message> receiverOptions = ReceiverOptions.create(Collections.unmodifiableMap(configuration));
        receiverOptions.subscription(Pattern.compile(topicPattern));
        return receiverOptions.toImmutable();
    }
}


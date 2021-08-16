package ru.xpendence.karfatester.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.xpendence.karfatester.dto.StarshipDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 14.02.19
 * Time: 17:36
 * e-mail: vyacheslav.chernyshov@stoloto.ru
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${kafka.producer.id}")
    private String kafkaProducerId;
    @Value("${kafka.topic.name}")
    private String nameTopic;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        return props;
    }

    @Bean
    public ProducerFactory<Long, StarshipDto> producerStarshipFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean("kafkaTemplate")
    public KafkaTemplate<Long, StarshipDto> kafkaTemplate() {
        KafkaTemplate<Long, StarshipDto> template = new KafkaTemplate<>(producerStarshipFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean("kafkaTemplateString")
    public KafkaTemplate<Long, StarshipDto> kafkaTemplateString() {
        KafkaTemplate<Long, StarshipDto> template = new KafkaTemplate<>(producerStarshipFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        return new KafkaAdmin(configs);
    }


    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(nameTopic)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }
}

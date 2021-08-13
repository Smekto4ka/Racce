package ru.xpendence.kafkaserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.xpendence.kafkaserver.dto.AbstractDto;
import ru.xpendence.kafkaserver.dto.StarshipDto;

import java.time.Duration;
import java.util.Arrays;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 12.02.19
 * Time: 22:26
 * e-mail: 2262288@gmail.com
 */
@Service
@Slf4j
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;
    private final Consumer<Long, AbstractDto> consumer;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate,
                               ObjectMapper objectMapper, ConsumerFactory<Long, AbstractDto> consumerFactory) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
        this.consumer = consumerFactory.createConsumer();
        consumer.subscribe(Arrays.asList("test"));
        log();
    }

    @Override
    public StarshipDto save(StarshipDto dto) {
        return null;
    }

    @Override
    public void send(StarshipDto dto) {
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    @Override
    @KafkaListener(id = "Starship", topics = {"test"}, containerFactory = "kafkaListenerContainerFactory")
    public void consume(StarshipDto dto) {
        log.info("=> consumed {}", writeValueAsString(dto));
    }


    @KafkaListener(id = "ddd", topics = {"text"}, containerFactory = "stringFactory")
    public void consumeString(String dto) {
        log.info("=> consumed {}" + dto);
    }


   // @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    public void log() {
        ConsumerRecords<Long, AbstractDto> consumerRecords = consumer.poll(Duration.ofSeconds(1));

        for (ConsumerRecord<Long, AbstractDto> consumerRecord : consumerRecords) {
            for (Header header : consumerRecord.headers()) {
                log.info("header key " + header.key() + "header value " + new String(header.value()));
            }
        }
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}

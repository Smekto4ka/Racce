package ru.xpendence.karfatester.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.xpendence.karfatester.dto.StarshipDto;

import java.time.LocalTime;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 14.02.19
 *  e-mail: 2262288@gmail.com
 */
@Service
@Slf4j
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final KafkaTemplate<Long, String> kafkaStringTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.name}")
    private String nameTopic ;
    @Autowired
    public StarshipServiceImpl(@Qualifier("kafkaTemplate") KafkaTemplate kafkaStarshipTemplate,
                               @Qualifier("kafkaTemplateString") KafkaTemplate kafkaStringTemplate,
                               ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    @Override
    public void produce() {
        StarshipDto dto = createDto();
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send(nameTopic ,55L, dto);
    }

    @Scheduled(initialDelay = 15000, fixedDelay = 5000)
    public void produceString() {
        String str = String.valueOf(LocalTime.now().toNanoOfDay() / 1000000);
        log.info(str);
        kafkaStringTemplate.send("text",str );
    }

    private StarshipDto createDto() {
        return new StarshipDto("Starship " + (LocalTime.now().toNanoOfDay() / 1000000));
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

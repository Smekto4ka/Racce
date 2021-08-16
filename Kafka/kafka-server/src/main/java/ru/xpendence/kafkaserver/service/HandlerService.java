package ru.xpendence.kafkaserver.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.xpendence.kafkaserver.dto.StarshipDto;

@Service
@KafkaListener(id = "Starship", topics = {"test"})
@Log4j2
public class HandlerService {
    @KafkaHandler()
    public void listen(String str) {
        log.info("String : " + str);
    }


    @KafkaHandler
    public void listen(StarshipDto dto) {
        log.info("Dto : " + dto);
    }

    @KafkaHandler(isDefault = true)
    public void listen(Object obj) {
        log.info("Object : " + obj);
    }
}

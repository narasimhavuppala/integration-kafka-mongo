package com.upanshu.service.consumer;

import com.upanshu.service.dao.ProtagonistDao;
import com.upanshu.service.event.Protagonist;
import com.upanshu.service.event.database.ProtagonistInformation;
import com.upanshu.service.processor.EventMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final ProtagonistDao protagonistDao;
    private final EventMapper eventMapper;

    public KafkaConsumer(ProtagonistDao protagonistDao, EventMapper eventMapper) {
        this.protagonistDao = protagonistDao;
        this.eventMapper = eventMapper;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}",concurrency = "3",groupId = "group_id" )
    public void consume(@Payload ConsumerRecord<String,Protagonist> payload, Acknowledgment ack) {
        logger.info("Consumed Message from show -> {}", payload.value().getShowName());
        ProtagonistInformation protagonistInformation = eventMapper.mapProtagonistToProtagonistInformation(payload.value());
        protagonistDao.insertProtagonistData(protagonistInformation);
        logger.info("Message received from consumer 1");
    }
}

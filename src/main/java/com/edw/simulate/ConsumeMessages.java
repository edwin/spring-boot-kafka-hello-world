package com.edw.simulate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *     com.edw.simulate.ConsumeMessages
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Jan 2023 15:25
 */
@Service
public class ConsumeMessages {

    private Logger logger = LoggerFactory.getLogger(ConsumeMessages.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void listenGroup(String message) throws InterruptedException {
        Thread.sleep(10L);
        logger.info("Reading {} ", message);
    }
}
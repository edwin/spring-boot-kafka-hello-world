package com.edw.simulate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

/**
 * <pre>
 *     com.edw.simulate.ProduceMessages
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Jan 2023 15:26
 */
@Service
public class ProduceMessages {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    private Logger logger = LoggerFactory.getLogger(ProduceMessages.class);

    @Autowired
    public ProduceMessages(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value(value = "${spring.kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Scheduled(fixedDelay = 1L, initialDelay = 5L)
    public void sendMessage() {
        String message = "message-" + UUID.randomUUID().toString();
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sending {} ", message);
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to Send {} ", message);
            }
        });
    }
}

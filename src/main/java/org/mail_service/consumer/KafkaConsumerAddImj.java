package org.mail_service.consumer;

import lombok.RequiredArgsConstructor;
import org.mail_service.entity.KafkaEventAddImj;
import org.mail_service.service.TelegramService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaConsumerAddImj extends
        AbstractKafkaConsumer<KafkaEventAddImj> {

    private final TelegramService telegramService;

    @Transactional
    @KafkaListener(topics = "${kafka.topics.add_imj}")
    public void addNewUser(String message, Acknowledgment acknowledgement) {
        KafkaEventAddImj kafkaEventAddImj = listener(message, KafkaEventAddImj.class);
        telegramService.addImjMessage(kafkaEventAddImj);
        acknowledgement.acknowledge();
    }
}

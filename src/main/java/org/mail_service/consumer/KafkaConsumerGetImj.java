package org.mail_service.consumer;

import lombok.RequiredArgsConstructor;
import org.mail_service.entity.KafkaEventGetImj;
import org.mail_service.service.TelegramService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class KafkaConsumerGetImj extends
        AbstractKafkaConsumer<KafkaEventGetImj> {

    private final TelegramService telegramService;

    @Transactional
    @KafkaListener(topics = "${kafka.topics.get_imj}")
    public void addNewUser(String message, Acknowledgment acknowledgement) {
        KafkaEventGetImj kafkaEventGetImj = listener(message, KafkaEventGetImj.class);
        telegramService.getImjMessage(kafkaEventGetImj);
        acknowledgement.acknowledge();
    }
}

package org.mail_service.consumer;

import lombok.RequiredArgsConstructor;
import org.mail_service.entity.UserDto;
import org.mail_service.service.TelegramService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class KafkaConsumerUserCreate extends
        AbstractKafkaConsumer<UserDto> {

    private final TelegramService telegramService;

    @Transactional
    @KafkaListener(topics = "${kafka.topics.create_account}")
    public void addNewUser(String message, Acknowledgment acknowledgement) {
        UserDto userDto = listener(message, UserDto.class);
        telegramService.createUserMessage(userDto);
        acknowledgement.acknowledge();
    }
}




package org.mail_service.consumer;

import lombok.RequiredArgsConstructor;
import org.mail_service.entity.UserDto;
import org.mail_service.service.TelegramService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KafkaProducerUserCreate extends
        AbstractKafkaConsumer<UserDto> {

    private final TelegramService telegramService;

    @Transactional
    @KafkaListener(topics = "${kafka.topics.create_account}")
    @Async("executor")
    public void addNewUser(String message, Acknowledgment acknowledgement) {
        UserDto userDto = listener(message, UserDto.class);
        telegramService.send(userDto, "create");
        acknowledgement.acknowledge();
    }
}




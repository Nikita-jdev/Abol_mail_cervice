package org.mail_service.service;

import lombok.RequiredArgsConstructor;
import org.mail_service.config.TelegramBotConfig;
import org.mail_service.entity.KafkaEventAddImj;
import org.mail_service.entity.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramBotConfig telegramBotConfig;

    public void createUserMessage(UserDto user) {
        String messageText = "Create a new user " + user.getUserName();
        telegramBotConfig.send(user, messageText);
    }

    public void addImjMessage(KafkaEventAddImj kafkaEventAddImj) {
        String messageText = "In backed " + kafkaEventAddImj.getBucketName()
                             + "added images with weight " + kafkaEventAddImj.getImjSize();
        telegramBotConfig.send(kafkaEventAddImj.getUserDto(), messageText);
    }
}

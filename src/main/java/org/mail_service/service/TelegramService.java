package org.mail_service.service;

import lombok.RequiredArgsConstructor;
import org.mail_service.config.TelegramBotConfig;
import org.mail_service.entity.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramBotConfig telegramBotConfig;

    public void send(UserDto user, String messageText) {
        telegramBotConfig.send(user, messageText);
    }
}

package su.spiridonov.discord2telegram.telegram.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import su.spiridonov.discord2telegram.BotRunner;
import su.spiridonov.discord2telegram.discord.helper.DiscordMessageHelper;
import su.spiridonov.discord2telegram.telegram.TelegramBotConfig;

public class TelegramMessageHelper extends TelegramBotConfig {
    private static Logger logger = LoggerFactory.getLogger(TelegramMessageHelper.class);
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String dsChatId = BotRunner.findDiscordChatByTelegram(update.getMessage().getChatId());
            if (!dsChatId.contains("!"))
                DiscordMessageHelper.sendMessage(update, Long.valueOf(dsChatId));
        }
    }

    public void sendMessage(Long tgChatId, String dsMsg) {
        SendMessage message = new SendMessage(Long.toString(tgChatId), dsMsg);
        try {
            execute(message);
            logger.debug(dsMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

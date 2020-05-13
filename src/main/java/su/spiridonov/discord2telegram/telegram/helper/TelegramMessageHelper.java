package su.spiridonov.discord2telegram.telegram.helper;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import su.spiridonov.discord2telegram.discord.helper.DiscordMessageHelper;
import su.spiridonov.discord2telegram.telegram.TelegramBotConfig;

public class TelegramMessageHelper extends TelegramBotConfig {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            DiscordMessageHelper.sendMessage(update);
        }
    }

    public void sendMessage(Long tgChatId, String dsMsg) {
        SendMessage message = new SendMessage()
                .setChatId(tgChatId)
                .setText(dsMsg);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

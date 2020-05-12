package su.spiridonov.discord2telegram.telegram.helper;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import su.spiridonov.discord2telegram.discord.DiscordBot;
import su.spiridonov.discord2telegram.discord.helper.DiscordMessageHelper;
import su.spiridonov.discord2telegram.telegram.TelegramBot;
import su.spiridonov.discord2telegram.telegram.TelegramBotConfig;

public class TelegramMessageHelper extends TelegramBotConfig {
    private static final String FROM_TELEGRAM_PREFIX = "FROM TELEGRAM\n ";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getChatId().equals(TelegramBotConfig.getCHAT_ID()))
                DiscordMessageHelper.sendMessage(FROM_TELEGRAM_PREFIX +
                        update.getMessage().getFrom().getUserName() + ":" +
                        update.getMessage().getText());
        }
    }

    public void sendMessage(String msg) {
        SendMessage message = new SendMessage()
                .setChatId(TelegramBotConfig.getCHAT_ID())
                .setText(msg);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

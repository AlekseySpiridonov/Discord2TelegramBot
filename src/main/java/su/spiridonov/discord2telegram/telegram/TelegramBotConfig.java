package su.spiridonov.discord2telegram.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import su.spiridonov.discord2telegram.BotRunner;

import java.util.Properties;

public abstract class TelegramBotConfig extends TelegramLongPollingBot {
    private String BOT_USERNAME;
    private String BOT_TOKEN;
    private static long CHAT_ID;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public static long getCHAT_ID() {
        return CHAT_ID;
    }

    public TelegramBotConfig() {
        Properties properties = BotRunner.getSystemProperties();
        this.BOT_USERNAME = properties.getProperty("telegram_botname");
        this.BOT_TOKEN = properties.getProperty("telegram_token");
        this.CHAT_ID = Long.parseLong(properties.getProperty("telegram_chatId"));
    }
}

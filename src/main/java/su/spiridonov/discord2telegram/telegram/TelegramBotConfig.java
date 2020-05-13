package su.spiridonov.discord2telegram.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import su.spiridonov.discord2telegram.BotRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class TelegramBotConfig extends TelegramLongPollingBot {
    private String BOT_USERNAME;
    private String BOT_TOKEN;
    private static List<Long> CHAT_IDs;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public static List<Long> getCHAT_IDs() {
        return CHAT_IDs;
    }

    public TelegramBotConfig() {
        Properties properties = BotRunner.getSystemProperties();
        Properties chatIdsProperties = BotRunner.getChatsForSynchronisation();

        this.BOT_USERNAME = properties.getProperty("telegram_botname");
        this.BOT_TOKEN = properties.getProperty("telegram_token");
        CHAT_IDs = new ArrayList<>();
        for (Object k : chatIdsProperties.keySet()) {
            String tgChatId = chatIdsProperties.getProperty(k.toString());
            CHAT_IDs.add(Long.parseLong(tgChatId));
        }
    }
}

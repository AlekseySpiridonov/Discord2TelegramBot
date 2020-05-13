package su.spiridonov.discord2telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import su.spiridonov.discord2telegram.discord.DiscordBot;
import su.spiridonov.discord2telegram.discord.DiscordBotConfig;
import su.spiridonov.discord2telegram.telegram.TelegramBot;
import su.spiridonov.discord2telegram.telegram.TelegramBotConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BotRunner {
    private static Properties systemProperties;
    private static Properties chatsForSynchronisation;
    private static Logger logger = LoggerFactory.getLogger(BotRunner.class);

    public static void runBots() {
        try {
            loadSystemProperties();
        } catch (Exception e) {
            logger.error("CRITICAL ISSUE: System properties can't load correctly:" + e);
            System.exit(-1);
        }
        TelegramBot.runNewBot();
        DiscordBot.startBot();
    }

    public static Properties getSystemProperties() {
        return systemProperties;
    }

    public static Properties getChatsForSynchronisation() {
        return chatsForSynchronisation;
    }

    private static void loadSystemProperties() throws IOException {
        String config_path = System.getProperty("config");
        Properties properties = new Properties();
        properties.load(new FileInputStream(config_path));
        systemProperties = properties;


        String pathToBaseWithChats = properties.getProperty("chats_for_sync");
        properties = new Properties();
        properties.load(new FileInputStream(pathToBaseWithChats));
        chatsForSynchronisation = properties;
    }

    public static Long findDiscordChatByTelegram(Long tgChatId) {
        for (Object k : chatsForSynchronisation.keySet()) {
            if (chatsForSynchronisation.getProperty(k.toString()).equals(tgChatId.toString())) {
                return Long.parseLong(k.toString());
            }
        }
        return null;
    }

    public static Long findTelegramChatByDiscordId(Long dsChatId) {
        String tgChatId = null;
        try {
            tgChatId = chatsForSynchronisation.getProperty(dsChatId.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
        if (tgChatId != null) {
            return Long.parseLong(tgChatId);
        }
        return null;
    }
}

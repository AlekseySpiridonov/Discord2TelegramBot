package su.spiridonov.discord2telegram.discord;

import su.spiridonov.discord2telegram.BotRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DiscordBotConfig {
    static String TOKEN;
    static List<Long> DISCORD_CHAT_IDs;
    static DiscordBotConfig discordBotConfig;

    public DiscordBotConfig() {
        Properties properties = BotRunner.getSystemProperties();
        Properties chatIdsProperties = BotRunner.getChatsForSynchronisation();

        TOKEN = properties.getProperty("discord_token");
        DISCORD_CHAT_IDs = new ArrayList<>();
        for (Object k : chatIdsProperties.keySet()) {
            String dsChatId = k.toString();
            DISCORD_CHAT_IDs.add(Long.parseLong(dsChatId));
        }
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static List<Long> getDiscordChatIds() {
        return DISCORD_CHAT_IDs;
    }

    public static DiscordBotConfig getDiscordConfig() {
        if (discordBotConfig == null) {
            discordBotConfig = new DiscordBotConfig();
        }
        return discordBotConfig;
    }
}

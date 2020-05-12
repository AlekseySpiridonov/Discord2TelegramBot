package su.spiridonov.discord2telegram.discord;

import su.spiridonov.discord2telegram.BotRunner;

import java.util.Properties;

public class DiscordBotConfig {
    static String TOKEN;
    static long DISCORD_CHAT_ID;
    static DiscordBotConfig discordBotConfig;

    public DiscordBotConfig() {
        Properties properties = BotRunner.getSystemProperties();
        TOKEN = properties.getProperty("discord_token");
        DISCORD_CHAT_ID = Long.parseLong(properties.getProperty("discord_chatId"));
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public  static long getDiscordChatId() {
        return DISCORD_CHAT_ID;
    }

    public static DiscordBotConfig getDiscordConfig() {
        if (discordBotConfig == null) {
            discordBotConfig = new DiscordBotConfig();
        }
        return discordBotConfig;
    }
}

package su.spiridonov.discord2telegram.discord.helper;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.TextChannel;
import discord4j.core.object.util.Snowflake;
import su.spiridonov.discord2telegram.discord.DiscordBot;
import su.spiridonov.discord2telegram.discord.DiscordBotConfig;
import su.spiridonov.discord2telegram.telegram.helper.TelegramMessageHelper;

public class DiscordMessageHelper {
    private static String FROM_DISCORD_PREFIX = "FROM DISCORD\n ";

    private static void sendMessage(DiscordClient client, String msg) {
        Snowflake discordChat = Snowflake.of(DiscordBotConfig.getDiscordChatId());
        TextChannel channel = (TextChannel) client.getChannelById(discordChat).block();
        channel.createMessage(msg).subscribe();
    }

    public static void sendMessage(String msg) {
        sendMessage(DiscordBot.getClient(), msg);
    }

    public static void onUpdateReceived(Message message) {
        TelegramMessageHelper t = new TelegramMessageHelper();
        if (message.getChannelId().equals(Snowflake.of(DiscordBotConfig.getDiscordChatId())))
            t.sendMessage(FROM_DISCORD_PREFIX +
                    message.getAuthor().get().getUsername() + ":" + message.getContent().get());
    }

}

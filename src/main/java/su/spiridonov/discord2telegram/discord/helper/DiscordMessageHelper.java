package su.spiridonov.discord2telegram.discord.helper;

import discord4j.core.DiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.common.util.Snowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import su.spiridonov.discord2telegram.placeholders.MessagePlaceHolders;
import su.spiridonov.discord2telegram.BotRunner;
import su.spiridonov.discord2telegram.discord.DiscordBot;
import su.spiridonov.discord2telegram.telegram.helper.TelegramMessageHelper;

public class DiscordMessageHelper {
    private static Logger logger = LoggerFactory.getLogger(DiscordMessageHelper.class);
    private static void sendMessage(DiscordClient client, Long dsChatId, String msg) {
        Snowflake discordChat = Snowflake.of(dsChatId);
        client.getChannelById(discordChat).createMessage(msg).block();
        logger.debug(msg);
    }

    public static void sendMessage(Update tgMsg, Long dsChatId) {
        String msg = MessagePlaceHolders.FROM_TELEGRAM_PREFIX +
                tgMsg.getMessage().getFrom().getUserName() + ": " +
                tgMsg.getMessage().getText();
        if (dsChatId != null)
            sendMessage(DiscordBot.getClient(), dsChatId, msg);
    }

    public static void onUpdateReceived(Message message) {
        TelegramMessageHelper t = new TelegramMessageHelper();

        String tgChatId = BotRunner.findTelegramChatByDiscordId(message.getChannelId().asLong());
        if (tgChatId != null || !tgChatId.contains("!"))
            t.sendMessage(Long.valueOf(tgChatId), MessagePlaceHolders.FROM_DISCORD_PREFIX +
                    message.getAuthor().get().getUsername() + ": " + message.getContent());
    }

}

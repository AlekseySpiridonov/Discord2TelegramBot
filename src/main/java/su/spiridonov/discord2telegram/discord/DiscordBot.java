package su.spiridonov.discord2telegram.discord;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.spiridonov.discord2telegram.discord.helper.DiscordMessageHelper;

public class DiscordBot {

    static DiscordClient client = null;
    static String botName;
    static String botDiscriminator;

    private static Logger logger = LoggerFactory.getLogger(DiscordBot.class);

    public static void startBot() {
        DiscordBotConfig discordBotConfig = DiscordBotConfig.getDiscordConfig();
        client = DiscordClientBuilder.create(discordBotConfig.getTOKEN()).build();
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(ready -> {
                            botName = ready.getSelf().getUsername();
                            botDiscriminator = ready.getSelf().getDiscriminator();
                            logger.info("Logged in as " + botName);
                        }
                );

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .subscribe(event -> {
                    Message message = event.getMessage();
                    if (!isItMyMessage(message))
                        DiscordMessageHelper.onUpdateReceived(message);
                });

        client.login().block();
    }

    public static DiscordClient getClient() {
        return client;
    }

    private static boolean isItMyMessage(Message msg) {
        if (msg.getAuthor().get().getUsername().equals(botName) &&
                msg.getAuthor().get().getDiscriminator().equals(botDiscriminator)) {
            return true;
        } else {
            return false;
        }
    }
}

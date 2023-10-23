package su.spiridonov.discord2telegram.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import su.spiridonov.discord2telegram.telegram.helper.TelegramMessageHelper;

public class TelegramBot {
    private static Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    public static void runNewBot() {
        TelegramBotsApi botsApi;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramMessageHelper());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

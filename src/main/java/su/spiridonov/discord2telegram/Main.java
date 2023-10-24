package su.spiridonov.discord2telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Discord2TelegramBot v"+Main.class.getPackage().getImplementationVersion());
        BotRunner botRunner = new BotRunner();
        botRunner.runBots();
    }
}

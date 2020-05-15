package su.spiridonov.discord2telegram;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class FindCorrectChatIDTest {
    private static Logger logger = LoggerFactory.getLogger(FindCorrectChatIDTest.class);
    BotRunner botRunner = null;
    Long dID4Test = Long.valueOf(111); // Discord Chat ID
    Long tgID4Test = Long.valueOf(222); // Telegram Chat ID

    @Before
    public void createContext() {
        System.setProperty("config", "src/test/resources/system.properties.test");
        botRunner = new BotRunner();
    }

    @Test
    public void findTelegramChatByDiscordId() {
        assertTrue(tgID4Test.equals(botRunner.findTelegramChatByDiscordId(dID4Test)));
    }

    @Test
    public void findDiscordChatByTelegram() {
        assertTrue(dID4Test.equals(botRunner.findDiscordChatByTelegram(tgID4Test)));
    }


}
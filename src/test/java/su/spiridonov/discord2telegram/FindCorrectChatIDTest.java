package su.spiridonov.discord2telegram;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class FindCorrectChatIDTest {
    private static Logger logger = LoggerFactory.getLogger(FindCorrectChatIDTest.class);
    BotRunner botRunner = null;
    String dsID4Test = "111"; // Discord Chat ID
    String tgID4Test = "222"; // Telegram Chat ID

    String tgID4TestRead = "!00"; // Discord Chat ID Read Only
    String dsID4TestRead = "!00"; // Telegram Chat ID Read Only

    @Before
    public void createContext() {
        System.setProperty("config", "src/test/resources/system.properties.test");
        botRunner = new BotRunner();
    }

    @Test
    public void findTelegramChatByDiscordId() {
        assertTrue(tgID4Test.equals(botRunner.findTelegramChatByDiscordId(Long.valueOf(dsID4Test))));
    }

    @Test
    public void findTelegramChatByDiscordIdRead() {
        assertTrue(null == botRunner.findTelegramChatByDiscordId(Long.valueOf(dsID4TestRead.substring(1))));
    }

    @Test
    public void findDiscordChatByTelegram() {
        assertTrue(dsID4Test.equals(botRunner.findDiscordChatByTelegram(Long.valueOf(tgID4Test))));
    }

    @Test
    public void findDiscordChatByTelegramRead() {
        assertTrue(null == botRunner.findDiscordChatByTelegram(Long.valueOf(tgID4TestRead.substring(1))));
    }


}
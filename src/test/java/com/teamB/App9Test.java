package com.teamB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class App9Test {

    @Test
    public void shouldAnswerWithTrue() {
        Player mike = new Player("Mike", 0);
        mike.useUndo();
        assertTrue(mike.getNumberOfUndos() == 0);
    }

    @Test
    public void chatTest() {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        chatBot bot =new chatBot();

            try

        {

            telegramBotsApi.registerBot(bot);
        }
            catch(
        TelegramApiRequestException e)

        {
            e.printStackTrace();
        }
        assertTrue(bot.getBotUsername().compareTo("risk117Bot") ==0);
    }

    @Test
    public void chatTest2() {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        chatBot bot =new chatBot();

        try

        {

            telegramBotsApi.registerBot(bot);
        }
        catch(
                TelegramApiRequestException e)

        {
            e.printStackTrace();
        }
        assertTrue(bot.getBotToken().compareTo("774469753:AAFU9MopSKroH1nfs-U-7_SEk6C3RaTlGiA") ==0);
    }



}

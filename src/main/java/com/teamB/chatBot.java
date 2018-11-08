package com.teamB;

import com.pengrad.telegrambot.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class chatBot extends TelegramLongPollingBot {

    public void writeToReceivedMessageToFile(String msg){
        try {
            File file = new File("fileToS3");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n"+ msg);
            fr.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message_text;

        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            System.out.println(message_text);
            writeToReceivedMessageToFile(message_text);
        }
    }

    //hardcode chatID just for now
    public void sendMessage(String message){
        SendMessage message12 = new SendMessage() // Create a message object object
                .setChatId("-265208817")
                .setText(message);

        try {
            execute(message12); // Sending our message object to user);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "risk117Bot";
    }

    @Override
    public String getBotToken() {
        return "774469753:AAFU9MopSKroH1nfs-U-7_SEk6C3RaTlGiA";
    }




    public static void main(String []args){
        String input[] = new String[1];
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        chatBot bot = new chatBot();

        try{

            telegramBotsApi.registerBot(bot);
        }
        catch(TelegramApiRequestException e){
            e.printStackTrace();
        }

    }
}

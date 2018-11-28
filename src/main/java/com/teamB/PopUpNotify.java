package com.teamB;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;

public class PopUpNotify
{
    PopUpNotify(){

    }
    public boolean fileCheck(File file, long lastModified){
        file = new File("fileToS3");
        //long lastMod = file.lastModified();
        long lastMod = lastModified;
        while(lastMod == file.lastModified()){
        }
        return true;
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }




    public static void main(String []args) {

    }



}
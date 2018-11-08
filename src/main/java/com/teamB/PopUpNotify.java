package com.teamB;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
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
//        boolean didFileChange = false;
//
//
//        TimerTask task = new FileWatcher(new File("fileToS3"), didFileChange);
//
//        Timer timer = new Timer();
//        timer.schedule(task, new Date(), 1000);
//
//        if((((FileWatcher) task).getTruth() == true)){
//            System.out.println("works");
//        }

        File file1 = new File("fileToS3");
        long lastMod = file1.lastModified();

        PopUpNotify my = new PopUpNotify();

        try {
            File file = new File("fileToS3");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\ndata");
            fr.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        boolean myTruth = my.fileCheck(new File("fileToS3"),lastMod);
        System.out.println(myTruth);


    }



}
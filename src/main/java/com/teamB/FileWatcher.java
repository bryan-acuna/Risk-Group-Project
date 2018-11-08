package com.teamB;

import java.util.*;
import java.io.*;

public class FileWatcher extends TimerTask {
    private long timeStamp;
    private File file;
    private boolean truth;

    public FileWatcher( File file, boolean didFileChange ) {
        this.file = file;
        this.timeStamp = file.lastModified();
        this.truth = didFileChange;
    }

    public void setTruth(boolean bool){
        this.truth = bool;
    }
    public boolean getTruth(){
        return this.truth;
    }

    public final void run() {
        long timeStamp = file.lastModified();

        if( this.timeStamp != timeStamp ) {
            this.timeStamp = timeStamp;
            onChange(file);
            //System.out.println("Filechanged");
        }
    }

    protected  void onChange( File file ){
        // here we code the action on a change
        System.out.println("File " + file.getName() + " have change !");
        this.truth = true;
    }
}
package com.teamB;

import javax.swing.JOptionPane;

public class PopUpNotify
{
    PopUpNotify(){

    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String []args) {
        infoBox("you are being attacked", "fight him");
    }
}
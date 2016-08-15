/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softidea.www.public_access.login_user;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author deepalsuranga
 */
public class DisplayTrayIcon {

    static TrayIcon trayIcon;

    public DisplayTrayIcon() {

        ShowTratIcon();
    }

    public static void ShowTratIcon(){

        try {
            
            if (!SystemTray.isSupported()) {
                System.out.println("=========== No tray suppoted===========================");
            }
            trayIcon = new TrayIcon(CreateIcon("/FormatFactoryicon100.png", "Softidea Cash Collector"));
            final SystemTray tray = SystemTray.getSystemTray();
            
            try {
                tray.add(trayIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            Logger.getLogger(DisplayTrayIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param path
     * @param desc
     * @return
     * @throws java.lang.Exception
     */
    protected static Image CreateIcon(String path, String desc){

        URL ImageURL = DisplayTrayIcon.class.getResource(path);

        return (new ImageIcon(ImageURL, desc)).getImage();

    }

}

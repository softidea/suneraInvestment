/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsczone.www.lookAndFeel;

import javax.swing.UIManager;

/**
 *
 * @author deepalsuranga
 */
public class pro_lookandfeel {

    public static void Set() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

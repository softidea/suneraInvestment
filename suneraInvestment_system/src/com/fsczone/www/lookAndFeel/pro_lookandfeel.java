
package com.fsczone.www.lookAndFeel;

import javax.swing.UIManager;


public class pro_lookandfeel {

    public static void Set() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

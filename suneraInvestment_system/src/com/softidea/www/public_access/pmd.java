/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softidea.www.public_access;

import javax.swing.JTextField;

/**
 *
 * @author deepalsuranga
 */
public class pmd {

    public static boolean EmtyisTextFiled(JTextField jTextField) {

        boolean isEm = jTextField.getText().isEmpty();
        boolean iss = false;
        if (isEm==true) {
            iss = false;
        }else if(isEm==false){
        iss=true;
        }
        return iss;
        

    }

}

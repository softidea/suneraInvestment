/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softidea.www.private_access.methods;

/**
 *
 * @author deepalsuranga
 */
public class md_Calc {

    public static String stringTodoubleString(String AmounttoTextFiled00) {
        Double AmountD = Double.parseDouble(AmounttoTextFiled00);

        Long roundAmount = Math.round(AmountD);

        int Amount_int = roundAmount.intValue();

        String Amount_text = Amount_int + ".00";

        return Amount_text;

    }

    public static double stringTodouble(String AmounttoTextFiled00) {
        Double AmountD = Double.parseDouble(AmounttoTextFiled00);

        Long roundAmount = Math.round(AmountD);

        int Amount_int = roundAmount.intValue();

        String Amount_text = Amount_int + ".00";

        double new_amount = Double.parseDouble(Amount_text);

        return new_amount;

    }

}

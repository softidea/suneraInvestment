/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softidea.www.private_access.methods;

import com.softidea.www.public_connection.MC_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deepalsuranga
 */
public class md_loans {

    /**
     *
     * @param id_fund
     * @param loanAmount
     * @return due_fundAmount
     */
    public static double minToFunderFund(int id_fund, String loanAmount) {
        double updated_due_fundAmount = 0.00;
        String amount = "0.00";
        int id = 0;
        try {
            // me funder ge
            ResultSet rs_fund = MC_DB.myConnection().createStatement().executeQuery("select * from fund where idfund='" + id_fund + "'");

            while (rs_fund.next()) {
                amount = rs_fund.getString("fund_update");
                id = rs_fund.getInt("idfund");

            }
            
            double fund_update = md_Calc.stringTodouble(amount);
            double lon_amount = md_Calc.stringTodouble(loanAmount);
            updated_due_fundAmount = fund_update - lon_amount;

            MC_DB.update_data("UPDATE fund SET fund_update='" + updated_due_fundAmount + "' where idfund='"+id+"'");
            
            System.out.println("OKKKK-update fund!");

        } catch (SQLException ex) {
            System.out.println("Method-minToFunderFund:error");
            ex.printStackTrace();
        }

        return updated_due_fundAmount;

    }

}

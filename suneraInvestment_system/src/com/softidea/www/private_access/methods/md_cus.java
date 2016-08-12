package com.softidea.www.private_access.methods;

import com.softidea.www.public_connection.MC_DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class md_cus {

    public static boolean saveCustomer(String nic, String fullname, String address, String phoneNumber, String gender_o, String loan_no, String loan_date, String loan_amount, String loan_mainperiodtype, String loan_period, String loan_installment, String additional_interest, String payable_amount, String status, String interest, int idfunder) {
        boolean b = false;
        try {
            ResultSet rs1;
            String qry_add_customer = "INSERT INTO customer(cus_nic,cus_fullname,cus_address,cus_contact,cus_gender) VALUES ('" + nic.trim().toUpperCase() + "','" + fullname.trim() + "','" + address.trim() + "','" + phoneNumber.trim() + "','" + gender_o + "');";
            //String qry_add_customer = "CALL addCus('" + nic.trim().toUpperCase() + "','" + fullname.trim() + "','" + address.trim() + "','" + phoneNumber.trim() + "','" + gender_o + "');";
            Connection myConnection = MC_DB.myConnection();
            Statement createStatement = myConnection.createStatement();
            createStatement.executeUpdate(qry_add_customer);
            /////////////////////////////////////////////////////////////////
            long insertId = 0;
            PreparedStatement getLastInsertId = myConnection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rslast = getLastInsertId.executeQuery();
            if (rslast.next()) {
                insertId = rslast.getLong("last_insert_id()");

            }
            //System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD::::D" + insertId);
            String due_amount = "0.00";

            String qry_add_loan = "INSERT INTO loans (loan_no,loan_date,loan_amount,loan_mainperiodtype,loan_period,loan_installment,additional_interest,payable_amount,due_loan_amount,loan_status,idcustomer,idfunder) VALUES('" + loan_no + "','" + loan_date + "','" + loan_amount + "','" + loan_mainperiodtype + "','" + loan_period + "','" + loan_installment + "','" + additional_interest + "','" + payable_amount + "','" + due_amount + "','" + status + "'," + insertId + "','" + idfunder + "');";

            MC_DB.update_data(qry_add_loan);
            System.out.println("OK Customer And Loan Successfully Added!");
            b = true;
        } catch (Exception e) {
            b = false;
            System.out.println("Add Customer-md_cus-saveLoan : ");
            e.printStackTrace();
        }
        return b;
    }

    public static void loadCustomer() {
        try {
            ResultSet resultSet = null;
            try {
                resultSet = MC_DB.search_AlluseTable("customer");
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(md_cus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

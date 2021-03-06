package com.softidea.www.private_access.adminstrator;

import com.softidea.www.public_connection.MC_DB;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Admin_cashManagment extends javax.swing.JPanel {

    double RI = 0;

    double TOT_LOANS = 0;
    double TOT_INSTALLMENT = 0;
    double TOT_WITHDRAWAL = 0;
    double TOT_FUNDS = 0;

    double TOT_CAPITAL = 0;
    double TOT_CAPITAL_LOANS = 0;
    double OUTSTANDING_CAPITAL = 0;
    double OUTSTANDING_INTEREST = 0;
    double TOT_ASSET = 0;

    public Admin_cashManagment() {
        initComponents();
        new Thread(() -> {
            try {
                setCurrentDate();
                viewAllCash();
                viewCapital();
                viewLoanDueAmounts();
                viewTotalloans();
                getOUtstandingInterest();
                getReceivedInterest();
                setDueTotalInterest();
                viewCashTableData();
                viewTotalAssets();
                getTotalRI();
                calArrius();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

//view all cash account
    public void viewAllCash() {
//        System.out.println("view alll;;; date");
        DefaultTableModel dtm = (DefaultTableModel) tb_cashAccount.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM cash_account");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("idcash_account"));
                v.add(rs.getString("amount"));
                v.add(rs.getString("cash_ac_type"));
                v.add(rs.getString("cash_ac_discription"));
                v.add(rs.getString("date"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//view all cash account

    //view table date
    public void viewCashTableData() {
        double TL = 0;
        double TF = 0;
        double TI = 0;
        double TW = 0;
        int rowsCount = tb_cashAccount.getRowCount();
        for (int i = 0; i < rowsCount; i++) {
            if (tb_cashAccount.getValueAt(i, 2).toString().equals("Loan")) {
                TL += Double.parseDouble(tb_cashAccount.getValueAt(i, 1).toString());
            } else if (tb_cashAccount.getValueAt(i, 2).toString().equals("Fund")) {
                TF += Double.parseDouble(tb_cashAccount.getValueAt(i, 1).toString());
            } else if (tb_cashAccount.getValueAt(i, 2).toString().equals("Installment")) {
                TI += Double.parseDouble(tb_cashAccount.getValueAt(i, 1).toString());
            } else if (tb_cashAccount.getValueAt(i, 2).toString().equals("Withdrawal")) {
                TW += Double.parseDouble(tb_cashAccount.getValueAt(i, 1).toString());
            }
        }
        lblfunds.setText(TF + "0");
        this.TOT_FUNDS = TF;

//        lblloans.setText(TL + "0");
//        this.TOT_LOANS = TL;
        lbltot_installment.setText(TI + "0");
        this.TOT_INSTALLMENT = TI;

        lblwithdrawals.setText(TW + "0");
        this.TOT_WITHDRAWAL = TW;

    }

    //view table date
    //set due ineterest
    public void setDueTotalInterest() {
        double OI = Double.parseDouble(lb_v_outstandingCapital.getText());
        double RI = 0;
        if (this.RI != 0) {
            RI = this.RI;
        }
        System.out.println("OI======" + OI);
        System.out.println("RI======" + RI);
        lb_v_outstandingInterest.setText(OI - RI + "0");
        this.OUTSTANDING_INTEREST = (OI - RI);
    }
    //set due ineterest

    //view total assets
    public void viewTotalAssets() {
        double OC = Double.parseDouble(lb_v_outstandingCapital.getText());
        double OI = Double.parseDouble(lb_v_outstandingInterest.getText());
        System.out.println("OC+++++++++++++++++++" + OC);
        System.out.println("OI+++++++++++++++++++++++" + OI);
        lb_v_totalAsset.setText((OC + OI) + "0");
        this.TOT_ASSET = (OC + OI);
    }

    //view total assets
    //get received interest
    public void getReceivedInterest() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int idloan = rs.getInt("idloans");
                int period = Integer.parseInt(rs.getString("loan_period"));
                double installment = rs.getDouble("loan_installment");
                double loanAmount = rs.getDouble("loan_amount");

                double payable_interest = (period * installment) - loanAmount;
                System.out.println(payable_interest + " payable_interest");
                ResultSet rs2 = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) AS sumPayment FROM installment WHERE idloans='" + idloan + "'");
                if (rs2.next()) {
                    double totPayment = Double.parseDouble(rs2.getInt("sumPayment") + "");
                    double paid_interest = (payable_interest / loanAmount * totPayment);
                    RI += paid_interest;

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //get received interest

    //get outstading ineterest
    public double getOUtstandingInterest() {
        double OI = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int period = Integer.parseInt(rs.getString("loan_period"));
                double installment = rs.getDouble("loan_installment");
                double loanAmount = rs.getDouble("loan_amount");
                OI += ((period * installment) - loanAmount);

            }
            System.out.println("getOUtstandingInterest " + OI);
            return OI;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    //get outstading ineterest

    //view capital
    public final void viewCapital() {

        //total capital=funds+interest
        double TL = getTotalFundAmounts();
        System.out.println("TL-" + TL);
        double TI = getOUtstandingInterest();
        System.out.println("TI-" + TI);
        double TCapital = TL + TI;
        System.out.println("total capital" + TL + TI);
        lb_v_totalCapital.setText(TCapital + "0");
        this.TOT_CAPITAL = TCapital;

    }
    //view capital

    //view due loan amount
    public final void viewLoanDueAmounts() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(due_loan_amount) AS sumDueLoan FROM loans");
            if (rs.next()) {
                lb_v_outstandingCapital.setText(Double.parseDouble(rs.getInt("sumDueLoan") + "") + "0");
                this.OUTSTANDING_CAPITAL = Double.parseDouble(rs.getInt("sumDueLoan") + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //view due loan amount

//view loan amounts
    public final void viewTotalloans() {
        lb_v_totalLoan.setText(getTotalLoanAmounts() + "0");
        this.TOT_CAPITAL_LOANS = getTotalLoanAmounts();
    }
//view loan amounts

//get sum RI
    public void getTotalRI() {
        double RI = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int period = Integer.parseInt(rs.getString("loan_period"));
                double loanAmount = rs.getDouble("loan_amount");
                double installment = rs.getDouble("loan_installment");
                int idloan = rs.getInt("idloans");

                System.out.println("idloan================" + idloan);
                double paidAmount = 0;

                ResultSet rs2 = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) AS calPayment FROM installment WHERE idloans='" + idloan + "'");
                if (rs2.next()) {
                    paidAmount = Double.parseDouble(rs2.getInt("calPayment") + "");

                }
                RI += ((period * installment) - loanAmount) / (period * installment) * paidAmount;
            }
            System.out.println("RI----------------" + RI);
            lb_v_withdrawAvailableAmount.setText(Math.round(RI) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//get sum RI

// saving wihdrawal to cash account
    public void saveWithdrawCash() {
        try {
            String withdrawDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
            String withdrawAmount = tf_withdrawalAmount.getText();
            String withdrawDes = ta_withdrawlDescription.getText();
            String cashType = "Withdrawal";
            String cashStatus = "Active";
            if (!(withdrawAmount.isEmpty() && withdrawDes.isEmpty())) {

                if (Double.parseDouble(tf_withdrawalAmount.getText()) <= Double.parseDouble(lb_v_withdrawAvailableAmount.getText())) {

                    MC_DB.myConnection().createStatement().executeUpdate("INSERT INTO cash_account (`date`,amount,cash_ac_type,cash_ac_discription,cash_ac_status) VALUES ('" + withdrawDate + "','" + withdrawAmount + "','" + cashType + "','" + withdrawDes + "','" + cashStatus + "')");
                    JOptionPane.showMessageDialog(this, "Cash successfully withdrawed");

                } else {
                    JOptionPane.showMessageDialog(this, "Withdrawal Amount should be less than Received Interest Amount " + lb_v_withdrawAvailableAmount.getText(), "Withdrwal Amount can not Proceed", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Can not save empty values", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//saving withdrawal to cash account

//view cash account
    DefaultTableModel dtm_cash;

    public void viewCashAccount() {

//        System.out.println(dc_startDate.getDate());
//        String d = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
//        System.out.println(d+"hello");     
        String sDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
        String eDate = new SimpleDateFormat("yyyy-MM-dd").format(dc_endDate.getDate());
        String cashType = cb_cashType.getSelectedItem().toString();

        System.out.println(sDate + " ^^ " + eDate + " ^^ " + cashType);

        new Thread(() -> {
            try {
                ResultSet rs = null;
                dtm_cash = (DefaultTableModel) tb_cashAccount.getModel();
                dtm_cash.setRowCount(0);
                try {

                    if (!(sDate.isEmpty() && eDate.isEmpty() && cashType.isEmpty())) {
                        if (cashType.equals("All")) {
                            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM cash_account WHERE date BETWEEN '" + sDate + "' AND '" + eDate + "'");
                            dtm_cash.setRowCount(0);
                            while (rs.next()) {
                                Vector v = new Vector();
                                System.out.println(rs.getString("idcash_account"));
                                v.add(rs.getString("idcash_account"));
                                v.add(rs.getDouble("amount"));
                                v.add(rs.getString("cash_ac_type"));
                                v.add(rs.getString("cash_ac_discription"));
                                v.add(rs.getString("date"));
                                dtm_cash.addRow(v);

                            }
                        } else {
                            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM cash_account WHERE date BETWEEN '" + sDate + "' AND '" + eDate + "' AND cash_ac_type='" + cashType + "'");
                            dtm_cash.setRowCount(0);
                            while (rs.next()) {
                                Vector v = new Vector();
                                System.out.println(rs.getString("idcash_account"));
                                v.add(rs.getString("idcash_account"));
                                v.add(rs.getDouble("amount"));
                                v.add(rs.getString("cash_ac_type"));
                                v.add(rs.getString("cash_ac_discription"));
                                v.add(rs.getString("date"));
                                dtm_cash.addRow(v);

                            }
                        }
                        viewCashTableData();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
    //view cash account

    //view totfunds
    public double getTotalUpdateFunds() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(fund_update) AS countFound FROM fund");
            if (rs.next()) {
                double totFund = rs.getInt("countFound");
                return totFund;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //view tot funds

    //view total loan amounts
    public double getTotalLoanAmounts() {

        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS loanSUM FROM cash_account WHERE cash_ac_type='Loan'");
            if (rs.next()) {
                int loan = rs.getInt("loanSUM");
                return loan;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //view total loan amounts

    //view total installment cash
    public double getTotalInstallmentAmounts() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS InstallmentSUM FROM cash_account WHERE cash_ac_type='Installment'");
            if (rs.next()) {
                int installment = rs.getInt("InstallmentSUM");
                return installment;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
    //view total installment cash

    //view total withdrawal amounts
    public double getTotalWithdrawalAmounts() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS WithdrawalSUM FROM cash_account WHERE cash_ac_type='Withdrawal'");
            if (rs.next()) {
                int with = rs.getInt("WithdrawalSUM");
                return with;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //view total withdrawal amounts

    //view total fund amounts
    public double getTotalFundAmounts() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS FundSUM FROM cash_account WHERE cash_ac_type='Fund'");
            if (rs.next()) {
                return Double.parseDouble(rs.getInt("FundSUM") + "");
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //view total fund amounts

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dc_startDate = new com.toedter.calendar.JDateChooser();
        dc_endDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cb_cashType = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        tf_withdrawalAmount = new javax.swing.JTextField();
        lb_addRoute4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_withdrawlDescription = new javax.swing.JTextArea();
        bt_addFund1 = new javax.swing.JButton();
        lb_v_withdrawAvailableAmount = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cashAccount = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_v_totalCapital = new javax.swing.JLabel();
        lb_v_totalLoan = new javax.swing.JLabel();
        lb_v_outstandingCapital = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_v_outstandingInterest = new javax.swing.JLabel();
        bt_addFund = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lb_v_totalAsset = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        arr = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblfunds = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblwithdrawals = new javax.swing.JLabel();
        bt_addFund2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lb_tot_income = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblinstallments = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbltot_installment = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(66, 66, 66));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Start Date :");

        dc_startDate.setDateFormatString("yyyy-MM-dd");
        dc_startDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dc_endDate.setDateFormatString("yyyy-MM-dd");
        dc_endDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("End Date :");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/home/images/Google Web Search_40.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Select Cash Type :");

        cb_cashType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_cashType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Fund", "Loan", "Withdrawal", "Installment" }));
        cb_cashType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_cashTypeItemStateChanged(evt);
            }
        });
        cb_cashType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_cashTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dc_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(dc_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6))
                    .addComponent(cb_cashType, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_cashType))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dc_endDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dc_startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));

        tf_withdrawalAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_withdrawalAmount.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_withdrawalAmount.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_withdrawalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_withdrawalAmountActionPerformed(evt);
            }
        });
        tf_withdrawalAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_withdrawalAmountKeyTyped(evt);
            }
        });

        lb_addRoute4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute4.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute4.setText("Withdrawal Amount :");

        ta_withdrawlDescription.setColumns(20);
        ta_withdrawlDescription.setRows(5);
        jScrollPane3.setViewportView(ta_withdrawlDescription);

        bt_addFund1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addFund1.setForeground(new java.awt.Color(255, 255, 255));
        bt_addFund1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addFund1.setText("Withdraw Cash");
        bt_addFund1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addFund1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addFund1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addFund1ActionPerformed(evt);
            }
        });

        lb_v_withdrawAvailableAmount.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lb_v_withdrawAvailableAmount.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_withdrawAvailableAmount.setText("00.00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Available Withdrawal :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lb_addRoute4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(166, 166, 166))
                    .addComponent(tf_withdrawalAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addFund1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_v_withdrawAvailableAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_addRoute4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_withdrawalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_withdrawAvailableAmount)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_addFund1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tb_cashAccount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_cashAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cash Id", "Cash Amount", "Cash Type", "Description", "Credit Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_cashAccount);

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total Capital :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Capital Loans  :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Outstanding Capital :");

        lb_v_totalCapital.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalCapital.setForeground(new java.awt.Color(0, 204, 0));
        lb_v_totalCapital.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalCapital.setText("0.00");

        lb_v_totalLoan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalLoan.setForeground(new java.awt.Color(255, 234, 0));
        lb_v_totalLoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalLoan.setText("0.00");

        lb_v_outstandingCapital.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_outstandingCapital.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_outstandingCapital.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_outstandingCapital.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("(without interest)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Outstanding Interest :");

        lb_v_outstandingInterest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_outstandingInterest.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_outstandingInterest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_outstandingInterest.setText("0.00");

        bt_addFund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addFund.setForeground(new java.awt.Color(255, 255, 255));
        bt_addFund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addFund.setText("Print Final Report");
        bt_addFund.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addFund.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addFund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addFundActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Assets Total :");

        lb_v_totalAsset.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totalAsset.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_totalAsset.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalAsset.setText("0.00");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Arrears :");

        arr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        arr.setForeground(new java.awt.Color(255, 204, 0));
        arr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        arr.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_v_outstandingCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_v_outstandingInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_v_totalAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arr, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_v_totalLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_v_totalCapital, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(arr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb_v_totalCapital))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_totalLoan)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_outstandingCapital)
                    .addComponent(jLabel9))
                .addGap(4, 4, 4)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lb_v_outstandingInterest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_totalAsset)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(66, 66, 66));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Funds :");

        lblfunds.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblfunds.setForeground(new java.awt.Color(255, 255, 255));
        lblfunds.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblfunds.setText("0.00");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Withdrawal :");

        lblwithdrawals.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblwithdrawals.setForeground(new java.awt.Color(255, 255, 255));
        lblwithdrawals.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblwithdrawals.setText("0.00");

        bt_addFund2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addFund2.setForeground(new java.awt.Color(255, 255, 255));
        bt_addFund2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addFund2.setText("Print");
        bt_addFund2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addFund2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addFund2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addFund2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Daily Total Income :");

        lb_tot_income.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_tot_income.setForeground(new java.awt.Color(255, 255, 51));
        lb_tot_income.setText("0.00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Received Interest :");

        lblinstallments.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblinstallments.setForeground(new java.awt.Color(255, 255, 51));
        lblinstallments.setText("0.00");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Total Installment:");

        lbltot_installment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltot_installment.setForeground(new java.awt.Color(255, 255, 255));
        lbltot_installment.setText("0.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfunds, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblwithdrawals, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(lbltot_installment, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lblinstallments, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lb_tot_income, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addFund2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_addFund2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblwithdrawals, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_tot_income)
                                .addComponent(lblinstallments))
                            .addComponent(lbltot_installment)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfunds, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        calTotalPaidInterest();
//        viewCashAccount();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void bt_addFundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFundActionPerformed

        //jc_start.getDate()
        String startDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
        String endDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_endDate.getDate());
        String todayDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        String cashType = cb_cashType.getSelectedItem().toString();
        if (cashType.equals("All")) {
            JOptionPane.showMessageDialog(this, "Print all Temporarly Disabled");
        } else {

            String totCapital = lb_v_totalCapital.getText();
            String totCapLoans = lb_v_totalLoan.getText();
            String out_capital = lb_v_outstandingCapital.getText();
            String out_interest = lb_v_outstandingInterest.getText();
            String asset = lb_v_totalAsset.getText();

            String subtitle = "Final Report";

            String path = "src//Reports//loan//report_cash_AdvancedReport.jrxml";
            //E:\Project_SE\suneraInvestment\suneraInvestment_system\src\Reports\customer\report_customerandloandetalsHistory.jrxml

            cash_reportView(path, startDate, endDate, subtitle, cashType, totCapital, totCapLoans, out_capital, out_interest, asset);
        }

    }//GEN-LAST:event_bt_addFundActionPerformed

    private void bt_addFund1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFund1ActionPerformed
        saveWithdrawCash();
    }//GEN-LAST:event_bt_addFund1ActionPerformed

    private void tf_withdrawalAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_withdrawalAmountKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_withdrawalAmountKeyTyped

    private void cb_cashTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cashTypeItemStateChanged

        dtm_cash = (DefaultTableModel) tb_cashAccount.getModel();
        dtm_cash.setRowCount(0);
        viewCashAccount();


    }//GEN-LAST:event_cb_cashTypeItemStateChanged

    private void bt_addFund2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFund2ActionPerformed

        //jc_start.getDate()
        String startDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
        String endDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_endDate.getDate());
        String todayDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

        String totfund = lblfunds.getText();
        String totloan = lb_v_totalLoan.getText();
        String installment = lbltot_installment.getText();
        String withdraw = lblwithdrawals.getText();

        String subtitle = "Daily";

        String path = "src//Reports//loan//report_cash_dateRangeReport.jrxml";
        //E:\Project_SE\suneraInvestment\suneraInvestment_system\src\Reports\customer\report_customerandloandetalsHistory.jrxml

        md_reportView(path, startDate, endDate, todayDate, totfund, totloan, installment, withdraw, subtitle);

    }//GEN-LAST:event_bt_addFund2ActionPerformed

    private void cb_cashTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cashTypeActionPerformed


    }//GEN-LAST:event_cb_cashTypeActionPerformed

    private void tf_withdrawalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_withdrawalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_withdrawalAmountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arr;
    private javax.swing.JButton bt_addFund;
    private javax.swing.JButton bt_addFund1;
    private javax.swing.JButton bt_addFund2;
    private javax.swing.JComboBox cb_cashType;
    public static com.toedter.calendar.JDateChooser dc_endDate;
    public static com.toedter.calendar.JDateChooser dc_startDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_addRoute4;
    private javax.swing.JLabel lb_tot_income;
    private javax.swing.JLabel lb_v_outstandingCapital;
    private javax.swing.JLabel lb_v_outstandingInterest;
    private javax.swing.JLabel lb_v_totalAsset;
    private javax.swing.JLabel lb_v_totalCapital;
    private javax.swing.JLabel lb_v_totalLoan;
    private javax.swing.JLabel lb_v_withdrawAvailableAmount;
    private javax.swing.JLabel lblfunds;
    private javax.swing.JLabel lblinstallments;
    private javax.swing.JLabel lbltot_installment;
    private javax.swing.JLabel lblwithdrawals;
    private javax.swing.JTextArea ta_withdrawlDescription;
    private javax.swing.JTable tb_cashAccount;
    private javax.swing.JTextField tf_withdrawalAmount;
    // End of variables declaration//GEN-END:variables

    private static void setCurrentDate() {
        dc_startDate.setDate(new Date());
        dc_endDate.setDate(new Date());
    }

    private void cash_reportView(String rp_parth, String startDate, String endDate, String subtitle, String cashType, String totCapital, String totCapLoans, String out_capital, String out_interest, String asset) {

        try {
            JasperReport jp = JasperCompileManager.compileReport(rp_parth);
            System.out.println("===========jasper report compiled successfully==========");
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("startdate", startDate);
            map.put("enddate", endDate);
            map.put("cashtype", cashType);
            map.put("sub_type", subtitle);
            map.put("totcapital", totCapital);
            map.put("totcapitalloans", totCapLoans);
            map.put("tot_out_capital", out_capital);
            map.put("tot_out_interest", out_interest);
            map.put("tot_asset", asset);

            System.out.println(startDate);
            System.out.println(endDate);
            System.out.println(cashType);
            System.out.println(subtitle);
            System.out.println(totCapital);
            System.out.println(totCapLoans);
            System.out.println(out_capital);
            System.out.println(out_interest);
            System.out.println(asset);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, map, MC_DB.myConnection());
            JasperViewer.viewReport(jasperPrint, false);
            JRViewer v = new JRViewer(jasperPrint);
            //jTabbedPane1.addTab(monthName+" Income In "+jc_Check_monthly_Income_In_Course.getSelectedItem().toString(), v);
//            if (jTabbedPane1.getTabCount() > 0) {
//                jTabbedPane1.remove(jTabbedPane1.getTabCount() - 1);
//            }
//
//            jTabbedPane1.addTab("View Reports", v);

        } catch (Exception e) {
            e.printStackTrace();
            //Logger.getLogger(Inventory_reports.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void md_reportView(String rp_parth, String sdate, String edate, String todayDate, String totfund, String totloan, String installment, String withdraw, String subtitle) {

        try {
            JasperReport jp = JasperCompileManager.compileReport(rp_parth);
            System.out.println("===========jasper report compiled successfully==========");
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("startdate", sdate);
            map.put("enddate", edate);
            map.put("todaydate", todayDate);
            map.put("totfund", totfund);
            map.put("totloans", totloan);
            map.put("totinstallment", installment);
            map.put("totwithdrawal", withdraw);
            map.put("p_subType", subtitle);

            System.out.println(sdate);
            System.out.println(edate);
            System.out.println(todayDate);
            System.out.println(totfund);
            System.out.println(totloan);
            System.out.println(installment);
            System.out.println(withdraw);
            System.out.println(subtitle);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, map, MC_DB.myConnection());
            JasperViewer.viewReport(jasperPrint, false);
            JRViewer v = new JRViewer(jasperPrint);
            //jTabbedPane1.addTab(monthName+" Income In "+jc_Check_monthly_Income_In_Course.getSelectedItem().toString(), v);
//            if (jTabbedPane1.getTabCount() > 0) {
//                jTabbedPane1.remove(jTabbedPane1.getTabCount() - 1);
//            }
//
//            jTabbedPane1.addTab("View Reports", v);

        } catch (Exception e) {
            e.printStackTrace();
            //Logger.getLogger(Inventory_reports.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void calArrius() {
        double arriers = 0;
        double paid = 0;
        try {
            ResultSet rs_main = MC_DB.myConnection().createStatement().executeQuery("SELECT idloans FROM loans WHERE loan_status='active'");
            while (rs_main.next()) {
                String loanID = rs_main.getString(1);
                ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) FROM installment WHERE idloans='" + loanID + "'");
                if (rs.next()) {
                    paid = rs.getDouble(1);
                }
                String loanDate = "";
                int period = 0;
                double installement = 0;

                String loantype = "";
                String loan_main_type = "";
                rs = MC_DB.myConnection().createStatement().executeQuery("SELECT loan_mainperiodtype,loan_date,loan_period,loan_installment,loan_type FROM loans WHERE idloans='" + loanID + "'");
                if (rs.next()) {
                    loantype = rs.getString(1);
                    loanDate = rs.getString(2);
                    period = rs.getInt(3);
                    installement = rs.getDouble(4);
                    loan_main_type = rs.getString(5);
                }

                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                if (loan_main_type.toLowerCase().contains("daily") || (loan_main_type.toLowerCase().contains("weekly"))) {

                    String tempdate = loanDate;
                    int no_of_days = 0;
                    int no_of_saturdays = 0;

                    Date d = new SimpleDateFormat("yyyy-MM-dd").parse(loanDate);
                    String year = loanDate.split("-")[0];
                    int day = Integer.parseInt(new SimpleDateFormat("yyyy-DDD").format(d).split("-")[1]);

                    for (int i = 1; (i <= period) && (!today.equals(tempdate)); i++) {

                        Date currentdate = new SimpleDateFormat("yyyy-DDD").parse(year + "-" + (day + i));

                        tempdate = new SimpleDateFormat("yyyy-MM-dd").format(currentdate);

                        String nowday = new SimpleDateFormat("EEEEEEEE").format(currentdate);

                        if (nowday.equalsIgnoreCase("Saturday")) {

                            no_of_saturdays++;
                            if (loantype.equalsIgnoreCase("With Out Saturday(Week days)")) {
                                period++;
                                System.out.println(period);
                            }
                        }
                        no_of_days++;

                    }

                    if (loantype.equalsIgnoreCase("With Out Saturday(Week days)")) {
                        arriers += (no_of_days - no_of_saturdays) * installement;
                    } else if (loantype.equalsIgnoreCase("With Saturday")) {
                        arriers += no_of_days * installement;

                    } else {

                        arriers += (no_of_days / 7) * installement;
                    }

                } else {
                    Date td = new Date();
                    int datdif = 0;
                    int no_of_months = 0;
                    String year = loanDate.split("-")[0];
                    int l_month = Integer.parseInt(loanDate.split("-")[1]);
                    for (int i = 1; (i <= period) && (datdif <= 0); i++) {
                        String tempd = l_month + i + "";
                        if ((l_month + i) < 10) {
                            tempd = "0" + (l_month + i);
                        }

                        Date curr = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + tempd + "-31");
                        datdif = curr.compareTo(td);
                        no_of_months++;

                    }

                    arriers += no_of_months * installement;

                }
                arriers -= paid;

            }
            arr.setText(arriers + "");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calTotalPaidInterest() {
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(dc_startDate.getDate());
        String eDate = new SimpleDateFormat("yyyy-MM-dd").format(dc_endDate.getDate());
        System.out.println(sDate);
        String cType = cb_cashType.getSelectedItem().toString();
        double total_paid_interest = 0;
        double total_daily_income = 0;
        if (cType.equals("Installment")) {
            for (int i = 0; i < tb_cashAccount.getRowCount(); i++) {
                try {
                    String loanDescrip = tb_cashAccount.getValueAt(i, 3).toString();
                    String loanId[] = loanDescrip.split("=");
//                System.out.println("Loan IDDDDDDDDDDDDDD"+loanId[1]);
                    String dataQuery = "SELECT l.`loan_amount`,l.`loan_period`,l.`loan_installment`,i.`payment`,SUM(i.`payment`) AS sumPayment FROM loans l LEFT JOIN installment i ON l.idloans=i.`idloans` WHERE i.`idloans`='" + loanId[1] + "' AND payment_date BETWEEN '" + sDate + "' AND '" + eDate + "'";

                    ResultSet rs_data = MC_DB.myConnection().createStatement().executeQuery(dataQuery);
                    if (rs_data != null) {
                        if (rs_data.next()) {
                            double loan_amount = rs_data.getDouble(1);
                            double installment = rs_data.getDouble(3);
                            int period = rs_data.getInt(2);
                            double payment=rs_data.getDouble(4);
                            double paid_amount = rs_data.getDouble("sumPayment");

                            //calculating the paid interest
                            double pa = period * installment;
                            total_paid_interest += (Math.round(((pa - loan_amount) / pa) * paid_amount));
                            total_daily_income += payment;
                            //calculating the paid interest
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
            lblinstallments.setText(total_daily_income + "0");
            lb_tot_income.setText(total_paid_interest + "0");
        }

    }

}

package com.softidea.www.private_access.adminstrator;

import com.softidea.www.public_connection.MC_DB;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin_cashManagment extends javax.swing.JPanel {

    public Admin_cashManagment() {
        initComponents();
        setCurrentDate();
        viewCashAccount();
        viewCapital();
        viewTotalFunds();
        viewTotalloans();
        getOUtstandingInterest();
        getReceivedInterest();
        setDueTotalInterest();
        setTotalDiscounts();
        setTotalWithdrawals();
        viewTotalProfit();
        viewCashTableData();
    }

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
       lblfunds.setText(TF+"0");
       lblloans.setText(TL+"0");
       lblinstallments.setText(TI+"0");
       lblwithdrawals.setText(TW+"0");
       lblprofit.setText((TF+TI)-(TL+TW)+"0");
//       lblnetprofit.setText((TF+TI)-(TL+TW)-(TF-TL)+"0");
       lblnetprofit.setText((TI-TW)+"0");

    }

    //view table date
    //view total profit
    public void viewTotalProfit() {
        double TF = Double.parseDouble(lb_v_totalFund.getText());
        double TL = Double.parseDouble(lb_v_totalLoan.getText());
        double TW = Double.parseDouble(lb_v_totWithdrawals.getText());
        double TI = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS sumIns FROM cash_account WHERE cash_ac_type='Installment'");
            if (rs.next()) {
                TI = Double.parseDouble(rs.getInt("sumIns") + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lb_v_profit.setText((TF + TI) - (TL + TW) + "0");
        lb_v_netprofit.setText((TF + TI) - (TL + TW)-(TF-TL)+"0");
    }
    //view total profit

    //set total withdrawals
    public void setTotalWithdrawals() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(amount) AS sumWithdraw FROM cash_account WHERE cash_ac_type='Withdrawal'");
            if (rs.next()) {
                double totwith = Double.parseDouble(rs.getInt("sumWithdraw") + "");
                lb_v_totWithdrawals.setText(totwith + "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set total withdrawals
    //set total discounts
    public void setTotalDiscounts() {
        double TD = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int idloans = rs.getInt("idloans");
                ResultSet rs2 = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(discount) AS sumDiscount FROM installment WHERE idloans='" + idloans + "'");
                if (rs2.next()) {
                    TD += Double.parseDouble(rs2.getInt("sumDiscount") + "");
                }
            }
            System.out.println("Total Discounts " + TD);
            lb_v_totalDiscount.setText(TD + "0");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //set total discounts

    //set due ineterest
    public void setDueTotalInterest() {
        double OI = Double.parseDouble(lb_v_outstandingInterest.getText());
        double PI = Double.parseDouble(lb_v_receivedInterest.getText());
        lb_v_dueInterest.setText(OI - PI + "0");
    }
    //set due ineterest

    //get received interest
    public void getReceivedInterest() {
        double RI = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int idloan = rs.getInt("idloans");
                int period = Integer.parseInt(rs.getString("loan_period"));
                double installment = Double.parseDouble(rs.getString("loan_installment"));
                double loanAmount = Double.parseDouble(rs.getString("loan_amount"));

                double payable_interest = (period * installment) - loanAmount;
                System.out.println(payable_interest + " payable_interest");
                ResultSet rs2 = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) AS sumPayment FROM installment WHERE idloans='" + idloan + "'");
                if (rs2.next()) {
                    double totPayment = Double.parseDouble(rs2.getInt("sumPayment") + "");
                    double paid_interest = (payable_interest / loanAmount * totPayment);
                    RI += paid_interest;

                }

            }
            lb_v_receivedInterest.setText(Math.round(RI) + ".00");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //get received interest

    //get outstading ineterest
    public void getOUtstandingInterest() {
        double OI = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                int period = Integer.parseInt(rs.getString("loan_period"));
                double installment = Double.parseDouble(rs.getString("loan_installment"));
                double loanAmount = Double.parseDouble(rs.getString("loan_amount"));
                OI += ((period * installment) - loanAmount);

            }
            lb_v_outstandingInterest.setText(OI + "0");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //get outstading ineterest

    //view capital
    public void viewCapital() {
        double income = getTotalUpdateFunds() + getTotalInstallmentAmounts();
        double expense = getTotalLoanAmounts() + getTotalWithdrawalAmounts();
        lb_v_totalCapital.setText((income - expense) + "0");
    }
    //view capital

    //view funds
    public void viewTotalFunds() {
        lb_v_totalFund.setText(getTotalFundAmounts() + "0");
    }
    //view funds

    //view loan amounts
    public void viewTotalloans() {
        lb_v_totalLoan.setText(getTotalLoanAmounts() + "0");
    }
    //view loan amounts

    //saving wihdrawal to cash account
    public void saveWithdrawCash() {
        try {
            String withdrawDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
            String withdrawAmount = tf_withdrawalAmount.getText();
            String withdrawDes = ta_withdrawlDescription.getText();
            String cashType = "Withdrawal";
            String cashStatus = "Active";
            if (!(withdrawAmount.isEmpty() && withdrawDes.isEmpty())) {
                if (Double.parseDouble(lb_v_receivedInterest.getText()) >= Double.parseDouble(withdrawAmount)) {
                    MC_DB.myConnection().createStatement().executeUpdate("INSERT INTO cash_account (date,amount,cash_ac_type,cash_ac_discription,cash_ac_status) VALUES('" + withdrawDate + "','" + withdrawAmount + "','" + cashType + "','" + withdrawDes + "','" + cashStatus + "')");
                    JOptionPane.showMessageDialog(null, "Cash Withdraw Successfully");
                    tf_withdrawalAmount.setText("");
                    ta_withdrawlDescription.setText("");
                    viewCashAccount();

                } else {
                    JOptionPane.showMessageDialog(null, "Withdrawal Amount shpuld be less than or equal to Received Interest", "Error", JOptionPane.ERROR_MESSAGE);
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
    public void viewCashAccount() {
        ResultSet rs = null;
        DefaultTableModel dtm = (DefaultTableModel) tb_cashAccount.getModel();
        dtm.setRowCount(0);
        try {
            String sDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
            String eDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_endDate.getDate());
            String cashType = cb_cashType.getSelectedItem().toString();

            if (!(sDate.isEmpty() && eDate.isEmpty() && cashType.isEmpty())) {
                if (cashType.equals("All")) {
                    rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM cash_account WHERE date BETWEEN '" + sDate + "' AND '" + eDate + "'");
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(rs.getString("idcash_account"));
                        v.add(rs.getString("amount"));
                        v.add(rs.getString("cash_ac_type"));
                        v.add(rs.getString("cash_ac_discription"));
                        v.add(rs.getString("date"));
                        dtm.addRow(v);

                    }
                } else {
                    rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM cash_account WHERE date BETWEEN '" + sDate + "' AND '" + eDate + "' AND cash_ac_type='" + cashType + "'");
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(rs.getString("idcash_account"));
                        v.add(rs.getString("amount"));
                        v.add(rs.getString("cash_ac_type"));
                        v.add(rs.getString("cash_ac_discription"));
                        v.add(rs.getString("date"));
                        dtm.addRow(v);

                    }
                }
                viewCashTableData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
                return Double.parseDouble(rs.getString("FundSUM"));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cashAccount = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lb_v_totalCapital = new javax.swing.JLabel();
        lb_v_totalFund = new javax.swing.JLabel();
        lb_v_totalLoan = new javax.swing.JLabel();
        lb_v_outstandingInterest = new javax.swing.JLabel();
        lb_v_totWithdrawals = new javax.swing.JLabel();
        lb_v_netprofit = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_v_receivedInterest = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_v_dueInterest = new javax.swing.JLabel();
        bt_addFund = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lb_v_totalDiscount = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lb_v_profit = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblfunds = new javax.swing.JLabel();
        lblloans = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblinstallments = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblwithdrawals = new javax.swing.JLabel();
        lblprofit = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bt_addFund2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lblnetprofit = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(66, 66, 66));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Start Date :");

        dc_startDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3)
                        .addComponent(lb_addRoute4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_withdrawalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bt_addFund1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_addRoute4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_withdrawalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(10, 10, 10)
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

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total Funds :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Loan  :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Outstanding Interest :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Total Withdrawals :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Net Profit :");

        lb_v_totalCapital.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalCapital.setForeground(new java.awt.Color(0, 204, 0));
        lb_v_totalCapital.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalCapital.setText("0.00");

        lb_v_totalFund.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalFund.setForeground(new java.awt.Color(0, 204, 0));
        lb_v_totalFund.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalFund.setText("0.00");

        lb_v_totalLoan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalLoan.setForeground(new java.awt.Color(255, 234, 0));
        lb_v_totalLoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalLoan.setText("0.00");

        lb_v_outstandingInterest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_outstandingInterest.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_outstandingInterest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_outstandingInterest.setText("0.00");

        lb_v_totWithdrawals.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totWithdrawals.setForeground(new java.awt.Color(244, 67, 102));
        lb_v_totWithdrawals.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totWithdrawals.setText("0.00");

        lb_v_netprofit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_netprofit.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_netprofit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_netprofit.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Received Interest :");

        lb_v_receivedInterest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_receivedInterest.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_receivedInterest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_receivedInterest.setText("0.00");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Due Interest :");

        lb_v_dueInterest.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_dueInterest.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_dueInterest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_dueInterest.setText("0.00");

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
        jLabel12.setText("Total Discounts :");

        lb_v_totalDiscount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalDiscount.setForeground(new java.awt.Color(255, 87, 34));
        lb_v_totalDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalDiscount.setText("0.00");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total Profit(+Fund) :");

        lb_v_profit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_profit.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_profit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_profit.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lb_v_totalLoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lb_v_totWithdrawals, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_v_totalFund, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_v_totalCapital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_v_netprofit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_outstandingInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_dueInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_v_receivedInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_totalDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_profit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb_v_totalCapital))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_v_totalFund))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_totalLoan)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_outstandingInterest)
                    .addComponent(jLabel9))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lb_v_receivedInterest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_dueInterest)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_totalDiscount)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_v_totWithdrawals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_profit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_netprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(66, 66, 66));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Funds :");

        lblfunds.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblfunds.setForeground(new java.awt.Color(255, 255, 255));
        lblfunds.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblfunds.setText("0.00");

        lblloans.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblloans.setForeground(new java.awt.Color(255, 255, 255));
        lblloans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblloans.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Loans :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Installments :");

        lblinstallments.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblinstallments.setForeground(new java.awt.Color(255, 255, 255));
        lblinstallments.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblinstallments.setText("0.00");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Withdrawal :");

        lblwithdrawals.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblwithdrawals.setForeground(new java.awt.Color(255, 255, 255));
        lblwithdrawals.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblwithdrawals.setText("0.00");

        lblprofit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblprofit.setForeground(new java.awt.Color(255, 255, 255));
        lblprofit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblprofit.setText("0.00");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Profit :");

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
        jLabel16.setText("Net Profit :");

        lblnetprofit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblnetprofit.setForeground(new java.awt.Color(255, 255, 255));
        lblnetprofit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblnetprofit.setText("0.00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblfunds, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblloans, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblinstallments, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblwithdrawals, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnetprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addFund2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_addFund2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblinstallments, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblwithdrawals, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblnetprofit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblloans, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfunds, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        viewCashAccount();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void bt_addFundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFundActionPerformed


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

        viewCashAccount();
        

    }//GEN-LAST:event_cb_cashTypeItemStateChanged

    private void bt_addFund2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFund2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_addFund2ActionPerformed

    private void cb_cashTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cashTypeActionPerformed
        
        
        
    }//GEN-LAST:event_cb_cashTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addFund;
    private javax.swing.JButton bt_addFund1;
    private javax.swing.JButton bt_addFund2;
    private javax.swing.JComboBox cb_cashType;
    private com.toedter.calendar.JDateChooser dc_endDate;
    private com.toedter.calendar.JDateChooser dc_startDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_addRoute4;
    private javax.swing.JLabel lb_v_dueInterest;
    private javax.swing.JLabel lb_v_netprofit;
    private javax.swing.JLabel lb_v_outstandingInterest;
    private javax.swing.JLabel lb_v_profit;
    private javax.swing.JLabel lb_v_receivedInterest;
    private javax.swing.JLabel lb_v_totWithdrawals;
    private javax.swing.JLabel lb_v_totalCapital;
    private javax.swing.JLabel lb_v_totalDiscount;
    private javax.swing.JLabel lb_v_totalFund;
    private javax.swing.JLabel lb_v_totalLoan;
    private javax.swing.JLabel lblfunds;
    private javax.swing.JLabel lblinstallments;
    private javax.swing.JLabel lblloans;
    private javax.swing.JLabel lblnetprofit;
    private javax.swing.JLabel lblprofit;
    private javax.swing.JLabel lblwithdrawals;
    private javax.swing.JTextArea ta_withdrawlDescription;
    private javax.swing.JTable tb_cashAccount;
    private javax.swing.JTextField tf_withdrawalAmount;
    // End of variables declaration//GEN-END:variables

    private void setCurrentDate() {
        dc_startDate.setDate(new Date());
        dc_endDate.setDate(new Date());
    }
}

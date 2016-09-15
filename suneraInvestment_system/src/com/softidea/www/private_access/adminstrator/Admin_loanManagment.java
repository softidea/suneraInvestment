package com.softidea.www.private_access.adminstrator;

import com.fsczone.www.lookAndFeel.pro_lookandfeel;
import com.softidea.www.public_connection.MC_DB;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Admin_loanManagment extends javax.swing.JPanel {

    public Admin_loanManagment() {
        initComponents();
        setCurrentDate();
        new Thread(() -> {
            viewLoans();
            viewLoanCounts();
        }).start();
        new Thread(() -> {
            try {
                pro_lookandfeel.Set();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            viewTotalLoanAmount();
        }).start();

    }

    //count daily loans
    public void viewLoanCounts() {
        System.out.println("view loan counts");
        String[] loan_type = {"Daily", "Weekly", "Monthly"};
        System.out.println(loan_type[0]);
        System.out.println(loan_type[1]);
        System.out.println(loan_type[2]);

        lb_v_totdailyLoans.setText(countLoanTypes(loan_type[0]) + "");
        lb_v_totweeklyLoans.setText(countLoanTypes(loan_type[1]) + "");
        lb_v_totmonthlyLoans.setText(countLoanTypes(loan_type[2]) + "");
        int dl = Integer.parseInt(lb_v_totdailyLoans.getText());
        int wl = Integer.parseInt(lb_v_totmonthlyLoans.getText());
        int ml = Integer.parseInt(lb_v_totweeklyLoans.getText());
        lb_v_totloans.setText(dl + wl + ml + "");
    }

    public int countLoanTypes(String loan_type) {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT COUNT(idloans) AS dailyCount FROM loans WHERE loan_type='" + loan_type + "';");

            if (rs.next()) {
                return rs.getInt("dailyCount");
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //count daily loans

    //count total loan amounts
    public void viewTotalLoanAmount() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(loan_amount) AS loanAmount FROM loans");
            if (rs.next()) {
                lb_v_loanAmount.setText(rs.getInt("loanAmount") + ".00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //count total loan amounts

    //view loans
    ResultSet rs = null;

    public void viewLoans() {

        DefaultTableModel dtm = (DefaultTableModel) tb_loans.getModel();
        dtm.setRowCount(0);
            String startDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
            String endDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_endDate.getDate());
            String loanType = cb_cashType.getSelectedItem().toString();
            System.out.println(startDate);
            System.out.println(endDate);

            try {
                System.out.println("inner view loans");

                try {
                    if (!(startDate == null && endDate == null && loanType.isEmpty())) {
                        System.out.println("inner validation");
                        if (loanType.equals("All")) {
                            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans WHERE loan_date BETWEEN '" + startDate + "' AND '" + endDate + "'");
                            while (rs.next()) {
                                Vector v = new Vector();
                                v.add(rs.getString("idloans"));
                                v.add(rs.getString("loan_no"));
                                v.add(rs.getString("loan_date"));
                                v.add(rs.getDouble("loan_amount"));
                                v.add(rs.getString("loan_mainperiodtype"));
                                v.add(rs.getString("loan_period"));
                                v.add(rs.getString("loan_status"));
                                dtm.addRow(v);
                            }
                        } else {
                            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans WHERE loan_date BETWEEN '" + startDate + "' AND '" + endDate + "' AND loan_mainperiodtype='" + loanType + "'");
                            while (rs.next()) {
                                Vector v = new Vector();
                                v.add(rs.getString("idloans"));
                                v.add(rs.getString("loan_no"));
                                v.add(rs.getString("loan_date"));
                                v.add(rs.getDouble("loan_amount"));
                                v.add(rs.getString("loan_mainperiodtype"));
                                v.add(rs.getString("loan_period"));
                                dtm.addRow(v);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    //view loans

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dc_startDate = new com.toedter.calendar.JDateChooser();
        dc_endDate = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        btn_loanSearch = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cb_cashType = new javax.swing.JComboBox();
        bt_addFund = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_loans = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lb_v_loanAmount = new javax.swing.JLabel();
        lb_v_totloans = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lb_v_totdailyLoans = new javax.swing.JLabel();
        lb_v_totweeklyLoans = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lb_v_totmonthlyLoans = new javax.swing.JLabel();

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

        btn_loanSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/home/images/Google Web Search_40.png"))); // NOI18N
        btn_loanSearch.setBorderPainted(false);
        btn_loanSearch.setContentAreaFilled(false);
        btn_loanSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_loanSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loanSearchActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Select Period Type :");

        cb_cashType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_cashType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Daily", "Weekly", "Monthly" }));
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

        bt_addFund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addFund.setForeground(new java.awt.Color(255, 255, 255));
        bt_addFund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addFund.setText("Print Report");
        bt_addFund.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addFund.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addFund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_addFundMouseClicked(evt);
            }
        });
        bt_addFund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addFundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dc_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(dc_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_loanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cb_cashType, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_cashType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dc_endDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_loanSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dc_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tb_loans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Loan Number", "Loan Date", "Amount", "Period Type", "Period", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_loans);

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Total Loans :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Total Loan Amount :");

        lb_v_loanAmount.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        lb_v_loanAmount.setForeground(new java.awt.Color(242, 69, 69));
        lb_v_loanAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_v_loanAmount.setText("0.00");

        lb_v_totloans.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totloans.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_totloans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_v_totloans.setText("00");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total Daily Loans :");

        lb_v_totdailyLoans.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totdailyLoans.setForeground(new java.awt.Color(255, 204, 0));
        lb_v_totdailyLoans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_v_totdailyLoans.setText("00");

        lb_v_totweeklyLoans.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totweeklyLoans.setForeground(new java.awt.Color(255, 204, 0));
        lb_v_totweeklyLoans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_v_totweeklyLoans.setText("00");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total Weekly Loans :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Total Monthly Loans :");

        lb_v_totmonthlyLoans.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_totmonthlyLoans.setForeground(new java.awt.Color(255, 204, 0));
        lb_v_totmonthlyLoans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_v_totmonthlyLoans.setText("00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_totmonthlyLoans, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_loanAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_totdailyLoans, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_totweeklyLoans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_totloans, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_v_totdailyLoans)
                .addGap(18, 18, 18)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_v_totweeklyLoans)
                .addGap(18, 18, 18)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lb_v_totmonthlyLoans)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lb_v_totloans)
                .addGap(28, 28, 28)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb_v_loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loanSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loanSearchActionPerformed

        viewLoans();

    }//GEN-LAST:event_btn_loanSearchActionPerformed

    private void bt_addFundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFundActionPerformed

    }//GEN-LAST:event_bt_addFundActionPerformed

    private void cb_cashTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_cashTypeItemStateChanged

    }//GEN-LAST:event_cb_cashTypeItemStateChanged

    private void bt_addFundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_addFundMouseClicked


    }//GEN-LAST:event_bt_addFundMouseClicked

    private void cb_cashTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cashTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_cashTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addFund;
    private javax.swing.JButton btn_loanSearch;
    private javax.swing.JComboBox cb_cashType;
    private com.toedter.calendar.JDateChooser dc_endDate;
    private com.toedter.calendar.JDateChooser dc_startDate;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_v_loanAmount;
    private javax.swing.JLabel lb_v_totdailyLoans;
    private javax.swing.JLabel lb_v_totloans;
    private javax.swing.JLabel lb_v_totmonthlyLoans;
    private javax.swing.JLabel lb_v_totweeklyLoans;
    private javax.swing.JTable tb_loans;
    // End of variables declaration//GEN-END:variables

    private void setCurrentDate() {

        dc_startDate.setDate(new Date());
        dc_endDate.setDate(new Date());

    }
}

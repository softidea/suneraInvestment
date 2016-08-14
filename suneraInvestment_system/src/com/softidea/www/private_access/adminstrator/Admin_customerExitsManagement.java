package com.softidea.www.private_access.adminstrator;

import com.fsczone.www.lookAndFeel.pro_lookandfeel;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.cb_selectFund;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.tf_address;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.tf_contact;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.tf_name;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.tf_nic;
import static com.softidea.www.private_access.adminstrator.Admin_workArea.jp_adminMainPanel;
import com.softidea.www.private_access.methods.md_Calc;
import com.softidea.www.private_access.methods.md_cus;
import com.softidea.www.private_access.methods.md_loans;
import com.softidea.www.public_access.pmd;
import com.softidea.www.public_connection.MC_DB;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin_customerExitsManagement extends javax.swing.JPanel {

    boolean ifNewLoanAmountLessThanDueAmount = false;

    public Admin_customerExitsManagement() {

        initComponents();
        new Thread(() -> {
            try {
                pro_lookandfeel.Set();
                bt_updateCustomer.setEnabled(false);
                tf_nic.grabFocus();

                md_loadFunder();
                md_tb_loadCustomer("active");

        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //get current date time with Date()
                Date date = new Date();
                dc_registrationDate.setDate(date);
                String periodType = cb_mainInstallmentPeriodType.getSelectedItem().toString().substring(0, 1).toUpperCase();
                md_genLoadId(periodType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tf_contact = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rb_male = new javax.swing.JRadioButton();
        tf_address = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        rb_female = new javax.swing.JRadioButton();
        tf_nic = new javax.swing.JTextField();
        bt_search = new javax.swing.JButton();
        bt_updateCustomer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pl_viewCustomer = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tf_period = new javax.swing.JTextField();
        tf_loanAmount = new javax.swing.JTextField();
        bt_addLoan = new javax.swing.JButton();
        cb_selectFunder_customer = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lb_v_cusLoanAmount = new javax.swing.JLabel();
        lb_v_cusDueLoanAmoun = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tf_loanNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tf_newloanNumber = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_extraInterest = new javax.swing.JTextField();
        dc_registrationDate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cb_selectFund = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cb_periodType = new javax.swing.JComboBox();
        cb_mainInstallmentPeriodType = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_v_loanAmount = new javax.swing.JLabel();
        lb_v_totalAmount = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lb_v_installment = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lb_v_profit = new javax.swing.JLabel();
        lb_v_period = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_v_finalDate = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Address:");

        tf_contact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_contact.setPreferredSize(new java.awt.Dimension(300, 40));
        tf_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_contactKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_contactKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Customer Name :");

        genderGroup.add(rb_male);
        rb_male.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb_male.setForeground(new java.awt.Color(255, 255, 255));
        rb_male.setText("Male");
        rb_male.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        rb_male.setContentAreaFilled(false);
        rb_male.setFocusPainted(false);
        rb_male.setFocusable(false);
        rb_male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_maleActionPerformed(evt);
            }
        });
        rb_male.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rb_maleKeyReleased(evt);
            }
        });

        tf_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_addressKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NIC Number :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contact No :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Gender :");

        tf_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nameKeyReleased(evt);
            }
        });

        genderGroup.add(rb_female);
        rb_female.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb_female.setForeground(new java.awt.Color(255, 255, 255));
        rb_female.setText("Female");
        rb_female.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        rb_female.setContentAreaFilled(false);
        rb_female.setFocusPainted(false);
        rb_female.setFocusable(false);
        rb_female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_femaleActionPerformed(evt);
            }
        });
        rb_female.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rb_femaleKeyReleased(evt);
            }
        });

        tf_nic.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_nic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nicKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_nicKeyTyped(evt);
            }
        });

        bt_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/home/images/Google Web Search_40.png"))); // NOI18N
        bt_search.setBorderPainted(false);
        bt_search.setContentAreaFilled(false);
        bt_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_searchActionPerformed(evt);
            }
        });

        bt_updateCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_updateCustomer.setForeground(new java.awt.Color(255, 255, 255));
        bt_updateCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_updateCustomer.setText("Update Customer");
        bt_updateCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_updateCustomer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_updateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tf_nic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_male, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb_female, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_address)
                    .addComponent(tf_name)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(bt_updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_male, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_female, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));

        pl_viewCustomer.setLayout(new java.awt.CardLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Registration Date:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Loan Amount :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Period :(Hit Enter)");

        tf_period.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_period.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_periodActionPerformed(evt);
            }
        });
        tf_period.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_periodKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_periodKeyTyped(evt);
            }
        });

        tf_loanAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_loanAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_loanAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_loanAmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_loanAmountKeyTyped(evt);
            }
        });

        bt_addLoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addLoan.setForeground(new java.awt.Color(255, 255, 255));
        bt_addLoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addLoan.setText("Add Loan");
        bt_addLoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addLoan.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addLoanActionPerformed(evt);
            }
        });

        cb_selectFunder_customer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_selectFunder_customer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Funder" }));
        cb_selectFunder_customer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_selectFunder_customerItemStateChanged(evt);
            }
        });
        cb_selectFunder_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_selectFunder_customerMouseClicked(evt);
            }
        });
        cb_selectFunder_customer.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cb_selectFunder_customerPropertyChange(evt);
            }
        });
        cb_selectFunder_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cb_selectFunder_customerKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Select Funder:");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(91, 91, 91));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Loan Amount :");

        lb_v_cusLoanAmount.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lb_v_cusLoanAmount.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_cusLoanAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_cusLoanAmount.setText("0.00");

        lb_v_cusDueLoanAmoun.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_cusDueLoanAmoun.setForeground(new java.awt.Color(255, 0, 0));
        lb_v_cusDueLoanAmoun.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_cusDueLoanAmoun.setText("0.00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Due Loan Amount :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Current Loan No :");

        tf_loanNumber.setEditable(false);
        tf_loanNumber.setBackground(new java.awt.Color(255, 255, 255));
        tf_loanNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tf_loanNumber.setForeground(new java.awt.Color(255, 51, 51));
        tf_loanNumber.setText("LO-00000000-0X-0");
        tf_loanNumber.setToolTipText("");
        tf_loanNumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("New Loan No :");

        tf_newloanNumber.setEditable(false);
        tf_newloanNumber.setBackground(new java.awt.Color(255, 255, 255));
        tf_newloanNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tf_newloanNumber.setForeground(new java.awt.Color(255, 51, 51));
        tf_newloanNumber.setText("LO-00000000-0X-0");
        tf_newloanNumber.setToolTipText("");
        tf_newloanNumber.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_cusLoanAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_v_cusDueLoanAmoun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_loanNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_newloanNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_loanNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_newloanNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lb_v_cusLoanAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_cusDueLoanAmoun)
                    .addComponent(jLabel6))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Extra Interest :");

        tf_extraInterest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_extraInterest.setPreferredSize(new java.awt.Dimension(300, 40));
        tf_extraInterest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_extraInterestKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_extraInterestKeyTyped(evt);
            }
        });

        dc_registrationDate.setDateFormatString("yyyy-MM-dd");
        dc_registrationDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dc_registrationDate.setMinSelectableDate(new java.util.Date(-62135785690000L));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Select Fund:");

        cb_selectFund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_selectFund.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Fund" }));
        cb_selectFund.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_selectFundItemStateChanged(evt);
            }
        });
        cb_selectFund.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cb_selectFundKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Main Installment Period Type:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Period Type:");

        cb_periodType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_periodType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "With Out Saturday(Week days)", "With Saturday" }));
        cb_periodType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_periodTypeItemStateChanged(evt);
            }
        });

        cb_mainInstallmentPeriodType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_mainInstallmentPeriodType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Weekly", "Monthly" }));
        cb_mainInstallmentPeriodType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_mainInstallmentPeriodTypeItemStateChanged(evt);
            }
        });
        cb_mainInstallmentPeriodType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cb_mainInstallmentPeriodTypeKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_selectFunder_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_selectFund, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_extraInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_mainInstallmentPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_periodType, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_period, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dc_registrationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_addLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pl_viewCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pl_viewCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addComponent(cb_selectFunder_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel22)
                        .addGap(6, 6, 6)
                        .addComponent(cb_selectFund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18)
                        .addGap(6, 6, 6)
                        .addComponent(tf_loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19)
                        .addGap(5, 5, 5)
                        .addComponent(tf_extraInterest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel20)
                        .addGap(4, 4, 4)
                        .addComponent(cb_mainInstallmentPeriodType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(cb_periodType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14)
                        .addGap(7, 7, 7)
                        .addComponent(tf_period, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16)
                        .addGap(6, 6, 6)
                        .addComponent(dc_registrationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bt_addLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Loan Amount :");

        lb_v_loanAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_loanAmount.setForeground(new java.awt.Color(0, 204, 0));
        lb_v_loanAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_loanAmount.setText("0.00");

        lb_v_totalAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_totalAmount.setForeground(new java.awt.Color(0, 204, 0));
        lb_v_totalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_totalAmount.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Installment :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total Payble Amount:");

        lb_v_installment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_installment.setForeground(new java.awt.Color(255, 102, 0));
        lb_v_installment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_installment.setText("0.00");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Profit :");

        lb_v_profit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_v_profit.setForeground(new java.awt.Color(255, 0, 0));
        lb_v_profit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_profit.setText("0.00");

        lb_v_period.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_v_period.setForeground(new java.awt.Color(255, 255, 0));
        lb_v_period.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_period.setText("0.00");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Period :");

        lb_v_finalDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_v_finalDate.setForeground(new java.awt.Color(255, 255, 255));
        lb_v_finalDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_v_finalDate.setText("0000-00-00");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("End Date:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lb_v_period, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb_v_installment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_profit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_totalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_loanAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_v_finalDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lb_v_loanAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_v_totalAmount)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_period)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_v_installment)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_v_profit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(lb_v_finalDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rb_maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_maleActionPerformed

    }//GEN-LAST:event_rb_maleActionPerformed

    private void rb_femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_femaleActionPerformed

    }//GEN-LAST:event_rb_femaleActionPerformed

    private void bt_addLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addLoanActionPerformed

        if (this.ifNewLoanAmountLessThanDueAmount) {
            String today_date = "";
            int isOK = JOptionPane.showConfirmDialog(this, "This loan amount will be settle the previous loan due paymnet,Do you want to add new loan?", "Confrom", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            md_profitCalculation();
            if (isOK == JOptionPane.YES_OPTION) {

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                today_date = sdf.format(d);

                try {
                    String gender = "male";
                    if (Admin_customerExitsManagement.rb_male.isSelected()) {
                        gender = "male";
                    } else if (Admin_customerExitsManagement.rb_female.isSelected()) {
                        gender = "female";
                    }

                    ///////////////////////////////////
                    String gen_loan_id = tf_newloanNumber.getText().toUpperCase();

                    ////////////////////////////////////////////////////////////////
                    try {
                        MC_DB.myConnection().createStatement().executeQuery("SELECT e.`funder_name`, e.`idfunder`, u.`fund`, u.`fund_date`,u.`idfund` FROM `funder` AS e LEFT JOIN `fund` AS u ON e.`idfunder` = u.`funder_idfunder`;");
                        MC_DB.myConnection().createStatement().executeQuery("SELECT * from funder where idfunder='" + md_funderID() + "' ");
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin_customerManagment.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //////////////////////////////////////////////////////////
                    String idwithFund = cb_selectFund.getSelectedItem().toString();

                    if (cb_selectFund.getSelectedIndex() == 0) {
                        cb_periodType.setEnabled(true);
                    } else {
                        cb_periodType.setEnabled(false);
                        lb_v_finalDate.setText("");
                    }

                    int fundID = 0;
                    try {
                        String[] id = idwithFund.split("-", 0);
                        if (id != null) {

                            fundID = Integer.parseInt(id[0]);

                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    /////////////////////////////////////////////////////
                    //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    //get current date time with Date()
                    Date date = new Date();
                    dc_registrationDate.setDate(date);

                    ///////////////////////////////////////////////////////   
                    if (pmd.EmtyisTextFiled(tf_nic) && pmd.EmtyisTextFiled(tf_name) && pmd.EmtyisTextFiled(tf_address) && pmd.EmtyisTextFiled(tf_contact) && pmd.EmtyisTextFiled(tf_loanAmount) && pmd.EmtyisTextFiled(tf_period)) {
                        SimpleDateFormat sdfc = new SimpleDateFormat("yyyy-MM-dd");
                        String register_date = sdf.format(d);
                        String subType = "withSaturday";
                        if (cb_periodType.getSelectedIndex() == 0) {
                            subType = "withOutSaturday";
                        } else {
                            subType = "withSaturday";
                        }

                        boolean isture = md_cus.saveCustomer(
                                tf_nic.getText().toLowerCase(),
                                tf_name.getText().toLowerCase(),
                                tf_address.getText().toLowerCase(),
                                tf_contact.getText().toLowerCase(),
                                gender,
                                gen_loan_id,
                                register_date,
                                tf_loanAmount.getText().trim(),
                                cb_mainInstallmentPeriodType.getSelectedItem().toString() + "-" + subType,
                                tf_period.getText(),
                                lb_v_installment.getText(),
                                tf_extraInterest.getText(),
                                lb_v_totalAmount.getText(),
                                "0.00",
                                "active",
                                fundID + "",
                                md_funderID()
                        );

                        String sql_tocash = "INSERT INTO `cash_account` (`date`,`amount`,`cash_ac_type`,`cash_ac_discription`,`cash_ac_status`) VALUES ('" + register_date + "','" + md_Calc.stringTodoubleString(tf_loanAmount.getText().trim()) + "','Loan','" + "NIC:" + tf_nic.getText().toString() + "-Loan Number:" + tf_loanNumber.getText() + "','Active');";
                        MC_DB.update_data(sql_tocash);
                        md_tb_loadCustomer("active");
                        JOptionPane.showMessageDialog(this, fundID);
                        md_loans.minToFunderFund(fundID, lb_v_loanAmount.getText());

                        if (isture == true) {
                            JOptionPane.showMessageDialog(this, "Customer And Loan Successfully \n Saved!");
                            String periodType = cb_mainInstallmentPeriodType.getSelectedItem().toString().substring(0, 1).toUpperCase();
                            md_genLoadId(periodType);
                        } else {
                            JOptionPane.showMessageDialog(this, "Customer And Loan  \n Not Saved!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Field(s) is Empty! \n Can't add Loan");
                    }

                } catch (HeadlessException e) {
                    e.printStackTrace();
                }

            } else if (isOK == JOptionPane.NO_OPTION) {
                md_profitCalculation();
            }

            try {
                //settle the previous loan
                ResultSet rs_loanid = MC_DB.myConnection().createStatement().executeQuery("SELECT idloans FROM loans WHERE loan_no='" + tf_loanNumber.getText().trim() + "'");
                if (rs_loanid.next()) {
                    int idloan = rs_loanid.getInt("idloans");
                    MC_DB.myConnection().createStatement().executeUpdate("INSERT INTO installment (payment,payment_date,discount,idloans) VALUES ('" + tf_loanAmount.getText() + "','" + today_date + "','00.00','" + idloan + "')");
                    MC_DB.myConnection().createStatement().executeUpdate("UPDATE loans SET loan_status='IN-Active' WHERE idloans='" + idloan + "'");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //settle the previous loan
        } else {
            JOptionPane.showMessageDialog(this, "Loan Amount should be Greater than due Loan,for the Previous Loan Settlment", "Loan Proceed Stopped", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_bt_addLoanActionPerformed

    private void tf_loanAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_loanAmountKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_extraInterest.grabFocus();
        }

    }//GEN-LAST:event_tf_loanAmountKeyReleased

    private void tf_nicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nicKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {

            tf_name.grabFocus();
        }

    }//GEN-LAST:event_tf_nicKeyReleased

    private void tf_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nameKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_address.grabFocus();
        }

    }//GEN-LAST:event_tf_nameKeyReleased

    private void tf_addressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_addressKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            rb_male.isSelected();
        }

    }//GEN-LAST:event_tf_addressKeyReleased

    private void rb_maleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rb_maleKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_contact.grabFocus();
        }

    }//GEN-LAST:event_rb_maleKeyReleased

    private void rb_femaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rb_femaleKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_contact.grabFocus();
        }

    }//GEN-LAST:event_rb_femaleKeyReleased

    private void tf_periodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_periodActionPerformed


    }//GEN-LAST:event_tf_periodActionPerformed

    private void tf_periodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_periodKeyReleased

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {

            md_profitCalculation();
            calDate();
        }

    }//GEN-LAST:event_tf_periodKeyReleased

    private void cb_selectFunder_customerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cb_selectFunder_customerPropertyChange


    }//GEN-LAST:event_cb_selectFunder_customerPropertyChange

    private void cb_selectFunder_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_selectFunder_customerMouseClicked


    }//GEN-LAST:event_cb_selectFunder_customerMouseClicked

    private void cb_selectFunder_customerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_selectFunder_customerItemStateChanged

        loadFund(md_funderID());

    }//GEN-LAST:event_cb_selectFunder_customerItemStateChanged


    private void tf_loanAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_loanAmountKeyPressed

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {

            double dueAmount = Double.parseDouble(lb_v_cusDueLoanAmoun.getText());
            double currentLoan = Double.parseDouble(tf_loanAmount.getText());

            if (dueAmount < currentLoan) {
                this.ifNewLoanAmountLessThanDueAmount = true;
            } else {
                JOptionPane.showMessageDialog(this, "Loan Amount should be Greater than due Loan,for the Previous Loan Settlment", "Loan Proceed Stopped", JOptionPane.WARNING_MESSAGE);
                tf_extraInterest.setText("");
                tf_period.setText("");
                this.ifNewLoanAmountLessThanDueAmount = false;

            }

        }

    }//GEN-LAST:event_tf_loanAmountKeyPressed

    private void cb_periodTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_periodTypeItemStateChanged

        String periodType = cb_mainInstallmentPeriodType.getSelectedItem().toString().substring(0, 1).toUpperCase();
        md_genLoadId(periodType);

        //md_genLoadId();

    }//GEN-LAST:event_cb_periodTypeItemStateChanged

    private void tf_contactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_contactKeyReleased

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            cb_selectFunder_customer.grabFocus();
        }


    }//GEN-LAST:event_tf_contactKeyReleased

    private void cb_selectFunder_customerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_selectFunder_customerKeyReleased

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            cb_selectFund.grabFocus();
        }

    }//GEN-LAST:event_cb_selectFunder_customerKeyReleased

    private void cb_selectFundKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_selectFundKeyReleased

        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            tf_loanAmount.grabFocus();
        }

    }//GEN-LAST:event_cb_selectFundKeyReleased

    private void tf_extraInterestKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_extraInterestKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_extraInterestKeyReleased

    private void cb_mainInstallmentPeriodTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_mainInstallmentPeriodTypeKeyReleased

        if (cb_mainInstallmentPeriodType.getSelectedIndex() == 0) {
            cb_periodType.setEnabled(true);

        }


    }//GEN-LAST:event_cb_mainInstallmentPeriodTypeKeyReleased

    private void bt_updateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateCustomerActionPerformed

        md_updateCustomer();
        bt_updateCustomer.setEnabled(false);
        md_clear_funder();

    }//GEN-LAST:event_bt_updateCustomerActionPerformed

    String localNIC = "";
    String databaseNIC = "";
    String dbfullname = "";
    String dbaddress = "";
    String dbcontact = "";
    String dbgender = "";

    String gt_nic = "";
    String gt_fullname = "";
    String gt_address = "";
    String gt_contact = "";
    String gt_gender = "male";

    private void bt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_searchActionPerformed
        localNIC = tf_nic.getText().trim().toLowerCase();

        gt_nic = tf_nic.getText().toLowerCase().trim();
        gt_fullname = tf_name.getText().trim();
        gt_address = tf_address.getText().trim();
        gt_contact = tf_contact.getText().trim();

        try {

            ResultSet search_nic = MC_DB.search_dataOne("customer", "cus_nic", localNIC);
            if (search_nic.next()) {
                databaseNIC = search_nic.getString("cus_nic");
                dbfullname = search_nic.getString("cus_fullname");
                dbaddress = search_nic.getString("cus_address");
                dbcontact = search_nic.getString("cus_contact");
                dbgender = search_nic.getString("cus_gender");
                int cus_id = search_nic.getInt("idcustomer");

                //load loan data
                ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans WHERE idcustomer='" + cus_id + "'");
                if (rs.next()) {
                    String loanAmount = rs.getString("loan_amount");
                    String dueLoanAmount = rs.getString("due_loan_amount");
                    String loanNo = rs.getString("loan_no");
                    tf_loanNumber.setText(loanNo);
                    lb_v_cusLoanAmount.setText(loanAmount);
                    lb_v_cusDueLoanAmoun.setText(dueLoanAmount);
                }

                //load loan data
            } else {
                JOptionPane.showMessageDialog(this, "No Customer Found", "Error", JOptionPane.ERROR_MESSAGE);

            }
            if (!(dbfullname.isEmpty() && dbaddress.isEmpty() && dbcontact.isEmpty() && dbgender.isEmpty())) {

                tf_nic.setText(databaseNIC);
                tf_name.setText(dbfullname);
                tf_address.setText(dbaddress);
                if (dbgender.equals("male")) {
                    rb_male.setSelected(true);
                } else {
                    rb_female.setSelected(true);
                }
                tf_contact.setText(dbcontact);
                bt_updateCustomer.setEnabled(true);
            }
//
//            if (rb_female.isSelected()) {
//                gt_gender = "male";
//            } else {
//                gt_gender = "female";
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_bt_searchActionPerformed

    private void tf_loanAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_loanAmountKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_loanAmountKeyTyped

    private void tf_extraInterestKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_extraInterestKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_extraInterestKeyTyped

    private void tf_periodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_periodKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_periodKeyTyped

    private void tf_contactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_contactKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PLUS)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_contactKeyTyped

    private void tf_nicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nicKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_V)) {
            evt.consume();
        }


    }//GEN-LAST:event_tf_nicKeyTyped

    private void cb_mainInstallmentPeriodTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_mainInstallmentPeriodTypeItemStateChanged

        String periodType = cb_mainInstallmentPeriodType.getSelectedItem().toString().substring(0, 1).toUpperCase();
        md_genLoadId(periodType);
//        String periodType = "D";
//
//        if (cb_mainInstallmentPeriodType.getSelectedIndex() == 0) {
//            periodType = "D";
//        } else if (cb_periodType.getSelectedIndex() == 1) {
//            periodType = "W";
//
//        } else if (cb_periodType.getSelectedIndex() == 2) {
//            periodType = "M";
//        }
//
//        md_genLoadId(periodType);

    }//GEN-LAST:event_cb_mainInstallmentPeriodTypeItemStateChanged

    private void cb_selectFundItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_selectFundItemStateChanged
        String periodType = cb_mainInstallmentPeriodType.getSelectedItem().toString().substring(0, 1).toUpperCase();
        md_genLoadId(periodType);

//        if (cb_mainInstallmentPeriodType.getSelectedIndex() == 0) {
//                periodType = "D";
//            } else if (cb_periodType.getSelectedIndex() == 1) {
//                periodType = "W";
//
//            } else if (cb_periodType.getSelectedIndex() == 2) {
//                periodType = "M";
//            }
//        
//        md_genLoadId(periodType);

    }//GEN-LAST:event_cb_selectFundItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addLoan;
    private javax.swing.JButton bt_search;
    private javax.swing.JButton bt_updateCustomer;
    private javax.swing.JComboBox cb_mainInstallmentPeriodType;
    private javax.swing.JComboBox cb_periodType;
    public static javax.swing.JComboBox cb_selectFund;
    public static javax.swing.JComboBox cb_selectFunder_customer;
    private com.toedter.calendar.JDateChooser dc_registrationDate;
    private javax.swing.ButtonGroup genderGroup;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lb_v_cusDueLoanAmoun;
    private javax.swing.JLabel lb_v_cusLoanAmount;
    private javax.swing.JLabel lb_v_finalDate;
    private javax.swing.JLabel lb_v_installment;
    private javax.swing.JLabel lb_v_loanAmount;
    private javax.swing.JLabel lb_v_period;
    private javax.swing.JLabel lb_v_profit;
    private javax.swing.JLabel lb_v_totalAmount;
    private javax.swing.JPanel pl_viewCustomer;
    public static javax.swing.JRadioButton rb_female;
    public static javax.swing.JRadioButton rb_male;
    public static javax.swing.JTextField tf_address;
    public static javax.swing.JTextField tf_contact;
    private javax.swing.JTextField tf_extraInterest;
    private javax.swing.JTextField tf_loanAmount;
    private javax.swing.JTextField tf_loanNumber;
    public static javax.swing.JTextField tf_name;
    private javax.swing.JTextField tf_newloanNumber;
    public static javax.swing.JTextField tf_nic;
    private javax.swing.JTextField tf_period;
    // End of variables declaration//GEN-END:variables
//rs = MC_DB.myConnection().createStatement().executeQuery("SELECT e.funder_name, e.idfunder, u.fund, u.fund_date,u.idfund FROM funder AS e LEFT JOIN fund AS u ON e.idfunder = u.funder_idfunder;");

    private void md_loadFunder() {

        try {

            ResultSet rs;
            //rs = MC_JavaDataBaseConnection.search_AlluseTable("funder");
            cb_selectFunder_customer.removeAllItems();
            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM funder ORDER BY idfunder DESC");
            while (rs.next()) {

                String funder_name = rs.getString("funder_name");
                int funder_id = rs.getInt("idfunder");
                cb_selectFunder_customer.addItem(funder_id + "-" + funder_name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadFund(int id) {

        try {

            ResultSet rs;
            //rs = MC_JavaDataBaseConnection.search_AlluseTable("funder");

            if (cb_selectFund.getItemCount() > 0) {
                cb_selectFund.removeAllItems();
            }

            //rs = MC_DB.myConnection().createStatement().executeQuery("SELECT e.funder_name, e.idfunder, u.fund, u.fund_date,u.idfund FROM funder AS e LEFT JOIN fund AS u ON e.idfunder = u.funder_idfunder;");
            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM fund WHERE funder_idfunder='" + id + "'  ORDER BY idfund DESC;");

            while (rs.next()) {

                String fund = rs.getString("fund");
                String funddate = rs.getString("fund_date");
                int fundid = rs.getInt("idfund");

                cb_selectFund.addItem(fundid + "-" + fund + "-" + funddate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void md_genLoadId(String periodType) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                int cus_id = 0;
                int loan_id = 0;
                try {
                    //String genidType = "LON-W-20160808-00001";
                    String perFix = "LO";
                    SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
                    String registration_date;
                    try {
                        dc_registrationDate.setDate(new Date());
                        Date date = dc_registrationDate.getDate();
                        sdff = new SimpleDateFormat("yyyy-MM-dd");
                        registration_date = sdff.format(date);

                        registration_date = sdff.format(date);
                    } catch (Exception e) {
                        registration_date = sdff.format(new Date());
                    }

//            JOptionPane.showMessageDialog(this, "Innnnnnnnnnnnneeeeee" + registration_date + "-" + periodType);
                    ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT MAX(idcustomer) AS HighestID FROM customer;");
                    while (rs.next()) {
                        cus_id = rs.getInt("HighestID");
                    }

                    ResultSet rsloanid = MC_DB.myConnection().createStatement().executeQuery("SELECT MAX(idloans) AS HighestID FROM loans;");
                    while (rsloanid.next()) {
                        loan_id = rsloanid.getInt("HighestID");
                    }

                    cus_id = cus_id + 1;
                    loan_id = loan_id + 1;

                    String newDateRegistarion = registration_date.replaceAll("-", "");
                    //JOptionPane.showMessageDialog(this, "ID is Max :" + cus_id + "----" + perFix + "-" + periodType + "-" + newDateRegistarion + cus_id);

                    tf_newloanNumber.setText(perFix + "-" + newDateRegistarion + "-" + cus_id + "" + periodType + "-" + loan_id);
                } catch (SQLException ex) {
                    cus_id = 0;
                    loan_id = 0;
                    //Logger.getLogger(jp_customer_add.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public int md_funderID() {
        String idwithname = (String) cb_selectFunder_customer.getSelectedItem();
        int funderID = 0;
        try {
            String[] id = idwithname.split("-", 0);
            if (id != null) {

                funderID = Integer.parseInt(id[0]);

            } else {
                idwithname = (String) cb_selectFunder_customer.getSelectedItem();
            }
        } catch (Exception e) {
        }
        return funderID;

    }

    private void md_profitCalculation() {

        try {
            Double loanAmount = Double.parseDouble(tf_loanAmount.getText().trim());
            Double extraInterest = Double.parseDouble(tf_extraInterest.getText().trim());
            Double period = Double.parseDouble(tf_period.getText().trim());

            Double fullAmount = loanAmount + extraInterest;
            Double profit = fullAmount - loanAmount;
            Double installment = fullAmount / period;

            Long roundloanAmount = Math.round(loanAmount);
            Long roundfullAmount = Math.round(fullAmount);
            Long roundInstallment = Math.round(installment);
            Long roundProfit = Math.round(profit);

            int loanAmount_int = roundloanAmount.intValue();
            int fullAmount_int = roundfullAmount.intValue();
            int installment_int = roundInstallment.intValue();
            int Profit_int = roundProfit.intValue();
            int Period_int = period.intValue();

            String loanAmount_text = loanAmount_int + ".00";
            String fullAmount_text = fullAmount_int + ".00";
            String installment_text = installment_int + ".00";
            String Profit_text = Profit_int + ".00";
            String Period_text = Period_int + "";

            lb_v_period.setText("");
            lb_v_period.setText(Period_text);

            lb_v_totalAmount.setText("");
            lb_v_totalAmount.setText(fullAmount_text);

            lb_v_installment.setText("");
            lb_v_installment.setText(installment_text);

            lb_v_loanAmount.setText("");
            lb_v_loanAmount.setText(loanAmount_text);

            lb_v_profit.setText("");
            lb_v_profit.setText(Profit_text);

            Toolkit.getDefaultToolkit().beep();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void md_tb_loadCustomer() {
//        
//        try {
//            ResultSet rs_ldfunder = null;
//            try {
//
//                //rs = MC_JavaDataBaseConnection.myConnection().createStatement().executeQuery("");
//                rs_ldfunder = MC_DB.myConnection().createStatement().executeQuery("Select c.cus_fullname,c.cus_contact, from customer as c left join loans as l ORDER BY c.idcustomer DESC");
//
//                DefaultTableModel dtm_cus = (DefaultTableModel) tb_customerAddView.getModel();
//                dtm_cus.setRowCount(0);
//                while (rs_ldfunder.next()) {
//                    Vector v = new Vector();
//                    v.add(rs_ldfunder.getRow());
//                    v.add(rs_ldfunder.getString("c.cus_fullname"));
//                    v.add(rs_ldfunder.getString("c.cus_contact"));
//                    
//                    dtm_cus.addRow(v);
//
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(Admin_funderInformation.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            rs_ldfunder.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Admin_funderInformation.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//    }
    private void calDate() {
        if (cb_periodType.getSelectedIndex() == 1) {
            dateCounter(2);
        } else {
            dateCounter(1);
        }
    }

    private void dateCounter(int daysToSkip) {
        int dayCount = Integer.parseInt(tf_period.getText());
        Calendar cd = Calendar.getInstance();
        int day = cd.get(Calendar.DAY_OF_WEEK);
        int datediff = 0;
        if (daysToSkip == 2) {
            datediff = Calendar.SATURDAY - day;
        } else {
            datediff = Calendar.SUNDAY - day;
            if (datediff < 0) {
                datediff += 7;
            }
        }
        System.out.println(datediff);
        dayCount -= datediff;
//        dayCount -= daysToSkip;
        cd.add(Calendar.DATE, datediff);
        cd.add(Calendar.DATE, daysToSkip);
        System.out.println(cd.getTime());
        while (dayCount >= 0) {
            if (daysToSkip == 2) {
                if (cd.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    dayCount--;
                }
            } else if (cd.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dayCount--;
                System.out.println(dayCount);
            }

            cd.add(Calendar.DATE, 1);
            System.out.println(cd.getTime());
        }
        String tt_v_finalDate = cd.getTime() + "";
        String text = tt_v_finalDate;
        String[] split = text.split(" ");
        String t1 = split[0];
        String t2 = split[1];
        String t3 = split[2];
        String t4 = split[5];

        lb_v_finalDate.setText(t1 + "-" + t2 + "-" + t3 + "-" + t4);
        String notformat = t2 + "-" + t3 + "-" + t4;
        Date d = new Date(notformat);
        // JOptionPane.showMessageDialog(this, d);

    }

    private void md_tb_loadCustomer(String status) {
        DefaultTableModel dtm;
        try {

            ResultSet rs;
            //rs = MC_JavaDataBaseConnection.search_AlluseTable("funder");
//            dtm = (DefaultTableModel) tb_customerAddView.getModel();
//            dtm.setRowCount(0);

            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM customer AS c LEFT JOIN loans AS l ON l.idcustomer=c.idcustomer WHERE loan_status='" + status + "' ORDER BY c.idcustomer DESC");
            while (rs.next()) {
                int cus_id = rs.getRow();
                String loan_date = rs.getString("l.loan_date");
                String loan_no = rs.getString("l.loan_no");
                String cus_nic = rs.getString("c.cus_nic");
                String loan_amount = rs.getString("l.loan_amount");
                String loan_period = rs.getString("l.loan_period");
                String loan_installment = rs.getString("l.loan_installment");
                String due_loan_amount = rs.getString("l.due_loan_amount");
                String loan_mainperiodtype = rs.getString("l.loan_mainperiodtype");
                String cus_fullname = rs.getString("c.cus_fullname");
                String cus_address = rs.getString("c.cus_address");
                String cus_contact = rs.getString("c.cus_contact");

                Vector v = new Vector();
                v.add(rs.getRow());
                v.add(loan_date);
                v.add(loan_no);
                v.add(cus_nic);
                v.add(loan_amount);
                v.add(loan_period);
                v.add(loan_installment);
                v.add(due_loan_amount);
                v.add(loan_mainperiodtype);
                v.add(cus_fullname);
                v.add(cus_address);
                v.add(cus_contact);

//                dtm.addRow(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void md_updateCustomer() {

        try {
            if (!(localNIC.isEmpty()) && !(databaseNIC.isEmpty())) {
                if (localNIC == databaseNIC) {

                    int isOK = JOptionPane.showConfirmDialog(this, "Are you Sure Update Customer Details!", "Confrom", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    MC_DB.update_data("customer SET cus_fullname = '" + gt_fullname + "',cus_address = '" + gt_address + "',cus_contact = '" + gt_contact + "',cus_gender = '" + gt_gender + "' WHERE cus_nic = '" + gt_nic + "' ;");
                    JOptionPane.showMessageDialog(this, "Updated!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void md_clear_funder() {

        tf_nic.setText("");
        tf_name.setText("");
        tf_address.setText("");
        rb_male.setSelected(false);
        rb_female.setSelected(false);
        tf_contact.setText("");

    }

}

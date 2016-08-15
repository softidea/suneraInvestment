package com.softidea.www.private_access.adminstrator;

import com.fsczone.www.lookAndFeel.pro_lookandfeel;
import com.softidea.www.public_connection.MC_DB;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin_fundManagment extends javax.swing.JPanel {

    public Admin_fundManagment() {
        initComponents();

        new Thread(() -> {
            try {

                pro_lookandfeel.Set();
                tf_funderName.grabFocus();
                md_tb_load_Funder();
                md_tb_load_Fund();
                md_loadFunder();
                loadAllFunders();
                loadActiveFunders();
                dc_backDate.setDate(new Date());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void loadFunderStatus() {

        String funder = cb_loadFunders.getSelectedItem().toString();
        String fundDetail[] = funder.split("-");

        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT funder_status FROM funder WHERE idfunder='" + Integer.parseInt(fundDetail[1]) + "'");
            if (rs.next()) {
                String status = rs.getString("funder_status");
                if (status.equals("Active")) {
                    cb_loadActiveStatus.setSelectedIndex(0);
                } else {
                    cb_loadActiveStatus.setSelectedIndex(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadAllFunders() {
        try {

            ResultSet rs_ldfunder = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM funder");
            Vector v = new Vector();

            while (rs_ldfunder.next()) {
                cb_loadFunders.addItem(rs_ldfunder.getString("funder_name") + "-" + rs_ldfunder.getString("idfunder"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public void loadActiveFunders() {
        try {

            ResultSet rs_ldfunder = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM funder WHERE funder_status='Active'");
            Vector v = new Vector();

            while (rs_ldfunder.next()) {
                cb_selectActiveFunder.addItem(rs_ldfunder.getString("funder_name") + "-" + rs_ldfunder.getString("idfunder"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_funder = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_inactive_fund = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tf_address = new javax.swing.JTextField();
        lb_addRoute = new javax.swing.JLabel();
        tf_funderName = new javax.swing.JTextField();
        lb_addRoute1 = new javax.swing.JLabel();
        bt_funder = new javax.swing.JButton();
        tf_contactNumber = new javax.swing.JTextField();
        lb_addRoute2 = new javax.swing.JLabel();
        vp_funderactiveinactive = new javax.swing.JPanel();
        lb_addRoute8 = new javax.swing.JLabel();
        bt_funder1 = new javax.swing.JButton();
        cb_selectActiveFunder = new javax.swing.JComboBox();
        lb_addRoute7 = new javax.swing.JLabel();
        cb_zerofund = new javax.swing.JComboBox();
        lb_addRoute9 = new javax.swing.JLabel();
        cb_zerofundstatus = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        tf_fundAmount = new javax.swing.JTextField();
        lb_addRoute3 = new javax.swing.JLabel();
        lb_addRoute4 = new javax.swing.JLabel();
        bt_addFund = new javax.swing.JButton();
        cb_selectFunder = new javax.swing.JComboBox();
        ta_FundDiscription = new javax.swing.JTextField();
        lb_addRoute5 = new javax.swing.JLabel();
        lb_addRoute6 = new javax.swing.JLabel();
        dc_backDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        vp_funderactiveinactive1 = new javax.swing.JPanel();
        bt_funder2 = new javax.swing.JButton();
        cb_loadFunders = new javax.swing.JComboBox();
        lb_addRoute11 = new javax.swing.JLabel();
        lb_addRoute12 = new javax.swing.JLabel();
        cb_loadActiveStatus = new javax.swing.JComboBox();
        bt_funder3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        tb_funder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_funder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Funder Name", "Address", "Phone Number", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_funder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_funderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_funder);
        if (tb_funder.getColumnModel().getColumnCount() > 0) {
            tb_funder.getColumnModel().getColumn(0).setPreferredWidth(3);
            tb_funder.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        tb_inactive_fund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_inactive_fund.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Funder", "Fund Amount", "Date", "Back Date", "Discription", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_inactive_fund.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_inactive_fundMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_inactive_fund);

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Funder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tf_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_address.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_address.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_addressKeyReleased(evt);
            }
        });

        lb_addRoute.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute.setText("Funder Name:");

        tf_funderName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_funderName.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_funderName.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_funderName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_funderNameKeyReleased(evt);
            }
        });

        lb_addRoute1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute1.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute1.setText("Address");

        bt_funder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_funder.setForeground(new java.awt.Color(255, 255, 255));
        bt_funder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_funder.setText("Add Funder");
        bt_funder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_funder.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_funder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_funderActionPerformed(evt);
            }
        });

        tf_contactNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_contactNumber.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_contactNumber.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_contactNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_contactNumberKeyReleased(evt);
            }
        });

        lb_addRoute2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute2.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute2.setText("Contact Number");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_funderName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bt_funder, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_addRoute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_funderName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_contactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_funder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vp_funderactiveinactive.setBackground(new java.awt.Color(66, 66, 66));
        vp_funderactiveinactive.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fund Settlement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        lb_addRoute8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute8.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute8.setText("Select Fund:");

        bt_funder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_funder1.setForeground(new java.awt.Color(255, 255, 255));
        bt_funder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_funder1.setText("Settle Fund");
        bt_funder1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_funder1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_funder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_funder1ActionPerformed(evt);
            }
        });

        cb_selectActiveFunder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_selectActiveFunder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_selectActiveFunderItemStateChanged(evt);
            }
        });
        cb_selectActiveFunder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectActiveFunderActionPerformed(evt);
            }
        });

        lb_addRoute7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute7.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute7.setText("Select Funder");

        cb_zerofund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_zerofund.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_zerofundItemStateChanged(evt);
            }
        });
        cb_zerofund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_zerofundActionPerformed(evt);
            }
        });

        lb_addRoute9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute9.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute9.setText("Status:");

        cb_zerofundstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_zerofundstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-Active" }));
        cb_zerofundstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_zerofundstatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vp_funderactiveinactiveLayout = new javax.swing.GroupLayout(vp_funderactiveinactive);
        vp_funderactiveinactive.setLayout(vp_funderactiveinactiveLayout);
        vp_funderactiveinactiveLayout.setHorizontalGroup(
            vp_funderactiveinactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vp_funderactiveinactiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vp_funderactiveinactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_funder1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(cb_zerofundstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_addRoute9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_zerofund, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_addRoute8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_selectActiveFunder, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_addRoute7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        vp_funderactiveinactiveLayout.setVerticalGroup(
            vp_funderactiveinactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vp_funderactiveinactiveLayout.createSequentialGroup()
                .addComponent(lb_addRoute7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_selectActiveFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_zerofund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_zerofundstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_funder1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Fund", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tf_fundAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_fundAmount.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_fundAmount.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_fundAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_fundAmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_fundAmountKeyTyped(evt);
            }
        });

        lb_addRoute3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute3.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute3.setText("Select Funder");

        lb_addRoute4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute4.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute4.setText("Fund Amount");

        bt_addFund.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_addFund.setForeground(new java.awt.Color(255, 255, 255));
        bt_addFund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_addFund.setText("Add Fund");
        bt_addFund.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_addFund.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_addFund.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addFundActionPerformed(evt);
            }
        });

        cb_selectFunder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_selectFunder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectFunderActionPerformed(evt);
            }
        });

        ta_FundDiscription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ta_FundDiscription.setText("None");
        ta_FundDiscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ta_FundDiscriptionMouseClicked(evt);
            }
        });
        ta_FundDiscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ta_FundDiscriptionActionPerformed(evt);
            }
        });

        lb_addRoute5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute5.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute5.setText("Discription:");

        lb_addRoute6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute6.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute6.setText("Back Date :");

        dc_backDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_addRoute5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cb_selectFunder, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_addRoute3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_addRoute4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_fundAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ta_FundDiscription)
                        .addComponent(lb_addRoute6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dc_backDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_addRoute3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_selectFunder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_fundAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dc_backDate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ta_FundDiscription, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_addFund, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Funder View:");

        vp_funderactiveinactive1.setBackground(new java.awt.Color(66, 66, 66));
        vp_funderactiveinactive1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funder Active/Inactive", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        bt_funder2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_funder2.setForeground(new java.awt.Color(255, 255, 255));
        bt_funder2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_funder2.setText("Status Change");
        bt_funder2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_funder2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_funder2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_funder2ActionPerformed(evt);
            }
        });

        cb_loadFunders.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_loadFunders.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_loadFundersItemStateChanged(evt);
            }
        });
        cb_loadFunders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_loadFundersActionPerformed(evt);
            }
        });

        lb_addRoute11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute11.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute11.setText("Select Funder");

        lb_addRoute12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute12.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute12.setText("Status:");

        cb_loadActiveStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_loadActiveStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-Active" }));
        cb_loadActiveStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_loadActiveStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vp_funderactiveinactive1Layout = new javax.swing.GroupLayout(vp_funderactiveinactive1);
        vp_funderactiveinactive1.setLayout(vp_funderactiveinactive1Layout);
        vp_funderactiveinactive1Layout.setHorizontalGroup(
            vp_funderactiveinactive1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vp_funderactiveinactive1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vp_funderactiveinactive1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_funder2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(cb_loadActiveStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_addRoute12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_loadFunders, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_addRoute11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        vp_funderactiveinactive1Layout.setVerticalGroup(
            vp_funderactiveinactive1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vp_funderactiveinactive1Layout.createSequentialGroup()
                .addComponent(lb_addRoute11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_loadFunders, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_addRoute12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_loadActiveStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_funder2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_funder3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_funder3.setForeground(new java.awt.Color(255, 255, 255));
        bt_funder3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_funder3.setText("View Inactive Funders");
        bt_funder3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_funder3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_funder3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_funder3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vp_funderactiveinactive1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(bt_funder3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(vp_funderactiveinactive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vp_funderactiveinactive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(vp_funderactiveinactive1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_funder3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void bt_funderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_funderActionPerformed

        try {

            String name = tf_funderName.getText().trim();
            String address = tf_address.getText().trim();
            String contact = tf_contactNumber.getText().trim();
            if (!(name.isEmpty() && address.isEmpty() && contact.isEmpty())) {

                ResultSet search_fundername = MC_DB.search_dataOne("funder", "funder_name", name);
                String db_fundername = "";
                while (search_fundername.next()) {
                    db_fundername = search_fundername.getString("funder_name").toLowerCase();

                }

                if (db_fundername.equals(name.toLowerCase())) {

                    JOptionPane.showMessageDialog(this, db_fundername + " alrady exists");

                } else {

                    String qu_addFunder = "INSERT INTO funder (funder_name,funder_address,funder_contact,funder_status) VALUES('" + name + "','" + address + "','" + contact + "','Active')";

                    if (!name.isEmpty()) {
                        if (!address.isEmpty()) {
                            if (!contact.isEmpty()) {

                                int i = JOptionPane.showConfirmDialog(this, "Are You Sure? \n Name :" + name + "\n Address :" + address + "\n Tel :" + contact + "", "Save Funder?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (i == JOptionPane.YES_OPTION) {
                                    new Thread(() -> {
                                        try {
                                            int executeUpdate = MC_DB.myConnection().createStatement().executeUpdate(qu_addFunder);
                                            if (executeUpdate > 0) {
                                                JOptionPane.showMessageDialog(Admin_fundManagment.this, "Funder " + name + " Successfully Saved!");
                                                tf_fundAmount.grabFocus();
                                                md_clearFunder();
                                                md_loadFunder();
                                                md_tb_load_Funder();
                                            }

                                        } catch (SQLException ex) {
                                            Logger.getLogger(Admin_userManagment.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }).start();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Contact Filed is empty!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Address Filed is empty!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Name Filed is empty!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill the fileds", "Empty Fields Found", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException e) {
        } catch (SQLException ex) {
            Logger.getLogger(Admin_fundManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_funderActionPerformed

    private void bt_addFundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addFundActionPerformed

        if (cb_selectFunder.getItemCount() > 0) {
            if (!(tf_fundAmount.getText().isEmpty() && dc_backDate.getDate() == null && ta_FundDiscription.getText().isEmpty())) {
                int i = JOptionPane.showConfirmDialog(this, "Are You Sure? ", "Save Fund?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (i == JOptionPane.YES_OPTION) {
                    md_addFund();
                    JOptionPane.showMessageDialog(this, "Successfully Fund Added!");
                    clearFund();
                    md_tb_load_Fund();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill the fileds", "Empty Fields Found", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please add Funder before Add Fund", "No Funder Found", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_bt_addFundActionPerformed

    private void cb_selectFunderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectFunderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_selectFunderActionPerformed

    private void tb_funderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_funderMouseClicked

        md_tb_load_Funder();

    }//GEN-LAST:event_tb_funderMouseClicked

    private void tb_inactive_fundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_inactive_fundMouseClicked

        md_tb_load_Fund();

    }//GEN-LAST:event_tb_inactive_fundMouseClicked

    private void tf_funderNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_funderNameKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_address.grabFocus();
        }

    }//GEN-LAST:event_tf_funderNameKeyReleased

    private void tf_addressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_addressKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            tf_contactNumber.grabFocus();
        }

    }//GEN-LAST:event_tf_addressKeyReleased

    private void tf_contactNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_contactNumberKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            //bt_funder.doClick();
        }

    }//GEN-LAST:event_tf_contactNumberKeyReleased

    private void tf_fundAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_fundAmountKeyReleased

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            ta_FundDiscription.grabFocus();
        }

    }//GEN-LAST:event_tf_fundAmountKeyReleased

    private void tf_fundAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_fundAmountKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_fundAmountKeyTyped

    private void ta_FundDiscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ta_FundDiscriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ta_FundDiscriptionActionPerformed

    private void ta_FundDiscriptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ta_FundDiscriptionMouseClicked
        ta_FundDiscription.selectAll();
    }//GEN-LAST:event_ta_FundDiscriptionMouseClicked

    private void bt_funder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_funder1ActionPerformed
       
        changeZeroFundStatus(cb_zerofundstatus.getSelectedItem().toString());
        
        
    }//GEN-LAST:event_bt_funder1ActionPerformed

    private void cb_selectActiveFunderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectActiveFunderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_selectActiveFunderActionPerformed

    private void cb_zerofundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_zerofundActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_zerofundActionPerformed

    private void cb_zerofundstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_zerofundstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_zerofundstatusActionPerformed

    private void bt_funder2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_funder2ActionPerformed
       
        changeFunderStatus();
        md_tb_load_Funder();
        md_tb_load_Fund();
        loadActiveFunders();
        
    }//GEN-LAST:event_bt_funder2ActionPerformed

    private void cb_loadFundersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_loadFundersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_loadFundersActionPerformed

    private void cb_loadActiveStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_loadActiveStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_loadActiveStatusActionPerformed

    private void bt_funder3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_funder3ActionPerformed
        
//        viewInactiveFunders();
        
    }//GEN-LAST:event_bt_funder3ActionPerformed

    private void cb_loadFundersItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_loadFundersItemStateChanged
     
        loadFunderStatus();  
        
    }//GEN-LAST:event_cb_loadFundersItemStateChanged

    private void cb_selectActiveFunderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_selectActiveFunderItemStateChanged
        
        loadZeroFunds(cb_selectActiveFunder.getSelectedItem().toString());
        
        
    }//GEN-LAST:event_cb_selectActiveFunderItemStateChanged

    private void cb_zerofundItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_zerofundItemStateChanged
       
        loadUpdateFundStatus(cb_zerofund.getSelectedItem().toString());
        
        
    }//GEN-LAST:event_cb_zerofundItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addFund;
    private javax.swing.JButton bt_funder;
    private javax.swing.JButton bt_funder1;
    private javax.swing.JButton bt_funder2;
    private javax.swing.JButton bt_funder3;
    private javax.swing.JComboBox cb_loadActiveStatus;
    private javax.swing.JComboBox cb_loadFunders;
    private javax.swing.JComboBox cb_selectActiveFunder;
    private javax.swing.JComboBox cb_selectFunder;
    private javax.swing.JComboBox cb_zerofund;
    private javax.swing.JComboBox cb_zerofundstatus;
    private com.toedter.calendar.JDateChooser dc_backDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_addRoute;
    private javax.swing.JLabel lb_addRoute1;
    private javax.swing.JLabel lb_addRoute11;
    private javax.swing.JLabel lb_addRoute12;
    private javax.swing.JLabel lb_addRoute2;
    private javax.swing.JLabel lb_addRoute3;
    private javax.swing.JLabel lb_addRoute4;
    private javax.swing.JLabel lb_addRoute5;
    private javax.swing.JLabel lb_addRoute6;
    private javax.swing.JLabel lb_addRoute7;
    private javax.swing.JLabel lb_addRoute8;
    private javax.swing.JLabel lb_addRoute9;
    private javax.swing.JTextField ta_FundDiscription;
    private javax.swing.JTable tb_funder;
    private javax.swing.JTable tb_inactive_fund;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_contactNumber;
    private javax.swing.JTextField tf_fundAmount;
    private javax.swing.JTextField tf_funderName;
    private javax.swing.JPanel vp_funderactiveinactive;
    private javax.swing.JPanel vp_funderactiveinactive1;
    // End of variables declaration//GEN-END:variables

    private void md_tb_load_Funder() {

        new Thread(() -> {
            try {
                try {
                    ResultSet rs_ldfunder = null;
                    try {

                        //rs = MC_JavaDataBaseConnection.myConnection().createStatement().executeQuery("");
                        rs_ldfunder = MC_DB.myConnection().createStatement().executeQuery("Select * from funder ORDER BY funder_name ASC");

                        DefaultTableModel dtm = (DefaultTableModel) tb_funder.getModel();
                        dtm.setRowCount(0);
                        while (rs_ldfunder.next()) {
                            Vector v = new Vector();
                            v.add(rs_ldfunder.getRow());
                            v.add(rs_ldfunder.getString("funder_name"));
                            v.add(rs_ldfunder.getString("funder_address"));
                            v.add(rs_ldfunder.getString("funder_contact"));
                            v.add(rs_ldfunder.getString("funder_status"));

                            dtm.addRow(v);

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(Admin_fundManagment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rs_ldfunder.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_fundManagment.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void md_tb_load_Fund() {

        new Thread(() -> {
            try {
                ResultSet rs_fund;
                ResultSet yu;
                try {

                    rs_fund = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM fund ORDER BY idfund DESC");
                    DefaultTableModel dtm_fund = (DefaultTableModel) tb_inactive_fund.getModel();
                    dtm_fund.setRowCount(0);
                    while (rs_fund.next()) {

                        Vector v = new Vector();
                        v.add(rs_fund.getRow());
                        yu = MC_DB.myConnection().createStatement().executeQuery("SELECT funder_name FROM funder where idfunder='" + rs_fund.getInt("funder_idfunder") + "'");
                        while (yu.next()) {
                            v.add(yu.getString("funder_name"));
                        }

                        v.add(rs_fund.getDouble("fund"));
                        v.add(rs_fund.getString("fund_date"));
                        v.add(rs_fund.getString("fund_enddate"));
                        v.add(rs_fund.getString("descriptin"));
                        v.add(rs_fund.getString("fund_status"));

                        dtm_fund.addRow(v);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void md_clearFunder() {

        tf_funderName.setText("");
        tf_address.setText("");
        tf_contactNumber.setText("");

    }

    private void md_loadFunder() {
        try {
            ResultSet rs11 = null;
            try {

                //rs = MC_JavaDataBaseConnection.search_AlluseTable("funder");
                cb_selectFunder.removeAllItems();
                rs11 = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM funder ORDER BY idfunder DESC");
                while (rs11.next()) {

                    String funder_name = rs11.getString("funder_name");
                    cb_selectFunder.addItem(funder_name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs11.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_fundManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void md_addFund() {
        String fundAmount = tf_fundAmount.getText().trim();
        String fundDiscription = ta_FundDiscription.getText().trim();
        ResultSet rss = null;

        Double fundAmountD = Double.parseDouble(fundAmount);

        Long roundfundAmount = Math.round(fundAmountD);

        int fundAmount_int = roundfundAmount.intValue();

        String fundAmount_text = fundAmount_int + ".00";

        if (!(fundAmount.isEmpty() && fundDiscription.isEmpty())) {
            try {
                try {
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(d);

                    String backDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_backDate.getDate());

                    //ResultSet rss = MC_JavaDataBaseConnection.myConnection().createStatement().executeQuery("SELECT idfunder FROM funder WHERE funder_name='" + cb_selectFunder.getSelectedItem().toString() + "'");
                    rss = MC_DB.myConnection().createStatement().executeQuery("SELECT idfunder FROM funder WHERE funder_name='" + cb_selectFunder.getSelectedItem() + "'");
                    int idfunder = 0;
                    if (rss.next()) {
                        idfunder = rss.getInt("idfunder");
                    }

                    String sql_qury = "INSERT INTO fund (fund,fund_update,fund_date,fund_enddate,descriptin,fund_status,funder_idfunder) VALUES ('" + fundAmount_text + "','" + fundAmount_text + "','" + date + "','" + backDate + "','" + ta_FundDiscription.getText().trim() + "','Active','" + idfunder + "')";
                    MC_DB.update_data(sql_qury);

                    String sql_tocash = "INSERT INTO `cash_account` (`date`,`amount`,`cash_ac_type`,`cash_ac_discription`,`cash_ac_status`) VALUES ('" + date + "','" + fundAmount_text + "','Fund','" + ta_FundDiscription.getText().trim() + "','Active');";
                    MC_DB.update_data(sql_tocash);

                    System.out.println("OTO cash Account");
                } catch (SQLException e) {

                    e.printStackTrace();
                }
                rss.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin_fundManagment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void clearFund() {

        tf_fundAmount.setText("");
        ta_FundDiscription.setText("");

    }

    private void changeFunderStatus() {
       
        String funder = cb_loadFunders.getSelectedItem().toString();
        String fundDetail[] = funder.split("-");
        int funderid=Integer.parseInt(fundDetail[1]);
        String funder_status= cb_loadActiveStatus.getSelectedItem().toString();
        System.out.println(fundDetail[1]);
        try {
            MC_DB.myConnection().createStatement().executeUpdate("UPDATE funder SET funder_status='"+funder_status+"' WHERE idfunder='"+funderid+"'");
            JOptionPane.showMessageDialog(this, "Funder status Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

//    private void viewInactiveFunders() {
//       DefaultTableModel dtm = (DefaultTableModel)tb_inactive_fund.getModel();
//       dtm.setRowCount(0);
//        try {
//            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM funder WHERE funder_status='In-Active'");
//            while(rs.next()){
//                Vector v = new Vector();
//               
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        
//    }

    private void loadZeroFunds(String funder) {
        
        String fundDetail[] = funder.split("-");
        
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM fund WHERE funder_idfunder='"+Integer.parseInt(fundDetail[1])+"' ");
            while(rs.next()){
                if(rs.getDouble("fund_update")==0){
                    cb_zerofund.addItem(rs.getDouble("fund_update")+"- Fund ID="+rs.getInt("idfund"));
                } 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUpdateFundStatus(String fund) {
        String fundDetail[]=fund.split("=");
        try {
            
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM fund WHERE idfund='"+Integer.parseInt(fundDetail[1])+"'");
            if(rs.next()){
                if(rs.getString("fund_status").equals("Active")){
                    cb_zerofundstatus.setSelectedIndex(0);
                }else{
                    cb_zerofundstatus.setSelectedIndex(1);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void changeZeroFundStatus(String fundStatus) {
        String fundDetail[] =cb_zerofund.getSelectedItem().toString().split("=");
        
        try {
            MC_DB.myConnection().createStatement().executeUpdate("UPDATE fund SET fund_status='"+fundStatus+"' WHERE idfund='"+Integer.parseInt(fundDetail[1])+"'");
            JOptionPane.showMessageDialog(this, "Fund Status have been changed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.softidea.www.private_access.adminstrator;

import static com.softidea.www.private_access.adminstrator.Admin_workArea.jp_adminMainPanel;
import com.softidea.www.public_connection.MC_DB;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin_adminManagment extends javax.swing.JPanel {

    /**
     * Creates new form Admin_ExpanceManagment
     */
    DefaultTableModel dtm;
    String Type_ty;

    public Admin_adminManagment() {
        initComponents();

        new Thread(() -> {
            try {
                md_tb_loadAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lb_addRoute7 = new javax.swing.JLabel();
        tf_newPassword = new javax.swing.JTextField();
        bt_AdminupdatePassword = new javax.swing.JButton();
        lb_loadusernameToPasswordChange = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bt_AddAdminOrUser = new javax.swing.JButton();
        tf_Username = new javax.swing.JTextField();
        lb_addRoute5 = new javax.swing.JLabel();
        lb_addRoute3 = new javax.swing.JLabel();
        lb_addRoute4 = new javax.swing.JLabel();
        tf_Password = new javax.swing.JTextField();
        tf_Conpassword = new javax.swing.JTextField();
        lb_addRoute6 = new javax.swing.JLabel();
        tf_adminFullName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_viewAdmins = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(61, 61, 61));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Adminstrator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        lb_addRoute7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute7.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute7.setText("New Password:");

        tf_newPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_newPassword.setBorder(null);
        tf_newPassword.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_newPassword.setMinimumSize(new java.awt.Dimension(300, 40));

        bt_AdminupdatePassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_AdminupdatePassword.setForeground(new java.awt.Color(255, 255, 255));
        bt_AdminupdatePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_AdminupdatePassword.setText("Update Administrator Password");
        bt_AdminupdatePassword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_AdminupdatePassword.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_AdminupdatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AdminupdatePasswordActionPerformed(evt);
            }
        });

        lb_loadusernameToPasswordChange.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lb_loadusernameToPasswordChange.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_loadusernameToPasswordChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_addRoute7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_newPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addComponent(bt_AdminupdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_loadusernameToPasswordChange, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(lb_addRoute7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tf_newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(bt_AdminupdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(60, Short.MAX_VALUE)))
        );

        jPanel1.setBackground(new java.awt.Color(66, 66, 66));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Administrator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        bt_AddAdminOrUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_AddAdminOrUser.setForeground(new java.awt.Color(255, 255, 255));
        bt_AddAdminOrUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_AddAdminOrUser.setText("Add Administrator");
        bt_AddAdminOrUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_AddAdminOrUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_AddAdminOrUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddAdminOrUserActionPerformed(evt);
            }
        });

        tf_Username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_Username.setBorder(null);
        tf_Username.setEnabled(false);
        tf_Username.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_Username.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_Username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_UsernameMouseClicked(evt);
            }
        });

        lb_addRoute5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute5.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute5.setText("Confrom-Password:");

        lb_addRoute3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute3.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute3.setText("Username:");

        lb_addRoute4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute4.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute4.setText("Password:");

        tf_Password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_Password.setBorder(null);
        tf_Password.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_Password.setMinimumSize(new java.awt.Dimension(300, 40));

        tf_Conpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_Conpassword.setBorder(null);
        tf_Conpassword.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_Conpassword.setMinimumSize(new java.awt.Dimension(300, 40));

        lb_addRoute6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute6.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute6.setText("Full name:");

        tf_adminFullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_adminFullName.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_adminFullName)
                    .addComponent(lb_addRoute6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_addRoute3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_addRoute4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_addRoute5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_Conpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                            .addComponent(bt_AddAdminOrUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lb_addRoute6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_adminFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_Conpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_AddAdminOrUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Administrator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(484, 250));

        tb_viewAdmins.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_viewAdmins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Full Name", "Username", "Password", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_viewAdmins.setGridColor(new java.awt.Color(255, 255, 255));
        tb_viewAdmins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_viewAdminsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_viewAdmins);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1312, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 603, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_AdminupdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AdminupdatePasswordActionPerformed

        if (lb_loadusernameToPasswordChange.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Firstly,Please select name of administrator \n in the table!");
        } else {

            if (!(tf_newPassword.equals(""))) {
                String qu_up_adminPassword = "UPDATE user_account SET us_password = '" + tf_newPassword.getText().trim() + "' WHERE `us_username` = '" + lb_loadusernameToPasswordChange.getText() + "' ;";

                MC_DB.update_data(qu_up_adminPassword);

                md_tb_loadAdmin();

                JOptionPane.showMessageDialog(this, dtb + " :Password is successfully updated!");
                tf_newPassword.setText("");
                lb_loadusernameToPasswordChange.setText("Select Table Row First");
            }
        }
    }//GEN-LAST:event_bt_AdminupdatePasswordActionPerformed

    private void bt_AddAdminOrUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddAdminOrUserActionPerformed

        String fullname = tf_adminFullName.getText().trim();
        String username = tf_Username.getText().trim().toLowerCase();
        String password = tf_Password.getText().trim();
        String conpass = tf_Conpassword.getText().trim();

        int w = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Add Access", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (w == JOptionPane.YES_OPTION) {

            try {
                if (password.equals(conpass)) {
                    MC_DB.update_data("INSERT INTO user_account (us_fullname,us_username,us_password,us_type,us_status) VALUES ('" + fullname + "','" + username + "','" + conpass + "','admin','11')");
                    // MC_JavaDataBaseConnection.myConnection().createStatement().executeUpdate("");
                    JOptionPane.showMessageDialog(this, "Administrator Successfully Added");
                    md_tb_loadAdmin();
                } else {
                    JOptionPane.showMessageDialog(this, "Password is not matched!");
                }

            } catch (HeadlessException ex) {
                Logger.getLogger(Admin_userManagment.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }//GEN-LAST:event_bt_AddAdminOrUserActionPerformed

    private void tf_UsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_UsernameMouseClicked

        int i = JOptionPane.showConfirmDialog(this, "Are You Sure Add " + Type_ty + "?", "Confirm?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
        );
        if (i == JOptionPane.YES_OPTION) {
            i = 1;
            tf_Username.setEnabled(true);
            tf_Username.setEditable(true);
            tf_Username.grabFocus();
        } else {
            tf_Username.setEnabled(false);
            tf_Username.setEditable(false);
        }

    }//GEN-LAST:event_tf_UsernameMouseClicked
    String dtb;
    private void tb_viewAdminsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_viewAdminsMouseClicked
        try {
            dtb = (String) dtm.getValueAt(tb_viewAdmins.getSelectedRow(), 2);
            lb_loadusernameToPasswordChange.setText(dtb);

        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tb_viewAdminsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_AddAdminOrUser;
    private javax.swing.JButton bt_AdminupdatePassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_addRoute3;
    private javax.swing.JLabel lb_addRoute4;
    private javax.swing.JLabel lb_addRoute5;
    private javax.swing.JLabel lb_addRoute6;
    private javax.swing.JLabel lb_addRoute7;
    private javax.swing.JLabel lb_loadusernameToPasswordChange;
    private javax.swing.JTable tb_viewAdmins;
    private javax.swing.JTextField tf_Conpassword;
    private javax.swing.JTextField tf_Password;
    private javax.swing.JTextField tf_Username;
    private javax.swing.JTextField tf_adminFullName;
    private javax.swing.JTextField tf_newPassword;
    // End of variables declaration//GEN-END:variables

    private void md_tb_loadAdmin() {

        new Thread(() -> {
            try {
                try {
                    ResultSet rs;
                    rs = MC_DB.search_dataOne("user_account", "us_type", "admin");

                    dtm = (DefaultTableModel) tb_viewAdmins.getModel();
                    dtm.setRowCount(0);

                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(rs.getRow());
                        v.add(rs.getString("us_fullname"));
                        v.add(rs.getString("us_username"));
                        v.add(rs.getString("us_password"));
                        int status = rs.getInt("us_status");

                        if (status == 11) {
                            v.add("Active Administrator");
                        } else if (status == 10) {
                            v.add("In-Active Administrator");
                        }
//                    if (status.equals("01")) {
//                    v.add("Active User");
//                }else if (status.equals("00")) {
//                    v.add("In-Active User");
//                }
                        dtm.addRow(v);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}

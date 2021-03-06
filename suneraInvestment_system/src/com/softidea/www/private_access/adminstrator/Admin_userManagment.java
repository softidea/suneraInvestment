
package com.softidea.www.private_access.adminstrator;

import com.softidea.www.public_connection.MC_DB;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Admin_userManagment extends javax.swing.JPanel {

   
    DefaultTableModel dtm;

    public Admin_userManagment() {
        initComponents();
        
        new Thread(() -> {
            try {

              md_tb_load_users();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_viewUsers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        bt_AddAdminOrUser = new javax.swing.JButton();
        tf_Username = new javax.swing.JTextField();
        lb_addRoute5 = new javax.swing.JLabel();
        lb_addRoute3 = new javax.swing.JLabel();
        lb_addRoute4 = new javax.swing.JLabel();
        tf_Password = new javax.swing.JTextField();
        tf_Conpassword = new javax.swing.JTextField();
        lb_addRoute6 = new javax.swing.JLabel();
        tf_FullName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lb_addRoute7 = new javax.swing.JLabel();
        tf_usernewPassword = new javax.swing.JTextField();
        bt_updatePassword = new javax.swing.JButton();
        lb_loadusernameToPasswordChange = new javax.swing.JLabel();
        cb_userstatus = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Users", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        tb_viewUsers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_viewUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Full Name", "Username", "Password", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_viewUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_viewUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_viewUsers);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel1.setBackground(new java.awt.Color(66, 66, 66));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        bt_AddAdminOrUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_AddAdminOrUser.setForeground(new java.awt.Color(255, 255, 255));
        bt_AddAdminOrUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_AddAdminOrUser.setText("Add User");
        bt_AddAdminOrUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_AddAdminOrUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_AddAdminOrUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddAdminOrUserActionPerformed(evt);
            }
        });

        tf_Username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        tf_Password.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_Password.setMinimumSize(new java.awt.Dimension(300, 40));

        tf_Conpassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_Conpassword.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_Conpassword.setMinimumSize(new java.awt.Dimension(300, 40));

        lb_addRoute6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute6.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute6.setText("Full Name:");

        tf_FullName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_FullName.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_FullName.setMinimumSize(new java.awt.Dimension(300, 40));
        tf_FullName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_FullNameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_addRoute5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_Conpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addComponent(bt_AddAdminOrUser, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_addRoute6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_FullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lb_addRoute6)
                .addGap(7, 7, 7)
                .addComponent(tf_FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel4.setBackground(new java.awt.Color(61, 61, 61));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        lb_addRoute7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_addRoute7.setForeground(new java.awt.Color(255, 255, 255));
        lb_addRoute7.setText("New Password:");

        tf_usernewPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_usernewPassword.setMaximumSize(new java.awt.Dimension(300, 40));
        tf_usernewPassword.setMinimumSize(new java.awt.Dimension(300, 40));

        bt_updatePassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_updatePassword.setForeground(new java.awt.Color(255, 255, 255));
        bt_updatePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_updatePassword.setText("Update User Password");
        bt_updatePassword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_updatePassword.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_updatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updatePasswordActionPerformed(evt);
            }
        });

        lb_loadusernameToPasswordChange.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb_loadusernameToPasswordChange.setForeground(new java.awt.Color(255, 255, 255));

        cb_userstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "In-Active" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_loadusernameToPasswordChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_updatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lb_addRoute7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tf_usernewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_userstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lb_loadusernameToPasswordChange, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_addRoute7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_usernewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_userstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_updatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void bt_AddAdminOrUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddAdminOrUserActionPerformed

        if (!(tf_FullName.getText().isEmpty() && tf_Username.getText().isEmpty() && tf_Password.getText().isEmpty() && tf_Conpassword.getText().isEmpty())) {
            int w = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Add Access", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (w == JOptionPane.YES_OPTION) {
                try {

                    String fullname = tf_FullName.getText().trim();
                    String username = tf_Username.getText().trim().toLowerCase();
                    String password = tf_Password.getText().trim().toLowerCase();
                    String conpass = tf_Conpassword.getText().trim().toLowerCase();
                    if (password.equals(conpass)) {

                        MC_DB.update_data("INSERT INTO user_account (us_fullname,us_username,us_password,us_type,us_status) VALUES ('" + fullname + "','" + username + "','" + conpass + "','user','01')");
                        // MC_JavaDataBaseConnection.myConnection().createStatement().executeUpdate("");
                        JOptionPane.showMessageDialog(this, "User Successfully Added");
                        md_tb_load_users();
                    }
                } catch (HeadlessException ex) {
                    Logger.getLogger(Admin_userManagment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Please fill the user fields","Empty Fields Found",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bt_AddAdminOrUserActionPerformed

    private void tf_UsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_UsernameMouseClicked

        int i = JOptionPane.showConfirmDialog(this, "Are You Sure Add User?", "Confirm?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
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

    private void bt_updatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updatePasswordActionPerformed

        if (lb_loadusernameToPasswordChange.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Firstly,Please select name of User \n in the table!");
        } else {

            if (!(tf_usernewPassword.getText().isEmpty())) {
                int userstaus = 01;
                if (cb_userstatus.getSelectedIndex() == 0) {
                    userstaus = 01;
                } else {
                    userstaus = 00;
                }
                String qu_up_adminPassword = "UPDATE user_account SET us_password = '" + tf_usernewPassword.getText().trim() + "',us_status = '" + userstaus + "' WHERE `us_username` = '" + lb_loadusernameToPasswordChange.getText() + "' ;";

                MC_DB.update_data(qu_up_adminPassword);
                JOptionPane.showMessageDialog(this, dtb + " :Password is successfully updated!");
                md_tb_load_users();
                tf_usernewPassword.setText("");
                lb_loadusernameToPasswordChange.setText("");
            } else {

                JOptionPane.showMessageDialog(this, "Password Can't be Empty!");

            }
        }

    }//GEN-LAST:event_bt_updatePasswordActionPerformed
    String dtb;
    private void tf_FullNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_FullNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_FullNameMouseClicked

    private void tb_viewUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_viewUsersMouseClicked

        try {
            dtb = (String) dtm.getValueAt(tb_viewUsers.getSelectedRow(), 2);
            lb_loadusernameToPasswordChange.setText(dtb);

        } catch (HeadlessException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tb_viewUsersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_AddAdminOrUser;
    private javax.swing.JButton bt_updatePassword;
    private javax.swing.JComboBox cb_userstatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_addRoute3;
    private javax.swing.JLabel lb_addRoute4;
    private javax.swing.JLabel lb_addRoute5;
    private javax.swing.JLabel lb_addRoute6;
    private javax.swing.JLabel lb_addRoute7;
    private javax.swing.JLabel lb_loadusernameToPasswordChange;
    private javax.swing.JTable tb_viewUsers;
    private javax.swing.JTextField tf_Conpassword;
    private javax.swing.JTextField tf_FullName;
    private javax.swing.JTextField tf_Password;
    private javax.swing.JTextField tf_Username;
    private javax.swing.JTextField tf_usernewPassword;
    // End of variables declaration//GEN-END:variables

    private void md_tb_load_users() {
        try {
            ResultSet rs;
            rs = MC_DB.search_dataOne("user_account", "us_type", "user");

            dtm = (DefaultTableModel) tb_viewUsers.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getRow());
                v.add(rs.getString("us_fullname"));
                v.add(rs.getString("us_username"));
                v.add(rs.getString("us_password"));
                int status = rs.getInt("us_status");

                if (status == 01) {
                    v.add("Active User");
                } else if (status == 00) {
                    v.add("In-Active User");
                }
                dtm.addRow(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

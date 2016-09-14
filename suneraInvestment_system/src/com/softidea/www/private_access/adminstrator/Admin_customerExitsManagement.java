package com.softidea.www.private_access.adminstrator;

import com.fsczone.www.lookAndFeel.pro_lookandfeel;
import static com.softidea.www.private_access.adminstrator.Admin_customerManagment.tf_nic;
import com.softidea.www.public_connection.MC_DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Admin_customerExitsManagement extends javax.swing.JPanel {

    boolean ifNewLoanAmountLessThanDueAmount = false;

    public Admin_customerExitsManagement() {

        initComponents();

        try {
            pro_lookandfeel.Set();
            new Thread(() -> {
                md_tb_loadCustomer("deactive");
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_customerAddView = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 102, 102));

        tb_customerAddView.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_customerAddView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Regidtation Date", "Loan NO", "Customer NIC", "Loan Amount", "Period", "Installment", "Due Payment", "Loan Type", "Full Name", "Address", "Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_customerAddView.getTableHeader().setReorderingAllowed(false);
        tb_customerAddView.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tb_customerAddViewMouseMoved(evt);
            }
        });
        tb_customerAddView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_customerAddViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_customerAddView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tb_customerAddViewMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_customerAddViewMouseMoved

        tb_customerAddView.setToolTipText("Click Record To ZOOM ++ Table");
    }//GEN-LAST:event_tb_customerAddViewMouseMoved

    private void tb_customerAddViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_customerAddViewMouseClicked


    }//GEN-LAST:event_tb_customerAddViewMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_customerAddView;
    // End of variables declaration//GEN-END:variables
//rs = MC_DB.myConnection().createStatement().executeQuery("SELECT e.funder_name, e.idfunder, u.fund, u.fund_date,u.idfund FROM funder AS e LEFT JOIN fund AS u ON e.idfunder = u.funder_idfunder;");

    private void md_tb_loadCustomer(String status) {
        DefaultTableModel dtm;
        try {

            ResultSet rs;
            dtm = (DefaultTableModel) tb_customerAddView.getModel();
            dtm.setRowCount(0);

            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM customer AS c LEFT JOIN loans AS l ON l.idcustomer=c.idcustomer WHERE loan_status='" + status + "' ORDER BY c.idcustomer DESC");
            while (rs.next()) {
                int cus_id = rs.getRow();
                String loan_date = rs.getString("l.loan_date");
                String loan_no = rs.getString("l.loan_no");
                String cus_nic = rs.getString("c.cus_nic");
                String loan_amount = rs.getString("l.loan_amount")+".00";
                String loan_period = rs.getString("l.loan_period");
                String loan_installment = rs.getString("l.loan_installment")+".00";
                String due_loan_amount = rs.getString("l.due_loan_amount")+".00";
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

                dtm.addRow(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

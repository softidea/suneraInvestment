package com.softidea.www.private_access.adminstrator;

import static com.softidea.www.private_access.adminstrator.Admin_cashManagment.dc_endDate;
import static com.softidea.www.private_access.adminstrator.Admin_cashManagment.dc_startDate;
import com.softidea.www.private_access.methods.md_Calc;
import com.softidea.www.public_access.pmd;
import com.softidea.www.public_connection.MC_DB;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Admin_installmentManagment extends javax.swing.JPanel {

    int loanID = 1;
    double due_Loan_Amount = 0;

    String CUS_NIC = "";
    String CUS_NAME = "";
    String CUS_ADDRESS = "";
    String CUS_CONTACT = "";

    String LOAN_NO = "";
    int LOAN_PERIOD = 0;
    double LOAN_AMOUNT = 0;
    double LOAN_INSTALLMENT = 0;
    String LOAN_REG_DATE = "";

    double PAID_AMOUNT = 0;
    double DUE_AMOUNT = 0;
    double DUE_INTEREST = 0;
    double PAID_INTEREST = 0;
    double PAYABLE_INTEREST = 0;
    double ARRIUS = 0;

    public Admin_installmentManagment() {
        initComponents();
        tf_payment.setTransferHandler(null);
        tf_discount.setTransferHandler(null);
        new Thread(() -> {
            try {

                setCurrentDate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void updateDueLoanAmount() {
        try {
            MC_DB.update_data("UPDATE loans SET due_loan_amount='" + due_Loan_Amount + "' WHERE idloans='" + loanID + "'");
            System.out.println("Due Loan Amount Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDueAmount() {
        double paidAmount = Double.parseDouble(tf_paidAmount.getText());
        double payableAmount = Double.parseDouble(tf_period.getText()) * Double.parseDouble(tf_installment.getText());
        double dueAmount = payableAmount - paidAmount;
        tf_dueAmount.setText(dueAmount + "0");
        due_Loan_Amount = dueAmount;
        this.DUE_AMOUNT = dueAmount;
    }

    public void loadCutomerLoanData() {
        try {
            String query = "SELECT * FROM customer c LEFT JOIN loans l ON c.`idcustomer`=l.`idcustomer`WHERE c.`cus_nic`='" + tf_nic.getText() + "' AND l.`loan_status`='active'";
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery(query);
            if (rs.next()) {

                this.CUS_NIC = tf_nic.getText().trim();

                tf_name.setText(rs.getString("c.cus_fullname"));
                this.CUS_NAME = rs.getString("c.cus_fullname");

                tf_address.setText(rs.getString("c.cus_address"));
                this.CUS_ADDRESS = rs.getString("c.cus_address");

                tf_contact.setText(rs.getString("c.cus_contact"));
                this.CUS_NIC = rs.getString("c.cus_contact");

                loanID = rs.getInt("l.idloans");
                LOAN_NO = rs.getString("l.loan_no");
//                        JOptionPane.showMessageDialog(this, "loan id" + loanID);
                tf_loanAmount.setText(rs.getDouble("l.loan_amount") + "0");
                this.LOAN_AMOUNT = rs.getDouble("l.loan_amount");

                tf_period.setText(rs.getString("loan_period"));
                this.LOAN_PERIOD = Integer.parseInt(rs.getString("loan_period"));

                tf_installment.setText(rs.getDouble("loan_installment") + "0");
                this.LOAN_INSTALLMENT = rs.getDouble("loan_installment");

                tf_regDate.setText(rs.getString("loan_date"));
                this.LOAN_REG_DATE = rs.getString("loan_date");

                lblloantype.setText(rs.getString("loan_mainperiodtype"));

                viewInstalments(loanID);
                setPaidAmount();
                setDueAmount();
                setInterestValues();

            } else {
                tf_payment.setEnabled(false);
                tf_discount.setEnabled(false);
                bt_payInstallment.setEnabled(false);
                bt_updateCustomer.setEnabled(false);
                JOptionPane.showMessageDialog(this, "No active Loan Found, For " + tf_nic.getText() + " all loans allready settled");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkNicAvailability(String cusNic) {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT DISTINCT cus_nic FROM customer WHERE cus_nic='" + cusNic + "'");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void calArrius() {
        double arriers = 0;
        double paid = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) FROM installment WHERE idloans='" + loanID + "'");
            if (rs.next()) {
                paid = rs.getDouble(1);
            }
            String loanDate = tf_regDate.getText();
            int period = Integer.parseInt(tf_period.getText());
            double installement = Double.parseDouble(tf_installment.getText());

            String loantype = "";
            rs = MC_DB.myConnection().createStatement().executeQuery("SELECT loan_mainperiodtype FROM loans WHERE idloans='" + loanID + "'");
            if (rs.next()) {
                loantype = rs.getString(1);
            }

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            if (loantype.toLowerCase().contains("daily") || (loantype.toLowerCase().contains("weekly"))) {

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
                        if (loantype.equalsIgnoreCase("Daily-withOutSaturday")) {
                            period++;
                            System.out.println(period);
                        }
                    }
                    no_of_days++;

                }

                if (loantype.equalsIgnoreCase("Daily-withOutSaturday")) {
                    arriers = (no_of_days - no_of_saturdays) * installement;
                } else if (loantype.equalsIgnoreCase("Daily-withSaturday")) {
                    arriers = no_of_days * installement;

                } else {

                    arriers = (no_of_days / 7) * installement;
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

                arriers = no_of_months * installement;

            }
            arriers -= paid;
            tf_arrius.setText(arriers + "0");
            this.ARRIUS = arriers;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setInterestValues() {

        double paid_discount = calPaidDiscount();
        double loanAmount = Double.parseDouble(tf_loanAmount.getText());
        double period = Double.parseDouble(tf_period.getText());
        double installment = Double.parseDouble(tf_installment.getText());
        double payable_amount = period * installment;
        double payable_interest = (payable_amount - loanAmount);
        double paidAmount = Double.parseDouble(tf_paidAmount.getText());

        double paid_interest = ((payable_amount - loanAmount) / payable_amount) * paidAmount;
        paid_interest = (Math.round(paid_interest));

        double due_interest = (payable_interest - (paid_interest) - paid_discount);
        due_interest = Math.round(due_interest);

        tf_paidInterest.setText(paid_interest + "0");
        this.PAID_INTEREST = paid_interest;

        tf_payableInterest.setText(payable_interest + "0");
        this.PAYABLE_INTEREST = payable_interest;

        tf_dueInterest.setText(due_interest + "0");
        this.DUE_INTEREST = due_interest;
    }

    public void setPaidAmount() {
        double amount = 0;
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(payment) AS sumPayment FROM installment WHERE idloans='" + loanID + "'");
            while (rs.next()) {

                amount = rs.getInt("sumPayment");
            }
            tf_paidAmount.setText(amount + "0");
            this.PAID_AMOUNT = amount;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentDate() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dc_installment.setDate(date);
    }

    //get installment count
    public int getInstallmentCount(int loanID) {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT COUNT(payment) AS installmentCount FROM installment WHERE idloans='" + loanID + "'");
            if (rs.next()) {
                return rs.getInt("installmentCount");
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //get installment count

    //get Loan Amount
    public double getLoanAmount() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT loan_amount FROM loans WHERE idloans='" + loanID + "'");
            if (rs.next()) {

                return Double.parseDouble(rs.getDouble("loan_amount") + "0");
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //get Loan Amount

    //get Installment Count total
    public double getInstalmentLoanAmount() {
        double amount = 0;
        try {
            ResultSet rs = MC_DB.search_dataOne("installment", "payment", loanID + "");
            while (rs.next()) {

                amount += Double.parseDouble(rs.getDouble("payment") + "0");
            }
            return amount;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    //get Installment Count total
    DefaultTableModel dtm;

    //view installment to the table

    public void viewInstalments(int LoanID) {
        dtm = (DefaultTableModel) tb_loanInstallment.getModel();
        dtm.setRowCount(0);
        new Thread(() -> {
            try {
                try {

                    ResultSet rs = MC_DB.search_dataOne("installment", "idloans", loanID + "");
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(rs.getRow());
                        v.add(rs.getString("payment_date"));
                        v.add(rs.getDouble("payment") + "0");
                        v.add(rs.getDouble("discount") + "0");
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

    //view installment to the table
    //cal paid discount
    public int calPaidDiscount() {
        try {
            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT SUM(discount) AS disCount FROM installment WHERE idloans='" + loanID + "'");
            if (rs.next()) {

                return rs.getInt("disCount");

            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //call paid discount
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tf_address = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_nic = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        tf_contact = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_loanAmount = new javax.swing.JLabel();
        tf_period = new javax.swing.JLabel();
        tf_installment = new javax.swing.JLabel();
        tf_regDate = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblloantype = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lb_v_finalDate = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf_paidAmount = new javax.swing.JLabel();
        tf_dueAmount = new javax.swing.JLabel();
        tf_paidInterest = new javax.swing.JLabel();
        tf_payableInterest = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tf_dueInterest = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tf_arrius = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tf_payment = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bt_updateCustomer = new javax.swing.JButton();
        bt_payInstallment = new javax.swing.JButton();
        dc_installment = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        tf_discount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_loanInstallment = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 102, 102));
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(66, 66, 66));

        tf_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_address.setEnabled(false);
        tf_address.setFocusable(false);
        tf_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_addressKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NIC Number :");

        tf_nic.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_nic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nicKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_nicKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Customer Name :");

        tf_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_name.setEnabled(false);
        tf_name.setFocusable(false);
        tf_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nameKeyReleased(evt);
            }
        });

        tf_contact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_contact.setEnabled(false);
        tf_contact.setFocusable(false);
        tf_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_contactKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Contact Number :");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/home/images/Google Web Search_40.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tf_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tf_contact)
                            .addComponent(tf_address)
                            .addComponent(tf_name))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Period :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Loan Amount :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Installment :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Registration Date :");

        tf_loanAmount.setBackground(new java.awt.Color(255, 255, 0));
        tf_loanAmount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_loanAmount.setForeground(new java.awt.Color(0, 204, 255));
        tf_loanAmount.setText("00.00");

        tf_period.setBackground(new java.awt.Color(255, 255, 0));
        tf_period.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_period.setForeground(new java.awt.Color(0, 204, 255));
        tf_period.setText("00");

        tf_installment.setBackground(new java.awt.Color(255, 255, 0));
        tf_installment.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_installment.setForeground(new java.awt.Color(0, 204, 255));
        tf_installment.setText("00.00");

        tf_regDate.setBackground(new java.awt.Color(255, 255, 0));
        tf_regDate.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_regDate.setForeground(new java.awt.Color(0, 204, 255));
        tf_regDate.setText("0000-00-00");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Loan Type :");

        lblloantype.setBackground(new java.awt.Color(255, 255, 0));
        lblloantype.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblloantype.setForeground(new java.awt.Color(0, 204, 255));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Closing Date :");

        lb_v_finalDate.setBackground(new java.awt.Color(255, 255, 0));
        lb_v_finalDate.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lb_v_finalDate.setForeground(new java.awt.Color(0, 204, 255));
        lb_v_finalDate.setText("0000-00-00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(tf_installment, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_period, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_regDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(lb_v_finalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tf_loanAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblloantype, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_loanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_period, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblloantype, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_installment, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_regDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_v_finalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Due Amount :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Paid Amount :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Paid Interest :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Payable Interest :");

        tf_paidAmount.setBackground(new java.awt.Color(255, 255, 0));
        tf_paidAmount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_paidAmount.setForeground(new java.awt.Color(0, 204, 255));
        tf_paidAmount.setText("00.00");

        tf_dueAmount.setBackground(new java.awt.Color(255, 255, 0));
        tf_dueAmount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_dueAmount.setForeground(new java.awt.Color(0, 204, 255));
        tf_dueAmount.setText("00.00");

        tf_paidInterest.setBackground(new java.awt.Color(255, 255, 0));
        tf_paidInterest.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_paidInterest.setForeground(new java.awt.Color(0, 204, 255));
        tf_paidInterest.setText("00.00");

        tf_payableInterest.setBackground(new java.awt.Color(255, 255, 0));
        tf_payableInterest.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_payableInterest.setForeground(new java.awt.Color(0, 204, 255));
        tf_payableInterest.setText("00.00");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Due Interest :");

        tf_dueInterest.setBackground(new java.awt.Color(255, 255, 0));
        tf_dueInterest.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_dueInterest.setForeground(new java.awt.Color(0, 204, 255));
        tf_dueInterest.setText("00.00");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Arrears :");

        tf_arrius.setBackground(new java.awt.Color(255, 255, 0));
        tf_arrius.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tf_arrius.setForeground(new java.awt.Color(0, 204, 255));
        tf_arrius.setText("00.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_paidAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tf_dueAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(0, 39, Short.MAX_VALUE))
                                    .addComponent(tf_payableInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(tf_dueInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tf_paidInterest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(tf_arrius, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_paidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel12)
                .addGap(11, 11, 11)
                .addComponent(tf_dueAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_paidInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_payableInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_arrius, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_dueInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(66, 66, 66));

        tf_payment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_paymentKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_paymentKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Payment :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Date :");

        bt_updateCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_updateCustomer.setForeground(new java.awt.Color(255, 255, 255));
        bt_updateCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_updateCustomer.setText("Print Report");
        bt_updateCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_updateCustomer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_updateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_updateCustomerActionPerformed(evt);
            }
        });

        bt_payInstallment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_payInstallment.setForeground(new java.awt.Color(255, 255, 255));
        bt_payInstallment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonNormal.png"))); // NOI18N
        bt_payInstallment.setText("Pay Installment");
        bt_payInstallment.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_payInstallment.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/com/softidea/www/public_access/images/SuneraInvestment_selctButtonHover.png"))); // NOI18N
        bt_payInstallment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_payInstallmentActionPerformed(evt);
            }
        });

        dc_installment.setDateFormatString("yyyy-MM-dd");
        dc_installment.setEnabled(false);
        dc_installment.setFocusable(false);
        dc_installment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Discount :");

        tf_discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_discount.setText("00.00");
        tf_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_discountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_discountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tf_discount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(234, 234, 234)))
                    .addComponent(bt_updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_payInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dc_installment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_payment, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(234, 234, 234))))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dc_installment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_payment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_payInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_updateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        tb_loanInstallment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_loanInstallment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Payament Date", "Payment", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_loanInstallment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_addressKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_addressKeyReleased

    private void tf_paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_paymentKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_paymentKeyReleased

    private void tf_paymentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_paymentKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_paymentKeyTyped

    private void bt_updateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_updateCustomerActionPerformed

        //jc_start.getDate()
//        String startDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_startDate.getDate());
//        String endDate = new SimpleDateFormat("YYYY-MM-dd").format(dc_endDate.getDate());
//        String todayDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        String path = "src//Reports//loan//report_installement_onecustomer.jrxml";
        //E:\Project_SE\suneraInvestment\suneraInvestment_system\src\Reports\customer\report_customerandloandetalsHistory.jrxml

        String CUS_NIC = tf_nic.getText();
        String CUS_NAME = tf_name.getText();
        String CUS_ADDRESS = tf_address.getText();
        String CUS_CONTACT = tf_contact.getText();
        String LOAN_NO = this.LOAN_NO;
        String LOAN_AMOUNT = tf_loanAmount.getText();
        String LOAN_PERIOD = tf_period.getText();
        String LOAN_INSTALLMENT = tf_installment.getText();
        String LOAN_REG_DATE = tf_regDate.getText();
        String PAID_AMOUNT = tf_paidAmount.getText();
        String DUE_AMOUNT = tf_dueAmount.getText();
        String ARRIES = tf_arrius.getText();

        System.out.println(CUS_NIC);
        System.out.println(CUS_NAME);
        System.out.println(CUS_ADDRESS);
        System.out.println(CUS_CONTACT);
        System.out.println(LOAN_NO);
        System.out.println(LOAN_AMOUNT);
        System.out.println(LOAN_INSTALLMENT);
        System.out.println(LOAN_PERIOD);
        System.out.println(LOAN_REG_DATE);
        System.out.println(DUE_AMOUNT);
        System.out.println(PAID_AMOUNT);
        System.out.println(ARRIES);

        md_InstallmentSingleView(path, CUS_NIC, CUS_NAME, CUS_CONTACT, CUS_ADDRESS, LOAN_NO, LOAN_AMOUNT, LOAN_PERIOD, LOAN_INSTALLMENT, LOAN_REG_DATE, PAID_AMOUNT, DUE_AMOUNT, ARRIES);

    }//GEN-LAST:event_bt_updateCustomerActionPerformed

    private void bt_payInstallmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_payInstallmentActionPerformed

        dtm.setRowCount(0);
        new Thread(this::md_updateCustomer).start();
    }//GEN-LAST:event_bt_payInstallmentActionPerformed

    private void tf_nicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nicKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!tf_nic.getText().isEmpty()) {
                if (checkNicAvailability(tf_nic.getText())) {
                    loadCutomerLoanData();
                    if (this.DUE_AMOUNT == 0) {
                        tf_arrius.setText("00.00");
                    } else {
                        calArrius();
                        calDate();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Nic Number Found,Please check the nic", "Invalid Data Found", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }//GEN-LAST:event_tf_nicKeyReleased

    private void tf_nicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nicKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_V) && tf_nic.getText().length() == 10) {
            evt.consume();
        }

    }//GEN-LAST:event_tf_nicKeyTyped

    private void tf_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nameKeyReleased


    }//GEN-LAST:event_tf_nameKeyReleased

    private void tf_contactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_contactKeyReleased


    }//GEN-LAST:event_tf_contactKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (tf_nic.getText() != "" || tf_nic.getText() != null) {
            if (!tf_nic.getText().isEmpty()) {
                if (checkNicAvailability(tf_nic.getText())) {
                    loadCutomerLoanData();
                    calArrius();
                    calDate();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Nic Number Found,Please check the nic", "Invalid Data Found", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tf_discountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_discountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_discountKeyReleased

    private void tf_discountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_discountKeyTyped

        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9' || c == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }


    }//GEN-LAST:event_tf_discountKeyTyped

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked


    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_payInstallment;
    private javax.swing.JButton bt_updateCustomer;
    private com.toedter.calendar.JDateChooser dc_installment;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_v_finalDate;
    private javax.swing.JLabel lblloantype;
    private javax.swing.JTable tb_loanInstallment;
    public static javax.swing.JTextField tf_address;
    private javax.swing.JLabel tf_arrius;
    public static javax.swing.JTextField tf_contact;
    public static javax.swing.JTextField tf_discount;
    private javax.swing.JLabel tf_dueAmount;
    private javax.swing.JLabel tf_dueInterest;
    private javax.swing.JLabel tf_installment;
    private javax.swing.JLabel tf_loanAmount;
    public static javax.swing.JTextField tf_name;
    public static javax.swing.JTextField tf_nic;
    private javax.swing.JLabel tf_paidAmount;
    private javax.swing.JLabel tf_paidInterest;
    private javax.swing.JLabel tf_payableInterest;
    public static javax.swing.JTextField tf_payment;
    private javax.swing.JLabel tf_period;
    private javax.swing.JLabel tf_regDate;
    // End of variables declaration//GEN-END:variables

    private void md_InstallmentSingleView(String rp_parth, String CUS_NIC, String CUS_NAME, String CUS_CONTACT, String CUS_ADDRESS, String LOAN_NO, String LOAN_AMOUNT, String LOAN_PERIOD, String LOAN_INSTALLMENT, String LOAN_REG_DATE, String PAID_AMOUNT, String DUE_AMOUNT, String ARRIES) {

        try {
            JasperReport jp = JasperCompileManager.compileReport(rp_parth);
            System.out.println("===========jasper report compiled successfully==========");
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("cus_nicLocal", CUS_NIC);
            map.put("cus_name", CUS_NAME);
            map.put("cus_address", CUS_ADDRESS);
            map.put("cus_contact", CUS_CONTACT);
            map.put("loanno", LOAN_NO);
            map.put("loanamount", LOAN_AMOUNT);
            map.put("installment", LOAN_INSTALLMENT);
            map.put("reg_date", LOAN_REG_DATE);
            map.put("period", LOAN_PERIOD);
            map.put("paidamount", PAID_AMOUNT);
            map.put("dueamount", DUE_AMOUNT);
            map.put("arries", ARRIES);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jp, map, MC_DB.myConnection());
            JasperViewer.viewReport(jasperPrint, false);
            JRViewer v = new JRViewer(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            //Logger.getLogger(Inventory_reports.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void md_updateCustomer() {

        if (loanID != 0) {
            if (pmd.EmtyisTextFiled(tf_payment) && md_Calc.stringTodouble(tf_payment.getText()) > 0.0) {
                if (Double.parseDouble(tf_payment.getText()) <= Double.parseDouble(tf_dueAmount.getText())) {
                    if (Double.parseDouble(tf_discount.getText()) <= Double.parseDouble(tf_dueInterest.getText())) {
                        try {
                            int PERIOD = 0;
                            int INSTALLMENT_COUNT = 0;
                            double LOAN_AMOUNT = 0;
                            double PAID_AMOUNT = 0;
                            double PAID_DISCOUNT = 0;
                            double PAYABLE_DISCOUNT = 0;
                            String installment_date = new SimpleDateFormat("YYYY-MM-dd").format(dc_installment.getDate());

                            ResultSet rs = MC_DB.myConnection().createStatement().executeQuery("SELECT * FROM loans WHERE idloans='" + loanID + "' AND loan_status='Active'");
                            if (rs.next()) {
                                System.out.println("inner rs");
                                PERIOD = Integer.parseInt(rs.getString("loan_period"));
                                System.out.println(PERIOD);
                                INSTALLMENT_COUNT = getInstallmentCount(loanID);
                                System.out.println(INSTALLMENT_COUNT);
                                LOAN_AMOUNT = getLoanAmount();
                                System.out.println(LOAN_AMOUNT);
                                PAID_AMOUNT = getInstalmentLoanAmount();
                                System.out.println(PAID_AMOUNT);

                                if (PERIOD >= INSTALLMENT_COUNT) {

                                    if (INSTALLMENT_COUNT >= 0) {

                                        if (LOAN_AMOUNT > PAID_AMOUNT) {

                                            int response = JOptionPane.showConfirmDialog(null, "Do you want to add this Payment?", "Confirm",
                                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                            if (response == JOptionPane.NO_OPTION) {
                                                System.out.println("No button clicked");
                                            } else if (response == JOptionPane.YES_OPTION) {

                                                String qy_saveInstallment = "INSERT INTO installment (payment,payment_date,discount,idloans) VALUES ('" + tf_payment.getText().trim() + "','" + installment_date + "','" + tf_discount.getText() + "','" + loanID + "')";
                                                MC_DB.update_data(qy_saveInstallment);
                                                JOptionPane.showMessageDialog(null, "Installment added successfully");

                                                viewInstalments(loanID);
                                                loadCutomerLoanData();
                                                updateDueLoanAmount();
                                                calArrius();

                                                String cash_des = tf_nic.getText() + " paid for load id=" + loanID;
                                                MC_DB.myConnection().createStatement().executeUpdate("INSERT INTO cash_account (date,amount,cash_ac_type,cash_ac_discription,cash_ac_status) VALUES('" + installment_date + "','" + tf_payment.getText().trim() + "','Installment','" + cash_des + "','Active')");
                                                tf_payment.setText("");
                                                System.out.println(this.DUE_AMOUNT + "this Due amount");
                                                if (this.DUE_AMOUNT == 0) {
                                                    System.out.println("loan amount 0 countttttt");
                                                    MC_DB.update_data("UPDATE loans SET loan_status='deactive' WHERE idloans='" + loanID + "'");
                                                    JOptionPane.showMessageDialog(this, "This " + this.LOAN_NO + " loan Successfully Settled");
                                                }
                                            }
                                        }
//                                            else if (LOAN_AMOUNT == PAID_AMOUNT) {
//                                            MC_DB.update_data("UPDATE loans SET loan_status='deactive' WHERE idloans='" + loanID + "'");
//                                            loadCutomerLoanData();
//                                            JOptionPane.showMessageDialog(null, "Loan already settled");
//                                        }

                                    }
                                }

                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Discount should be lower than or equal to Due Interest", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Payment should be lower than or equal to Due Amount", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {

                JOptionPane.showMessageDialog(this, "Please enter valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void calDate() {
        dateCounter();
    }

    private void dateCounter() {
        try {
            int period = Integer.parseInt(tf_period.getText());
            String loanDate = tf_regDate.getText();

            String loantype = lblloantype.getText();

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            if (loantype.toLowerCase().contains("daily") || (loantype.toLowerCase().contains("weekly"))) {

                String tempdate = loanDate;
                int no_of_days = 0;
                int no_of_saturdays = 0;

                Date d = new SimpleDateFormat("yyyy-MM-dd").parse(loanDate);
                String year = loanDate.split("-")[0];
                int day = Integer.parseInt(new SimpleDateFormat("yyyy-DDD").format(d).split("-")[1]);

                for (int i = 1; (i <= period); i++) {

                    Date currentdate = new SimpleDateFormat("yyyy-DDD").parse(year + "-" + (day + i));

                    tempdate = new SimpleDateFormat("yyyy-MM-dd").format(currentdate);

                    String nowday = new SimpleDateFormat("EEEEEEEE").format(currentdate);

                    if (nowday.equalsIgnoreCase("Saturday")) {

                        no_of_saturdays++;
                        if (loantype.equalsIgnoreCase("Daily-With Out Saturday(Week days)")) {
                            period++;

                        }
                    }
                    no_of_days++;

                }
                lb_v_finalDate.setText(tempdate);

            } else {

                int no_of_months = 0;
                String year = loanDate.split("-")[0];
                int l_month = Integer.parseInt(loanDate.split("-")[1]);
                String tempdate = "";
                for (int i = 1; (i <= period); i++) {
                    String tempd = l_month + i + "";
                    if ((l_month + i) < 10) {
                        tempd = "0" + (l_month + i);
                    }

                    Date curr = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + tempd + "-31");
                    tempdate = new SimpleDateFormat("yyyy-MM-dd").format(curr);
                    no_of_months++;

                }
                lb_v_finalDate.setText(tempdate);

//                arriers = no_of_months * installement;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

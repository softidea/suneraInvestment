package com.softidea.www.public_connection;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 @author : deepal_suranga
 */
public class MC_DB {

    public static Connection myCon;

    private MC_DB() {
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    private static String HOST = null;
    private static String DATABASE = null;
    private static String USER = null;
    private static String PASS = null;
    private static String PORT = null;

    //  Database credentials
    // public static void main(String[] args) {
    public static Connection myConnection() {

        //////////////////////////////////////////////////////////////////////
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("C:\\Softidea_Creation\\db_pro.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            //System.out.println(prop.getProperty("prot"));
            //System.out.println(prop.getProperty("dbname"));
            //System.out.println(prop.getProperty("username"));
            System.out.println(prop.getProperty("host"));

            HOST = prop.getProperty("host");
            PORT = prop.getProperty("prot");
            DATABASE = prop.getProperty("dbname");
            USER = prop.getProperty("username");
            PASS = prop.getProperty("password");

        } catch (IOException ex1) {
            JOptionPane.showMessageDialog(null, "Ex1: Please Check Database Proprty File!");
            //JOptionPane.showMessageDialog(null, "ex1: " + ex1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "e: " + e);
                }
            }
        }

        ////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

        //System.out.println("-------- MySQL JDBC Connection ------------");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex2) {
            System.out.println("Where is your MySQL JDBC Driver?");
            JOptionPane.showMessageDialog(null, "ex2: " + ex2);
            return null;
        }

        //System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException ex3) {
            System.out.println("Connection Failed! Check output console");
            JOptionPane.showMessageDialog(null, "Connection Failed!Please Check Your Internet Connection");
            JOptionPane.showMessageDialog(null, "ex3: " + ex3);
            return connection;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
            JOptionPane.showMessageDialog(null, "Failed to make connection!");
        }
        return connection;
    }

    public static String add_data_NoColumns(String table_name, String dataWithComa) {
        try {
            Statement statement = MC_DB.myConnection().createStatement();
            String querySql = "INSERT INTO " + table_name + " VALUES (" + dataWithComa + ")".trim();
            statement.executeUpdate(querySql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "MC_database:add_data_NoColumns::" + ex);
        }
        return null;
    }

    public static String add_data_WithColumns(String table_name, String ColumnsWithComa, String dataWithComa) {
        try {
            Statement statement = MC_DB.myConnection().createStatement();
            String querySql = "INSERT INTO " + table_name + "(" + ColumnsWithComa + ") VALUES (" + dataWithComa + ")".trim();
            //System.out.println("Query is: "+querySql);
            statement.executeUpdate(querySql);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "MC_database:add_data_WithColumns::" + ex);
        }
        return null;
    }

    public static void update_data(String SqlQuery) {

        try {
            Statement statement = MC_DB.myConnection().createStatement();
            statement.executeUpdate(SqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MC_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ResultSet search_dataAll(String use_table1, String columName2, String use_wherewithSingleComma3) {

        //"SELECT * FROM user_account WHERE us_username='" + username + "'"
        String SqlQuery = "SELECT * FROM " + use_table1 + " WHERE " + columName2 + "='" + use_wherewithSingleComma3 + "'";
        System.out.println("Quuuu :" + SqlQuery);
        ResultSet resultSet = null;
        Connection connection = MC_DB.myConnection();
        try {
            resultSet = connection.createStatement().executeQuery(SqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(MC_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;

    }

    public static ResultSet search_dataOne(String use_table1, String columName2, String use_where4) {
        Connection connection = MC_DB.myConnection();
        ResultSet resultSet = null;
        if (!(use_table1 == null && columName2 == null && use_where4 == null)) {
            if (!(use_table1.equals("") && "".equals(columName2) && "".equals(use_where4))) {
                String SqlQuery = "SELECT * FROM " + use_table1 + " WHERE " + columName2 + "='" + use_where4 + "'";

                try {
                    resultSet = connection.createStatement().executeQuery(SqlQuery);
                    // resultSet.getString(whatNeed);
                } catch (SQLException ex) {
                    Logger.getLogger(MC_DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resultSet;

    }

    public static ResultSet search_AlluseTable(String use_table) {
        Connection connection = MC_DB.myConnection();
        ResultSet resultSet = null;
        if (!(use_table == null)) {
            if (!(use_table.equals(""))) {
                String SqlQuery = "SELECT * FROM " + use_table + "'";

                try {
                    resultSet = connection.createStatement().executeQuery(SqlQuery);
                    // resultSet.getString(whatNeed);
                } catch (SQLException ex) {
                    Logger.getLogger(MC_DB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return resultSet;

    }

    ////////////////////////////////////////////////////
    /**
     *
     * @param tablename
     * @param columesWithcoma
     * @param valueWithcoma
     * @param ConnectionName
     * @param StatementName
     * @param PreparedStatementName
     * @param ResultSetName
     * @return LastId
     */
    public static long insertDataWithgetIDlast(String tablename, String columesWithcoma, String valueWithcoma, Connection ConnectionName, Statement StatementName, PreparedStatement PreparedStatementName, ResultSet ResultSetName) {
        long insertId = 0;
        try {

            String qry_add_customer = "INSERT INTO " + tablename + "(" + columesWithcoma + ") VALUES (" + valueWithcoma + ");";
            //String qry_add_customer = "CALL addCus('" + nic.trim().toUpperCase() + "','" + fullname.trim() + "','" + address.trim() + "','" + phoneNumber.trim() + "','" + gender_o + "');";
            ConnectionName = MC_DB.myConnection();
            StatementName = ConnectionName.createStatement();
            StatementName.executeUpdate(qry_add_customer);
            /////////////////////////////////////////////////////////////////

            PreparedStatementName = ConnectionName.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSetName = PreparedStatementName.executeQuery();
            if (ResultSetName.next()) {
                insertId = ResultSetName.getLong("last_insert_id()");

            }
        } catch (Exception e) {
        }
        return insertId;

    }

    /////////////////////////////////////////////////////////
//    public static void main(String[] args) {
//        MC_JavaDataBaseConnection.add_data_WithColumns("test","id,fname,lname,age", "5,'deeA','surA',10");
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Buwaneka Wimalasena
 */
public class DB {

    public static Connection con;

    public static Connection NC() throws Exception {
        if (con == null) {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Project_NC", "root", "123");
            return con;
        }
        return con;
    }
//////////////////////if wanna Save///////////////////////////
    public static void sud(String sql) throws Exception {
        DB.NC().createStatement().executeUpdate(sql);
    }
///////////////////////if wanna Search/////////////////////////
    public static ResultSet search(String search) throws Exception {
        ResultSet rs = DB.NC().createStatement().executeQuery(search);
        return rs;

    }

}

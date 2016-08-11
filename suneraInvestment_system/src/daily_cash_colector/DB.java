package daily_cash_colector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DB {
   private static Connection c;
   public static void CreateMyConnection() throws Exception {
       Class.forName("com.mysql.jdbc.Driver");
       c=DriverManager.getConnection("jdbc:mysql://localhost:3306/cash_collector","root","");
   }
   
   public static void idu(String sql) throws Exception{
   if(c==null){
   CreateMyConnection();
   }
   c.createStatement().executeUpdate(sql);
   }
   
   public static ResultSet search(String sql) throws Exception{
    
       if(c==null){
           CreateMyConnection();
       }
       return  c.createStatement().executeQuery(sql);
   }
   public static Connection getMyConnection () throws Exception{
            if(c == null){
            CreateMyConnection();
            
        }
            return c;
        }
   
   public static String loadDate() throws Exception {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
       String date=sdf.format(d);
        return date;
    }
}

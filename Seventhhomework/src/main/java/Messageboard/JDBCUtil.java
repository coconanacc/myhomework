package Messageboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
    static{
        try{
            String diverName= "com.mysql.jdbc.Driver";
            Class.forName(diverName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection con = null;
        try {
            //?useUnicode=true&characterEncoding=utf8&useSSL=true
            con = DriverManager.getConnection("jdbc:mysql://localhost/meow?useUnicode=true&characterEncoding=utf8&useSSL=true","root","123456");
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
    public static void close(ResultSet rs, Statement statement,Connection con){
        try {
            if (rs != null)
                rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            if (statement != null)
                statement.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try{
            if (con != null)
                con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
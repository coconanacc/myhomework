package Messageboard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.lang.Class.forName;

public class MessageBoardDaoImpl implements MessageBoardDao{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/message_board?characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    private static final String USER = "";
    private static final String PASS = "";

    private static MessageBoardDao instance = null;
    static {
        class.try {
            forName(JDBC_DRIVER)
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static MessageBoardDao getInstance() {
        if (instance == null) {
            synchronized (MessageBoardDao.class) {
                if (instance == null) {
                    instance = new MessageBoardDaoImpl();
                }
            }

        }
        return instance;


    }




    private Connection getConnection(){
      Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public void insertMessage(Message message){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into message_board(username,text,pid)value(?,?,?)";
        try {
            pstmt=con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt.setString(1, message.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt.setString(2, message.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt.setInt(3, message.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public List<Message> findMessagesByPid(int pid) {
        String sql = "SELECT * FROM message_board WHERE pid = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        java.util.List<Message> list = new ArrayList<Message>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pid);
            res = pstmt.executeQuery();
            while (res.next()) {
                Message message = new Message();
                message.setId(res.getInt("id"));
                message.setPid(res.getInt("pid"));
                message.setText(res.getString("text"));
                message.setUsername(res.getString("username"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(res, pstmt, con);
        }
        return list;
    }

    private void close(ResultSet res, PreparedStatement pstmt, Connection con) {
        try {
            res.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}

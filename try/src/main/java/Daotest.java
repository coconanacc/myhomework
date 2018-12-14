import java.io.IOException;
import java.sql.*;


public class Daotest {
    Connection con;
    Statement statement;
    ResultSet rs;

    public Connection getCon(){return con;}
    public Statement getStatement(){return statement;}
    public ResultSet getRs(){return rs;}

    public Daotest(Connection con){
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createtable() throws SQLException {
        String sql = "create table login(username varchar(100),password int)";
        statement.executeUpdate(sql);
    }

    public void insert(User user,Connection conn)throws SQLException {
        String sql = "insert into user(username,password) values(?,?)";
        System.out.println(sql);
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        System.out.println(pstmt.toString());

        pstmt.executeUpdate();

    }


    public static void insertStudentDao(User user){
        Connection con = JDBCUtil.getConnection();
        Daotest dao = new Daotest(con);
        try {

            dao.insert(user,con);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(dao.getRs(),dao.getStatement(),dao.getCon());
        }
    }

    public static void main(String[] args) {

    }

}




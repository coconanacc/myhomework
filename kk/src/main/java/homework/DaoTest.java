package homework;

import java.sql.*;

public class DaoTest {
    Connection con;
    Statement statement;
    ResultSet rs;

    public Connection getCon() {
        return con;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getRs() {
        return rs;
    }

    public DaoTest(Connection con) {
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        String sql = "create table redrock(id int,name varchar(100))";
        statement.executeUpdate(sql);
    }

    public void insert(Student student, Connection conn) throws SQLException {
        String sql = "insert into student(name,student_id) values(?,?)";
        System.out.println(sql);
//        String sql2 = "insert into redrock(name) values('张三')";
//        String sql3 = "insert into redrock(name) values('999')";

        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getStudent_id());
//        pstmt.setString(3, student.getCollege());
        System.out.println(pstmt.toString());

        pstmt.executeUpdate();

    }

    public void select() throws SQLException {
        String sql = "select id,name from redrock";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
        }
    }

    public static void insertStudentDao(Student student){
        Connection con = JDBCUtil.getConnection();
        DaoTest dao = new DaoTest(con);
        try {
//            dao.createTable();
            dao.insert(student,con);
//            dao.select();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(dao.getRs(),dao.getStatement(),dao.getCon());
        }
    }

    public static void main(String[] args) {

    }

}

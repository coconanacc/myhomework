import com.sun.corba.se.impl.orb.PrefixParserAction;

import java.sql.*;

public class DaoTest {
    Connection con;
    Statement statement;
    ResultSet rs;

    public Connection getCon() {return con;}

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getRs() {
        return rs;
    }

    public DaoTest(Connection con){
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insert(Student student,Connection conn) throws SQLException{
        String sql = "insert into select_class_list(num,student_id,name,sex,class_id,major_id,major,college,grade,status,select,type) value(?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);


        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, student.getNum());
        preparedStatement.setString(2, student.getStudent_id());
        preparedStatement.setString(3,student.getName());
        preparedStatement.setString(4,student.getSex());
        preparedStatement.setString(5,student.getClass_id());
        preparedStatement.setString(6,student.getMajor_id());
        preparedStatement.setString(7,student.getMajor());
        preparedStatement.setString(8,student.getCollege());
        preparedStatement.setString(9,student.getGrade());
        preparedStatement.setString(10,student.getStatus());
        preparedStatement.setString(11,student.getSelect());
        preparedStatement.setString(12,student.getType());
        System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();
    }


    public void select()throws SQLException {
        String sql = "select num,student_id,name,sex,class_id,major_id,major,college,grade,status,select,type form select_class_list";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String num = rs.getString("num");
            String student_id = rs.getString("student_id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String class_id = rs.getString("class_id");
            String major_id = rs.getString("major_id");
            String major = rs.getString("major");
            String college = rs.getString("college");
            String status = rs.getString("status");
            String select = rs.getString("select");
            String type = rs.getString("type");
        }
    }



    public static void insertStudentDao(Student student){
        Connection con = JDBCUtil.getConnection();
        DaoTest dao = new DaoTest(con);
        try {
            dao.insert(student,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(dao.getRs(),dao.getStatement(),dao.getCon());
        }


    }
    public static void main(String[] args){
        Connection connection = JDBCUtil.getConnection();
    }
}

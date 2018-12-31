import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {
    public static void main(String[] args){
        String name = "tang";
        String student_id = "2018211410";

        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into student(student_id,name) value(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student_id);
            preparedStatement.setString(2,name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

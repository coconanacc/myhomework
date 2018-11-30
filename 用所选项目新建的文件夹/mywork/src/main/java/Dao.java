import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
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

    public Dao(Connection con) {
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        String sql = "create table xx(id int,name varchar(100),age int,color varchar(100))";
        statement.executeUpdate(sql);
    }

    public void insert() throws SQLException {
        String sql1 = "insert into xx values(1,'可可',1,'奶牛')";
        String sql2 = "insert into xx values(2,'大花',2,'大橘')";
        String sql3 = "insert into xx values(3,'喵喵',2,'蓝猫')";
        String sql4 = "insert into xx values(4,'哼哼',3,'蓝猫')";
        String sql5 = "insert into xx values(6,'汪汪',2,'大橘')";
        String sql6 = "insert into xx values(7,'kk',1,'白猫')";
        String sql7 = "insert into xx values(8,'喵',2,'蓝猫')";
        String sql8 = "insert into xx values(9,'喵喵喵',2,'蓝猫')";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        statement.addBatch(sql4);
        statement.addBatch(sql5);
        statement.addBatch(sql6);
        statement.addBatch(sql7);
        statement.addBatch(sql8);

        int[] results = statement.executeBatch();
    }

    public void select() throws SQLException {
        String sql = "select id,name,age,color from meow";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String age = rs.getString("age");
            String color = rs.getString("color");

        }
    }

    public void update() throws SQLException{
        int id=3;
        String name = "喵喵";
        int age = 4;
        String color = "白";
        String sql = "update xx set name ='"+ name +"',age='"+ age +"',color'"+ color +"'where id='"+id;
        statement.addBatch(sql);
        rs = statement.executeQuery(sql);
    }

    public void delete() throws SQLException{
        int id=2;
        String sql = "delete from xx where id="+id;
        statement.addBatch(sql);
        rs = statement.executeQuery(sql);
    }

    public static void main(String[] args) {
        Connection con = JDBCUtil.getConnection();
        Dao dao = new Dao(con);
        try {
            dao.createTable();
            dao.insert();
            dao.select();
            dao.update();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(dao.getRs(), dao.getStatement(), dao.getCon());
        }
    }
}


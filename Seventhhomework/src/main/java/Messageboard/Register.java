package Messageboard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("application/json;charset=utf-8");
        if("".equals(username)||username==null||"".equals(password)||password==null)
            resp.getWriter().append("账户密码为空");
        else {
            Connection connection = JDBCUtil.getConnection();
            String sql = "insert into user (username,password) values (?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

                statement.execute();
                resp.getWriter().append("注册成功");
            } catch (SQLException e) {
                e.printStackTrace();
                resp.getWriter().append("注册失败");
            }


        }
    }
}

package Messageboard;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("application/json;charset=utf-8");
        if("".equals(username)||username==null||"".equals(password)||password==null)
            resp.getWriter().append("账号或密码为空");
        else{
            Connection connection = JDBCUtil.getConnection();
            String sql = "select username,password from user where username = ? &password = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,username);
                statement.setString(2,password);

                statement.execute();
                PrintWriter 登陆成功 = resp.getWriter().append("登陆成功");

            } catch (SQLException e) {
                e.printStackTrace();
                resp.getWriter().append("账号或密码错误\n请重新登陆");
            }
        }
    }

}

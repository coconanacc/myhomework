import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Level1 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String word = req.getParameter("password");
        resp.setContentType("application/json; charset=utf-8");
        String diverUsername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:aqlserver://localhost:1095;DatabaseName=login";
        String sql = "select Username from user where Username='"++"'"
        resp.getWriter().append("");
    }

}

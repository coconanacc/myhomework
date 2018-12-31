import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequest {
    public static String sendGet(String url, String prarm) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + prarm;
        URL realUrl = null;
        try {
            realUrl = new URL(urlNameString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (IOException e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

    }


    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            try {
                URLConnection connection = realUrl.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                out = new PrintWriter(connection.getOutputStream());
                out.print(param);
                out.flush();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;

                }
            } catch (IOException e) {

                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }



public static void main(String[] args){
    String s = HttpRequest.sendGet("http://jwzx.cqu.pt/kebiao/kb_stuList.php","jxb=A01181A1110010001");
    String s2 = StringUtils.replaceBlank(s);
    System.out.println(s2);
    String strPattern = "<tbody>(.*?)<tbody>";
    Pattern r2 = Pattern.compile(strPattern);
    String pattern3 = "<tr><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td><td>(.*?)</td></tr>";
    Pattern r3 = Pattern.compile(pattern3);

    Matcher m2 = r2.matcher(s);
    List<Student> studentList = new ArrayList<Student>();

    while (m2.find()){
        String stuStr = m2.group(0);

        Matcher m3 = r3.matcher(stuStr);
        if (m3.find()){
            Student student = new Student();
            student.setCollege(m3.group(8));
            student.setStudent_id(m3.group(2));
            student.setName(m3.group(3));
            student.setMajor(m3.group(7));
            student.setClass_id(m3.group(5));
            student.setNum(m3.group(1));
            student.setSex(m3.group(4));
            student.setMajor_id(m3.group(6));
            student.setGrade(m3.group(9));
            student.setStatus(m3.group(10));
            student.setSelect(m3.group(11));
            student.setType(m3.group(12));
            studentList.add(student);
        }
    }
    Gson gson = new Gson();

    System.out.println(gson.toJson(studentList));

    Connection connection = JDBCUtil.getConnection();
    DaoTest daoTest = new DaoTest(connection);
    for (int i=0; i<studentList.size(); i++){
        try {
            daoTest.insert(studentList.get(i), connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

}

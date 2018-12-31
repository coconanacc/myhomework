import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Demo {
    public static void main(String[] args) throws IOException, ParseException {
        String class_id = "04121803";
        String url = "http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey="+class_id;
        String response = HttpClientUtil.sendPost(url,null,"utf-8");

        Gson gson = new Gson();
        JsonObject jsonOb = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = jsonOb.getAsJsonArray("returnData");
        System.out.println(jsonArray.toString());


        for(int i = 1;i<jsonArray.size();i++)
        {
            Student tang = gson.fromJson(jsonArray.get(i), Student.class);
            System.out.println(tang.toString());
        }

    }
}

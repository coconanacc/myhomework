package homework;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.text.ParseException;

public class kk {
    public static void main(String[] args) throws IOException, ParseException{
        String class_id="04121803";
        String url="http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey="+class_id;
        String response=HttpClientUtil.sendPost(url,null,"utf-8");
        Gson gson=new Gson();
        JsonArray jsonArray = gson.fromJson(response, JsonObject.class).getAsJsonArray("returnData");
        System.out.println(jsonArray.toString());



        for(int i=0;i<jsonArray.size();i++){
            Student crown=new Student();
            JsonObject jsonObject =jsonArray.get(i).getAsJsonObject();
            crown.setName(jsonObject.get("name").getAsString());
            crown.setClass_id(jsonObject.get("class_id").getAsString());
            crown.setStudent_id(jsonObject.get("student_id").getAsString());
            crown.setCollege(jsonObject.get("English_name").getAsString());
            System.out.println(crown.toString());
            DaoTest.insertStudentDao(crown);
        }
    }
}

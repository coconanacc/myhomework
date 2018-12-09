package homework;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static CloseableHttpClient client;

    public static String sendPost(String url, Map<String,String> map, String encoding) throws ParseException, IOException{
        String body="";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httppost =new HttpPost(url);
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        if(map!=null){
            for(Map.Entry<String,String> entry :map.entrySet()){
                nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));

            }
        }
        httppost.setHeader("Content-type","application/json");
        httppost.setHeader("User-Agent","Mozilla/4.0(compatiable;MSIE 5.0; Windows NT;DigExt");
        CloseableHttpResponse response = client.execute(httppost);
        response.setHeader("Content-type","application/json");
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            body=(String) EntityUtils.toString((org.apache.http.HttpEntity) entity,encoding);
        }
        EntityUtils.consume((org.apache.http.HttpEntity)entity);
        response.close();
        return  body;
    }
        public static String sendGet(String url,String encoding) throws IOException{
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-type","text/html");
            CloseableHttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String body ="";
            if(entity!=null){
                body = EntityUtils.toString(entity,encoding);

            }
            EntityUtils.consume(entity);
            response.close();
            return body;
        }
        public static void main(String[] args) throws IOException{
        String class_id="04121803";
        String url = "http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey="+class_id;
        String webContent= sendGet(url,"utf-8");
            Gson gson = new Gson();

            JsonArray jsonArray=gson.fromJson(webContent, JsonObject.class).getAsJsonArray("returnData");
            System.out.println(jsonArray.toString());
        }
}

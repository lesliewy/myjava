package apache.httpclient.test1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by leslie on 2017/10/16.
 */
public class InterestRadar1 {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://apitest.tongdun.cn/bodyguard/apply/v3?partner_key=fea6a5eb98344bf4ac91c8bd9ad21bbf&partner_code=newnew&app_name=opertest_web");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("biz_code", "no"));
        nvps.add(new BasicNameValuePair("code_type", "mobile"));
        nvps.add(new BasicNameValuePair("scene_type", "notloan_again"));
        nvps.add(new BasicNameValuePair("user_code", "13100000015"));
        nvps.add(new BasicNameValuePair("encrypt_type", "no"));
        nvps.add(new BasicNameValuePair("_test", "true"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response2 = httpClient.execute(httpPost);
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            String entity2Str = entity2 != null ? EntityUtils.toString(entity2) : null;
            System.out.println("entity2Str: " + entity2Str);
            JSONObject json = JSON.parseObject(entity2Str);
            System.out.println("success: " + json.getString("success"));
            System.out.println("id: " + json.getString("id"));

            JSONObject resultDesc = ((JSONObject)json.get("result_desc"));
            System.out.println("result_desc: " + resultDesc);
            JSONObject interestRadar = ((JSONObject)resultDesc.get("INTERESTRADAR"));
            JSONArray indicatrix = (JSONArray)interestRadar.get("indicatrix");
            System.out.println("INTERESTRADAR: " + interestRadar);

            System.out.println("intention_score: " + interestRadar.getString("intention_score"));
            System.out.println("numOfIndicatrix: " + interestRadar.getInteger("numOfIndicatrix"));
            System.out.println("indicatrix: " + indicatrix);

//            Map<String, Object> resultMap = JSON.parseObject(entity2Str, HashMap.class);
//            System.out.println(ToStringBuilder.reflectionToString(resultMap));
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
    }
}

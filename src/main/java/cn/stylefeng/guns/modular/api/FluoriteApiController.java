package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.api.bean.GetToken;
import cn.stylefeng.guns.modular.api.bean.TokenResp;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.SecretKey;
import java.io.IOException;

/**
 * 萤石云后台接口
 */
@Controller
@RequestMapping(value = "Api")
public class FluoriteApiController {
    private static final String AppKey = "9e53b2561f314f00ba68b5d58d66e4c0";
    private static final String Secret = "c913977c35b6416cedb801a98b9a34c2";
    public static final MediaType JSON_TYPE = MediaType.get("application/json;charset=utf-8");
    public  OkHttpClient client = new OkHttpClient();
    @GetMapping("access_auth")
    @org.springframework.web.bind.annotation.ResponseBody
    public Object auth(){
        GetToken token = new GetToken();
        token.setAppKey(AppKey).setSecret(Secret);
        String tokenstr = JSON.toJSONString(token);

        try {
            String resp = post("https://open.ys7.com/api/lapp/token/get",tokenstr);
            TokenResp response = JSON.parseObject(resp,TokenResp.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    String post(String url,String json) throws IOException{
//        RequestBody body = RequestBody.create(JSON_TYPE,json);
        RequestBody body = new FormBody.Builder().add("AppKey",AppKey).add("Secret", Secret).build();
        Request request = new Request.Builder().url(url)
                .post(body).build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

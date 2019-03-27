package cn.stylefeng.guns.modular.api;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.modular.api.bean.TokenResp;
import cn.stylefeng.guns.modular.api.constant.Constant;
import cn.stylefeng.guns.modular.api.utils.HttpClientUtil;
import cn.stylefeng.guns.modular.system.model.Token;
import cn.stylefeng.guns.modular.system.service.ITokenService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 萤石云后台接口
 */
@Controller
@RequestMapping(value = "Api")
public class FluoriteApiController {

    @Autowired
    ITokenService tokenService;
    private static final String TOKENURL = "https://open.ys7.com/api/lapp/token/get";

    @GetMapping(value = "token")
    @ResponseBody
    public  String token(){
        Token token = tokenService.selectTokenById(1);
        if(token != null){
            if(token.getExpireTime().before(new DateTime()))
            {
                TokenResp tokenResp = getToken(Constant.appKey,Constant.appSecret);
                Token updateToken = new Token();
                updateToken.setId(1);
                updateToken.setAccessToken(tokenResp.getData().getAccessToken());
                updateToken.setExpireTime(new DateTime(tokenResp.getData().getExpireTime()));
                tokenService.update(updateToken,null);
                return tokenResp.getData().getAccessToken();
            }else{
                return token.getAccessToken();
            }

        }else{
            Token newToken = new Token();
            TokenResp tokenResp = getToken(Constant.appKey,Constant.appSecret);
            newToken.setExpireTime(new Date(tokenResp.getData().getExpireTime()));
            newToken.setAccessToken(tokenResp.getData().getAccessToken());
            tokenService.insert(newToken);
            return tokenResp.getData().getAccessToken();
        }
    }

    private TokenResp getToken(String AppKey,String AppSecret) {
        Map<String,String> header = new HashMap<>();
        header.put("content-type","application/x-www-form-urlencoded");
        Map<String,String> params = new HashMap<>();
        params.put("appKey",AppKey);
        params.put("appSecret",AppSecret);
        HttpEntity entity = HttpClientUtil.sendHttpsPost(TOKENURL,header,params,false);
        String str;
        if (entity != null){
            InputStream instreams = null;
            try {
                instreams = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
             str = streamToStr(instreams,"utf-8");
            TokenResp tokenResp = JSONObject.parseObject(str,TokenResp.class);
         return tokenResp;
        }
        return null;
    }

    private String streamToStr(InputStream inputStream,String chartSet){

        StringBuilder builder=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,chartSet));
            String con;
            while ((con=br.readLine())!=null){
                builder.append(con);
            }
            br.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

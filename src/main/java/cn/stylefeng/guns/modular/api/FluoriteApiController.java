package cn.stylefeng.guns.modular.api;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.modular.api.bean.BaseResp;
import cn.stylefeng.guns.modular.api.bean.TokenBean;
import cn.stylefeng.guns.modular.api.constant.Constant;
import cn.stylefeng.guns.modular.api.utils.HttpClientUtil;
import cn.stylefeng.guns.modular.system.service.ITokenService;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RestController
@RequestMapping(value = "Api")
public class FluoriteApiController {
    @Autowired
    ITokenService tokenService;

    private String accessToken;


    @PostMapping("add_device")
    public Object addDevice(String deviceSerial,String validateCode){

        accessToken = verifyToken();
        return addDevice(accessToken,deviceSerial,validateCode);
    }

    public  String verifyToken(){
        cn.stylefeng.guns.modular.system.model.Token token = tokenService.selectTokenById(1);
        if(token != null){
            if(token.getExpireTime().before(new DateTime()))
            {
                TokenBean newTokenBean = (TokenBean) getToken(Constant.appKey,Constant.appSecret);
                cn.stylefeng.guns.modular.system.model.Token updateToken = new cn.stylefeng.guns.modular.system.model.Token();
                updateToken.setId(1);
                updateToken.setAccessToken(newTokenBean.getData().getAccessToken());
                updateToken.setExpireTime(new DateTime(newTokenBean.getData().getExpireTime()));
                tokenService.update(updateToken,null);
                return newTokenBean.getData().getAccessToken();
            }else{
                return token.getAccessToken();
            }

        }else{
            cn.stylefeng.guns.modular.system.model.Token newToken = new cn.stylefeng.guns.modular.system.model.Token();
            TokenBean newtoken = (TokenBean) getToken(Constant.appKey,Constant.appSecret);
            newToken.setExpireTime(new Date(newtoken.getData().getExpireTime()));
            newToken.setAccessToken(newtoken.getData().getAccessToken());
            tokenService.insert(newToken);
            return newtoken.getData().getAccessToken();
        }
    }

    /**
     * 获取token
     * @param AppKey
     * @param AppSecret
     * @return
     */
    private Object getToken(String AppKey, String AppSecret) {
        Map<String,String> header = new HashMap<>();
        header.put("content-type","application/x-www-form-urlencoded");
        Map<String,String> params = new HashMap<>();
        params.put("appKey",AppKey);
        params.put("appSecret",AppSecret);
        TokenBean tokenBean = new TokenBean();
        return HttpResp(Constant.TOKENURL,header, params,tokenBean);
    }
    /**
     * 添加设备
     * @param deviceSerial
     * @param validateCode
     * @param
     */
    private Object addDevice(String accessToken,String deviceSerial,String validateCode){
        Map<String,String> header = new HashMap<>();
        header.put("content-type","application/x-www-form-urlencoded");
        Map<String,String> params = new HashMap<>();
        params.put("accessToken",accessToken);
        params.put("deviceSerial",deviceSerial);
        params.put("validateCode",validateCode);
        BaseResp baseResp = new BaseResp();
        return HttpResp(Constant.DEVICEADD,header, params,baseResp);
    }

    private Object getLiveAddress(String accessToken,String pageStart,String pageSize){
        Map<String,String> header = new HashMap<>();
        header.put("content-type","application/x-www-form-urlencoded");
        Map<String,String> params = new HashMap<>();
        params.put("accessToken",accessToken);
        if(!ObjectUtils.isEmpty(pageStart)) {
            params.put("pageStart", pageStart);
        }
        if(!ObjectUtils.isEmpty(pageSize)) {
            params.put("pageSize", pageSize);
        }
        BaseResp baseResp = new BaseResp();
        return HttpResp(Constant.VIDEOLIST,header, params,baseResp);
    }

    /**
     * 输入流转字符串
     * @param inputStream
     * @param chartSet
     * @return
     */
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

    /**
     * post请求返回数据
     * @param header
     * @param params
     * @return
     */
    private Object HttpResp(String url,Map<String, String> header, Map<String, String> params,Object object) {
        HttpEntity entity = HttpClientUtil.sendHttpsPost(url, header, params, false);
        String str;
        if (entity != null) {
            InputStream instreams = null;
            try {
                instreams = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            str = streamToStr(instreams, "utf-8");
            object = JSONObject.parseObject(str, Object.class);
            return object;
        }
        return null;
    }
}

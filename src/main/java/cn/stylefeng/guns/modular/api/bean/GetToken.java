package cn.stylefeng.guns.modular.api.bean;

public class GetToken {
    private String appKey;
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public GetToken setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public GetToken setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }
}

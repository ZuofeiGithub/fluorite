package cn.stylefeng.guns.modular.api.bean;

public class GetToken {
    private String AppKey;
    private String Secret;

    public String getAppKey() {
        return AppKey;
    }

    public GetToken setAppKey(String appKey) {
        AppKey = appKey;
        return this;
    }

    public String getSecret() {
        return Secret;
    }

    public GetToken setSecret(String secret) {
        Secret = secret;
        return this;
    }
}

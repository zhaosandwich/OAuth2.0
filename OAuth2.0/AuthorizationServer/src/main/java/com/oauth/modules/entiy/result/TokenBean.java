package com.oauth.modules.entiy.result;

/**
 * Created by Sandwich on 2016/7/8.
 */
public class TokenBean {
    private String access_token;
    private String open_id;

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

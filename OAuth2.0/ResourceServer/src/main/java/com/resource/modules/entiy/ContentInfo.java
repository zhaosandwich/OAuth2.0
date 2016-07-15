package com.resource.modules.entiy;

/**
 * Created by Sandwich on 2016/7/6.
 */
public class ContentInfo {
    private String content;
    private String openId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "{" +
                "操作->'" + content + '\'' +
                ", 用户ID->'" + openId + '\'' +
                '}';
    }
}

package com.oauth.modules.service;

import java.util.List;

/**
 * Created by Sandwich on 2016/7/7.
 */
public interface UserInfoService<T> {
    public List<T> queryUserInfo(String userName, String pwd);
}

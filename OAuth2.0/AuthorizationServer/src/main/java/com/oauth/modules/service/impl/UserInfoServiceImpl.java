package com.oauth.modules.service.impl;

import com.oauth.modules.dao.UserInfoDao;
import com.oauth.modules.entiy.UserInfoPO;
import com.oauth.modules.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/6.
 */
@Service("userInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    public List<UserInfoPO> queryUserInfo(String userName, String pwd) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName",userName);
        map.put("pwd",pwd);
        return userInfoDao.queryUserInfo(map);
    }
}

package com.resource.modules.service.impl;

import com.resource.modules.dao.UserInfoDao;
import com.resource.modules.entiy.UserInfo;
import com.resource.modules.service.UserInfoService;
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
    public List<UserInfo> getUserInfo(String open_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("open_id",open_id);
        return userInfoDao.getUserInfo(map);
    }
}

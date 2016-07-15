package com.oauth.modules.dao;

import com.oauth.common.MyBatisDao;
import com.oauth.modules.entiy.UserInfoPO;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/6.
 */
@MyBatisDao
public interface UserInfoDao<T> {
    public List<T> queryUserInfo(Map map);
}

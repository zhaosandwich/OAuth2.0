package com.resource.modules.dao;

import com.resource.common.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/6.
 */
@MyBatisDao
public interface UserInfoDao<T> {
    public List<T> getUserInfo(Map map);
}

package com.oauth.modules.dao;

import com.oauth.common.MyBatisDao;

import java.util.Map;

/**
 * Created by Sandwich on 2016/7/7.
 */
@MyBatisDao
public interface ContentInfoDao<T> {
    public int insertContents(Map map);
}

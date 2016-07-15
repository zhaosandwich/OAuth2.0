package com.resource.modules.dao;

import com.resource.common.MyBatisDao;
import com.resource.modules.entiy.ContentInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/7.
 */
@MyBatisDao
public interface ContentInfoDao<T> {
    public int insertContent(Map map);

    public List<ContentInfo> queryContent(Map map);
}

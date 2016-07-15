package com.resource.modules.service.impl;

import com.resource.modules.dao.ContentInfoDao;
import com.resource.modules.entiy.ContentInfo;
import com.resource.modules.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/7.
 */
@Service("contentServiceImpl")
public class ContentServiceImpl implements ContentService {
    @Autowired
    public ContentInfoDao contentInfoDao;

    @Override
    public boolean insertContent(String content, String openId, String createTime) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("content",content);
        map.put("openId",openId);
        map.put("createTime",createTime);
        if (contentInfoDao.insertContent(map) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<ContentInfo> queryContent(String open_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("openId",open_id);
        return contentInfoDao.queryContent(map);
    }
}

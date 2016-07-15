package com.oauth.modules.service.impl;

import com.oauth.modules.dao.ContentInfoDao;
import com.oauth.modules.dao.UserInfoDao;
import com.oauth.modules.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sandwich on 2016/7/7.
 */
@Service("contentServiceImpl")
public class ContentServiceImpl implements ContentService {
    @Autowired
    public ContentInfoDao contentInfoDao;

    @Override
    public boolean insertContents(String content, String openId, String createTime) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("content",content);
        map.put("openId",openId);
        map.put("createTime",createTime);
        if (contentInfoDao.insertContents(map) > 0){
            return true;
        }
        return false;
    }
}

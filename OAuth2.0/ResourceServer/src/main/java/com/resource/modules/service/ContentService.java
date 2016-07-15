package com.resource.modules.service;


import com.resource.modules.entiy.ContentInfo;

import java.util.List;

/**
 * Created by Sandwich on 2016/7/7.
 */
public interface ContentService {
    public boolean insertContent(String content, String openId, String createTime);

    public List<ContentInfo> queryContent(String open_id);
}

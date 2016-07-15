package com.resource.modules.controller;

import com.resource.common.JedisUtil;
import com.resource.common.Result;
import com.resource.common.StatusCode;
import com.resource.modules.entiy.ContentInfo;
import com.resource.modules.entiy.UserInfo;
import com.resource.modules.service.impl.ContentServiceImpl;
import com.resource.modules.service.impl.UserInfoServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandwich on 2016/7/6.
 */
@Controller
@RequestMapping(value = "/ResourceController")
public class ResourceController {

    private static final Log logger = LogFactory.getLog(ResourceController.class);

    @Autowired
    public UserInfoServiceImpl userInfoServiceImpl;
    @Autowired
    public ContentServiceImpl contentServiceImpl;

    /**
     * 获取资源
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/getResource.do", method = RequestMethod.GET)
    @ResponseBody
    public Result userInfo(HttpServletRequest req, HttpServletResponse resp){
        String access_token = req.getParameter("access_token").toString().trim();
        String open_id = req.getParameter("open_id").toString().trim();
        String oauhts = req.getParameter("oauhts").toString().trim();
        Result result = new Result();
        //1.判断access_token有效性
        String oauthStr = "";
        try {
            oauthStr = JedisUtil.getValue(access_token);
            if (oauthStr == null){
                result.setMsg(StatusCode.MSG_LOST_TOKEN);
                result.setStatusCode(StatusCode.LOST_PARAM);
                result.setData("");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2.申请资源与redis权限匹配
        String oauthList[] = oauthStr.split(";");
        boolean hasPower = false;   //是否有权限
        for (String str : oauthList){
            if (oauhts.equals(str)){
                hasPower = true;
            }
        }
        if (!hasPower){     //没有权限则直接返回
            result.setMsg(StatusCode.RESULT_NO_POWER);
            result.setStatusCode(StatusCode.NO_POWER);
            result.setData("");
            return result;
        }

        //3.获取资源并返回
        if (oauhts.equals("userInfo")){
            List<UserInfo> list = new ArrayList<UserInfo>();
            list = userInfoServiceImpl.getUserInfo(open_id);
            result.setMsg(StatusCode.RESULT_SUCCESS);
            result.setStatusCode(StatusCode.SUCCESS);
            result.setData(list.toString());
            return result;
        }
        if (oauhts.equals("oauthLog")){
            List<ContentInfo> list = new ArrayList<ContentInfo>();
            list = contentServiceImpl.queryContent(open_id);
            result.setMsg(StatusCode.RESULT_SUCCESS);
            result.setStatusCode(StatusCode.SUCCESS);
            result.setData(list.toString());
            return result;
        }
        return result;
    }

}

package com.oauth.modules.controller;

import com.oauth.common.JedisUtil;
import com.oauth.common.Result;
import com.oauth.common.StatusCode;
import com.oauth.modules.entiy.UserInfoPO;
import com.oauth.modules.entiy.result.CodeBean;
import com.oauth.modules.entiy.result.TokenBean;
import com.oauth.modules.service.impl.ContentServiceImpl;
import com.oauth.modules.service.impl.UserInfoServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sandwich on 2016/7/6.
 */
@Controller
@RequestMapping(value = "/OAuthController")
public class OAuthController{

    private static final Log logger = LogFactory.getLog(OAuthController.class);

    @Autowired
    public UserInfoServiceImpl userInfoServiceImpl;
    @Autowired
    public ContentServiceImpl contentServiceImpl;

    /**
     * 获取用户信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/userInfo.do", method = RequestMethod.GET)
    @ResponseBody
    public Result userInfo(HttpServletRequest req, HttpServletResponse resp){
        String redirUrl = req.getParameter("redirect");
        String userName = req.getParameter("userName").toString().trim();
        String pwd = req.getParameter("pwd").toString().trim();
        String openId = "";
        Result result = new Result();
        //授权字符串eg:userInfo;oauthLog,userInfo指代用户信息，oauthLog指代授权日志,两者以分号分隔
        String oauthStr = req.getParameter("oauth").toString().trim();
        List<UserInfoPO> list = new ArrayList<UserInfoPO>();
        list = userInfoServiceImpl.queryUserInfo(userName, pwd);
        //1.用户不存在则返回0状态码
        if (list.size() == 0){
            result.setStatusCode("301");
            result.setData("");
            result.setMsg("用户信息不正确");
            return result;
        }

        //2.保存code至redis
        String code = UUID.randomUUID().toString().trim().replaceAll("-", "");
        StringBuffer str = new StringBuffer();
        openId = list.get(0).getOpenId();
        str.append("openId:").append(openId).append("$").append("oauthStr:").append(oauthStr);
        try {
            JedisUtil.setValue(code, str.toString());//5分钟失效
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3.将授权日志记录进日志表
        Date dt=new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置显示格式
        String timeNow = df.format(dt);
        StringBuffer msg = new StringBuffer();
        msg.append(timeNow).append("--name is < "+userName+" > get Code,Code is "+code);
        contentServiceImpl.insertContents(msg.toString(), openId, timeNow);

        //4.存在，则返回code && redirUrl
        List<CodeBean> listData = new ArrayList<CodeBean>();
        CodeBean codeBean = new CodeBean();
        result.setStatusCode("200");
        result.setMsg("用户存在");
        codeBean.setCode(code);
        codeBean.setRedirUrl(redirUrl);
        listData.add(codeBean);
        result.setData(listData);
        //5.将客户端旧的Cookie 中的access_token，open_id清除
        Cookie token_cookie = new Cookie("access_token","");
        token_cookie.setPath("/");
        Cookie openid_cookie = new Cookie("open_id","");
        openid_cookie.setPath("/");
        resp.addCookie(token_cookie);
        resp.addCookie(openid_cookie);
        return result;
    }

    /**
     * 获取access_token
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/backToken.do", method = RequestMethod.GET)
    @ResponseBody
    public Result backToken(HttpServletRequest req, HttpServletResponse resp){
        Result result =  new Result();
        String code = req.getParameter("code").trim();
        String oauhts = req.getParameter("oauhts").trim();
        if (code == null){
            result.setMsg(StatusCode.MSG_PARAM);
            result.setStatusCode(StatusCode.ERROR_PARAM);
            result.setData("");
            return result;
        }
        //1.判断code是否有效
        String codeMsg = "";
        try {
            codeMsg = JedisUtil.getValue(code);
            if (codeMsg == null){   //redis不存在，则失效
                result.setMsg(StatusCode.MSG_LOST);
                result.setStatusCode(StatusCode.LOST_PARAM);
                result.setData("");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2.判断请求权限是否存在确认
        String [] msg = codeMsg.split("\\$");   //openId:09ci09ei0$oauthStr:userInfo;oauthLog
        String openIdStr = "";
        String oauthStr = "";
        openIdStr = msg[0].split(":")[1];     //将字符串openId:09ci09ei0，分割
        oauthStr = msg[1];      //oauthStr:userInfo;oauthLog
        String oauthStrList = oauthStr.split(":")[1];   //权限列表，权限之间用"分号"隔开
        String [] oauth = oauthStrList.split(";");
        boolean hasPower = false;   //      请求资源是否赋予权限
        for (String str : oauth){
            if (str.equals(oauhts)){
                hasPower = true;
            }
        }
        if (!hasPower){     //如果请求资源尚未赋予权限，直接返回
            result.setMsg(StatusCode.RESULT_NO_POWER);
            result.setStatusCode(StatusCode.NO_POWER);
            result.setData("");
            return result;
        }

        //3.设置redis KEY：access_token value：权限列表 并返回access_token和open_id;
        String access_token = UUID.randomUUID().toString().trim().replaceAll("-", "");
        try {
            JedisUtil.delValue(code);//删除code，code只能被使用一次
            JedisUtil.setValue(access_token, oauthStrList); //K-V  access_token - oauthStrList
        } catch (Exception e) {
            e.printStackTrace();
        }
        TokenBean tokenBean = new TokenBean();
        List<TokenBean> listData = new ArrayList<TokenBean>();
        tokenBean.setAccess_token(access_token);
        tokenBean.setOpen_id(openIdStr);
        listData.add(tokenBean);
        result.setData(listData);
        result.setStatusCode(StatusCode.SUCCESS);
        result.setMsg(StatusCode.RESULT_SUCCESS);
        //4.设置cookie
        Cookie token_cookie = new Cookie("access_token",access_token);
        token_cookie.setPath("/");
        Cookie openid_cookie = new Cookie("open_id",openIdStr);
        openid_cookie.setPath("/");
        resp.addCookie(token_cookie);
        resp.addCookie(openid_cookie);
        //5.记录日志操作
        Date dt=new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置显示格式
        String timeNow = df.format(dt);
        StringBuffer contents = new StringBuffer();
        contents.append(timeNow).append("--Get accessToken,accessToken is " + access_token);
        contentServiceImpl.insertContents(contents.toString(), openIdStr, timeNow);
        return result;
    }


}

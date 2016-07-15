package com.resource.common;

/**
 * Created by Sandwich on 2016/7/8.
 */
public class StatusCode {
    public static String SUCCESS = "200";
    public static String error = "";
    //>>>>>>>>>>>>>>>>>>>>>>>状态码<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //入参类 30X
    public static String ERROR_PARAM = "301";   //入参不正确
    public static String LOST_PARAM = "302";    //参数失效
    //结果类 40X
    public static String ERROR_RESULT = "401";  //没有正确的结果值
    public static String NO_POWER = "402";  //访问资源没权限，重新登陆赋予权限
    //异常类 50X
    public static String ERROR_EXCEPTION = "501";

    //>>>>>>>>>>>>>>>>>>信息类<<<<<<<<<<<<<<<<<<<<<<<<
    public static String MSG_PARAM = "参数不正确";
    public static String MSG_LOST_CODE = "CODE参数失效,重新登陆授权";
    public static String MSG_LOST_TOKEN = "TOKEN参数失效,重新登陆授权";


    //>>>>>>>>>>>>>>>>>结果类<<<<<<<<<<<<<<<<<<<<<<<<
    public static String RESULT_SUCCESS = "成功";
    public static String RESULT_NO_POWER = "访问资源没权限，重新登陆确认赋予权限";


}

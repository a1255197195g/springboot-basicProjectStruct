package com.kb.treatment.util.consts;

public class Constant {

    /**
     *   ResponseStatus : 定义返回值编码状态
     * */
    public interface ResponseStatus {

        //成功
        int SUCCESS  = 0;
        //失败
        int FAILURE   = -1;
        //没有登录
        int NO_LOGIN_ERROR   = 401;
        //访问路径不存在
        int NO_PATH_ERROR     = 404;
        //请求方式不正确
        int REQUEST_METHOD_ERROR = 405;
        //有必须的参数未上传
        int PARAMS_ERROR = 400;
        //服务器内部错误
        int SERVICE_ERROR = 500;
    }

    /**
     *   ResponseMessage : 定义返回消息
     * */
    public interface  ResponseMessage {

        //成功
        String SUCCESS_MSG = "成功";
        //未登录
        String NO_LOGIN_MSG = "没有登录";
        //访问路径不存在
        String NO_PATH_MSG = "访问路径不存在";
        //请求方式不正确
        String REQUEST_METHOD_ERROR_MSG = "请求方式不正确";
        //缺少必须的请求参数
        String LACK_PARAMS_ERROR_MSG = "缺少必须的请求参数";
        //服务器内部错误
        String SERVICE_ERROR_MSG = "服务器内部错误";
    }



}

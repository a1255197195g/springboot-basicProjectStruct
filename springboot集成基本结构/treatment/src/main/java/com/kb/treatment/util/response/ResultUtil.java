package com.kb.treatment.util.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.kb.treatment.util.consts.Constant;
import org.apache.tomcat.util.bcel.Const;


public class ResultUtil {

    /*
    *  @方法名称： success
    *  @功能描述： 请求数据返回成功时的返回值处理函数
    *  @param code:  返回状态
    *  @param msg:  返回消息
    *  @param data:
    *
    * @return com.kb.treatment.util.response.Result
    * @author mengze
    * @date  2018/12/5 18:12
    */
    public static Result success( Object data ){

        Result result = new Result();
        result.setCode( Constant.ResponseStatus.SUCCESS );
        result.setMsg( Constant.ResponseMessage.SUCCESS_MSG );
        result.setData(data);

        return result;
    };


    public static  Result success(){

        return success(null);
    }


    /*
    *  @方法名称： failure
    *  @功能描述：  当请求数据失败时候对返回消息体进行封装
    *  @param code:  状态码
    *  @param msg:   状态消息
    *  @param data:   数据
    *
    * @return com.kb.treatment.util.response.Result
    * @author mengze
    * @date  2018/12/5 18:38
    *
    */
    public static  Result failure( int code, String msg, Object data ){

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    public static  Result failure( int code, String msg ){

       return   failure( code, msg, null );
    }


    public static  Result failure(  String msg ){

        return failure( Constant.ResponseStatus.FAILURE, msg );
    }


}

package com.kb.treatment.configuration.ExceptionConfiguration;


import com.kb.treatment.util.response.Result;
import com.kb.treatment.util.response.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


/**
 *  全局异常处理
 * **/
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {


    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception ex ) throws Exception  {

        ex.printStackTrace();
        //如果属于自定义异常
        if ( ex instanceof  KbException ){

            KbException kbException = (KbException) ex;
            return ResultUtil.failure( kbException.getCode(),kbException.getMessage() );

        }else {

           return  ExceptionDetermine( req, ex );
        }
    }


    /**
     *   对系统异常进行分类处理
     * */
    public Result ExceptionDetermine( HttpServletRequest req, Exception e ){

        if ( e instanceof NoHandlerFoundException ){

            return ResultUtil.failure(404, "接口 ["+ req.getRequestURI() +"]不存在");

        }else if( e instanceof HttpRequestMethodNotSupportedException ){

            return ResultUtil.failure(405, "接口 ["+ req.getRequestURI() +"] 请求方法不正确");

        }else {
            return ResultUtil.failure( 500, "接口 ["+ req.getRequestURI() +"]内部异常");
        }
    }
}

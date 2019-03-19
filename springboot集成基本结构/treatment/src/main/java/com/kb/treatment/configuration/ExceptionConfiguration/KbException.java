package com.kb.treatment.configuration.ExceptionConfiguration;

import com.kb.treatment.util.consts.Constant;


/**
 *  自定义异常类
 * */
public class KbException extends RuntimeException{

    int code; //异常编码


    public static KbException  create( int code, String msg ){

        return new KbException( code, msg );
    }

    public static  KbException create( String msg ){

        return new KbException( msg );
    }





    public KbException( int  code, String msg ){

        super(msg);
        this.code = code;
    }

    public KbException( String msg ){

        this(Constant.ResponseStatus.FAILURE, msg);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



}

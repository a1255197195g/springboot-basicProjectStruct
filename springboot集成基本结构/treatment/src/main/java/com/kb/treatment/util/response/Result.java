package com.kb.treatment.util.response;


import com.fasterxml.jackson.annotation.JsonInclude;

//如果属性未null,那么就不进行序列化
@JsonInclude( JsonInclude.Include.NON_NULL )
public class Result<T> {

    //返回数据编码
    private int code;
    //返回数据的消息提示
    private String msg;
    //返回数据
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "ResultUtil{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}

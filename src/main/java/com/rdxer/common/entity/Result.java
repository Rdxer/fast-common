package com.rdxer.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result<T> {

    public static  Result<?> ofFailure (Integer code, String message){
        return Result.of(false,code,message,null);
    }
    public static  Result<?> ofFailure ( String message){
        return Result.of(false,StatusCode.ERROR,message,null);
    }
    public static <T> Result<T> ofSucceed(T data) {
        return Result.ofSucceed("Succeed",data);
    }
    public static <T> Result<T> ofSucceed(){
        return Result.of(true,StatusCode.OK,"Succeed",null);
    }
    public static <T> Result<T> ofSucceed(String message){
        return Result.of(true,StatusCode.OK,message,null);
    }
    public static <T> Result<T> ofSucceed(String message,T data){
        return Result.of(true,StatusCode.OK,message,data);
    }
    public static  Result<?> of(Boolean flag, Integer code, String message){
        return Result.of(flag,code,message,null);
    }

    public static <T> Result<T> of(Boolean flag, Integer code, String message,T data){
        return new Result<T>(flag,code,message,data);
    }


    public Result(Boolean flag, Integer code, String message,T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }



    private Boolean flag;
    private Integer code;
//    @JsonProperty("msg")
    private String message;
    private T data;

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "OK";
    }
    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }



    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

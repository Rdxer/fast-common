package com.rdxer.common.entity;

public interface StatusCode {
    public static final int OK = 200;//成功

    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足 访问被拒绝  403
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int REPERROR = 20005;//重复操作

    public static final int ParameterErr  = 20008;           //参数异常

    public static final int TokenExpired = 30001;  //凭证错误 token失效  token错误
    public static final int TokenIllegal = 30002;  // 令牌非法
    public static final int OtherClientsLoggedIn = 30003;  // 已经在其他客户端登陆
}
package com.yiseven.zhoudaxiao.common.response;

/**
 * Created by 邓锦辉
 *
 * @author hdeng
 */
public enum ResponseCode {

    SUCCESS(100, "SUCCESS"),
    ERROR(500, "ERROR"),
    URL_ERROR(404, "路径错误"),

    //运行时异常
    NULL_EXCEPTION(20001, "空值问题"),
    ARITH_EXCEPTION(20002, "算数问题"),
    COMMON_ERROR(99999, "未知错误"),


    //自定义异常
    NEED_LOGIN(401, "请先登录"),
    HEADER_LACK(11, "缺少请求头部，请重新登录"),
    REJECT_ACCESS(12,"限流操作——请求被拒绝访问"),
    RESULT_NULL(600, "结果为空"),
    DATABASE_ERROR(601, "数据库出错"),
    PARAM_WRONG(602, "参数有误"),
    PERSON_WRONG(603, "账号异常"),
    NO_POWER(604, "没有权利"),
    REDIS_WRONG(605, "Redis操作失败"),
    PRODUCT_ERROR(606, "商品信息有误");


    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

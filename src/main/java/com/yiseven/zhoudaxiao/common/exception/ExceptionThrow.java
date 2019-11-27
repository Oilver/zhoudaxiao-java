package com.yiseven.zhoudaxiao.common.exception;


import com.yiseven.zhoudaxiao.common.response.ResponseCode;

/**
 * @author hdeng
 */
public class ExceptionThrow {
    /**
     * 抛出自定义的异常
     * @param responseCode
     * @param condition
     */
    public static void cast(ResponseCode responseCode, boolean condition) {
        if (condition) {
            throw new CustomException(responseCode);
        }
    }
}

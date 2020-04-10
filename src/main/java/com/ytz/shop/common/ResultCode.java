
package com.ytz.shop.common;

/**
 * @ClassName: ResultCode
 * @Description:  枚举了一些常用API操作码
 * @author: yangtianzeng
 * @date: 2020/3/16 8:55
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败，服务器异常"),
    VALIDATE_FAILED(404, "参数检验失败，找不到路径"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}

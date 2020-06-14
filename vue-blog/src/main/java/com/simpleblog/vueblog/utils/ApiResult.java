package com.simpleblog.vueblog.utils;

/**
 * @author HouXudong
 * @data 2018-12-28
 */
public class ApiResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static final ApiResult SUCCESS = new ApiResult();

    public static final ApiResult NOT_FOUND = new ApiResult(404, "Not Found");

    public ApiResult() {
        this(null);
    }

    public ApiResult(T data) {
        this(200, "success", data);
    }

    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    public ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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
        return "ApiResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.simpleblog.vueblog.exception;

import com.simpleblog.vueblog.utils.ApiResult;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author:hxd
 * @date:2020/5/31
 */
public class UserNoticeException extends IllegalStateException {
    /**
     * 错误码，传给 {@link ApiResult}
     */
    private Integer code;
    /**
     * 消息 code，传给 {@link org.springframework.context.MessageSource#getMessage(String, Object[], Locale)}
     */
    private String messageCode;
    /**
     * 参数，传给 {@link org.springframework.context.MessageSource#getMessage(String, Object[], Locale)}的args
     */
    private String[] args;

    public UserNoticeException(String messageCode) {
        this(messageCode, (String[]) null);
    }

    public UserNoticeException(Integer code, String messageCode) {
        this(code, messageCode, null);
    }

    public UserNoticeException(String messageCode, String arg) {
        this(messageCode, new String[]{arg});
    }

    public UserNoticeException(String messageCode, String[] args) {
        this(400, messageCode, args);
    }

    public UserNoticeException(Integer code, String messageCode, String[] args) {
        super(messageCode);
        this.code = code;
        this.messageCode = messageCode;
        this.args = args;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "UserNoticeException{" +
                "code=" + code +
                ", messageCode='" + messageCode + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}

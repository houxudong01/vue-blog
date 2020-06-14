package com.simpleblog.vueblog.controller;

import com.simpleblog.vueblog.config.MessageCodeConsts;
import com.simpleblog.vueblog.exception.UserNoticeException;
import com.simpleblog.vueblog.utils.ApiResult;
import org.apache.shiro.ShiroException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author:hxd
 * @date:2020/5/31
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    /**
     * 自定义用户通知异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(UserNoticeException.class)
    ApiResult<String> notice(HttpServletRequest request, UserNoticeException e) {
        String message = this.messageSource.getMessage(e.getMessageCode(), e.getArgs(), this.localeResolver.resolveLocale(request));
        return new ApiResult<>(e.getCode(), message);
    }

    /**
     * Spring MVC 参数缺失异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    ApiResult parameterMissing(HttpServletRequest request, MissingServletRequestParameterException e) {
        String message = this.messageSource.getMessage(
                MessageCodeConsts.PARAMETER_MISSING,
                new Object[]{e.getParameterName()},
                this.localeResolver.resolveLocale(request));
        return new ApiResult(HttpStatus.BAD_GATEWAY.value(), message);
    }

    /**
     * Spring MVC 参数值无效异常处理，如字符串无法转换为需要的 Enum，Long 等
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ApiResult parameterInvalid(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String message = this.messageSource.getMessage(
                MessageCodeConsts.PARAMETER_INVALID,
                new Object[]{e.getName(), e.getValue()},
                this.localeResolver.resolveLocale(request));
        return new ApiResult(HttpStatus.BAD_GATEWAY.value(), message);
    }

    /**
     * mybatis 操作数据库异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(MyBatisSystemException.class)
    ApiResult dbErrorHandler(HttpServletRequest request, MyBatisSystemException e) {
        String message = this.messageSource.getMessage(
                MessageCodeConsts.DB_ERROR,
                null,
                this.localeResolver.resolveLocale(request));
        return new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * 通用的的默认异常处理，用来兜底
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    ApiResult commonErrorHandler(HttpServletRequest request, Exception e) {
        String message = this.messageSource.getMessage(
                MessageCodeConsts.UNKNOW_ERROR,
                null,
                this.localeResolver.resolveLocale(request));
        return new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * 捕捉shiro的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    ApiResult handle401(ShiroException e) {
        return new ApiResult(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    /**
     * 处理Assert的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    ApiResult handler(IllegalArgumentException e) throws IOException {
        logger.error("Assert异常:-------------->{}", e.getMessage());
        return new ApiResult(HttpStatus.BAD_REQUEST.value(), "IllegalArgumentException");
    }

    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ApiResult handler(MethodArgumentNotValidException e) throws IOException {
        logger.error("运行时异常:-------------->", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new ApiResult(HttpStatus.BAD_REQUEST.value(), objectError.getDefaultMessage());
    }

}

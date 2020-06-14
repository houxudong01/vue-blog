package com.simpleblog.vueblog.service;

import com.simpleblog.vueblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param response
     * @return
     */
    User login(String username, String password, HttpServletResponse response);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    User getUserInfo(Long id);

}

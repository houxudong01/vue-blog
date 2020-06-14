package com.simpleblog.vueblog.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simpleblog.vueblog.config.MessageCodeConsts;
import com.simpleblog.vueblog.entity.User;
import com.simpleblog.vueblog.exception.UserNoticeException;
import com.simpleblog.vueblog.mapper.UserMapper;
import com.simpleblog.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simpleblog.vueblog.utils.JwtUtils;
import com.simpleblog.vueblog.utils.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public User login(String username, String password, HttpServletResponse response) {
        ParameterUtil.required(username, "username");
        ParameterUtil.required(password, "password");

        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new UserNoticeException(HttpStatus.NOT_FOUND.value(), MessageCodeConsts.NOT_FOUND_USER);
        }
        if (!user.getPassword().equals(SecureUtil.md5(password))) {
            throw new UserNoticeException(HttpStatus.UNAUTHORIZED.value(), MessageCodeConsts.USER_PASSWORD_INVALID);
        }
        user.setPassword(null);
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return user;
    }

    @Override
    public User getUserInfo(Long id) {
        ParameterUtil.required(id, "id");
        User user = this.getById(id);
        if (user == null) {
            throw new UserNoticeException(HttpStatus.NOT_FOUND.value(), MessageCodeConsts.NOT_FOUND_USER);
        }
        return user;
    }
}

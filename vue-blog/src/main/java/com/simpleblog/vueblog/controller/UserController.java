package com.simpleblog.vueblog.controller;


import com.simpleblog.vueblog.service.UserService;
import com.simpleblog.vueblog.utils.ApiResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return this.userService.getUserInfo(id);
    }

    /**
     * 登录接口
     * 默认账号密码：markerhub / 111111
     */
    @CrossOrigin
    @PostMapping("/login")
    public ApiResult login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        return new ApiResult(this.userService.login(username, password, response));
    }

    /**
     * 退出
     *
     * @return
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    public ApiResult logout() {
        SecurityUtils.getSubject().logout();
        return ApiResult.SUCCESS;
    }

}

package com.simpleblog.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author:hxd
 * @date:2020/6/13
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

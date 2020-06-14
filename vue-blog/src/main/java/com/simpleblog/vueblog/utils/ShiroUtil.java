package com.simpleblog.vueblog.utils;

import com.simpleblog.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author:hxd
 * @date:2020/6/13
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}

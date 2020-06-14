package com.simpleblog.vueblog.controller;


import com.simpleblog.vueblog.service.BlogService;
import com.simpleblog.vueblog.utils.ApiResult;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 根据页码列出博客列表
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/listAll")
    public ApiResult listAll(Integer currentPage) {
        return new ApiResult(this.blogService.listAll(currentPage));
    }

    /**
     * 根据 id 获取到博客详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResult detail(@PathVariable(name = "id") Long id) {
        return new ApiResult(this.blogService.getDetailById(id));
    }

    /**
     * 编辑博客
     *
     * @param id          博客主键 id，为空时是首次新建编辑
     * @param title
     * @param description
     * @param content
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/edit")
    public ApiResult edit(Long id, @RequestParam String title, @RequestParam String description, @RequestParam String content) {
        return new ApiResult(this.blogService.createBlog(id, title, description, content));
    }

}

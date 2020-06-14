package com.simpleblog.vueblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.simpleblog.vueblog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
public interface BlogService extends IService<Blog> {

    /**
     * 根据页码列出博客列表
     *
     * @param currentPage
     * @return
     */
    IPage listAll(Integer currentPage);

    /**
     * 根据 id 获取博客详情
     *
     * @param Id
     * @return
     */
    Blog getDetailById(Long Id);

    /**
     * 编辑博客
     *
     * @param id
     * @param title
     * @param description
     * @param content
     * @return
     */
    Blog createBlog(Long id, String title, String description, String content);

}

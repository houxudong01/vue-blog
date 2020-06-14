package com.simpleblog.vueblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simpleblog.vueblog.config.MessageCodeConsts;
import com.simpleblog.vueblog.entity.Blog;
import com.simpleblog.vueblog.exception.UserNoticeException;
import com.simpleblog.vueblog.mapper.BlogMapper;
import com.simpleblog.vueblog.service.BlogService;
import com.simpleblog.vueblog.utils.ParameterUtil;
import com.simpleblog.vueblog.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hxd
 * @since 2020-06-13
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Override
    public IPage listAll(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage pageData = this.page(page, new QueryWrapper<Blog>().orderByDesc("gmt_modified"));
        logger.info("listAll: {}", pageData);
        return pageData;

    }

    @Override
    public Blog getDetailById(Long id) {
        ParameterUtil.required(id, "id");
        Blog blog = this.getById(id);
        if (blog == null) {
            throw new UserNoticeException(HttpStatus.NOT_FOUND.value(), MessageCodeConsts.NOT_FOUND_BlOG);
        }
        return blog;
    }

    @Override
    public Blog createBlog(Long id, String title, String description, String content) {
        ParameterUtil.required(title, "title");
        ParameterUtil.required(description, "description");
        ParameterUtil.required(content, "content");

        Long currentUserId = ShiroUtil.getProfile().getId();
        // 未登录
        if (currentUserId == null) {
            throw new UserNoticeException(HttpStatus.UNAUTHORIZED.value(), MessageCodeConsts.NOT_FOUND_USER);
        }

        Blog blog;
        // 首次新建
        if (id == null) {
            blog = new Blog();
            blog.setUserId(currentUserId);
        }
        // 编辑
        else {
            blog = this.getById(id);
            if (blog == null) {
                throw new UserNoticeException(HttpStatus.NOT_FOUND.value(), MessageCodeConsts.NOT_FOUND_BlOG);
            }
            // 判断是否有权限编辑
            if (!blog.getUserId().equals(currentUserId)) {
                throw new UserNoticeException(HttpStatus.FORBIDDEN.value(), MessageCodeConsts.PERMISSION_DENY);
            }
        }
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setContent(content);

        this.saveOrUpdate(blog);
        return blog;
    }
}

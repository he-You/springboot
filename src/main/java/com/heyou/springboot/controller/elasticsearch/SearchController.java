package com.heyou.springboot.controller.elasticsearch;

import com.heyou.springboot.dao.IBlogRepository;
import com.heyou.springboot.entity.Blog;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/24 16:26
 */
@RestController
@RequestMapping("/search")
@Api(tags = "ES-search")
public class SearchController {
    @Resource
    private IBlogRepository blogRepository;

    @GetMapping("/title/{keyword}")
    public List<Blog> searchByKeyword(@PathVariable("keyword")String keyword){
        return blogRepository.findByTitleLike(keyword);
    }
}

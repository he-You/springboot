package com.heyou.springboot.dao;

import com.heyou.springboot.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/26 22:57
 */
public interface IBlogRepository extends ElasticsearchRepository<Blog,String> {
    List<Blog> findByTitleLike(String keyword);
}

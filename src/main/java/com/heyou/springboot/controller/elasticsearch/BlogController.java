package com.heyou.springboot.controller.elasticsearch;

import com.heyou.springboot.dao.IBlogRepository;
import com.heyou.springboot.entity.Blog;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/26 22:55
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "ES-CRUD")
public class BlogController {
    @Autowired
    private IBlogRepository blogRepository;

    @PostMapping("/add")
    public Object add(@RequestBody Blog blog){
        blogRepository.save(blog);
        return blog;
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable String id) {
        Optional<Blog> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            Blog blog = blogModelOptional.get();
            return blog;
        }
        return null;
    }

    @GetMapping("/get/all")
    public Object getAll(){
        List<Blog> list = new ArrayList<>();
        Iterable<Blog> iterable = blogRepository.findAll();
        iterable.forEach(list::add);
        return list;
    }

    @PostMapping("/update")
    public Object updateById(@RequestBody Blog blogModel) {
        String id = blogModel.getBlogId();
        Blog save = blogRepository.save(blogModel);
        return save;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        blogRepository.deleteById(id);
    }

    @DeleteMapping("/delete/all")
    public void deleteById() {
        blogRepository.deleteAll();
    }

}


package com.heyou.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/26 22:56
 */
@Data
@Accessors(chain = true)
@Document(indexName = "elasticsearch",type = "blog")
public class Blog {
    @Id
    private String blogId;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
}

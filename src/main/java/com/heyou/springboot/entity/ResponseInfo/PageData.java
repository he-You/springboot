package com.heyou.springboot.entity.ResponseInfo;
/**
 * 简要说明. <br>
 * 返回体：分页对象
 * <p>
 * Date-Time：  2019/9/16 10:48
 * <p>
 *
 * @author heyou
 * @version 1.0.0
 */
public class PageData {
    //当前页码

    private Integer pageIndex = 1; //默认页码 1

    //当前每页条数

    private Integer pageSize = 10; //默认每页条数


    private Integer totalPage = 1; //默认为1


    private Long totalNum = 1L; //默认1

    //默认排序方向
    //private String order = Direction.DESC.toString();

    //排序字段
    private String properties;

    public PageData() {

    }

    public PageData(Integer pageIndex,Integer pageSize,Integer totalPage,Long totalNum) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalNum = totalNum;
    }
}

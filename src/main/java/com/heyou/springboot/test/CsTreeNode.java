package com.heyou.springboot.test;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 16:21
 */
public class CsTreeNode {
    //结点的数据域
    private Object data;
    //左孩子，右兄弟
    private CsTreeNode firstchild,nextsibling;
    public CsTreeNode(){   //构造一个空结点
        this(null);
    }
    public CsTreeNode(Object data){   //构造一个左孩子，右兄弟为空的结点
        this(data,null,null);
    }
    public CsTreeNode(Object data,CsTreeNode firstchild,CsTreeNode nextsibling){
        this.data=data;
        this.firstchild=firstchild;
        this.nextsibling=nextsibling;
    }
    public Object getdata(){
        return data;
    }
    public CsTreeNode getfirstchild(){
        return firstchild;
    }
    public CsTreeNode getnextsibling(){
        return nextsibling;
    }
    public void setdata(Object data){
        this.data=data;
    }
    public void setfirstchild(CsTreeNode firstchild){
        this.firstchild=firstchild;
    }
    public void setnextsibling(CsTreeNode nextsibling){
        this.nextsibling=nextsibling;
    }
}

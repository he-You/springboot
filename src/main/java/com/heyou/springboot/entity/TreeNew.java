package com.heyou.springboot.entity;

import java.util.List;
import java.util.Map;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 11:15
 */
public class TreeNew {
    private String parentId;

    private String identifier;

    private String specObjectRef;

    private List<TreeNew> children;

    private Map<String,TreeNew> treeMap;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<TreeNew> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNew> children) {
        this.children = children;
    }

    public String getSpecObjectRef() {
        return specObjectRef;
    }

    public void setSpecObjectRef(String specObjectRef) {
        this.specObjectRef = specObjectRef;
    }

    public Map<String, TreeNew> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(Map<String, TreeNew> treeMap) {
        this.treeMap = treeMap;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}

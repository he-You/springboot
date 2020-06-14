package com.heyou.springboot.entity;

import java.util.Map;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 12:09
 */
public class SpecHerarchyTreeMap {
    private String identifier;

    private String specObjectId;

    private Map<String,SpecHerarchyTreeMap> treeNewMap;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSpecObjectId() {
        return specObjectId;
    }

    public void setSpecObjectId(String specObjectId) {
        this.specObjectId = specObjectId;
    }

    public Map<String, SpecHerarchyTreeMap> getTreeNewMap() {
        return treeNewMap;
    }

    public void setTreeNewMap(Map<String, SpecHerarchyTreeMap> treeNewMap) {
        this.treeNewMap = treeNewMap;
    }
}

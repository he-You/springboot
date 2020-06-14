package com.heyou.springboot.entity;

import java.util.List;
import java.util.Map;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 11:17
 */
public class SpecificationNew {
    private String specificationIdentifier;

    private List<TreeNew> specherarchy;

    private Map<String,TreeNew> specHerarchyTreeMap;

    public String getSpecificationIdentifier() {
        return specificationIdentifier;
    }

    public void setSpecificationIdentifier(String specificationIdentifier) {
        this.specificationIdentifier = specificationIdentifier;
    }

    public List<TreeNew> getSpecherarchy() {
        return specherarchy;
    }

    public void setSpecherarchy(List<TreeNew> specherarchy) {
        this.specherarchy = specherarchy;
    }

    public Map<String, TreeNew> getSpecHerarchyTreeMap() {
        return specHerarchyTreeMap;
    }

    public void setSpecHerarchyTreeMap(Map<String, TreeNew> specHerarchyTreeMap) {
        this.specHerarchyTreeMap = specHerarchyTreeMap;
    }
}

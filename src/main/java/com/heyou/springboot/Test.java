package com.heyou.springboot;

import com.heyou.springboot.entity.SpecHerarchyTreeMap;
import com.heyou.springboot.entity.Specification;
import com.heyou.springboot.entity.TreeNew;
import com.heyou.springboot.util.DateUtil;

import java.util.*;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 11:24
 */
public  class Test {
    private static Specification setSpecificationValue(){
        Specification specification = new Specification();
        specification.setSpecificationIdentifier("202006141125");
        specification.setSpecherarchy(parseTree());
        specification.setTreeNewMap(parseTreeMap());
        return specification;
    }

    private static List<TreeNew> parseTree(){
        List<TreeNew> tree = new ArrayList<>();
        tree.add(getTree("l"));
        tree.add(getTree("r"));
        return tree;
    }

    private static Map<String, TreeNew> parseTreeMap(){
        Map<String,TreeNew> tree = new HashMap<>();
        tree.put(getTreeMap("l").getIdentifier(),getTreeMap("l"));
        tree.put(getTreeMap("r").getIdentifier(),getTreeMap("r"));
        return tree;
    }

    private static TreeNew getTreeMap(String s){
        TreeNew specHerarchyTreeMap = new TreeNew();
        specHerarchyTreeMap.setIdentifier(UUID.randomUUID().toString());
        specHerarchyTreeMap.setSpecObjectRef(DateUtil.getStringAllDate());
        if(s.equals("l")){
            specHerarchyTreeMap.setChildren(getTreeListL(""));
            specHerarchyTreeMap.setTreeMap(getTreeMapL());
        } else {
            specHerarchyTreeMap.setChildren(getTreeListR(""));
            specHerarchyTreeMap.setTreeMap(getTreeMapR());
        }

        return specHerarchyTreeMap;
    }

    private static TreeNew getTree(String s){
        TreeNew treeNew = new TreeNew();
        treeNew.setIdentifier(UUID.randomUUID().toString());
        treeNew.setSpecObjectRef(DateUtil.getStringAllDate());
        treeNew.setParentId("");
        if(s.equals("l")){
            treeNew.setTreeMap(new HashMap<>());
            treeNew.setChildren(getTreeListL(treeNew.getIdentifier()));
        } else {
            treeNew.setTreeMap(new HashMap<>());
            treeNew.setChildren(getTreeListR(treeNew.getIdentifier()));
        }

        return treeNew;
    }

    private static List<TreeNew> getTreeListL(String parentId){
        List<TreeNew> treeNew = new ArrayList<>();
        for(int i=0;i<5;i++){
            treeNew.add(treeNew(parentId));
        }
        return treeNew;
    }

    private static Map<String,TreeNew> getTreeMapL(){
        Map<String,TreeNew> treeNew = new HashMap<>();
        for(int i=0;i<5;i++){
            treeNew.put(treeNewMap().getIdentifier(),treeNewMap());
        }
        return treeNew;
    }

    private static List<TreeNew> getTreeListR(String parentId){
        List<TreeNew> treeNew = new ArrayList<>();
        for(int i=0;i<3;i++){
            treeNew.add(treeNew(parentId));
        }
        return treeNew;
    }

    private static Map<String,TreeNew> getTreeMapR(){
        Map<String,TreeNew> treeNew = new HashMap<>();
        for(int i=0;i<3;i++){
            treeNew.put(treeNewMap().getIdentifier(),treeNewMap());
        }
        return treeNew;
    }

    private static TreeNew treeNew(String parentId){
        TreeNew treeNew = new TreeNew();
        treeNew.setIdentifier(UUID.randomUUID().toString());
        treeNew.setSpecObjectRef(DateUtil.getStringAllDate());
        treeNew.setParentId(parentId);
        treeNew.setChildren(new ArrayList<>());
        return treeNew;
    }

    private static TreeNew treeNewMap(){
        TreeNew treeNew = new TreeNew();
        treeNew.setIdentifier(UUID.randomUUID().toString());
        treeNew.setSpecObjectRef(DateUtil.getStringAllDate());
        treeNew.setTreeMap(new HashMap<>());
        treeNew.setChildren(new ArrayList<>());
        return treeNew;
    }

    private static void compareTree(){
        Specification specificationOld = setSpecificationValue();
        Specification specificationNew = setSpecificationValue();
        // 将旧树放入数据中，遍历新树，
        // 遍历
        Map<String, TreeNew> allTreeNodeOld = getAllTreeNode(specificationOld.getTreeNewMap(), new HashMap<>());
        Map<String, TreeNew> allTreeNodeNew = getAllTreeNode(specificationOld.getTreeNewMap(), new HashMap<>());
        // 遍历新树，如果匹配到旧树的 key，将旧树对应的 map对象删除，旧树map剩下的就是新树删除的节点；再次遍历新树，将这个节点添加到指定的父节点下
        allTreeNodeOld.putAll(allTreeNodeNew);
        specificationNew.getSpecherarchy();
    }

    private static Map<String,TreeNew> getAllTreeNode(Map<String,TreeNew> treeNewMap,Map<String,TreeNew> resultMap){
        if(treeNewMap!=null && treeNewMap.size()>0){
            for(Map.Entry<String, TreeNew> entry : treeNewMap.entrySet()){
                String mapKey = entry.getKey();
                TreeNew mapValue = entry.getValue();
                resultMap.put(mapKey, mapValue);
                getAllTreeNode(mapValue.getTreeMap(),resultMap);
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
        compareTree();
    }
}

package com.heyou.springboot.test;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/6/14 16:18
 */
public class TestTree {
    private CsTreeNode root;  //树的根节点
    public TestTree(){      //构造一棵空树
        this.root=root;
    }
    public TestTree(CsTreeNode root){   //构造一棵树
        this.root=root;
    }
    public void preroottraverse(CsTreeNode t){  //树的先根遍历
        if(t!=null){
            System.out.print(t.getdata());
            preroottraverse(t.getfirstchild());
            preroottraverse(t.getnextsibling());
        }
    }
    public void postroottraverse(CsTreeNode t){  //树的后根遍历
        if(t!=null){
            postroottraverse(t.getfirstchild());
            System.out.print(t.getdata());
            postroottraverse(t.getnextsibling());
        }
    }
    public void leveltraverse(CsTreeNode t){   //树的层次遍历
        /*if(t!=null){
            Linkqueue l=new Linkqueue();
            l.offer(t);
            while(!l.isEmpty()){
                for(t=(cstreeNode)t.poll();t!=null;t=t.getnextsibling())
                    System.out.print(t.getdata()+" ");
                if(t.getfirstchild()!=null)
                    l.offer(t.getfirstchild());
            }
        }*/
    }
    public TestTree createcstree(){   //创建树
        CsTreeNode k=new CsTreeNode('k',null,null);
        CsTreeNode f=new CsTreeNode('f',k,null);
        CsTreeNode e=new CsTreeNode('e',null,f);
        CsTreeNode g=new CsTreeNode('g',null,null);
        CsTreeNode l=new CsTreeNode('l',null,null);
        CsTreeNode j=new CsTreeNode('j',null,null);
        CsTreeNode i=new CsTreeNode('i',l,j);
        CsTreeNode h=new CsTreeNode('h',null,i);
        CsTreeNode d=new CsTreeNode('d',h,null);
        CsTreeNode c=new CsTreeNode('c',g,d);
        CsTreeNode b=new CsTreeNode('b',e,c);
        CsTreeNode a=new CsTreeNode('a',b,null);
        return new TestTree(a);   //创建根节点为a的树
    }
    public static void main(String[] args){
        TestTree debug=new TestTree();
        TestTree cs=debug.createcstree();
        CsTreeNode root=cs.root;  //取得树的根节点
        System.out.println("树的先根遍历");
        cs.preroottraverse(root);
        System.out.println();
        System.out.println("树的后根遍历");
        cs.postroottraverse(root);
    }
}

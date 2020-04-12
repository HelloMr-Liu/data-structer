package com.king2.link;

/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/05 15:40
 * 描述: 节点类
 ******************************/
public class MyNode {
    public Object objectValue; //节点类内容

    public MyNode nextNode;     //当前节点的下一个相依节点

    public MyNode(Object objectValue){
        this.objectValue=objectValue;
    }

    @Override
    public String toString() {
        return "当前节点："+objectValue+"===下一个相依节点："+(nextNode==null?"空":nextNode.objectValue);
    }
}

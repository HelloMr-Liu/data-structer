package com.king2.link;


/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/09 14:41
 * 描述: 单向环形链表演示
 ******************************/
public class OnWayRingLinkDemo {

    public static void main(String[] args) {
        OnWayRingLink link=new OnWayRingLink();
        link.addNode(2);
        link.addNode(3);
        link.addNode(4);
        link.showNode();
    }
}



/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/09 14:42
 * 描述: 单向环形链表
 ******************************/
class OnWayRingLink{

    //创建一个头节点,牵引整个链表的第一个有效节点
    MyNode headNode=new MyNode(null);

    public void showNode(){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时变量用于存储当前节点向下相依节点信息
        MyNode temp=null;

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            System.out.println(temp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //更新的当前节点信息
            currNode=temp;
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/09 14:44
     * 描述: 添加节点方法(有序添加)
     ******************************/
    public void addNode(Object objValue){
        //获取头节点信息（牵引整个链表第一个有效节点）
        MyNode currNode=headNode;

        //将添加的内容封装到一个方法中
        MyNode newNode=new MyNode(objValue);

        //创建一个临时变量存储当前节点向下的相依节点信息
        MyNode temporary=null;

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            //更新当前节点信息
            currNode=temporary;

            if(currNode.nextNode==headNode.nextNode){
                break;
            }

        }

        //将当前节点向下相依节点信息更新为新节点信息
        currNode.nextNode=newNode;

        //将新节点向下的相依节点信息更新为当前节点向下的节点信息
        newNode.nextNode=headNode.nextNode==null?newNode:headNode.nextNode;


    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/09 14:45
     * 描述: 添加节点方法(排序添加)
     ******************************/
    public void addNodeOrderBy(Object objValue){


    }
}
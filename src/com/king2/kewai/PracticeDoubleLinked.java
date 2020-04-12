package com.king2.kewai;


//练习双向链表
public class PracticeDoubleLinked {

    public static void main(String[] args) {
        DoubleLinked linked=new DoubleLinked();
        linked.addNodeOrderBy(2);
        linked.addNodeOrderBy(1);
        linked.addNodeOrderBy(5);
        linked.addNodeOrderBy(4);
        linked.addNodeOrderBy(3);
        linked.showNode();

    }
}

//创建一个双向链表类
class DoubleLinked {

    public void showNode(){
        //获取头节点
        DoubleLinkedNode currNode=headNode;

        while(currNode.nextNode!=null){
            System.out.println(currNode.nextNode.currData+"====向上"+currNode.nextNode.preNode.currData+"===向下"+currNode.nextNode.nextNode.currData);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currNode=currNode.nextNode;
        }

    }

    //创建一个头节点
    DoubleLinkedNode headNode=new DoubleLinkedNode(null);

    //实现双向循环链表
    public void addNodeOrderBy(Object value){
        //获取对应的头节点信息
        DoubleLinkedNode currNode=headNode;

        //创建一个临时遍历存储当前节点的下一个相依节点信息
        DoubleLinkedNode temp=null;

        //将当前添加的信息封装到一个节点中
        DoubleLinkedNode newNode=new DoubleLinkedNode(value);

        //循环判断整个链表
        while(currNode.nextNode!=null){
            //获取当前节点的下一个相依节点
            temp=currNode.nextNode;

            if((Integer)temp.currData>(Integer) value){

                break;
            }

            //更新当前节点信息
            currNode=temp;

            //判断当前循环链表节点是否 在最后一个如果是最后一个跳出当前循环
            if(currNode.nextNode==headNode.nextNode){
                break;
            }
        }

        //判断当前链表为空怎么处理
        if(currNode.nextNode==null){
            //更新新节点的上下相依节点
            newNode.preNode=newNode;
            newNode.nextNode=newNode;

            //将当前新节点赋值给 当前节点向下相依节点上
            currNode.nextNode=newNode;

        }
        //当新添加的节点在整个链表最前一个怎么处理
        else if(currNode==headNode){

            //获取最后一个节点信息
            DoubleLinkedNode lastNode=headNode;
            while(lastNode.nextNode!=null){
                //更新当前节点信息
                lastNode=lastNode.nextNode;
                if(lastNode.nextNode==headNode.nextNode){
                    break;
                }
            }

            //更新新节点的上下相依节点位置
            newNode.preNode=lastNode;
            newNode.nextNode=currNode.nextNode;


            //更新当前节点向下相依节点的向上相依节点信息
            currNode.nextNode.preNode=newNode;

            //将更新当前向下相依节点信息
            currNode.nextNode=newNode;

            //更新末尾节点向下相依节点信息
            lastNode.nextNode=newNode;
        }

        //当前添加的节点在整个链表最后一个怎么处理
        else if(currNode.nextNode==headNode.nextNode){
            //更新新节点的上下相依节点
            newNode.preNode=currNode;
            newNode.nextNode=currNode.nextNode;

            //更新当前节点向下相依节点
            currNode.nextNode=newNode;

            headNode.nextNode.preNode=newNode;
        }
        //当新添加的节点在整个链表的中间怎么处理
        else{
            //更新新节点的上下相依节点信息
            newNode.preNode=currNode;
            newNode.nextNode=currNode.nextNode;


            currNode.nextNode.preNode=newNode;
            currNode.nextNode=newNode;
        }
    }



}

//创建一个双向链表节点类
class DoubleLinkedNode{

    public DoubleLinkedNode preNode;
    public DoubleLinkedNode nextNode;
    public Object currData;


    public DoubleLinkedNode(Object data){
        this.currData=data;
    }

}
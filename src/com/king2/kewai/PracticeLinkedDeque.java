package com.king2.kewai;


//链表实现双端队列
public class PracticeLinkedDeque {
    public static void main(String[] args) {
        LinkedDeque deque=new LinkedDeque();
        deque.addLastNode(3);
        deque.addLastNode(4);
        deque.addLastNode(5);
        deque.addFistNode(2);
        deque.addFistNode(1);
        deque.showDeque();
        System.out.println("===============删除=================");
        deque.deleteFirstNode();
        deque.deleteLastNode();
        deque.showDeque();
        System.out.println("=====还长度："+deque.length());

        System.out.println("===============全部删除=================");
        for(int index=0;index<deque.toArray().length;index++){

            System.out.println(deque.toArray()[index]);
        }


    }
}



//双端队列链表
class LinkedDeque{

    //创建一个头结点(牵引整个链表第一个元素)
    DequeNode head=new DequeNode(null);

    private int length; //存储对应的链表长度


    //添加最前节点
    public void addFistNode(Object value){
        length++;
        //获取头节点信息
        DequeNode currNode=head;

        //将新添加的内容封装到新节点中
        DequeNode newNode=new DequeNode(value);

        //更新当前节点下的相依节点信息
        newNode.nextNode=currNode.nextNode;
        currNode.nextNode=newNode;
    }


    //添加尾节点
    public void addLastNode(Object value){
        length++;
        //获取头节点信息
        DequeNode currNode=head;

        //将新添加的内容封装到新节点中
        DequeNode newNode=new DequeNode(value);

        //将当前节点循环到最后一个节点,实现添加尾节点
        while(currNode.nextNode!=null){

            //更新当前节点
            currNode=currNode.nextNode;
        }

        //将当前新节点添加到尾节点向下相依节点上
        currNode.nextNode=newNode;
    }


    //删除头节点
    public Object deleteFirstNode(){

        //获取头节点信息
        DequeNode currNode=head;

        DequeNode deleteNode=null;

        if(currNode.nextNode!=null){
            length--;
            deleteNode=currNode.nextNode;
            //移除第一个有效节点新
            currNode.nextNode=currNode.nextNode.nextNode;
        }
        return deleteNode.data;
    }


    //删除尾节点
    public Object deleteLastNode(){
        //获取头节点
        DequeNode currNode=head;

        DequeNode deleteNode=null;

        //循环遍历到最后一个节点上
        while(currNode.nextNode!=null){
            //判断当前节点的下一个节点的下一个节点是否为空如果为空当前节点是倒数第二个节点
            if(currNode.nextNode.nextNode==null){
                length--;
                deleteNode=currNode.nextNode;
                //为空将当前节点向下相依节点设置为空
                currNode.nextNode=null;
                break;
            }
            //更新当前节点
            currNode=currNode.nextNode;
        }
        return deleteNode.data;
    }


    //显示整个链表信息
    public void showDeque(){
        //获取头结点信息
        DequeNode currNode=head;

        while(currNode.nextNode!=null){
            System.out.println(currNode.nextNode.data);
            //更新当前节点
            currNode=currNode.nextNode;
        }
    }

    //判断的链表是否为空
    public boolean isNull(){
        return head.nextNode==null;
    }

    //获取当前链表整个长度
    public int length(){
        return length;
    }


    //将当前链表转换成一个数组
    public Object[] toArray(){
        //获取头节点
        DequeNode currNode=head;

        //创建一个数组存储链表中的数据
        Object[] objArray=new Object[length];

        int count=0;
        while(currNode.nextNode!=null){
            //将当前向下相依节点信息存储到数组中
            objArray[count++]=currNode.nextNode.data;

            //更新当前节点
            currNode=currNode.nextNode;

        }
        return objArray;
    }

}

//双端队列链表要用到的节点对象 该节点是单节点
class DequeNode{

    public DequeNode nextNode;  //向下相依节点对象
    public Object data;         //当前节点信息

    public DequeNode(Object data){
        this.data=data;
    }
}

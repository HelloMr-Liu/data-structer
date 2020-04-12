package com.king2.link;



/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/09 9:02
 * 描述: 双向链表演示
 ******************************/
public class DoubleLinkListDemo {


    public static void main(String[] args) {

        DoubleLinkList list=new DoubleLinkList();
        list.addDoubleNode(1);
        list.addDoubleNode(2);
        list.addDoubleNode(4);
        list.addDoubleNode(3);
        list.addDoubleNode(2);
        System.out.println("====================未排序的链表显示=================");
        list.showNode();

        DoubleLinkList list2=new DoubleLinkList();
        list2.addDoubleNodeOrderBy(1);
        list2.addDoubleNodeOrderBy(4);
        list2.addDoubleNodeOrderBy(6);
        list2.addDoubleNodeOrderBy(5);
        list2.addDoubleNodeOrderBy(2);
        list2.addDoubleNodeOrderBy(6);
        System.out.println("====================排序的链表显示=================");
        list2.showNode();

        System.out.println("===================删除了一个2后显示的内容==========");
        list2.deleteDoubleNode(2);
        list2.showNode();
    }
}




class DoubleLinkList{

    //创建头节点标志(作用：牵引指向当前整个链表的第一个节点信息)
    DoubleNode headNode=new DoubleNode(null);



    public void showNode(){
        //获取头节点(牵引整个链表的第一个有效节点信息)
        DoubleNode currNode=headNode;

        //创建一个临时变量存储当前节点向下相依节点信息
        DoubleNode temporary=null;

        while (currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            System.out.println(temporary);

            //更新当前节点信息
            currNode=temporary;
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/09 9:08
     * 描述: 创建一个默认添加双向链表末尾的方法(未排序)
     ******************************/
    public void addDoubleNode(Object objValue){

        //获取头节点信息（牵引整个链表第一个有效节点）
        DoubleNode currNode=headNode;

        //将当前添加的内容封装到一个新的节点上
        DoubleNode newNode=new DoubleNode(objValue);

        //创建一个临时变量存储当前节点的下一个相依节点信息
        DoubleNode temporary=null;

        while(currNode.nextNode!=null){

            //获取当前节点的下一个相依节点
            temporary=currNode.nextNode;

            //更新当前节点信息
            currNode=temporary;
        }

        //将当前节点信息赋值给新节点向前节点信息
        newNode.preNode=currNode;

        //将新节点赋值给当前节点的下一个相依节点上
        currNode.nextNode=newNode;
    }


    public void deleteDoubleNode(Object objValue){

        //获取头节点信息（牵引整个链表第一个有效节点信息）
        DoubleNode currNode=headNode;

        //创建一个临时变量用于存储当前节点的下一个相依节点信息
        DoubleNode temp=null;

        //创建一个标志用于判断是否指定节点删除成功
        Boolean flag=false;

        //获取当前第一个节点信息
        currNode=currNode.nextNode;
        while(currNode!=null){
            //获取当前节点的下一个相依节点信息
            temp=currNode.nextNode;

            if((Integer)currNode.objValue==(Integer)objValue){

                //当前节点的向上相依节点的向下相依节点信息更新为当前节点向下相依节点信息
                currNode.preNode.nextNode=currNode.nextNode;
                if(temp!=null){
                    //当前节点向下相依节点不为空就对向下相依节点的向上相依节点信息更新为当前节点的向上相依节点信息
                    currNode.nextNode.preNode=currNode.preNode;
                }
                flag=true;
                break;
            }

            //更新当前节点信息
            currNode=temp;
        }

        if(flag){
            System.out.println("节点："+objValue+"删除成功");
        }else{
            System.out.println("没有对应的节点可以删除");
        }


    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/09 9:18
     * 描述: 创建一个添加方法(排序)
     ******************************/
    public void addDoubleNodeOrderBy(Object objValue){

        //获取头节点信息（作用：牵引整个链表的第一个有效节点信息）
        DoubleNode currNode=headNode;

        //将新添加的内容封装到新创建好的节点上
        DoubleNode newNode=new DoubleNode(objValue);

        //创建一个临时变量用于封装当前节点的下一个相依节点信息
        DoubleNode temporary=null;

        while(currNode.nextNode!=null){

            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            if((Integer)temporary.objValue>(Integer)objValue){
                break;
            }

            //更新当前节点信息
            currNode=temporary;
        }

        if(currNode.nextNode==null){
            //将新节点的向上节点信息更新为当前节点
            newNode.preNode=currNode;

            //将当前节点向下节点信息更新为新节点信息
            currNode.nextNode=newNode;
        }else{

            //将新节点向下节点信息更新为 当前节点向下节点信息
            newNode.nextNode=currNode.nextNode;

            //将新节点向上节点信息更新为  当前节点信息
            newNode.preNode=currNode;

            //将当前节点向下节点信息的向上节点信息更新为新节点信息
            currNode.nextNode.preNode=newNode;

            //将当前节点向下信息更新为新节点信息
            currNode.nextNode=newNode;
        }


//        //获取当前第一个有效节点信息
//        currNode=currNode.nextNode;
//        while(currNode!=null){
//            //获取当前节点向下相依节点信息
//            temporary=currNode.nextNode;
//
//            if((Integer)currNode.objValue>(Integer)objValue){
//                //将新节点向下相依节点信息更新为当前节点
//                newNode.nextNode=currNode;
//
//                //将新节点向上相依节点信息更新为当前节点向上相依节点信息
//                newNode.preNode=currNode.preNode;
//
//                //当前节点向上相依节点信息的向下相依节点信息更新为新节点信息
//                currNode.preNode.nextNode=newNode;
//
//                //当前节点向上相依节点信息更新为新节点信息
//                currNode.preNode=newNode;
//                break;
//            }else if(temporary==null){
//                //当前节点向下相依节点信息更新为新节点信息
//                currNode.nextNode=newNode;
//
//                //将新节点的向上相依节点信息更新为当前节点信息
//                newNode.preNode=currNode;
//            }
//            //更新当前节点信息
//            currNode=temporary;
//        }
//
//        if(headNode.nextNode==null){
//
//            //将新节点向上的节点信息更新为头节点信息
//            newNode.preNode=headNode;
//
//            //将新节点信息赋值给头节点的向下相依节点信息
//            headNode.nextNode=newNode;
//        }
    }




}


/**********************
 * 作者: 刘梓江
 * 时间: 2019/12/09 9:03
 * 描述: 双向链表节点
 ******************************/
class DoubleNode{

    public Object objValue;     //当前节点的内容
    public DoubleNode preNode;  //当前节点的向前相依节点
    public DoubleNode nextNode; //当前节点的向后相依节点

    public DoubleNode(Object initObjValue){
        this.objValue=initObjValue;
    }

    @Override
    public String toString() {
        return "当前节点："+objValue+"===向前相依节点："+(preNode==null?"空":preNode.objValue)+"===向后相依节点"+(nextNode==null?"空":nextNode.objValue);
    }
}

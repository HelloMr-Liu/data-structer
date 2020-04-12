package com.king2.link;

public class DoubleLiuMyLinkListDemo {
    public static void main(String[] args) {

//        DoubleLiuMyLinkList list1=new DoubleLiuMyLinkList();
//        list1.addNode(1);
//        list1.addNode(4);
//        list1.addNode(3);
//        list1.addNode(2);
//        list1.addNode(5);
//        System.out.println("==================未排序的双向链表============");
//        list1.showNode();

        DoubleLiuMyLinkList list2=new DoubleLiuMyLinkList();
        list2.cycleDoubleLink(1);
        list2.cycleDoubleLink(4);
        list2.cycleDoubleLink(3);
        list2.cycleDoubleLink(2);
        list2.cycleDoubleLink(5);
        System.out.println("==================排序的双向链表============");
        list2.showNode();

    }
}



class DoubleLiuMyLinkList{

    //创建一个头节点信息
    DoubleNode headNode=new DoubleNode(null);


    public void showNode(){
        //获取头节点信息
        DoubleNode currNode=headNode;

        //创建一个临时变量存储当前节点向下相依节点信息
        DoubleNode temp=null;

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(temp);

            //更新当前节点信息
            currNode=temp;
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 20:04
     * 描述: 普通添加(不排序的)
     ******************************/
    public void  addNode(Object objValue){
        //获取头节点
        DoubleNode currNode=headNode;

        //创建临时变量获取当前节点向下相依节点信息‘
        DoubleNode temporary=null;

        //将新添加的内容封装到一个新节点上
        DoubleNode newNode=new DoubleNode(objValue);

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点上
            temporary=currNode.nextNode;

            //跟新当前节点信息
            currNode=temporary;
        }

        //将新节点向上节点信息更新为当前节点
        newNode.preNode=currNode;

        //将新节点信息更新在当前节点向下相依节点上
        currNode.nextNode=newNode;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 20:15
     * 描述: 添加新节点 (排序)
     ******************************/
    public void addNodeOrderBy(Object objValue){
        //获取头节点信息
        DoubleNode currNode=headNode;

        //创建一个临时变量存储当前节点向下相依节点信息
        DoubleNode temp=null;

        //将新添加的内容封装到新的节点上
        DoubleNode newNode=new DoubleNode(objValue);

        //循环遍历当前链表信息
        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            if((Integer)temp.objValue>(Integer)objValue){
                break;
            }

            //更新当前节点信息
            currNode=temp;
        }

        if(currNode.nextNode==null){
            //将新节点的向上相依节点更新为当前节点信息
            newNode.preNode=currNode;

            //将当前节点向下相依节点信息更新为新节点信息
            currNode.nextNode=newNode;
        }else{

            //将新节点信息向上相依节点信息跟新为当前节点信息
            newNode.preNode=currNode;

            //将新节点信息向下相依节点信息更新为当前节点向下相依节点信息
            newNode.nextNode=currNode.nextNode;

            //将当前节点向下相依节点信息的向上相依节点信息更新为新节点
            currNode.nextNode.preNode=newNode;

            //将当前节点向下相依节点信息更新为新节点信息
            currNode.nextNode=newNode;
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 20:25
     * 描述: 删除指定节点
     ******************************/
    public void deleteNode(Object objValue){
        //获取头节点信息
        DoubleNode currNode=headNode;

        //创建一个临时变量存储当前节点向下相依节点信息
        DoubleNode temp=null;

        //创建一个标志用于是否删除了指定的节点
        Boolean flag=false;

        //循环遍历整个链表
        while(currNode.nextNode!=null){
            //获取当前节点向下节点信息
            temp=currNode.nextNode;


            if((Integer)temp.objValue==(Integer)objValue){

                //将当前节点的向下相依节点信息更新为当前节点的向下相依节点的向下相依节点
                currNode.nextNode=currNode.nextNode.nextNode;

                if(currNode.nextNode.nextNode!=null){
                    //将当前节点的向下相依节点的向下相依节点信息的向上相依节点信息更新为当前节点
                    currNode.nextNode.nextNode.preNode=currNode;
                }

                flag=true;
                break;
            }

            //更新当前节点信息
            currNode=temp;
        }
        if(flag){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 20:38
     * 描述: 循环双向链表
     ******************************/
    public void cycleDoubleLink(Object objValue){
        //获取头节点信息
        DoubleNode currNode=headNode;

        //创建临时变量用于存储当前节点向下相依节点信息
        DoubleNode temp=null;

        //将新添加的内容封装到一个新节点
        DoubleNode newNode=new DoubleNode(objValue);

        //带头头节点
        /**
         * 思考两个问题
         *      1:链表为空和链表循环到最后一个怎么处理
         *      2：新节点在中间怎么处理
         */

//        while(currNode.nextNode!=null&&currNode.nextNode!=headNode){
//            //获取当前节点向下相依节点信息
//            temp=currNode.nextNode;
//
//            if((Integer)temp.objValue>(Integer)objValue){
//               break;
//            }
//
//            //更新当前节点信息
//            currNode=temp;
//        }
//
//        //链表为空和链表循环到最后一个怎么处理
//        if(currNode.nextNode==null||currNode.nextNode==headNode){
//
//            //将新节点向上节点信息更新为当前节点信息
//            newNode.preNode=currNode;
//
//            //将新节点向下相依节点信息更新为头节点
//            newNode.nextNode=headNode;
//
//            //将当前节点向下相依节点信息更新为新节点
//            currNode.nextNode=newNode;
//
//        //新节点在中间怎么处理
//        }else{
//            //将新节点向下相依节点信息更新为当前节点向下节点
//            newNode.nextNode=currNode.nextNode;
//
//            //将新节点向上相依节点信息更新为当前节点
//            newNode.preNode=currNode;
//
//            //将当前节点向下相依节点的向上相依节点信息更新为新节点
//            currNode.nextNode.preNode=newNode;
//
//            //将当前节点向下相依节点更新为新节点
//            currNode.nextNode=newNode;
//        }
        //不带头节点
        /**
         * 思考4个问题
         *      1:整个链表为空时候怎么处理
         *      2:当前新节点为最小的时候怎么处理
         *      3：在中间的时候怎么处理
         *      4：末尾的时候怎么处理
         */

        while(currNode.nextNode!=null){
            //获取当前节点的向下相依节点
            temp=currNode.nextNode;

            if((Integer)temp.objValue>(Integer)objValue){
                break;
            }

            //更新当前节点信息
            currNode=temp;
            if(currNode.nextNode==headNode.nextNode){
                //代表已经到最后一个了所以无需循环跳出即可
                break;
            }
        }
        //整个链表为空时候怎么处理
        if(currNode.nextNode==null){
            //将新节点的向上相依节点信息更新为本身
            newNode.preNode=newNode;

            //将新节点的向下相依节点信息更新问本身
            newNode.nextNode=newNode;

            //将新节点赋值给当前节点向下相依节点信息
            currNode.nextNode=newNode;


        //当前节点为最小节点时候怎么处理
        }else if(currNode==headNode){

            //获取末尾节点
            DoubleNode lastNode=headNode;
            while(lastNode.nextNode!=null){
                //获取当前节点的向下相依节点
                temp=lastNode.nextNode;
                //更新当前节点信息
                lastNode=temp;
                if(currNode.nextNode==headNode.nextNode){
                    //代表已经到最后一个了所以无需循环跳出即可
                    break;
                }
            }
            //将末尾节点的向下相依节点更新为新节点
            lastNode.nextNode=newNode;

            //将新节点向上节点信息更新为lastNode
            newNode.preNode=lastNode;

            //将新节点向下节点信息更新为当前节点向下相依节点信息
            newNode.nextNode=currNode.nextNode;

            currNode.nextNode.preNode=newNode;

            //将当前节点信息向下相依节点信息更新为新节点信息
            currNode.nextNode=newNode;


        //当节点在末尾的时候怎么处理
        }else if(currNode.nextNode==headNode.nextNode){
            //将新节点向下节点信息更新为头节点第一个节点信息
            newNode.nextNode=currNode.nextNode;

            //将新节点向上节点信息更新为 当前节点信息
            newNode.preNode=currNode;

            //将当前节点向下节点信息更新为新节点信息
            currNode.nextNode=newNode;

        //节点在中间的时候怎么处理
        }else{
            //将新节点向上节点信息更新为当前节点信息
            newNode.preNode=currNode;

            //将新节点向下节点信息更新为当前节点信息向下节点
            newNode.nextNode=currNode.nextNode;

            //将当前节点向下节点信息向上节点信息更新为新节点
            currNode.nextNode.preNode=newNode;

            //将当前节点想下节点信息更新为新节点
            currNode.nextNode=newNode;
        }
    }




}

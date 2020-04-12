package com.king2.link;

public class JosephDemo {
    public static void main(String[] args) throws Exception {
        JosephLinkList list=new JosephLinkList();
        list.cycleOneWayLink(1);
        list.cycleOneWayLink(3);
        list.cycleOneWayLink(4);
        list.cycleOneWayLink(2);
        list.cycleOneWayLink(5);
        list.cycleOneWayLink(6);
        list.cycleOneWayLink(7);
        list.cycleOneWayLink(8);
        list.cycleOneWayLink(9);
        list.cycleOneWayLink(10);
        list.cycleOneWayLink(11);
        list.cycleOneWayLink(12);
        list.cycleOneWayLink(13);
        list.cycleOneWayLink(14);
        list.cycleOneWayLink(15);
        list.cycleOneWayLink(16);
        list.cycleOneWayLink(17);
        list.cycleOneWayLink(18);
        list.cycleOneWayLink(19);
        list.cycleOneWayLink(20);
        list.cycleOneWayLink(21);
        list.cycleOneWayLink(22);
        list.cycleOneWayLink(24);
        list.cycleOneWayLink(23);
        list.cycleOneWayLink(25);
        list.Joseph(2);
        System.out.println("============");
        list.showNode();
    }
}


class JosephLinkList{
    //创建一个头节点信息
    MyNode headNode=new MyNode(null);


    public void showNode() throws Exception {
        //获取头节点信息
        MyNode currNode = headNode;

        //创建一个临时遍历,存储当前节点向下相依节点信息
        MyNode temporary = null;

        //遍历当前整个链表
        while (currNode.nextNode != null) {
            //获取当前节点向下相依节点
            temporary = currNode.nextNode;

            System.out.println(temporary);
            Thread.sleep(300);
            //更新当前节点信息
            currNode = temporary;
        }

    }




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 21:52
     * 描述: 实现单向循环排序链表
     ******************************/
    public void cycleOneWayLink(Object objValue){
        //获取头节点信息
        MyNode currNode= headNode;

        //创建一个临时变量用户存储当前节点向下相依节点信息
        MyNode temp=null;

        //将新添加的内容封装到一个新的节点中
        MyNode newNode=new MyNode(objValue);

        //循环遍历整个链表
        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            if((Integer)temp.objectValue>(Integer)objValue){
                break;
            }

            //更新当前节点信息
            currNode=temp;

            if(currNode.nextNode==headNode.nextNode){
                //代表已经到达了末尾跳出即可
                break;
            }
        }

        //链表为空的时候怎么处理
        if(currNode.nextNode==null){

            newNode.nextNode=newNode;
            currNode.nextNode=newNode;

        //当新节点为最小时候怎么处理
        }else if(currNode==headNode){
            //获取最后节点
            MyNode lastNode=headNode;
            while(lastNode.nextNode!=null){
                temp=lastNode.nextNode;
                lastNode=temp;
                if(lastNode.nextNode==headNode.nextNode){
                    break;
                }
            }

            lastNode.nextNode=newNode;
            newNode.nextNode=currNode.nextNode;
            currNode.nextNode=newNode;

        //当为最后一个节点怎么处理
        }else if(currNode.nextNode==headNode.nextNode){
            newNode.nextNode=headNode.nextNode;
            currNode.nextNode=newNode;

        //中间的时候怎么处理
        }else{
            newNode.nextNode=currNode.nextNode;
            currNode.nextNode=newNode;
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 8:53
     * 描述: 单向循环链表实现约瑟夫问题
     ******************************/
    public void Joseph(Integer number){
        MyNode currNode=headNode;
        MyNode temp=null;
        Integer countNumber=0;

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点
            temp=currNode.nextNode;

            countNumber++;

            if(countNumber!=0&&countNumber%number==0){

                //当链表中只有最后一个有效节点怎么处理
                if(temp.nextNode==temp){
                    System.out.println("最后一个是："+temp.objectValue);
                    break;
                }

                System.out.println(temp.objectValue);

                //当节点是第一个节点的时候怎么处理
                if(temp==headNode.nextNode){
                    //获取尾节点信息
                    MyNode lastNode=headNode;

                    while(lastNode.nextNode!=null){
                        //更新当前节点
                        lastNode=lastNode.nextNode;
                        //判断是否是最后一个
                        if(lastNode.nextNode==headNode.nextNode){
                            break;
                        }
                    }
                    //更新为元素的向下相依节点
                    lastNode.nextNode=temp.nextNode;

                    //将头节点的向下相依节点信息更新为当前节点向下相依节点
                    headNode.nextNode=temp.nextNode;

                //中间和末尾怎么处理
                }else{
                    currNode.nextNode=currNode.nextNode.nextNode;
                }
            }
            //更新当前节点信息
            currNode=temp;
        }
    }

}
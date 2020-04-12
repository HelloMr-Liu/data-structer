package com.king2.link;

public class LiuMyLinkListDemo {

    public static void main(String[] args) throws Exception {
//        LiuMyLinkList list1=new LiuMyLinkList();
//        list1.addNode(1);
//        list1.addNode(4);
//        list1.addNode(5);
//        list1.addNode(2);
//        list1.addNode(3);
//        System.out.println("===================未排序的遍历=============");
//        list1.showNode();
//        System.out.println("=================有效节点个数："+ list1.getLength());
//
//        LiuMyLinkList list2=new LiuMyLinkList();
//        list2.addNodeOrderBy(1);
//        list2.addNodeOrderBy(4);
//        list2.addNodeOrderBy(5);
//        list2.addNodeOrderBy(2);
//        list2.addNodeOrderBy(3);
//       // list2.deleteNode(4);
//        System.out.println("===================排完序的遍历=============");
//        list2.showNode();
//        System.out.println("=================有效节点个数："+ list2.getLength());
//        //System.out.println("==============获取倒数第四个："+(list2.searchLastNode(5)));
//        System.out.println("=================反转后1==============");
//        list2.reverseNode();
//        list2.showNode();
//        System.out.println("=================从未到头打印不破坏链表结构==============");
//        list2.showNodeLastAndHead();
//
//
//        LiuMyLinkList list3=new LiuMyLinkList();
//        list3.addNodeOrderBy(1);
//        list3.addNodeOrderBy(4);
//        list3.addNodeOrderBy(5);
//
//        LiuMyLinkList list4=new LiuMyLinkList();
//        list4.addNodeOrderBy(6);
//        list4.addNodeOrderBy(7);
//        list4.addNodeOrderBy(8);
//        LiuMyLinkList.mergeLink(list3,list4);
//        System.out.println("===============合并后=============");
//        list3.showNode();

        LiuMyLinkList list5=new LiuMyLinkList();
        list5.cycleOneWayLink(2);
        list5.cycleOneWayLink(4);
        list5.cycleOneWayLink(3);
        list5.cycleOneWayLink(1);
        list5.showNode();

    }
}


class LiuMyLinkList{


    //创建一个头节点
    MyNode headNode=new MyNode(null);



    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 12:53
     * 描述: 显示节点信息方法
     ******************************/
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
     * 时间: 2019/12/10 9:59
     * 描述: 普通添加节点内容（有序）
     ******************************/
    public void addNode(Object objValue){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时变量，存储当前节点的下一个相依节点
        MyNode temporary=null;

        //将添加的内容封装到一个新的节点上
        MyNode newNode=new MyNode(objValue);

        //循环整个链表
        while(currNode.nextNode!=null){

            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            //更新当前节点信息
            currNode=temporary;
        }

        //将当前节点向下相依节点信息更新为新节点信息
        currNode.nextNode=newNode;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:00
     * 描述: 添加新内容(排序)
     ******************************/
    public void addNodeOrderBy(Object objValue){
        //获取头节点信息(牵引整个链表第一个有效节点信息)
        MyNode currNode=headNode;

        //创建一个临时遍历，存储当前节点向下相依节点信息
        MyNode temporary=null;

        //将新添加的内容封装到新节点中
        MyNode newNode=new MyNode(objValue);

        //循环遍历整个链表
        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            //将temporary中的内容与新添加内容比较
            if((Integer)temporary.objectValue>(Integer)objValue){
                break;
            }

            //更新当前节点信息
            currNode=temporary;
        }

        if(currNode.nextNode==null){
            //将新节点信息更新在当前节点向下相依节点上
            currNode.nextNode=newNode;
        }else{
            //将当前节点向下相依节点信息赋值给新节点向下相依节点信息上
            newNode.nextNode=currNode.nextNode;

            //将将新节点赋值给当前节点向下相依节点上
            currNode.nextNode=newNode;
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:12
     * 描述: 删除链表中的一个节点
     ******************************/
    public void deleteNode(Object objValue){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时变量存储带你节点向下相依节点信息
        MyNode temporay=null;

        //创建一个标志用于是否真正的删除了指定节点
        boolean flag=false;

        //循环遍历整个链表
        while(currNode.nextNode!=null){
            //获取当前节点信息向下相依节点信息
            temporay=currNode.nextNode;

            if((Integer)temporay.objectValue==(Integer) objValue){
                flag=true;
                break;
            }

            //更新当前节点信息
            currNode=temporay;
        }
        if(flag){
            //将当前节点向下相依节点的向下相依节点信息赋值给在当前节点向下相依节点上
            currNode.nextNode=currNode.nextNode.nextNode;
        }else{
            System.out.println("没有指定的节点"+objValue+"要删除,删除失败");
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:22
     * 描述: 获取整合链表中的有效节点个数
     ******************************/
    public Integer getLength(){
        //获取头节点(牵引整个链表中第一个有效节点信息)
        MyNode currNode=headNode;

        //创建一个统计变量,统计出整个链表中有效节点个数
        Integer countNumber=0;

        //创建一个临时遍历,存储当前节点向下相依节点信息
        MyNode temporary=null;

        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            countNumber++;

            //更新当前节点信息
            currNode=temporary;
        }
        return countNumber;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:28
     * 描述: 查找单链表中的倒数第K个节点
     ******************************/
    public Object searchLastNode(Integer number){
        //获取当前整个链表的有效节点个数
        Integer countNumber=getLength();

        if(countNumber==0){
            throw  new RuntimeException("当前链表中没有有效节点信息无法查询获取");
        }else if(number<=0||number>countNumber){
            throw  new RuntimeException("当前指定的倒数数值不合法");
        }

        //获取当前头节点(牵引整个链表中第一个有效节点)
        MyNode currNode=headNode;

        //创建一个临时遍历,存储当前节点向下相依节点信息
        MyNode temp=null;

        for(int index=0;index<=(countNumber-number);index++){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            //更新当前节点信息
            currNode=temp;
        }
        return currNode.objectValue;

    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:37
     * 描述: 对单链表实现反转
     ******************************/
    public void reverseNode(){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时遍历存储当前节点向下相依节点
        MyNode temporary=null;

        //获取当前节点第一个有效节点
        currNode=currNode.nextNode;

        //创建一个辅助节点用存储反转节点后信息
        MyNode helpNode=new MyNode(null);

        while(currNode!=null){
            //获取当前节点向下相依节点信息存储到临时变量上
            temporary=currNode.nextNode;


            //将辅助节点向下相依节点信息赋值给当前节点向下相依节点上
            currNode.nextNode=helpNode.nextNode;

            //将当前节点信息赋值给辅助节点向下相依节点上
            helpNode.nextNode=currNode;

            //更新当前节点信息
            currNode=temporary;
        }
        //将辅助节点信息更新在头节点信息上
        headNode.nextNode=helpNode.nextNode;
    }

    
    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:45
     * 描述: 从尾到头打印链表信息并且不破坏链表原结构
     ******************************/
    public void showNodeLastAndHead(){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时变量存储当前节点向下相依节点信息
        MyNode temporary=null;

        //创建一个栈对象
        ArrayStack as=new ArrayStack(getLength());

        //循环遍历整个链表
        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temporary=currNode.nextNode;

            //将当前向下相依节点信息存储在栈中
            as.push((Integer) temporary.objectValue);
            //更新当前节点信息
            currNode=temporary;
        }

        //遍历栈中的内容
        while(!as.isNull()){
            System.out.println("打印栈中的内容："+as.pop());
        }

    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 13:51
     * 描述: 合并两个有序链表 将链表2中的内容合并在链表1中
     ******************************/
    public static void mergeLink(LiuMyLinkList mergeLink1,LiuMyLinkList mergeLink2){
        if(mergeLink1.headNode.nextNode==null||mergeLink2.headNode.nextNode==null){
            System.out.println("无需合并");
            return;
        }

        //获取链表1的头节点信息
        MyNode currNode1=mergeLink1.headNode;
        //创建链表1的临时变量
        MyNode temp1=null;


        //获取链表2中的头节点信息
        MyNode currNode2=mergeLink2.headNode;
        //创建链表2的临时变量
        MyNode temp2=null;
        //获取链表2中的第一个有效节点
        currNode2=currNode2.nextNode;

        //循环遍历链表2中的节点
        while(currNode2!=null){
            //获取链表2中的当前节点的向下相依节点
            temp2=currNode2.nextNode;

            //重新将当前链表1中的节点更新为链表1头节点
            currNode1=mergeLink1.headNode;

            //循环遍历链表1中的内容
            while(currNode1.nextNode!=null){
                //获取链表1中的当前节点向下相依节点上
                temp1=currNode1.nextNode;

                //将temp1中的内容与currNode2中的内容比较
                if((Integer)temp1.objectValue>(Integer) currNode2.objectValue){
                    break;
                }
                //更新链表1中的当前节点信息
                currNode1=temp1;
            }
            //将当前节点1中向下相依节点信息赋值给当前节点2向下相依节点上
            currNode2.nextNode=currNode1.nextNode;

            //将当前节点1向下相依节点更新为当前节点2上
            currNode1.nextNode=currNode2;

            //更新链表2中的当前节点
            currNode2=temp2;
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/10 14:19
     * 描述: 循环单向添加链表 不带头节点/和带头节点信息
     ******************************/
    public void cycleOneWayLink(Object objValue){
        //获取头节点信息
        MyNode currNode=headNode;

        //创建一个临时变量用于存储当前节点向下相依节点上
        MyNode temp=null;

        //将当前新添加的内容封装到一个新的节点上
        MyNode newNode=new MyNode(objValue);


//        //带头节点方式
//        while(currNode.nextNode!=null&&currNode.nextNode!=headNode){
//            //获取当前节点向下相依节点信息
//            temp=currNode.nextNode;
//
//            if((Integer)temp.objectValue>(Integer)objValue){
//                break;
//            }
//            //更新当前节点信息
//            currNode=temp;
//        }
//
//        if(currNode.nextNode==null||currNode.nextNode==headNode){
//            //将新节点向下相依节点中的内容更新为头节点信息
//            newNode.nextNode=headNode;
//
//            //将新节点赋值给当前节点向下相依节点上
//            currNode.nextNode=newNode;
//
//        }else{
//            //将当前节点向下相依节点的信息赋值给新节点向下相依节点上
//            newNode.nextNode=currNode.nextNode;
//
//            //将新节点信息赋值给 当前节点向下相依节点上
//            currNode.nextNode=newNode;
//        }

        //不带头节点方式
        while(currNode.nextNode!=null){
            //获取当前节点向下相依节点信息
            temp=currNode.nextNode;

            if((Integer)temp.objectValue>(Integer)objValue){
                break;
            }
            //更新当前节点信息
            currNode=temp;
            if(currNode.nextNode==headNode.nextNode){
                //代表已经循环到最后一个了且没有匹配成功 跳出
                break;
            }
        }

        //表示一开始都没有值考虑步骤
        if(currNode.nextNode==null){
            //将当前新节点向下相依节点的内容值为本身
            newNode.nextNode=newNode;

            //将新节点赋值给当前节点的向下相依节点上
            currNode.nextNode=newNode;

        //表示当前新节点是最小的节点考虑步骤
        }else if(currNode==headNode){

            //提示：表示当前的新添加内容为最小内容所以要对末尾的节点向下相依节点地址要更改才当前新节点

            //获取末尾节点信息
            MyNode lastNode=headNode;
            while(lastNode.nextNode!=null){
                //获取当前节点向下相依节点信息
                temp=lastNode.nextNode;

                //更新当前节点信息
                lastNode=temp;
                if(lastNode.nextNode==headNode.nextNode){
                    //代表已经循环到最后一个了且没有匹配成功 跳出
                    break;
                }
            }
            //更新末尾节点中向下相依节点的地址
            lastNode.nextNode=newNode;

            //将当前新节点向下相依节点内容更新为当前节点向下相依节点内容
            newNode.nextNode=currNode.nextNode;
            //将当前新节点中的内容赋值给当前节点向下相依节点信息
            currNode.nextNode=newNode;

        //表示最后一个内容
        }else if(currNode.nextNode==headNode.nextNode){
            //将新节点中的向下相依节点信息地址跟新为当前节点向下相依节点信息
            newNode.nextNode=currNode.nextNode;

            //将当前节点向下相依节点信息更新为新节点信息
            currNode.nextNode=newNode;

        }else{
            //将当前新节点向下相依节点内容更新为当前节点向下相依节点内容
            newNode.nextNode=currNode.nextNode;
            //将当前新节点中的内容赋值给当前节点向下相依节点信息
            currNode.nextNode=newNode;
        }

    }

}
import com.king2.link.ArrayStack;
import com.king2.link.MyNode;

/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/06 15:05
 * 描述: 链表
 ******************************/
public class MyLinkList {


    //创建头节点(用于牵引整个链表的第一个节点信息)
    MyNode headNode=new MyNode(null);




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:07
     * 描述: 普通添加节点(未排序)
     ******************************/
    public void addLink(Object objValue){
        //获取头节点
        MyNode  currNode=headNode;


        //创建一个节点对象
        MyNode newNode=new MyNode(objValue);


        //创建一个变量用于存储当前节点信息
//        MyNode temporary=null;


        while(currNode.nextNode!=null){
            //获取当前节点的下一个相依节点信息
            currNode=currNode.nextNode;
        }
        currNode.nextNode=newNode;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:07
     * 描述: 普通添加节点(排序)
     ******************************/
    public void addLinkOrderBy(Object objValue){
        //获取头结点
        MyNode currNode=headNode;
        //创建一个节点对象
        MyNode newNode=new MyNode(objValue);


        //创建一个变量用于存储当前节点信息
        MyNode temporary=null;


        while(currNode.nextNode!=null){
            temporary=currNode.nextNode;
            if((Integer)temporary.objectValue>(Integer)objValue){
                break;
            }
            //更新当前节点信息
            currNode=temporary;


        }


        if(currNode.nextNode==null){
            currNode.nextNode=newNode;


        }else{
            //将当前节点的下一个相依节点给新节点的相依节点
            newNode.nextNode=currNode.nextNode;
            currNode.nextNode=newNode;
        }
    }




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:23
     * 描述: 删除指定节点
     ******************************/
    public void delete(Object delValue){
        //获取头节点信息
        MyNode currNode=headNode;


        //创建一个变量用于存储当前节点信息
        MyNode temporary=null;


        while(currNode.nextNode!=null){
            //获取当前节点,对应的下一个相依节点
            temporary=currNode.nextNode;
            if((Integer)temporary.objectValue==(Integer)delValue){
                break;
            }
            //更新当前节点信息
            currNode=temporary;
        }
        if(currNode.nextNode==null){
            throw new RuntimeException("没有对应的链表信息,无法删除");
        }else{
            currNode.nextNode=currNode.nextNode.nextNode;
        }
    }




    //显示队列信息
    public void showLink(){
        //获取头节点
        MyNode currNode=headNode;


        //创建一个临时变量用于存储每一个节点的下一个相依节点信息
        MyNode temporary=null;


        while(currNode.nextNode!=null){
            temporary=currNode.nextNode;
            System.out.println(temporary);


            //更新当前节点
            currNode=temporary;
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:36
     * 描述: 计算单链表中的有效节点个数
     ******************************/
    public Integer getLength(){


        //获取头结点信息
        MyNode currNode=headNode;


        //创建一个临时变量,用于存储当前节点的下一个相依节点
        MyNode temporary=null;


        //创建一个变量统计有效个数
        Integer countNumber=0;


        while(currNode.nextNode!=null){
            //获取当前节点的下一个相依节点
            temporary=currNode.nextNode;
            countNumber++;
            //更新当前节点信息
            currNode=temporary;
        }
        return   countNumber;
    }




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:42
     * 描述: 查找单链表中的倒数第K个节点
     ******************************/
    public Integer lastNode(Integer lastNumber){


        //获取当前链表的有效个数
        Integer countNumber=getLength();


        //判断当前值是否合法
        if(countNumber==0){
            throw  new  RuntimeException("链表为空,无法取数据");
        }else if(lastNumber<=0||lastNumber>countNumber){
            throw  new  RuntimeException("当前你指定的个数不合法");
        }


        //获取头节点
        MyNode currNode=headNode;


        //创建一个临时变量用于存储当前节点的下一个相依节点
        MyNode temporary=null;


        for(int index=0;index<=(countNumber-lastNumber);index++){
            //存储当前节点的下一个相依节点
            temporary=currNode.nextNode;


            //跟新当前节点
            currNode=temporary;
        }
        return  (Integer)currNode.objectValue;
    }




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 15:58
     * 描述: 对单链表实现反正
     ******************************/
    public void reverseNode(){


        //判断链表是否为空
        if(headNode.nextNode==null){
            System.out.println("链表为空");
            throw new RuntimeException("链表为空");
        }




        //获取第一个当前节点
        MyNode currNode=headNode.nextNode;


        //创建一个辅助节点,将翻转好的节点存储于辅助节点上
        MyNode helpNode=new MyNode(null);


        //创建一个临时变量用于存储,当前节点的下一个相依节点
        MyNode temporary=null;


        while(currNode!=null){


            //获取当前节点的下一个相依节点
            temporary=currNode.nextNode;


            //将辅助节点的下一个相依节点赋值给当前节点的下一个相依节点
            currNode.nextNode=helpNode.nextNode;
            //将当前节点的信息赋值给辅助节点的下一个相依节点
            helpNode.nextNode=currNode;


            //对当前节点变量指定到下一个节点信息
            currNode=temporary;
        }
        //将辅助节点的相依节点更新在头结点的下一个相依节点上
        headNode.nextNode=helpNode.nextNode;
    }




    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 16:11
     * 描述: 从尾到头打印链表信息并且不破坏链表原结构
     ******************************/
    public void showNodeLastAndHead(){


        //判断链表是否为空
        if(headNode.nextNode==null){
            throw new RuntimeException("整个链表为空");
        }


        //创建一个栈结构的对象
        ArrayStack as=new ArrayStack(getLength());


        //获取头节点信息
        MyNode currNode=headNode;


        //创建一个临时变量用于存储当前节点的下一个节点信息
        MyNode temporary=null;


        while(currNode.nextNode!=null){


            //获取当前节点的下一个相依节点信息
            temporary=currNode.nextNode;


            as.push((Integer) temporary.objectValue);


            //更新当前节点信息
            currNode=temporary;
        }


        //打印栈中的内容
        while(!as.isNull()){
            System.out.println(as.pop());
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 16:26
     * 描述: 合并两个有序链表
     ******************************/
    public static  void mergeLink(MyLinkList mergeLink,MyLinkList targetLink){


        //判断当前2个链表其中是否无有效节点
        if(mergeLink.headNode.nextNode==null||targetLink.headNode.nextNode==null){
            throw new RuntimeException("当前链表中的内容已经为空");
        }

        //获取mergeLink的头节点
        MyNode mergeNode=mergeLink.headNode;
        //创建临时变量存储mergeNode节点的下一个相依节点信息
        MyNode temp1=null;


        //获取targetLink的头节点
        MyNode targetNode=targetLink.headNode;
        //创建临时变量存储targetNode节点的下一个相依节点信息
        MyNode temp2=null;


        //获取mergeNode的第一个有效节点信息
        mergeNode=mergeNode.nextNode;
        while(mergeNode!=null){
            //获取当前mergeNode节点下一个相依节点信息
            temp1=mergeNode.nextNode;

            //重新刷新当前targetNode 从头节点信息开始
            targetNode=targetLink.headNode;

            while(targetNode.nextNode!=null){
                //获取targetNode节点的下一个相依节点信息
                temp2=targetNode.nextNode;

                //判断targetNode的下一个相依节点信息是否大于当前mergeNode的信息
                if((Integer)temp2.objectValue>(Integer)mergeNode.objectValue){
                    break;
                }

                //更新当前targetNode节点信息
                targetNode=temp2;
            }

            //将targetNode的下一个相依节点信息赋值给mergeNode的下一个相依节点上
            mergeNode.nextNode=targetNode.nextNode;

            //将当前mergeNode节点赋值给targetNode的下一个相依节点上
            targetNode.nextNode=mergeNode;

            //更新mergeNode节点
            mergeNode=temp1;
        }


    }


}






class MyLinkListDemo{


    public static void main(String[] args) {
/*
        MyLinkList ml=new MyLinkList();
        ml.addLink(1);
        ml.addLink(4);
        ml.addLink(2);
        ml.addLink(3);
        ml.addLink(5);
        ml.showLink();


        System.out.println("=========排序后的===============");
        MyLinkList ml2=new MyLinkList();
        ml2.addLinkOrderBy(1);
        ml2.addLinkOrderBy(4);
        ml2.addLinkOrderBy(2);
        ml2.addLinkOrderBy(3);
        ml2.addLinkOrderBy(5);
        ml2.showLink();
        System.out.println("===============有效个数============"+ml2.getLength());


//        System.out.println("删除3");
//        ml2.delete(3);
//        ml2.delete(1);
//        ml2.delete(2);
//        ml2.delete(4);
//        ml2.delete(5);
//        ml2.showLink();
//        System.out.println("===============有效个数============"+ml2.getLength());


        System.out.println("==============获取倒数第2个=============="+ml2.lastNode(2));


        System.out.println("链表翻转第一次");
        ml2.reverseNode();
        ml2.showLink();
        System.out.println("链表翻转第二次");
        ml2.reverseNode();
        ml2.showLink();


        System.out.println("=================从尾到头打印节点信息============");
        ml2.showNodeLastAndHead();


*/


        MyLinkList list1=new MyLinkList();
        list1.addLinkOrderBy(6);
        list1.addLinkOrderBy(1);
        System.out.println("链表1的排序后的内容===================");
        list1.showLink();


        MyLinkList list2=new MyLinkList();
        list2.addLinkOrderBy(3);
        list2.addLinkOrderBy(5);
        list2.addLinkOrderBy(8);
        list2.addLinkOrderBy(4);
        list2.addLinkOrderBy(7);
        System.out.println("链表2的排序后的内容===================");
        list2.showLink();


        System.out.println("===========将链表2的内容合并到链表1中，并再次查看链表1中的内容");
        MyLinkList.mergeLink(list1,list2);
        list1.showLink();






    }


}
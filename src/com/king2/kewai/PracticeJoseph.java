package com.king2.kewai;


//实现约瑟夫问题

//Josephus有过的故事：39 个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓。
//于是决定了自杀方式，41个人排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀。
//然后下一个重新报数，直到所有人都自杀身亡为止。然而Josephus 和他的朋友并不想遵从，
//Josephus要他的朋友先假装遵从，他将朋友与自己安排在第16个与第31个位置，于是逃过了这场死亡游戏
public class PracticeJoseph {

    public static void main(String[] args) {

        int numberCount=0;
        int number=41;
        //使用双向循环排序链表的
        DoubleLinked link=new DoubleLinked();
        for(int index=1;index<=number;index++){
            link.addNodeOrderBy(index);
        }


        //获取当前链表头节点信息
        DoubleLinkedNode currNode=link.headNode;
        while(currNode.nextNode!=null){
            DoubleLinkedNode temp=currNode.nextNode;
            numberCount++;

            if(numberCount==3){
                if(number--<3){
                    break;
                }
                System.out.println(temp.currData);

                //判断当前循环链表是否是最后一个自身自己
                if(temp.nextNode==temp){
                    break;
                //当前节点为第一个的时候怎么删除处理
                }else if(temp==link.headNode.nextNode){
                    //获取对应的末尾节点
                    DoubleLinkedNode lastNode=link.headNode;
                    while(lastNode.nextNode!=null){

                        lastNode=lastNode.nextNode;
                        if(lastNode.nextNode==link.headNode.nextNode){
                            break;
                        }

                    }
                    //更新末尾节点信息
                    lastNode.nextNode=temp.nextNode;

                    temp.nextNode.preNode=lastNode;

                    //更新头节点的指向
                    link.headNode.nextNode=temp.nextNode;
                //最后一个和中间怎么删除及处理
                }else
                {
                    currNode.nextNode=temp.nextNode;
                    temp.nextNode.preNode=currNode;
                }
                numberCount=0;
            }
            currNode=temp;
        }
    }
}

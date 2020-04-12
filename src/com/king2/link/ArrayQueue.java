package com.king2.link;


/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/06 14:06
 * 描述: 数组队列
 ******************************/
public class ArrayQueue {

    public Integer maxSize;     //最大的队列存储长度
    public Integer headPoint;   //队列头指针 用于取数据用
    public Integer lastPoint;   //队列尾指针 用于存数据用
    public Integer array[];     //队列容器

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:09
     * 描述: 初始化队列信息
     ******************************/
    public ArrayQueue(Integer initMaxSzie){
        this.maxSize=initMaxSzie;
        this.headPoint=0;
        this.lastPoint=-1;
        array=new Integer[this.maxSize];
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:10
     * 描述: 判断队列是否为满
     ******************************/
    public boolean isFull(){
        return this.lastPoint==this.maxSize-1;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:12
     * 描述: 判断队列是否为null
     ******************************/
    public boolean isNull(){
        return this.headPoint>this.lastPoint;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:13
     * 描述: 存储队列
     ******************************/
    public void addQueue(Integer value){

        //判断队列是否为满
        if(this.isFull()){
            System.out.println("队列已经为满");
            throw new RuntimeException("队列未满,无法存储数据");
        }
        //对当前的尾指针进行自增
        this.lastPoint++;
        this.array[this.lastPoint]=value;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:15
     * 描述: 获取对列信息吧
     ******************************/
    public Integer getQueue(){

        //判断队列是否为null
        if(this.isNull()){
            System.out.println("队列为空");
            throw new RuntimeException("队列为空,无法取数据");
        }
        return this.array[this.headPoint++];
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:22
     * 描述: 显示有效队列
     ******************************/
    public void showQueue(){
        for(int index=this.headPoint;index<=this.lastPoint;index++){
            System.out.println("有效队列元素:"+this.array[index]);
        }
    }

}

class ArrayQueueDemo{

    public static void main(String[] args) {
        ArrayQueue aq=new ArrayQueue(5);
        aq.addQueue(2);
        aq.addQueue(3);
        aq.addQueue(3);
        aq.addQueue(3);
        aq.addQueue(3);

        aq.showQueue();
        System.out.println("取完数据队列信息");
        aq.getQueue();
        aq.getQueue();
        aq.getQueue();
        aq.getQueue();
        aq.getQueue();
        aq.getQueue();
        aq.showQueue();
    }

}

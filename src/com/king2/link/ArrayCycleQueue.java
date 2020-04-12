package com.king2.link;


/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/06 14:25
 * 描述: 数组循环队列
 ******************************/
public class ArrayCycleQueue {


    public Integer maxSize;     //最大的队列存储长度
    public Integer headPoint;   //队列头指针 用于取数据用
    public Integer lastPoint;   //队列尾指针 用于存数据用
    public Integer array[];     //队列容器

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:29
     * 描述: 初始化队列容器
     ******************************/
    public ArrayCycleQueue(Integer initMaxSize){

        this.maxSize=initMaxSize;
        this.headPoint=0;
        this.lastPoint=0;
        this.array=new Integer[this.maxSize];

    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:34
     * 描述: 判断整个队列是否为满
     ******************************/
    public boolean isFull(){
        return (this.lastPoint+1)%this.maxSize==this.headPoint;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:36
     * 描述:
     ******************************/
    public boolean isNull(){
        return this.headPoint==this.lastPoint;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:37
     * 描述: 存储队列
     ******************************/
    public void addQueue(Integer value){

        //判断是否未满
        if(this.isFull()){
            System.out.println("队列已经为满");
            throw new RuntimeException("队列未满,无法存储数据");
        }
        this.array[this.lastPoint]=value;

        //存储玩当前元素后对当前尾指针进行更新
        this.lastPoint=(this.lastPoint+1)%this.maxSize;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:42
     * 描述: 取队列信息
     ******************************/
    public Integer getQueue(){
        //判断整个队列是否为空
        if(isNull()){
            System.out.println("队列为空");
            throw new RuntimeException("队列为空,无法取数据");
        }

        Integer val=this.array[this.headPoint];
        //更新当前的头指针
        this.headPoint=(this.headPoint+1)%maxSize;
        return val;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/06 14:47
     * 描述: 统计队列的有效个数
     ******************************/
    public Integer getLength(){
        return (lastPoint+maxSize-headPoint)%maxSize;
    }

}

class ArrayCycleQueueDemo{

    public static void main(String[] args) {
        ArrayCycleQueue acq=new ArrayCycleQueue(5);
        acq.addQueue(1);
        acq.addQueue(2);
        acq.addQueue(3);
        acq.addQueue(4);
        System.out.println("取出："+acq.getQueue());
        acq.addQueue(5);
        System.out.println("取出："+acq.getQueue());
        System.out.println("取出："+acq.getQueue());
        System.out.println("取出："+acq.getQueue());
        System.out.println("取出："+acq.getQueue());


        System.out.println("有效个数"+acq.getLength());


    }

}

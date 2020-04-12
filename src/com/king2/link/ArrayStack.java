package com.king2.link;


/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/06 14:06
 * 描述: 数组栈
 ******************************/
public class ArrayStack {

    public Integer maxSize;
    public Integer lastPoint;
    public Integer array[];

    public  ArrayStack(Integer initMaxSize){
        this.maxSize=initMaxSize;
        this.lastPoint=-1;
        this.array=new Integer[this.maxSize];
    }


    public boolean isNull(){
       return  lastPoint==-1;
    }
    public boolean isFull(){
        return lastPoint==maxSize-1;
    }

    public void push(Integer value){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        //更新当前指针
        this.lastPoint++;
        this.array[this.lastPoint]=value;
    }

    public Integer pop(){
        if(isNull()){
            System.out.println("队列为空");
            throw  new RuntimeException("队列为空");
        }
        //更新当前指针
        return this.array[this.lastPoint--];
    }
}

class ArrayStackDemo{

    public static void main(String[] args) {

        ArrayStack as=new ArrayStack(5);
        as.push(1);
        as.push(2);
        as.push(3);
        as.push(4);

        System.out.println("取出："+as.pop());
        System.out.println("取出："+as.pop());
        System.out.println("取出："+as.pop());
        System.out.println("取出："+as.pop());
    }

}

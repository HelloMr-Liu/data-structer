package com.king2.stack;


/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/11 15:51
 * 描述: 数组栈演示
 ******************************/
public class MyStackDemo {
    public static void main(String[] args) {
        MyStack ms=new MyStack(5);
        ms.push(1);
        ms.push(2);
        ms.push(3);
        ms.push(4);
        ms.push(5);
        System.out.println("出栈一次"+ms.pop());
        ms.showStack();
    }
}


class MyStack{

    private Integer maxSize;  //最大栈容量

    private Integer topPoint; //扎顶指针

    private Integer [] array; //栈容器

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:53
     * 描述: 初始化容器
     ******************************/
    public MyStack(Integer initMaxSize){

        this.maxSize=initMaxSize;
        this.topPoint=-1;
        this.array=new Integer[this.maxSize];
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:53
     * 描述: 压栈的时候判断是否为满
     ******************************/
    public boolean isFull(){
        return this.topPoint==this.maxSize-1;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:54
     * 描述: 出栈的时候判断是否为空
     ******************************/
    public boolean isNull(){
        return this.topPoint==-1;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:55
     * 描述: 压栈
     ******************************/
    public void push(Integer value){
        //判断是否为满
        if(isFull()){
            System.out.println("栈满");
            return;
        }

        this.topPoint++;
        this.array[this.topPoint]=value;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:56
     * 描述: 出栈
     ******************************/
    public Integer pop(){
        //判断是否为空
        if(isNull()){
            throw  new RuntimeException("栈空");
        }
        return  this.array[this.topPoint--];
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/11 15:58
     * 描述: 显示栈
     ******************************/
    public void showStack(){
        if(!isNull()){
            for(int index=topPoint;index>=0;index--){
                System.out.println("数据："+this.array[index]);
            }
        }else{
            System.out.println("没有数据无法显示");
        }

    }
}

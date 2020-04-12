
package com.king2.back;

public class MyBackDemo1 {


    public static void main(String[] args) {
        printBackTest(3);
        System.out.println("3的阶乘："+jieCheng(5));
    }

    //回溯打印阶乘结果
    public static int jieCheng(int n){

        if(n>1){
            return n*jieCheng(n-1);
        }
        return n;
    }



    //回溯打印测试
    public static  void printBackTest(int b){

        if(b>=1){
            //回溯自身
            printBackTest(b-1);
        }
        System.out.println("当前方法栈中的数值："+b);
    }




}

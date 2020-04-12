package com.king2.kewai;

public class PracticeDiamond {
    public static void main(String[] args) {
        diamondVersion2();
    }

    //打印菱形版本1
    public static void diamondVersion1(){

        //打印上一半菱形形状
        for(int index=1;index<=5;index++){

            //打印上部分的空格
            for(int index2=1;index2<=(5-index);index2++){
                System.out.print("\t");
            }
            //循环当前行对应的星星
            for(int index3=1;index3<=(index*2-1);index3++){
                System.out.print("☆\t");
            }
            System.out.println();
        }

        //打印下一半菱形
        for(int index4=1;index4<=4;index4++){

            //输出对应的空格
            for(int index5=1;index5<=index4;index5++){
                System.out.print("\t");
            }
            //输出当前行对应的值
            for(int index6=1;index6<=9-(index4*2);index6++){
                System.out.print("☆\t");
            }
            System.out.println();
        }
    }

    //打印菱形版本2
    public static void diamondVersion2(){

        for(int index=1;index<=9;index++){

            //输出对应的空格 这里要用到绝对值函数
            for(int index2=1;index2<=Math.abs(5-index);index2++){
                System.out.print("\t");
            }


            //输出对应每行的五角星
            for(int index3=1;index3<=9-Math.abs(5-index)*2;index3++){
                System.out.print("♥\t");
            }
            System.out.println();
        }


    }
}

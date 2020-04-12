package com.king2.search;


import java.util.Arrays;

//练习斐波那契数列
public class PracticeFeiBoNaQie {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getFeiBo(20)));
    }


    public static int [] getFeiBo(int topN){
        return diGuiVersion(2,topN,null);
    }

    public static int [] diGuiVersion(int n,int topN,int [] newArray){
        if(newArray==null){
            newArray=new int[topN];
            //创建斐波那契数组
            newArray[0]=1;
            newArray[1]=1;
        }
        int midValue=newArray[n-1]+newArray[n-2];
        if(n<topN){
            newArray[n]=midValue;
            //递归调用
            diGuiVersion(++n,topN,newArray);
        }
        return newArray;
    }

}


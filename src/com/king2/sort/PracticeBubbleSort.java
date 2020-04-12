package com.king2.sort;

import java.util.Arrays;

public class PracticeBubbleSort {

    public static void main(String[] args) {

        //创建一个未排好序的数组
        int[] newArray = new int[80];
        for (int i = 0; i < 80; i++) {
            newArray[i] = (int) (Math.random() * 8000);
        }

        long startTime=System.currentTimeMillis();
        bubbleSortVersion6(newArray);
        System.out.println(Arrays.toString(newArray));
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }

    //冒泡排序算法
    public static void bubbleSortVersion1(int [] arry){
        //创建一个临时变量用于临时存储交换的元素内容
        int temp=0;

        //创建外层循环用作与排序n-1次数
        for(int index=0;index<arry.length-1;index++){

            //创建内层循环,用于当前循环比较出一个最大值
            for(int index2=0;index2<arry.length-1-index;index2++){

                //如果当前值比下一个元素值大就进行交换位置
                if(arry[index2]>arry[index2+1]){

                    temp=arry[index2+1];
                    arry[index2+1]=arry[index2];
                    arry[index2]=temp;
                }
            }
        }
    }

    public static void bubbleSortVersion2(int [] arry){

        //创建一个临时变量用于临时存储交换的元素内容
        int temp=0;
        int count1=0;

        boolean flag=true;

        //创建外层循环用作与排序次数
        for(int index=0;index<arry.length-1;index++){

            //创建内层循环,用于当前循环比较出一个最大值 提示：由于每次循环当前元素要与下一个元素比较索引总长度-1 否者当前元素如果是最后一个在比较下一个元素就是索引越界异常
            for(int index2=0;index2<arry.length-1-index;index2++){
                count1++;
                //如果当前值比下一个元素值大就进行交换位置
                if(arry[index2]>arry[index2+1]){
                    flag=false;
                    temp=arry[index2+1];    //将后面元素值赋给临时变量
                    arry[index2+1]=arry[index2];//将前面值给后面元素
                    arry[index2]=temp;//将后面值给前面元素上
                }
            }

            //判断当前临时变量是否有变话
            if(flag){
                break; //说明整个数组都是排好序的无需在比较 跳出循环即可
            }else{
                flag=true;//再次更新当前标志用作与下次判断比较
            }

        }
        System.out.println(count1);
    }

    public static void bubbleSortVersion3(int [] sort){

        //创建一个临时变量用于存储对应的交换元素内容
        int tmep;

        //创建一个标志当一次循环比较下来一次都没有进行元素位置之间的交换就代表当前数组已经是排序好的
        boolean flag=true;

        //创建外循环用作用排序循环总长度-1次数
        for(int i=0;i<sort.length-1;i++){

            //创建内循环每次循环比较出一次最大值放在数组索引对应的末尾
            //注意：这里必须要在-1因为循环到末尾的时候一定是倒数第二个如果是倒数第一个元素在比较下一个元素就会索引越界异常
            for(int j=0;j<sort.length-i-1;j++){
                if(sort[j]<sort[j+1]){

                    tmep=sort[j+1];     //将后元素存储到临时变量
                    sort[j+1]=sort[j];  //将当前元素存储到后下一个索引位置上
                    sort[j]=tmep;       //将后元素内容存储到当前索引位置上

                    flag=false;
                }

            }

            if(flag){
                break; //代表当前一次循环比较下元素之间没有更换任何一个位置代表该数组已经是排好序了
            }else{
                flag=true;//用作于下次循环比较判断
            }
        }
    }


    //冒泡排序版本4
    public static void bubbleSortVersion4(int [] sort){

        //创建变量用作于2个元素进行之间的赋值交换
        int temp;

        //创建一个标志用作于判断是否已经是排序好的数组
        boolean flag=true;

        for(int i=0;i<sort.length-1;i++){

            //执行本次循环比较出指定索引元素范围内的最大值，由于是两两比较索引当前循环到是每次有效索引的第二个即可不然会出现索引越界
            for(int j=0;j<sort.length-1-i;j++){

                //如果当前索引元素大于当前索引+1元素就进行交换位置
                if(sort[j]>sort[j+1]){
                    temp=sort[j+1];
                    sort[j+1]=sort[j];
                    sort[j]=temp;
                    flag=false;
                }
            }
            if(false){
                break; //说明当前数组已经是排好序的无需再比较跳出即可

            }else{
                flag=true; //重新修改标志用作于下次比较
            }
        }
    }


    //冒泡排序版本5
    public static void bubbleSortVersion5(int [] sort){

        //创建一个辅助变量用户辅助2个之间的元素交换
        int temp;

        //创建一个辅助标志用作与是否当前数组已经是排好序的
        boolean flag=true;

        //创建外循环用作与执行冒泡排序的次数(总长度-1)
        for(int index=0;index<sort.length-1;index++){


            //创建一个内循环用作与在当前索引元素范围内中筛选出对应的最大/小值
            //减一应为是元素22比较如果当前元素是最后一个比较的话下一个元素就是指针越界
            for(int index2=0;index2<sort.length-1-index;index2++){


                if(sort[index2]>sort[index2+1]){
                    //实现2个元素之间的交换
                    temp=sort[index2+1];            //将后一个元素存储到辅助变量上
                    sort[index2+1]=sort[index2];    //将当前索引元素内容赋值给后一个元素
                    sort[index2]=temp;              //将后一个索引元素赋值给当前索引元素

                    flag=false;
                }

            }

            if(flag){
                break; //表示当前数组是排好序的无需在比较退出即可
            }else{
                flag=true; //重置当前标志,用作与下次比较
            }
        }
    }


    //冒泡排序版本6
    public static void bubbleSortVersion6(int []arr ){

        boolean flag=true;
        //创建外循环用作用循环比较次数(总长度-1次)
        for(int index=0;index<arr.length-1;index++){

            //创建内循环每次循环结束筛选出一个最大值放对应末尾索引上
            for(int index2=0;index2<arr.length-1-index;index2++){

                //比较当前2个相依节点元素筛选出最大值
                if(arr[index2]>arr[index2+1]){

                    int temp=arr[index2+1];
                    arr[index2+1]=arr[index2];
                    arr[index2]=temp;
                    flag=false;
                }
            }

            if(flag){
                //当前一次都没有替换说明当前数组就是有序的索引无需再次比较
                break;
            }else{
                flag=true;
            }
        }
    }
}

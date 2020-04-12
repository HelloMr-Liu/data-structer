package com.king2.sort;


import java.util.Arrays;

//练习归并排序算法
public class PracticeMergeSort {
    public static void main(String[] args) {
        int [] newArr={77129,72767,55740,55398,57361,57790,78468,67999, 67964,55398,55399,88,57361,57790,78468,67999,55398,57361,57790,78468,67999,6666,888888};
        mergeSortVersion3(newArr,0,newArr.length-1,new int[newArr.length]);
        System.out.println(Arrays.toString(newArr));

        //创建一个未排好序的数组
        /*int[] newArray = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            newArray[i] = (int) (Math.random() * 80000000);
        }
        Long startTime=System.currentTimeMillis();
        mergeSortVersion1(newArray,0,newArray.length-1,new int[80000000]);
      //  System.out.println(Arrays.toString(newArray));
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);*/
    }


    //归并排序版本1 用到的是回溯思想和分治思想
    public static void mergeSortVersion1(int[] arr,int left,int right,int[] temp){
        //如果left指针<right才能继续归并排序
        if(left<right){

            //获取当前大致均匀的才分两边的索引位置
            int midIndex=(left+right)/2;

            //先将左边的区域继续分治回溯排序
            mergeSortVersion1(arr,left,midIndex,temp);

            //将右边的区域继续分治回溯排序
            mergeSortVersion1(arr,midIndex+1,right,temp);

            //创建2个移动指针
            int moveLeftPoint=left;
            int moveRightPoint=midIndex+1;

            int count=left;

            //将当前索引位置区域继续合并比较排序
            while(moveLeftPoint<=midIndex&&moveRightPoint<=right){

                //先比较moveRightPoint指针索引元素是否小于moveLeftPoint指针元素
                if(arr[moveRightPoint]<arr[moveLeftPoint]){

                    //将当前moveRightPoint指针索引元素赋值给当前对应的temp数组上
                    temp[count++]=arr[moveRightPoint];
                    moveRightPoint++;//当前指针向前一位
                }else{

                    //将当前moveLeftPoint指针索引元素赋值给当前对应的temp数组上
                    temp[count++]=arr[moveLeftPoint];
                    moveLeftPoint++;//当前指针向前一位
                }
            }

            //将某个区域中的多余元素依次的添加到temp数组中
            while(moveLeftPoint<=midIndex){
                //将当前左边的索引元素依次赋值给temp中
                temp[count++]=arr[moveLeftPoint];
                moveLeftPoint++;
            }
            while(moveRightPoint<=right){
                //将当前右边的索引元素依次赋值给temp中
                temp[count++]=arr[moveRightPoint];
                moveRightPoint++;
            }

            //将当前索引范围区域中的temp内容赋值给原数组arr中
            count=left;
            while(count<=right){
                arr[count]=temp[count];
                count++;
            }
        }
    }

    //归并排序版本2 用到的是回溯思想和分治思想
    public static void mergeSortVersion2(int[] arr,int left,int right,int[] temp){

        //拆分无序区域的判断条件
        if(left<right){

            //已当前索引范围内容的中间进行拆分
            int midIndex=(left+right)/2;

            //进行向左拆分
            mergeSortVersion2(arr,left,midIndex,temp);

            //进行向右拆分
            mergeSortVersion2(arr,midIndex+1,right,temp);



            //定义一个temp操作的索引变量
            int count=left;
            //定义2个移动指针
            int moveLeft=left;
            int moveRight=midIndex+1;

            //当不能可拆分区域的时候就进行将当前拆分的小组进行排序比较
            while(moveLeft<=midIndex&&moveRight<=right){

                if(arr[moveRight]<arr[moveLeft]){
                    temp[count++]=arr[moveRight];
                    moveRight++;
                }else{
                    temp[count++]=arr[moveLeft];
                    moveLeft++;
                }
            }


            //判断当前moveLeft或moveLeft是否还有一些索引元素没有移动到temp数组中
            while(moveLeft<=midIndex){
                temp[count++]=arr[moveLeft];
                moveLeft++;
            }
            while(moveRight<=right){
                temp[count++]=arr[moveRight];
                moveRight++;
            }



            //然后将当前temp中的内容对应的索引位置合并到arr中
            count=left;
            while(count<=right){
                arr[count]=temp[count];
                count++;
            }

        }
    }































    //归并排序版本2 用到的是回溯思想和分治思想
    public static void mergeSortVersion3(int[] arr,int left,int right,int[] temp){


        //判断当前的索引的区域范围有效
        if(left<right){

            //去一个当前索引范围的大致中间值
            int midIndex=(left+right)/2;

            //先进行当前中间索引以左边的进行再次索引拆分
            mergeSortVersion3(arr,left,midIndex,temp);

            //在进行当前中间索引以右边的进行再次索引拆分
            mergeSortVersion3(arr,midIndex+1,right,temp);


            //创建2个移动变量
            int moveLeft=left;
            int moveRight=midIndex+1;

            //创建一个辅助数组对应所需的索引
            int count=left;

            while(moveLeft<=midIndex&&moveRight<=right){


                //判断当前moveRight索引对应的元素是否小于midIndex索引对应的元素
                if(arr[moveRight]<arr[moveLeft]){

                    //将当前右边移动指针的索引赋值给temp数组中
                    temp[count++]=arr[moveRight];
                    moveRight++;

                }else{
                    //同理
                    //将当前左边移动指针的索引赋值给temp数组中
                    temp[count++]=arr[moveLeft];
                    moveLeft++;
                }
            }


            //判断是否当前索引范围的元素是否都移动到temp数组中
            while(moveLeft<=midIndex){
                //将当前左边移动指针的索引赋值给temp数组中
                temp[count++]=arr[moveLeft];
                moveLeft++;

            }

            while(moveRight<=right){
                //将当前右边移动指针的索引赋值给temp数组中
                temp[count++]=arr[moveRight];
                moveRight++;
            }

            //将当前temp索引范围对应的元素重新赋值个arr中
            count=left;
            while(count<=right){
                arr[count]=temp[count];
                count++;
            }
        }
    }

}




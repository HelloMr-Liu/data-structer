package com.king2.search;


//插值查找算法
public class PracticeInsertValueSearch {
    public static void main(String[] args) {

        int [] newArr={11,12,13,14,15,16,17,19};

        System.out.println("查找到的索引："+insertValueSearchVersion1(newArr,0,newArr.length-1,19));
    }

    //插值查找算法
    public static int insertValueSearchVersion1(int []arr ,int left,int right,int searchElement){

        //结束判断递归条件,添加searchElement<arr[left]||searchElement>arr[right]防止算数异常
        if(left>right||searchElement<arr[left]||searchElement>arr[right]){
            return -999;
        }

        //动态的计算一个比例中间索引
        int midIndex= left + (right - left) * ((searchElement - arr[left]) / (arr[right] - arr[left]));
        int currElement=arr[midIndex];

        if(searchElement<currElement){
            //向左递归
            return insertValueSearchVersion1(arr ,left,midIndex-1,searchElement);
        }else if(searchElement>currElement){
            //向右递归
            return insertValueSearchVersion1(arr ,midIndex+1,right,searchElement);
        }else{
            return midIndex;
        }
    }

    public static int insertSeatch(int a[] , int value) {

        if(a.length == 0)
            return -1;

        int low = 0;
        int high = a.length - 1;
        int mid = -1 ;
        while(low <= high) {

            mid = low +( (value - a[low]) / ( a[high] - a[low] )) *(high - low);
            if(value > a[mid])
                low = mid + 1;
            else if(value < a[mid])
                high = mid - 1;
            else return mid;
        }

        return -1;
    }

}

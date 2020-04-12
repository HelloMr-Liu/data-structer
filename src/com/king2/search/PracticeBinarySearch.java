package com.king2.search;


import com.king2.sort.PracticeQuicksort;

//使用二分查找
public class PracticeBinarySearch {

    public static void main(String[] args) {
        int[] newArr = new int[10000000];
        for (int index = 0; index < 10000000; index++) {
            newArr[index] = index + 1;
        }


        long startTime = System.currentTimeMillis();
        //线性查找
        for (int index = 0; index < newArr.length; index++) {
            if (newArr[index] == 8888888) {
                System.out.println("查找到的索引：" + index);
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        System.out.println("=======================");

        int[] newArr2 = new int[10000000];
        for (int index = 0; index < 10000000; index++) {
            newArr2[index] = (int) (Math.random() * 8000000);
        }
        newArr2[999999] = 999999;
        //二分查找情况
        long startTime2 = System.currentTimeMillis();
        PracticeQuicksort.quickSortPitFillDataVersion6(newArr2, 0, newArr2.length - 1);
        System.out.println("查找到的索引：" + binarySearchVersion2(newArr, 0, newArr2.length - 1, 999999));
        long endTime2 = System.currentTimeMillis();
        System.out.println("花费时间：" + (endTime2 - startTime2));


    }

    //二分查找递归版
    public static int binarySearchVersion1(int[] arr, int left, int right, int searchElement) {

        System.out.println("折半查找次数");
        //判断当前索引范围是否在有效范围中
        if (left > right) {
            return -999999; //代表没有找到
        }

        //获取当前中间索引
        int currIndex = (left + right) / 2;

        //获取当前索引范围的大致中间元素
        int currElement = arr[currIndex];

        //判断searchElement是否小于currElement
        if (searchElement < currElement) {
            //往当前currElement的左边进行再次递归查找
            return binarySearchVersion1(arr, left, currIndex - 1, searchElement);

            //判断当前searchElement==currElement
        } else if (searchElement == currElement) {
            //直接返回当前索引
            return currIndex;

        } else {
            //往当前currElement的右边进行再次递归查找
            return binarySearchVersion1(arr, currIndex + 1, right, searchElement);
        }
    }

    //二分查找递归版的一些小优化
    public static int binarySearchVersion2(int[] arr, int left, int right, int searchElement) {
        //创建判断条件用于结束递归调用
        if (left > right || searchElement < arr[0] || searchElement > arr[arr.length - 1]) {
            //当前列表中没有对应的查询元素
            return -99;
        }

        //获取当前的中间索引及对应的元素
        int midIndex = (left + right) / 2;
        int currElement = arr[midIndex];

        //判断查找的元素是否小于当前元素
        if (searchElement < currElement) {
            //往左递归再次查找
            return binarySearchVersion2(arr, left, midIndex - 1, searchElement);
        }
        //判断查找的元素是否大于当前元素
        else if (searchElement > currElement) {
            //往右递归再次查找
            return binarySearchVersion2(arr, midIndex + 1, right, searchElement);
        } else {
            return midIndex;
        }
    }


    //二分查找非递归版
    public static int binarySearchVersion3(int[] arr, int left, int right, int searchElement) {

        //定义存储当前对应的中间索引及对应的元素变量
        int midIndex;
        int midElement;

        //当只有 left <=right 的是才能在进行查找元素,否则当前无需列表没有找到指定的元素
        while (left <= right) {

            //获取对应的中间索引及对应的元素
            midIndex = (left + right) / 2;
            midElement = arr[midIndex];

            //判断是否searchElement<midElement
            if (searchElement < midElement) {
                //将当前midIndex-1复制给right
                right = midIndex - 1;
            }
            //判断是否searchElement>midElement
            else if (searchElement > midElement) {
                //将当前midIndex+1复制给left
                left = midIndex + 1;

            } else {
                return midIndex;
            }
        }

        //没有找到默认范围-999
        return -999;
    }
}

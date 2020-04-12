package com.king2.sort;

public class PracticeInsertSort {

    public static void main(String[] args) {
        //创建一个未排好序的数组
        int[] newArray = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            newArray[i] =i+16;
        }
        Long startTime=System.currentTimeMillis();
        insertSortVersion5(newArray);
        //System.out.println(Arrays.toString(newArray));
        Long endTime=System.currentTimeMillis();

        System.out.println(endTime-startTime);


    }

    //插入排序版本1
    public static int [] insetSortVersion1(int [] sort){

        //默认创建一个有序列表,里面存储的元素都是排好序的
        int [] orderBy=new int [sort.length];
        //默认将无序列表的第一个元素存储到有序列表中
        orderBy[0]=sort[0];

        //循环无序列表从1开始
        for(int i=1;i<sort.length;i++){

            int orderByListIndex=i;

            //循环有序列表
            while(--orderByListIndex>=0){

                //判断当前无序列表中的元素是否>=有序列表中的指定索引元素
                if(sort[i]>=orderBy[orderByListIndex]){
                    break;
                }else{
                    //将有序列表数组对应的currIndex-1索引元素移动到currIndex上
                    orderBy[orderByListIndex+1]=orderBy[orderByListIndex];
                }
            }
            //将已当前currIndex添加到有序列表数组中
            orderBy[orderByListIndex+1]=sort[i];
        }
        return orderBy;
    }

    //插入排序版本2 做了一点点小优化
    public static void insertSortVersion2(int [] sort){

        //创建一个变量存储对应当前循环索引的元素
        int currInsertElement;
        //创建一个变量存储对应元素插入位置的索引信息
        int currInsertIndex;

        //循环判断次数是总长度-1次,默认一开始索引0为有序列表第一个元素
        for(int index=1;index<sort.length;index++){

            currInsertElement=sort[index]; //存储当前要插入的元素
            currInsertIndex=index;

            //循环有序列表中的index索引-1个元素
            while(--currInsertIndex>=0&& currInsertElement>sort[currInsertIndex]){
                //当前插入的元素小于有序列表指定的索引元素就进行将指定currInsertIndex元素位置往后移动
                sort[currInsertIndex+1]=sort[currInsertIndex];
            }

            //将当前的待插入元素插入到currInsertIndex+1中
            sort[currInsertIndex+1]=currInsertElement;
        }
    }


    //插入排序版本3
    public static void insertSortVersion3(int [] sort){


        //创建2个变量一个存储插入的元素一个存储插入的索引
        int insertElement;
        int insertIndex;

        //创建外循环比作与未比较的区域索引元素范围
        for(int index=1;index<sort.length;index++){

            //创建当前未比较区域索引的元素及索引
            insertElement=sort[index];
            insertIndex=index;

            //循环一个内循环区域(有序列表区域)
            while(--insertIndex>=0&&insertElement<sort[insertIndex]){
                //就将有效列表区域中对应的insertIndex索引元素往后移动一下
                sort[insertIndex+1]=sort[insertIndex];
            }

            //将当前带插入的元素插入到指定索引下的有序列表区域
            sort[insertIndex+1]=insertElement;
        }

    }



    //插入排序版本4
    public static void insertSortVersion4(int [] sort){
        //创建2个变量一个存储待插入的元素一个存储待插入对应的索引
        int insertElement;
        int insertIndex;

        //创建外循环遍历对应的无序列表区域中的元素 默认第一个索引元素在有序列表区域中所有当前索引从1开始
        for(int index=1;index<sort.length;index++){

            //将当前带插入的索引及元素存储到辅助变量上
            insertElement=sort[index];
            insertIndex=index;

            //循环有序列表区域中的索引元素
            while(--insertIndex>=0&&insertElement>sort[insertIndex]){

                //当前带插入的元素比有效列表中当前对应索引元素小索引将当前有效元素往后移动一位以此类推
                sort[insertIndex+1]=sort[insertIndex];
            }
            //将当前待插入的元素存入到对应的待插入索引+1的位置上即可
            sort[insertIndex+1]=insertElement;
        }

    }






    //插入排序版本5
    public static void insertSortVersion5(int []arr){

        //创建外循环 用作于循环比较次数，并且当前0到index-1这个范围都是有序区
        for(int index=1;index<arr.length;index++){

            //创建一个移动指针,及对应当前待插入元素
            int insertValue=arr[index];
            int movePoint=index;


            //循环比较出对应的大小位置的索引并插入进去
            while((--movePoint)>=0&&arr[movePoint]>insertValue){

                //将当前有序区域中的元素往后移动一位
                arr[movePoint+1]=arr[movePoint];
            }

            //将当前待插入元素插入到对应的移动指针位置上
            arr[movePoint+1]=insertValue;
        }


    }































}

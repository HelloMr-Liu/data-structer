package com.king2.sort;

public class PracticeShellSort {

    public static void main(String[] args) {
        //创建一个未排好序的数组
        int[] newArray =new int[800000000];
        for (int i = 0; i < 800000000; i++) {
            newArray[i] =i /*(int) (Math.random() * 800000000)*/;
        }

        Long startTime=System.currentTimeMillis();
        shellSortVersion4(newArray);
       // System.out.println(Arrays.toString(newArray));
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }

    //希尔排序版本2
    public static void shellSortVersion2(int []sort){

        //创建一个变量存储当前步长数
        int stepLength=sort.length;

        //创建2个变量一个存储待插入的元素及对应的索引
        int insertElement;
        int insertIndex;

        //循环计算出当前步长数
        while(((stepLength/=2)>0)){


            //按照当前索引(当前步长数)开始,进行当前索引范围下以步长的规律形式进行插入排序
            for(int index=stepLength;index<sort.length;index++){

                //存储当前待插入比较的元素
                insertElement=sort[index];
                insertIndex=index;

                //按照步长的规律进行对当前索引范围下的元素插入排序比较
                while((insertIndex-=stepLength)>=0&&insertElement<sort[insertIndex]){

                    //当前待插入的元素小于该区域中insertIndex指定的元素就将当前insertIndex的元素往后移动一个步长的距离以此类推
                    sort[insertIndex+stepLength]=sort[insertIndex];
                }

                //将待插入的元素插入到指定insertIndex+stepLength的位置中
                sort[insertIndex+stepLength]=insertElement;

            }
        }
    }


    //希尔排序版本3
    public static void shellSortVersion3(int [] sort){

        //创建一个存储对应步长数变量
        int stepLength=sort.length;

        //创建2个存储带插入元素及对应的索引
        int insertValue;
        int insertIndex;

        //循环判断出当前步上
        while((stepLength/=2)>0){

            //循环当前每组下对应步长规律的元素
            for(int index=stepLength;index<sort.length;index++){

                //存储当前待插入比较的元素及索引
                insertValue=sort[index];
                insertIndex=index;

                //循环当前组下对应步长规律的形式之间的元素比较
                while((insertIndex-=stepLength)>=0&&insertValue>sort[insertIndex]){
                    //待插入的元素小于insertIndex对应的索引元素就将insertIndex对应的索引元素往后移动一个步长距离
                    sort[insertIndex+stepLength]=sort[insertIndex];
                }

                //将当前待插入元素插入到对应的索引+步长上
                sort[insertIndex+stepLength]=insertValue;
            }
        }
    }


    //希尔排序版本1
    public static void shellSortVersion1(int []sort){

        //创建一个存储步长数的变量
        int stepLength=sort.length;

        //创建2个变量当前待插入的索引元素及对应的索引
        int insertElement;
        int insertIndex;


        //使用while循环计算出步长
        while((stepLength/=2)>0){

            //创建一个对应指定步长的无序列表循环区域
            for(int index=stepLength;index<sort.length;index++){

                insertElement=sort[index];
                insertIndex=index;

                //循环当前步长的索引元素区域
                while((insertIndex-=stepLength)>=0&&insertElement<sort[insertIndex]){
                    //当前带插入比较的元素小于当前步长对应的索引元素所以将步长继续往前左执行
                    sort[insertIndex+stepLength]=sort[insertIndex];
                }

                //将当前带插入的元素插入到指定的insertIndex+步长的索引位置上
                sort[insertIndex+stepLength]=insertElement;
            }
        }
    }

    //希尔排序版本4
    public static void shellSortVersion4(int []arr){

        //定义一个步长变量
        int stepLength=arr.length;

        //循环算出每次步长信息
        while((stepLength/=2)>0){

            //循环创建一个当前无序列表区域
            for(int index=stepLength;index<arr.length;index++){

                //定义一个移动指针
                int movePoint=index;
                //创建一个变量存储对应当前待插入元素
                int insertElement=arr[index];

                //与对应按步长规律的有序列表区域比较对应的值
                while((movePoint-=stepLength)>=0&&arr[movePoint]<insertElement){

                    //将有序列表中元素往后移动一位
                    arr[movePoint+stepLength]=arr[movePoint];

                }
                //将当前待插入元素插入到指定移动指针中
                arr[movePoint+stepLength]=insertElement;
            }
        }
    }
























}

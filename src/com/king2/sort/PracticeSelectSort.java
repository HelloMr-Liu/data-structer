package com.king2.sort;


import java.util.Arrays;

//练习选择排序
public class PracticeSelectSort {
    public static void main(String[] args) {

        //创建一个未排好序的数组
        int[] newArray = new int[80];
        for (int i = 0; i < 80; i++) {
            newArray[i] = (int) (Math.random() * 8000);
        }
        Long startTime=System.currentTimeMillis();
        selectSortVersion7(newArray);
        System.out.println(Arrays.toString(newArray));
        Long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }
    //注意这种方式就和冒泡差不多了所以这种方式弃用
    public static void selectSortVersion1(int [] sort){
        long startTime=System.currentTimeMillis();
        int count=0;
        //创建一个临时变量用于存储当前循环对应的最小值信息
        int smallValue;

        for(int i=0;i<sort.length-1;i++){

            //将当前外层循环的索引对应的元素存储到临时变量上用作与内存循环比较
            smallValue=sort[i];

            for(int j=i+1;j<sort.length;j++){
                count++;
                if(smallValue>sort[j]){
                    //由于j索引的元素小于当前smallValue对应的值所以将当前j元素赋值给smallValue变量上
                    int temp=sort[j];
                    sort[j]=smallValue;
                    smallValue=temp;

                }
            }
            //将当前查找的最小值存储到当前i索引上
            sort[i]=smallValue;
        }
        long endTime=System.currentTimeMillis();
        System.out.println(count+"总耗时："+(endTime-startTime));
    }



    public static void selectSortVersion2(int [] sort){
        long startTime=System.currentTimeMillis();
        long count=0;

        //定义一个变量存储每次循环比较对应的最小值信息及对应的最小索引
        int smallValue=0;
        int smalIndex=0;

        //创建外循环判断比较次数（总长度-1）
        for(int i=0;i<sort.length-1;i++){

            //获取当前外循环对应的元素信息默认是当前最小值
            smallValue=sort[i];

            //创建内循环判断获取出本次循环的最小索引及对应的元素
            for(int j=i+1;j<sort.length;j++){
                count++;
                if(smallValue>sort[j]){
                    //获取当前内循环最小值和对应的索引
                    smallValue=sort[j];
                    smalIndex=j;
                }
            }

            //更新外循环对应的索引元素信息
            sort[smalIndex]=sort[i];
            sort[i]=smallValue;
        }

        long endTime=System.currentTimeMillis();
        System.out.println(count+"总耗时："+(endTime-startTime));
    }

    public static void selectSortVersion3(int [] sort){

        //创建一个存储本次循环最小值,及对应的元素索引
        int smallValue=-1;
        int smallIndex=-1;


        //创建外循环用作与循环判断的比较数组长度-1次数
        for(int i=0;i<sort.length;i++){

            //初始化将当前外循环索引对应的元素设置为最小值
            smallValue=sort[i];
            smallIndex=i;

            //创建内循环到循环结束后获取对应的最小元素及对应的索引
            for(int j=i+1;j<sort.length;j++){
                //判断当前最小元素变量是否大于当前内循环索引元素
                if(smallValue>sort[j]){

                    //将更新新的最小元素和对应的索引
                    smallIndex=j;
                    smallValue=sort[j];
                }

            }

            //将当前的最小元素赋值给当前外循环索引位置上,与最小索引位置的元素进行交换
            sort[smallIndex]=sort[i];
            sort[i]=smallValue;

        }
    }
    public static void selectSortVsersion4(int [] sort){

        //创建存储本次循环过后对应的最下元素及对应的索引
        int smallValue=-1;
        int smallIndex=-1;

        //创建外循环用作于比较次数
        for(int i=0;i<sort.length-1;i++){


            //默认将当前外循环索引及对应的元素为最小元素
            smallValue=sort[i];
            smallIndex=i;

            for(int j=1+i;j<sort.length;j++){

                //判断如果j对应的元素小于smallValue就将当前元素及索引赋值给 smallValue,smallIndex
                if(smallValue>sort[j]){

                    smallIndex=j;
                    smallValue=sort[j];
                }

            }

            //如果当前smallIndex！=i说明还有更小的元素
            if(smallIndex!=i){
                //将对应的最小元素存储到当前i索引位置上
                sort[smallIndex]=sort[i];
                sort[i]=smallValue;
            }
        }
    }

    //选择排序版本5
    public static void selectSortVersion5(int [] sort){
        //创建2个变量一个存储当前最小元素和索引
        int smallValue;
        int smallIndex;

        //创建外循环用作于循环比较次数 总长度-1次数
        for(int index=0;index<sort.length-1;index++){

            //存储当前外循环对应的索引元素
            smallValue=sort[index];
            smallIndex=index;

            //创建内容循环比较出一次索引元素范围内容的最小值
            for(int index2=index+1;index2<sort.length;index2++){

                //判断比较比较出本次循环的最小值
                if(smallValue>sort[index2]){
                    smallValue=sort[index2];
                    smallIndex=index2;
                }
            }

            //将于当前循环元素与smallValue交换值
            sort[smallIndex]=sort[index];
            sort[index]=smallValue;
        }
    }


    //选择排序
    public static void selectSortVersion6(int [] sort){

        //创建2个辅助变量一个存储最大/小值 一个存储当前对应值的索引
        int helpValue;
        int helpIndex;

        //创建外循环用作与将最大/小值内容存储到当前索引位置上
        for(int index=0;index<sort.length-1;index++){

            //默认将当前索引对应的元素存储到2个变量上
            helpValue=sort[index];
            helpIndex=index;

            //创建内循环记录本次循环结束后对应的最大/小值信息
            for(int index2=index+1;index2<sort.length;index2++ ){

                //判断比较筛选出当前索引元素范围的内中的最大/小元素
                if(helpValue>sort[index2]){

                    //将当前元素及对应的索引存储到对应的辅助变量上
                    helpValue=sort[index2];
                    helpIndex=index2;
                }
            }
            if(index!=helpIndex){

                //将辅助变量上的元素与当前外循环索引的元素之间22交换
                sort[helpIndex]=sort[index];
                sort[index]=helpValue;
            }
        }
    }












    //选择排序版本7
    public static void selectSortVersion7(int[]arr){


        //创建2个变量用于 存储当前循环下最小元素及对应的索引
        int smallValue;
        int smallIndex=-1;

        //创建外循环用作于循环比较次数
        for(int index=0;index<arr.length-1;index++){


            //默认将当前外循环索引元素存储到变量中
            smallValue=arr[index];

            //创建内循环 筛选出对应的最小元素或最大元素
            for(int index2=index+1;index2<arr.length;index2++){

                //筛选出当前循环最小元素
                if(smallValue>arr[index2]){

                    smallValue=arr[index2];
                    smallIndex=index2;
                }
            }

            //将当前最小元素对应的索引与当前外循环索引元素进行交换
            arr[smallIndex]=arr[index];
            arr[index]=smallValue;
        }
    }


























}

package com.king2.sort;


//练习对应的基数排序算法
public class PracticeBaseSort {

    public static void main(String[] args) {

        //创建一个未排好序的数组
        int[] newArray = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            newArray[i] = (int) (Math.random() * 800000000);
        }
        //int [] newArr={77129,72767,55740,55398,57361,57790,78468,67999, 67964,55398,55399,88,57361,57790,78468,67999,55398,57361,57790,78468,67999,6666,888888};
        Long startTime=System.currentTimeMillis();
        baseSortVersion2(newArray);
        Long endTime=System.currentTimeMillis();
      //System.out.println(Arrays.toString(newArray));
        System.out.println(endTime-startTime);

    }

    //基数排序
    public static void baseSortVersion1(int []arr){

        //创建10个桶存储对应的无序列表信息
        int [][] bucketArr=new int [10][arr.length];

        //创建一个记录每个桶中存储到多少个元素数组
        int [] bucketCounts=new int[10];

        //定义一个标志代表用于标志当前位数对应的下一个位数是否存在
        boolean flag=true;

        int digitDividend=1; //代表当前的位数被除数
        int judgeDigitDividend=10;//代表下一次位数

        while(true){

            //将当前无序列表中的元素按照对应的位数存储到对应的桶中
            for(int index=0;index<arr.length;index++){

                //获取当前的位数
                int currDigit=arr[index]/(digitDividend)%10;

                //判断当前位数下的下一个位数是否存在
                int currFirstDigit=arr[index]/(judgeDigitDividend)%10;
                if(currFirstDigit>0){
                    flag=false;
                }

                //将当前安装指定的位数存储到对应的桶中
                bucketArr[currDigit][bucketCounts[currDigit]]=arr[index];
                //并且统计当前对应桶的元素个数
                bucketCounts[currDigit]++;
            }

            //定义一个无序列表数组中的索引变量
            int count=0;

            //将桶中的数据依次取出依次存入到原始数组中
            for(int index2=0;index2<bucketArr.length;index2++){

                //判断当前桶是否有有效个数
                if(bucketCounts[index2]!=0){
                    for(int index3=0;index3<bucketCounts[index2];index3++){
                        //将当前桶下对应的有效元素依次取出存到arr中
                        arr[count++]= bucketArr[index2][index3];
                    }
                }
                //将当前桶中的有效个数清空
                bucketCounts[index2]=0;
            }

            //更新当前算出位数对应的被除数
            digitDividend*=10;
            judgeDigitDividend*=10;

            //当前的位数为最后一位无需再循环
            if(flag){
                break;
            }else{
                flag=true;
            }
        }
    }


    //基数排序
    public static void baseSortVersion2(int []arr){

        //创建一个桶数组
        int [][] buckte=new int [10][arr.length];

        //创建一个记录每个桶中的元素个数索引
        int [] buckteCounts=new int [buckte.length];

        //存储当前被除数变量,下一个被除数变量
        int currentDividend=1;int lastDividend=10;

        //创建2个变量一个是存储当前位数变量,下一个位数变量
        int currentDigit; int lastDigit;

        //定义一个跳出循环遍历
        boolean flag=true;

        //用于执行一定遍(装桶,取桶)
        while(true){

            //装桶
            for(int index=0;index<arr.length;index++){

                int currNumber=arr[index];

                //获取当前数值位数
                currentDigit=currNumber/currentDividend%10;
                lastDigit=currNumber/lastDividend%10;
                if(lastDigit>0){
                    flag=false;
                }

                //将当前数值按照对应的位数存入到桶中
                buckte[currentDigit][buckteCounts[currentDigit]]=currNumber;
                //将当前记录某桶中的有效元素个数
                buckteCounts[currentDigit]++;
            }

            int count=0;
            //取桶
            for(int index=0;index<buckte.length;index++){

                //判断当前桶中是否有数据
                if(buckteCounts[index]>0){

                    //将当前桶中数据依次放入到原数组中
                    for(int index2=0;index2<buckteCounts[index];index2++){
                        arr[count++]=buckte[index][index2];
                    }

                    //当当前桶中对应的计数桶清零
                    buckteCounts[index]=0;
                }

            }

            //更新当前对应的被除数
            currentDividend*=10;
            lastDividend*=10;

            //判断是否需要跳出循环
            if(flag){
                break;
            }else{
                flag=true;
            }

        }
    }
}

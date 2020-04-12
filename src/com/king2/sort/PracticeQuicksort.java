package com.king2.sort;

import java.util.Arrays;

public class PracticeQuicksort {

    public static void main(String[] args) {
/*        Map<Integer,Integer> hashMap=new HashMap<>();
        int[] newArray =new int[800000000];
        int count=0;
        for(int index=0;index<=80000000) {
            int number=(int) (Math.random() * 80000000);
            newArray[count++] =number ;
        }
        System.out.println();
        quickSortPitFillDataVersion5(newArray,0,newArray.length-1);
       // System.out.println(Arrays.toString(newArray));*/

        //创建一个未排好序的数组
        int[] newArray = new int[80];
        for (int i = 0; i < 80; i++) {
            newArray[i] = (int) (Math.random() * 8000000);
        }

        System.out.println();
        Long startTime=System.currentTimeMillis();
        quickSortPitFillDataVersion6(newArray,0,newArray.length-1);
        Long endTime=System.currentTimeMillis();
        System.out.println(Arrays.toString(newArray));
        System.out.println(endTime-startTime);
    }



    //快速排序之挖坑填数据法 以最左为中心轴
    public static void quickSortPitFillDataVersion1(int []arr,int left,int right){


        //判断当前左指针>=右指针说明当前已经当前分的区域只有一个元素所以无序比较即可
        if(left>=right)return;

        //默认是将当前区域的第一个元素为中轴元素,也相当于默认左边为第一个元素
        int pivotValue=arr[left];


        //定义2个移动左右指针
        int moveLeft=left;
        int moveRight=right;

        //只有左指针小于右指针才进行循环移动
        while(moveLeft<moveRight){

            /**
             * 注意：如果是你中轴值是最左元素那么一开始就先移动右指针，不然就会将最右边元素依次的覆盖问题,同理一开始如果是以右为中轴值道理也是由于
             *      所以由此可见  如果是以最左元素为中轴值 一开始就先移动右指针
             *                 如果是以最右元素为中轴值 一开始就先移动左指针
             */

            //先移动扫描右指针
            while(moveRight>moveLeft&&arr[moveRight]>=pivotValue){
                //当前右指针索引确实大于或等于中轴之将当前指针往前移动一位
                moveRight--;
            }
            if(moveRight>moveLeft){
                //说明当前2个指针没有碰撞到一起且遇到了当前右指针索引元素小于中轴值将当前右指针元素移动到左指针索引上
                arr[moveLeft]=arr[moveRight];
            }


            //移动扫描左指针
            while(moveLeft<moveRight&&arr[moveLeft]<pivotValue){

                //当前左指针索引确实没有大于右指针且对应的左指元素确实小于中轴值索引左索引向后移动一位
                moveLeft++;
            }
            if(moveLeft<moveRight){
                //说明当前2个指针没有碰撞一起且遇到了左指针元素大于中轴值索引将当前左元素存储到右指针索引上
                arr[moveRight]=arr[moveLeft];

            }



            //判断如果当前移动左右指针相等索引当前中轴值分区以结束将当前轴值值添加在左指针索引上
            if(moveLeft==moveRight){
                arr[moveLeft]=pivotValue;
            }
        }

        //本次区域对应的分区结束
        //递归调用左边区域的内容进行再次分区
        quickSortPitFillDataVersion1(arr,left,moveLeft-1);

        //递归调用右边区域的内容进行再次分区
        quickSortPitFillDataVersion1(arr,moveRight+1,right);

    }

    //快速排序之挖坑填数据法 以最右为中心轴
    public static void quickSortPitFillDataVersion2(int []arr,int left,int right){

        //定义无序再次分区排序的判断条件
        if(left>=right) return;

        //创建中心轴的值
        int pivotValue=arr[right];

        //定义2个左右移动指针
        int moveLeft=left;
        int moveRight=right;

        //创建循环当前分区下的索引元素情况,当前左指针大于或等于右指针当前分区比较结束
        while (moveLeft < moveRight) {


            /**
             * 提示：是以最右元素为中轴一开始就要先移动左指针索引 如果如果还是移动右指针就会出现最左边的元素会被覆盖
             */
            //移动左指针
            while(moveLeft<moveRight&&arr[moveLeft]<pivotValue){
                //如果当前左指针元素符合以上条件就将左指针向后移动一位以此类推
                moveLeft++;
            }
            if(moveLeft<moveRight){
                //表示当前左指针指定的索引元素大于等于中轴值将当前左指针元素移动到右指针索引上
                arr[moveRight]=arr[moveLeft];

            }

            //移动右指针
            while(moveLeft<moveRight&&arr[moveRight]>=pivotValue){
                //如果当前右指针的元素符合以上条件就将右指针索引向前移动一位以此类推
                moveRight--;
            }
            if(moveLeft<moveRight){
                //表示当前左指元素小于中轴值索引将当前右指针元素移动到左指针索引上
                arr[moveLeft]=arr[moveRight];
            }

        }

        /**
         * 表示当前左指针与右指针相撞(等) 将中轴值存入到当前左右指针索引中
         * 所以当前中轴值以左边都是小于中轴值的元素，右边都是大于等于中轴值的元素
         */
        arr[moveLeft]=pivotValue;


        /**
         *当前分区排序已经比较结束将当前分区的左右边分区递归比较
         */
        //比较当前分区下以中轴值左边的区域
        quickSortPitFillDataVersion2(arr,left,moveLeft-1);

        //比较当前分区下以中轴值右边的区域
        quickSortPitFillDataVersion2(arr,moveRight+1,right);
    }





    //快速排序之数据挖坑法 版本3我以最左元素为中轴值
    public static void quickSortPitFillDataVersion3(int []arr ,int left,int right){
        //判断2个指针是否右指定区域可以排序
        if(left>=right)return; //没有指定区域可以分区比较排序

        //定义一个中轴值变量(已最左元素）为中心轴值
        int pivotValue=arr[left];

        //创建2个移动左右指针
        int moveLeftPoint=left;
        int moveRightPoint=right;


        //循环当前2个指针范围下的可以排序比较区域
        while(moveLeftPoint<moveRightPoint){


            //注意：由于是最左元素为中心轴值一开始一定要是先移动右指针,同理如果是最右为中心轴值 就要先移动左指针

            //先移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveRightPoint]>=pivotValue){

                //符合当前条件，将当前右指针往前移动一位以此类推
                moveRightPoint--;

            }
            //将当前右指针的元素小于中轴值,就将右指针索引元素赋值给左指针索引上
            if(moveLeftPoint<moveRightPoint){
                arr[moveLeftPoint]=arr[moveRightPoint];
            }

            //移动左指针
            while(moveLeftPoint<moveRightPoint&&arr[moveLeftPoint]<pivotValue){

                //符合当前条件,将当前左指向后移动一位, 以此类推
                moveLeftPoint++;
            }
            //将当前左指针元素大于等中轴值 赋值给当前右指针索引上
            if(moveLeftPoint<moveRightPoint){
                arr[moveRightPoint]=arr[moveLeftPoint];
            }

        }


        //当2个指针相撞(索引相等)将中轴值元素赋值给当前索引上,这样中轴值左边区域都是小于中轴值的元素 右边区域都是大于等于中轴值元素 这是从小到大排列
        if(moveLeftPoint==moveRightPoint){
            arr[moveLeftPoint]=pivotValue;
        }

        //将当前中轴值左边区域进行分区排序
        quickSortPitFillDataVersion3(arr,left,moveLeftPoint-1);

        //将当前中轴值右边区域进行分区排序
        quickSortPitFillDataVersion3(arr,moveRightPoint+1,right);
    }


    //快速排序值数据挖坑法 以最右元素为中轴值
    public static void quickSortPitFillDataVersion4(int []arr ,int left,int right){

        //判断当前是否有可比较排序区域
        if(left>=right)return;

        //创建一个中轴值 以最右元素为中轴值
        int pivoteValue=arr[right];

        //创建2个可移动指针
        int moveLeftPoint=left;
        int moveRightPoint=right;


        //循环当前可比较排序区域
        while(moveLeftPoint<moveRightPoint){

            //由于当前是最右元素为中心轴所有先移动 左指针

            //移动左指针
            while(moveLeftPoint<moveRightPoint&&arr[moveLeftPoint]<pivoteValue){
                //指针想后移动一位 以此类推
                moveLeftPoint++;
            }

            //将当前左指针元素赋值给右指针索引上
            if(moveLeftPoint<moveRightPoint){
                arr[moveRightPoint]=arr[moveLeftPoint];
            }

            //移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveRightPoint]>=pivoteValue){
                //将当前右指针向前移动一位 依次类推
                moveRightPoint--;

            }
            if(moveLeftPoint<moveRightPoint){
                arr[moveLeftPoint]=arr[moveRightPoint];
            }
        }

        //当前2个指针相撞(相等)
        if(moveLeftPoint==moveRightPoint){
            //将当前中轴值元素赋值给当前索引上
            arr[moveRightPoint]=pivoteValue;
        }


        //指针当前中轴值元素索引左边区域进行分区排序
        quickSortPitFillDataVersion4(arr,left,moveLeftPoint-1);

        //指针当前中轴值元素索引右边区域进行分区排序
        quickSortPitFillDataVersion4(arr,moveRightPoint+1,right);
    }


    //快速排序之挖坑填数法  中轴值为区域中的中心
    public static void quickSortPitFillDataVersion5(int arr[],int left,int right){

        //判断当前2个指针对应的区域是否可以执行排序分区
        if(left>=right) return;

        //创建一个中轴值
        int pivoteValue=arr[(left+right)/2];

        //创建2个可移动指针
        int moveLeftPoint=left;
        int moveRightPoint=right;

        boolean flag=true;

        //循环当前区域的的指针信息
        while(moveLeftPoint<moveRightPoint){

            //这里默认先移动左指针,
            while(moveLeftPoint<moveRightPoint&&arr[moveLeftPoint]<pivoteValue){
                //符合条件将左指针往后移动一位
                moveLeftPoint++;
            }
            if(moveLeftPoint<moveRightPoint){
                if(flag){
                    flag=false;
                    arr[(left+right)/2]=arr[moveLeftPoint];
                }else{
                    arr[moveRightPoint]=arr[moveLeftPoint];
                }
            }

            //移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveRightPoint]>=pivoteValue){
                moveRightPoint--;

            }
            if(moveLeftPoint<moveRightPoint){
                arr[moveLeftPoint]=arr[moveRightPoint];
            }
        }

        //当前2个指针相撞(相等)
        if(moveLeftPoint==moveRightPoint){
            //将当前中轴值元素赋值给当前索引上
            arr[moveRightPoint]=pivoteValue;
        }

        //指针当前中轴值元素索引左边区域进行分区排序
        quickSortPitFillDataVersion5(arr,left,moveLeftPoint-1);

        //指针当前中轴值元素索引右边区域进行分区排序
        quickSortPitFillDataVersion5(arr,moveRightPoint+1,right);

    }




    //快速排序之数据挖坑法版本6
    public static void quickSortPitFillDataVersion6(int []arr,int left,int right){
        //递归结束条件
        if(left>=right)return;

        //创建一个中轴值
        int pivotValue=arr[left];

        //创建2个移动指针
        int moveLeft=left;
        int moveRight=right;


        while(moveLeft<moveRight){

            //先移动右指针
            while(moveLeft<moveRight&&arr[moveRight]>=pivotValue){
                moveRight--;
            }
            if(moveLeft<moveRight){
                //将当前指针索引元素添加到左指针索引坑上
                arr[moveLeft]=arr[moveRight];
            }

            //移动左指针
            while(moveLeft<moveRight&&arr[moveLeft]<pivotValue){
                moveLeft++;
            }
            if(moveLeft<moveRight){
                //将当前指针索引元素添加到左指针索引坑上
                arr[moveRight]=arr[moveLeft];
            }
        }

        //将当前中轴值添加到2个指针相等的索引位置上
        arr[moveLeft]=pivotValue;


        //指针当前中轴值元素索引左边区域进行分区排序
        quickSortPitFillDataVersion6(arr,left,moveLeft-1);

        //指针当前中轴值元素索引右边区域进行分区排序
        quickSortPitFillDataVersion6(arr,moveRight+1,right);


    }

























    //快速排序之左右指针法
    public static void quickSortLeftRightPointVersion1(int []arr, int left ,int right){
        //判断当前是否可以在分区比较
        if(left>=right) return;

        //创建一个中轴值 以该区域索引范围内容的最后一个元素为中轴值
        int pivot=arr[right];

        //创建2个移动指针 一个是左指针一个是右指针
        int moveLeftPoint=left;
        int moveRightPoint=right;

        //只有当左指针比右指针小才能进行与该区域比较交换
        while(moveLeftPoint<moveRightPoint){


            //如果是以最右元素为中轴值,就要先移动左指针,同理如果是以最左元素为中轴值,就要先移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveLeftPoint]>=pivot){
                //符合以上条件就将当前左指针向后移动一位 依次类推
                moveLeftPoint++;
            }


            //移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveRightPoint]<=pivot){
                //符合以上条件就将当前左指针向后移动一位 依次类推
                moveRightPoint--;
            }



            //将2个指针索引元素交换给对方
            if(moveLeftPoint<moveRightPoint){
                int temp=arr[moveRightPoint];
                arr[moveRightPoint]=arr[moveLeftPoint];
                arr[moveLeftPoint]=temp;
            }

        }

        //当2个指针相撞(相等)
        if(moveLeftPoint==moveRightPoint){
            //将当前指针索引位置和中轴值进行之间的交换,这样已中轴值元素索引中心左边区域的索引元素都是小于的,右边的区域都是大于等于的
            arr[right]=arr[moveRightPoint];
            arr[moveRightPoint]=pivot;
        }

        //以中轴值索引为中心对左边区域进行递归分区排序
        quickSortLeftRightPointVersion1(arr,left,moveLeftPoint-1);
        quickSortLeftRightPointVersion1(arr,moveRightPoint+1,right);
    }



    //快速排序之左右指针法
    public static void quickSortLeftRightPointVersion2(int [] arr,int left,int right){
        //判断当前区域是否还需要排序
        if(left>=right) return;

        //创建一个中心轴值(以最左元素)
        int pivotValue=arr[left];

        //创建2个移动指针
        int moveLeftPoint=left;
        int moveRightPoint=right;

        //循环当前区域下的指针
        while(moveLeftPoint<moveRightPoint){

            //注意：如果是以左为中心轴就要先移动右指针 移动左指针 不然会出现排出的还是有乱序的

            //移动右指针
            while(moveLeftPoint<moveRightPoint&&arr[moveRightPoint]>=pivotValue){

                //符合条件将右指针向前移动一位以此类推
                moveRightPoint--;
            }

            //移动左指针
            while(moveLeftPoint<moveRightPoint&&arr[moveLeftPoint]<=pivotValue){

                //符合条件将左指针向后移动一位 依次类推
                moveLeftPoint++;

            }

            //如果当2个指针没有相撞(相等),说明2个指针对应的索引元素要相互交换
            if(moveLeftPoint<moveRightPoint){
                int temp=arr[moveRightPoint];
                arr[moveRightPoint]=arr[moveLeftPoint];
                arr[moveLeftPoint]=temp;
            }
        }

        //当指针相撞说明当前区域以中性轴为中心左边都是小于中心轴值右边都是大于等于中心轴值
        if(moveLeftPoint==moveRightPoint){

            //将当前中心轴元素与相撞索引元素进行交换
            arr[left]=arr[moveLeftPoint];
            arr[moveLeftPoint]=pivotValue;
        }

        //以中轴值索引为中心对左边区域进行递归分区排序
        quickSortLeftRightPointVersion2(arr,left,moveLeftPoint-1);

        //以中轴值索引为中心对右边区域进行递归分区排序
        quickSortLeftRightPointVersion2(arr,moveRightPoint+1,right);
    }





    public static void quickSortLeftRightPointVersion3(int []arr,int left,int right){

        if(left>=right)return;


        //中轴值
        int pivot=arr[left];

        //移动指针
        int moveLeft=left;
        int moveRight=right;

        while(moveLeft<moveRight){


            //先移动右指针
            while(moveLeft<moveRight&&arr[moveRight]>=pivot){
                moveRight--;
            }

            //移动左指针
            while(moveLeft<moveRight&&arr[moveLeft]<=pivot){
                moveLeft++;
            }

            if(moveLeft<moveRight){
                int temp=arr[moveLeft];
                arr[moveLeft]=arr[moveRight];
                arr[moveRight]=temp;
            }
        }

        //中轴值与移动左指针索元素替换
        arr[left]=arr[moveLeft];
        arr[moveLeft]=pivot;

        quickSortLeftRightPointVersion3(arr,left,moveLeft-1);
        quickSortLeftRightPointVersion3(arr,moveLeft+1,right);
    }

}




package com.king2.kewai;


import java.util.*;

//8皇后的演示思想，回溯和栈的思想
public class Practice8Queen {

    public  static int countNumber=0;
    public  static int successfulNumber=0;

    public static void main(String[] args) {

        ArrayList<List<Object>> resultList=new ArrayList<>();
        eightQueenVersion3(resultList,8,null);

        System.out.println(countNumber+"次比较判断次数");
        System.out.println(successfulNumber+"次组合成功参数");
        for(List<Object> arr:resultList){
            System.out.println(arr.toString());
        }

    }







    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/17 10:19
     * 描述: 使用回溯和双端队列实现八皇后
     * 参数名              类型                            说明
     * resultList         ArrayList<List<String>>        存储每一个八皇后组合的结果集合(用户提供)
     * indexDeque         LinkedDeque                    存储各个回调层的双端队列
     * layerNumber        int                            总层数（用户提供）
     * 返回：
     ******************************/
    public static void eightQueenVersion2(ArrayList<List<Object>> resultList,int  layerNumber,LinkedDeque indexDeque){

        if(indexDeque==null){
            indexDeque=new LinkedDeque();
        }

        //循环当前每一层下的索引信息
        for(int index=0;index<layerNumber;index++){

            //将当前回调函数层对应的索引存储到indexDeque中
            indexDeque.addLastNode(index);

            //判断当前回调函数层与前面的层进行规则比较
            if(moreSuccessful(indexDeque)){

                //判断当前层是否已经是最后回调层的函数
                if(layerNumber==indexDeque.length()){
                    successfulNumber++;

                    //创建集合封装组合好的一组八皇后索引封装到该集合中
                    List<Object> list=new ArrayList<>();
                    Object[] indexObjectArray = indexDeque.toArray();
                    Collections.addAll(list,indexObjectArray);

                    //将当前一组八皇后集合存储到结果集中
                    resultList.add(list);

                }else{

                    //没有到最后一层继续使用回调方法
                    eightQueenVersion2(resultList,layerNumber,indexDeque);
                }
            }

            //更新当前回调函数层对应的索引
            indexDeque.deleteLastNode();
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/17 10:19
     * 描述: 使用回溯和双端队列实现八皇后
     * 参数名              类型                            说明
     * resultList         ArrayList<List<String>>        存储每一个八皇后组合的结果集合(用户提供)
     * totalNumberLayer   int                            总层数（用户提供）
     * indexDeque         LinkedDeque                    存储各个回调层的双端队列
     * 返回：
     ******************************/
    public static void eightQueenVersion3(ArrayList<List<Object>> resultList,int  totalNumberLayer,LinkedDeque indexDeque){

        //判断双端链表队列是否为空
        if(indexDeque==null){
            indexDeque=new LinkedDeque();
        }

        //循环当前回溯方法层中索引值
        for(int index=0;index<totalNumberLayer;index++){

            //将当前回溯层中对应的当前循环索引存储到队列中
            indexDeque.addLastNode(index);

            //判断当前的层索引与之前层对应的索引冲突
            if(moreSuccessful(indexDeque)){
                //判断当前回溯层数书否是totalNumberLayer层
                if(totalNumberLayer==indexDeque.length()){
                    successfulNumber++;

                    //将当前一组八皇后成功的组合封装到一个集合中
                    List<Object> list=new ArrayList<>();
                    Object[] objects = indexDeque.toArray();
                    Collections.addAll(list,objects);

                    //将当前list存储到结果集中
                    resultList.add(list);
                }
                //由于不是在最后一层所以进行在回调直到最后一层
                else{
                    eightQueenVersion3(resultList,totalNumberLayer,indexDeque);
                }

            }
            //更新当前层对应的索引信息
            indexDeque.deleteLastNode();
        }
    }
    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 21:07
     * 描述: 当前层与之前层是否条件成立条件什么情况下成立,判断不能在同一列上其不能在同一斜线上,
     * 参数名              类型           说明
     * indexArray         LinkedDeque   存储每一层的索引信息
     * 返回：
     ******************************/
    public static boolean moreSuccessful(LinkedDeque indexDeque){
        countNumber++;
        //获取当前回溯方法的总层数
        int currLayerNnumber=indexDeque.length()-1;

        if(currLayerNnumber==0)return  true; //代表当前回溯在第一层无需比较

        //将当前各个层存储对应的索引信息转换成数组
        Object[] indexObjArray = indexDeque.toArray();

        //提示自身层的索引不要比较
        for(int index=0;index<currLayerNnumber;index++){

            int currIndex=(Integer) indexObjArray[index];                   //获取之前几层对应的索引
            int currLayerIndex=(Integer)indexObjArray[currLayerNnumber];    //获取当前层的索引

            //判断是否在同一列上
            if(currIndex==currLayerIndex){
                return  false;
            }

            //判断是否在同一斜线上 列差===行差 注意：这里使用一个绝对值函数 将负数变成正数
            if(currLayerNnumber-index==Math.abs(currLayerIndex-currIndex)){
                return false;
            }
        }
        return true;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 20:48
     * 描述: 实现8皇后方式
     * 参数名                  类型                                  说明
     * resultList            ArrayList<List<Integer>>              存储每一组组合的信息(对象用户提供)
     * layerNumber           int                                   代表当前回调在第几层数一开始从0层开始
     * indexArray            Integer[]                             存储各个层下对应的索引值
     * 返回：无
     * 提示：判断2个其中之间是否规则成立,判断不能在同一列上其不能在同一斜线上,
     *      如何判断同一斜线山就是2个棋子之间行差等于列差就代表同一斜线上
     ******************************/
    public static void eightQueen(ArrayList<List<Integer>> resultList, int layerNumber, Integer [] indexArray){

        //判断当前索引数组是否为空
        if(indexArray==null){
            indexArray=new Integer[8];
        }

        //循环当前层的索引信息
        for(int index=0;index<8;index++){

            //将当前层对应的索引存入到indexArray中
            indexArray[layerNumber]=index;

            //判断当前层与之前层的位置是否有冲突
            if(moreSuccessful(indexArray,layerNumber)){

                //条件成立判断是否回调到最后一层
                if(layerNumber==7){
                    successfulNumber++;
                    List<Integer> list = new ArrayList<Integer>(indexArray.length);
                    Collections.addAll(list, indexArray);

                    //已经实在最后一层了将当前对应的一组成功的8皇后组合存储到resultList中
                    resultList.add(list);
                }else {

                    //规则成功但是还没有走到最后一层所有继续回溯
                    eightQueen(resultList,layerNumber+1,indexArray);
                }
            }
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 21:07
     * 描述: 当前层与之前层是否条件成立条件什么情况下成立,判断不能在同一列上其不能在同一斜线上,
     * 参数名              类型           说明
     * indexArray         Integer []    各个层的索引信息
     * currLayerNnumber   int           当前层数
     * 返回：
     ******************************/
    public static boolean moreSuccessful(Integer [] indexArray,int currLayerNnumber){
        if(currLayerNnumber==0) return true;
        //循环比较当前层与之前的层对应的索引是否条件成立
        for(int index=0;index<currLayerNnumber;index++){

            /**
             * 提示：判断2个其中之间是否规则成立,判断不能在同一列上其不能在同一斜线上,
             *      如何判断同一斜线山就是2个棋子之间行差等于列差就代表同一斜线上
             */
            //判断是否在同一列上
            if(indexArray[index]==indexArray[currLayerNnumber]){
                return false;
            }

            //判断是否在同一斜线上 提示：之间行差等于列差就代表同一斜线上
            if(currLayerNnumber-index==Math.abs(indexArray[currLayerNnumber]-indexArray[index])){
                return false;
            }
        }
        return true;
    }
}

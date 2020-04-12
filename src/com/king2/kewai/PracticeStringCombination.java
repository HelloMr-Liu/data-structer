package com.king2.kewai;


import java.util.*;

//各个字符串组合练习
public class PracticeStringCombination {


    public static void main(String[] args) {

        String [] macthingArray={"上","下","左","右1"};
        ArrayList<List<String>> resultList=new ArrayList<>();

        //stringCombination(resultList,macthingArray,null);
        stringCombinationVersion3(resultList,macthingArray,null);
        for(List<String> arr:resultList){
            System.out.println(arr.toString());
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/15 20:43
     * 描述:
     * 参数名                      类型                                说明
     * resultList           ArrayList<ArrayList<String>>        封装各种组合的集合(用户提供)
     * macthingStringArray  String[]                            封装各个字符串(用户提供)
     * indexStack           Stack<Integer>                      存储各个回调方法层中对应的循环索引
     * 返回：无
     ******************************/
    public static void stringCombination(ArrayList<ArrayList<String>> resultList, String[] macthingStringArray, Stack<Integer> indexStack){
        //判断indexStack是否为空
        if(indexStack==null){
            indexStack=new Stack<>();
        }

        for(int index=0;index<macthingStringArray.length;index++){

            //判断当前回溯的索引是否在indexStack拥有,如果有就跳过本次回溯中的循环
            if(indexStack.contains(index)) continue;

            //将当前索引存储到索引栈中
            indexStack.push(index);

            //如果索引栈中的长度>=组合字符串数组的长度说明已经回溯到最后一个循环方法中
            if(indexStack.size()>=macthingStringArray.length){

                int count=0;
                int [] indexArr=new int[indexStack.size()];
                //创建一个数组存储对应的弹栈中的信息
                while(!indexStack.isEmpty()){
                    indexArr[count++]=indexStack.pop();
                }
                //创建一个新的集合存储当前一组组合后的字符串
                ArrayList<String> newArrayList=new ArrayList<>();

                for(int index2=indexArr.length-1;index2>=0;index2--){
                    //提示索引越大对应的元素是之前的循环回溯索引
                    newArrayList.add(macthingStringArray[indexArr[index2]]);

                    //除indexArr中第一个元素其他都再次存入到索引栈中
                    if(index2!=0){
                        indexStack.push(indexArr[index2]);
                    }
                }
                //将新集合存到resultList中
                resultList.add(newArrayList);
            }else{

                //使用回溯方法进行下一轮的回调
                stringCombination(resultList,macthingStringArray,indexStack);

                //调用的回溯方法结束将对应索引栈中对应索引给弹出更新对应位置回调层的索引信息
                indexStack.pop();
            }
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 19:43
     * 描述: 字符串交替组合方法
     * 参数名                      类型                          说明
     * resultList         ArrayList<ArrayList<String>>         组合结果集    (用户提供)
     * macthingArr        String []                            字符串匹配数组 (用户提供)
     * indexStack         Stack                                存储各个回溯下的索引栈对象(用户不需要提供)
     * 返回：
     ******************************/
    public static void stringCombinationVersion2(ArrayList<ArrayList<String>> resultList,String [] macthingArr,Stack<Integer> indexStack){

        //判断索引栈是否为空
        if(indexStack==null){
            indexStack=new Stack<>();
        }

        //遍历各个回调方法中的字符数组
        for(int index=0;index<macthingArr.length;index++){

            //判断当前回调中的循环索引是否在其它回调函数层中包含
            if(indexStack.contains(index)) continue;


            //如果没包含就将当前回调方法中的索引存入到索引栈中
            indexStack.push(index);

            //判断当前回调函数层数是否在最后一层
            if(indexStack.size()>=macthingArr.length){

                //代表的当前回调方法层最后一层,将索引栈中的索引取出并组合一组字符串组合

                //创建一个索引数组存储索引栈中的内容,
                int [] indexArray=new int[indexStack.size()];
                int count=0;
                while(indexStack.size()>0){
                    //提示：最靠索引栈栈顶的元素对应都是最后的字符  我们组合的顺序是从前往后组合
                    indexArray[count++]=indexStack.pop();
                }

                //创建一个集合封装对应当前的字符串组合信息
                ArrayList<String>  combinationList=new ArrayList<>();

                //循环索引数组
                for(int i=indexArray.length-1;i>=0;i--){

                    combinationList.add(macthingArr[indexArray[i]]);

                    if(i!=0){
                        //除了之前栈顶元素其他元素索引再次存入到索引栈中
                        indexStack.push(indexArray[i]);
                    }
                }
                resultList.add(combinationList);

            }else{

                //使用回调函数将层级走到最后一层为止
                stringCombinationVersion2(resultList,macthingArr,indexStack);

                //回调结束后返回当前层,并将当前层对应的循环索引值更新
                indexStack.pop();
            }
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/17 20:04
     * 描述: 对应一组字符串之前不同位置的组合方式
     * 参数名              类型                                说明
     * resultList       ArrayList<List<String>>             存储每一组不同字符串位置的组合集
     * macthingArray    String[]                            不同字符串组
     * indexDeque       Deque                               存储各个回溯层对应的循环索引
     * 返回：
     ******************************/
    public static void stringCombinationVersion3(ArrayList<List<String>> resultList, String[] macthingArray, Deque<Integer> indexDeque){
        //判断索引队列是否为空
        if(indexDeque==null){
            indexDeque=new ArrayDeque<>();
        }

        //循环当前回溯中的macthingArray数组
        for(int index=0;index<macthingArray.length;index++){

            //判断当前索引与之前的索引队列是否包含
            if(indexDeque.contains(index))continue;  //包含就跳过本次循环

            //将当前回溯中的循环索引存储到索引队列中
            indexDeque.addLast(index);

            //判断当前循环层是否是最后一次
            if(indexDeque.size()==macthingArray.length){

                //将当前一组符合条件的字符串匹配组合封装到一个集合上
                List<String> list=new ArrayList<>();
                Object[] objects = indexDeque.toArray();
                for(int i=0;i<objects.length;i++){
                    list.add(macthingArray[Integer.parseInt(objects[i].toString())]);
                }

                //将当前集合存储到结果集中
                resultList.add(list);

            }else{
                //还是没有回溯到最后一层继续回溯
                stringCombinationVersion3(resultList,macthingArray,indexDeque);
            }
            //更新当前的回溯层对应的索引信息
            indexDeque.removeLast();
        }
    }



}

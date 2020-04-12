package com.king2.kewai;


import java.util.*;

//练习笛卡尔积
//思路：使用栈(存储各个组下某一个的循环索引)和回溯(用于实现各个组的循环)
public class PracticeCartesian {

    public static void main(String[] args) {
        String [] [] macthingArray={
                {"中国","香港","澳门","美国"},
                {"红色","黑色","粉色"},
                {"16GB","32GB","64GB","128GB","156GB"}
        };
        ArrayList<List<String>> resultList=new ArrayList<>();

        implementCartesianVersion2(resultList,macthingArray,null);
        for(List<String> list:resultList){
            System.out.println(list.toString());
        }
    }



    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 9:14
     * 描述: 实现笛卡尔积方法
     * 参数名              类型                                 说明
     * resultList       ArrayList<ArrayList<String>>        封装每一组笛卡尔积结果(该对象用户提供)
     * macthingArray    String [] []                        封装每一组匹配的数组(该数组用户提供)
     * indexStack       Stack<Integer>                      存储各个回溯之间索引的栈(用户无需提供)
     * 返回：
     ******************************/
    public static void implementCartesian(ArrayList<ArrayList<String>> resultList, String [][] macthingArray, Stack<Integer> indexStack){

        //判断索引栈是否为空
        if(indexStack==null){
            //创建一个索引栈对象
            indexStack=new Stack<>();
        }

        //获取对应当前索引栈的个数匹配取出一组macthingArray中的组合循环
        String [] currMatching=macthingArray[indexStack.size()];

        for(int index=0;index<currMatching.length;index++){

            //将当前回溯中的索引存入到索引栈中
            indexStack.push(index);

            if(indexStack.size()==macthingArray.length){
                //已经回溯到最后一组macthingArray的匹配数组中了

                //将indexStack转换成一个数组
                int [] newIndexStack=new int [indexStack.size()];
                int count=0;
                //将索引栈中的依次取出存到新的索引数组中,提示：新的索引位置越小的索引位置元素对应的是macthingArray最后一组的数组匹配字符串
                while(indexStack.size()>0){
                    newIndexStack[count++]=indexStack.pop();
                }
                count=0;//将当前变量设置为0重新使用该变量

                //创建一个集合存储对应的新匹配的一组字符串组合
                ArrayList<String> newGroupList=new ArrayList<>();

                for(int index2=newIndexStack.length-1;index2>=0;index2--){
                    //获取当前对应的count的一组字符串数组
                    String [] currGroup=macthingArray[count++];

                    //将当前currGroup对应的index2数组索引元素存入到newGroupList中
                    newGroupList.add(currGroup[newIndexStack[index2]]);

                    //除了当前回溯对应的索引之外其他都重新存入到索引栈中
                    if(index2!=0){
                        indexStack.push(newIndexStack[index2]);
                    }
                }
                //将newGroupList存入到resultList集合中
                resultList.add(newGroupList);

            }else{

                //还没有回溯到最后macthingArray一组的数组匹配,继续回溯
                implementCartesian(resultList,macthingArray,indexStack);

                //将当前索引栈对应的当前回溯方法中索引值给弹出下次更新新索引值
                indexStack.pop();
            }
        }
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/16 20:07
     * 描述: 使用回调方法和栈实现笛卡尔集
     * 参数名              类型                                  说明
     * resultList         ArrayList<ArrayList<String>>         存储每一组组合好结果的集合（用户提供）
     * macthingArry       int [][]                             存储没一组的匹配内容数组(用户提供)
     * indexStack         Stack                                存储每一层回调方法中对应的索引
     * 返回：
     ******************************/
    public static void implementCartesianVersion(ArrayList<ArrayList<String>> resultList,String [][] macthingArry,Stack<Integer> indexStack){

        //判断索引栈中是否为空
        if(indexStack==null){
            indexStack=new Stack<>();
        }

        //循环当前回调层对应的一组字符组合数组
        String[] currArray = macthingArry[indexStack.size()];
        for(int index=0;index<currArray.length;index++){

            //将当前回调层对应的循环索引存储到索引栈中
            indexStack.push(index);

            //判断是否是在最后一层回调层中
            if(indexStack.size()==macthingArry.length){

                //创建一个索引数组存储索引栈中所有的元素
                int [] indexArray=new int[indexStack.size()];
                int count=0;
                while(indexStack.size()>0){

                    //提示：最靠索引栈栈顶的元素对应都是最后的字符  我们组合的顺序是从前往后组合
                    indexArray[count++]=indexStack.pop();
                }

                //创建一个集合存储一组组合信息
                ArrayList<String> cartesianList=new ArrayList<>();

                count=0;
                //循环循环当前索引数组，提示我们组合的顺序是从前往后组合
                for(int i=indexArray.length-1;i>=0;i--){
                    cartesianList.add(macthingArry[count++][indexArray[i]]);

                    //除了当前栈顶元素之外其他从新添加到索引栈顶
                    if(i!=0){
                        indexStack.push(indexArray[i]);
                    }

                }
                resultList.add(cartesianList);
            }else{

                //继续回调直到回调到最底层这样才能进行字符串组合操作
                implementCartesianVersion(resultList,macthingArry,indexStack);

                //当前回调的方法结束后就移除当前层对应的索引对当前的层的索引进行更新
                indexStack.pop();
            }
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/17 20:33
     * 描述: 使用双端队列和回溯实现笛卡尔积
     * 参数名              类型                              说明
     * resultList         ArrayList<List<String>>          存储每一组组合成功的笛卡尔积组合
     * macthingArray      String [][]                      每一组要用到的组合字符数组
     * indexDeque         Deque<Integer>                   存储每一次回溯层的索引信息
     * 返回：
     ******************************/
    public static void implementCartesianVersion2(ArrayList<List<String>> resultList, String [][] macthingArray, Deque<Integer> indexDeque){

        //判断当前双端队列是否为空
        if(indexDeque==null){
            indexDeque=new ArrayDeque<>();
        }

        //循环当前回溯层对应的一组字符组合数组
        String [] currStringMacthingArray=macthingArray[indexDeque.size()];
        for(int index=0;index<currStringMacthingArray.length;index++){

            //将当前回溯层对应的索引存储到双端队列中
            indexDeque.addLast(index);

            //判断当前是否在最后一层
            if(indexDeque.size()==macthingArray.length){

                //将当前的一组笛卡尔积组合存储到集合中
                List<String> list=new ArrayList<>();
                Object[] objects = indexDeque.toArray();
                for(int i=0;i<objects.length;i++){
                    String macthing[]=macthingArray[i];
                    list.add(macthing[Integer.parseInt(objects[i].toString())]);
                }

                //将一组组合存储到结果集中
                resultList.add(list);
            }else{

                //没回溯到最后一层继续回溯
                implementCartesianVersion2(resultList,macthingArray,indexDeque);
            }

            //更新当前层对应的索引信息
            indexDeque.removeLast();
        }
    }
}


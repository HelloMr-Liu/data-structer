package com.king2.back;


import java.util.ArrayList;
import java.util.Stack;

//迷宫回溯算法演示
public class MazeBackDemo {

    public static void main(String[] args) {






        String [] routeArray={"上","下","左","右"};

        //创建一个集合里面存储的也是一个集合 该集合中封装了一组字符串组合
        ArrayList<ArrayList<String>> resultList=new ArrayList<>();
        setRouteBack(resultList,routeArray,null);
        for(ArrayList<String> arrList:resultList){
            System.out.println("============"+arrList.toString()+"方向的迷宫走法开始===========");

            //创建一个7行8列的二维数组(迷宫)  0代表：为走过的路 1代表：围墙    2代表：已走过   3：当前的路是死路 4:挡板
            int [] [] maze=new int[7][8];


            //添加对应的围墙，围墙就表示挡住对应的去路 （设置成1就代表一个围墙）
            for(int index=0;index<maze.length;index++){
                for(int index2=0;index2<maze[index].length;index2++){
                    if(index>=1&&index<=5){
                        maze[index][0]=1;
                        maze[index][7]=1;
                    }else{
                        maze[index][index2]=1;
                    }
                }
            }

            System.out.println("============添加对应的挡板===========");
            maze[3][1] =4;
            maze[3][2] =4;

            //默认一开始从指定位置的位置开始
            goMaze(maze,1,1,arrList);

            showMaze(maze);

            System.out.println("============"+arrList.toString()+"方向的迷宫走法结束===========");
            System.out.println();
        }

    }

    //走迷宫方法
    public static boolean goMaze(int [][] maze,int line,int column,ArrayList<String> directionList){

        //判断当前对应的路径是否是终点
       if(maze[5][6]==2){
           return true;
       }else{

           //代表当前的路没有走过
           if(maze[line][column]==0){
               //并将当前路设置已走过
               maze[line][column]=2;

               //设置对应的路线
               //一开始向下走
               if(goMaze(maze,directionLine(directionList.get(0),line),directionColumn(directionList.get(0),column),directionList)){
                   return true;

               //向右边走
               }else if(goMaze(maze,directionLine(directionList.get(1),line),directionColumn(directionList.get(1),column),directionList)){
                   return true;

               //向上走
               }else if(goMaze(maze,directionLine(directionList.get(2),line),directionColumn(directionList.get(2),column),directionList)){
                   return true;

               //向左走
               }else if(goMaze(maze,directionLine(directionList.get(3),line),directionColumn(directionList.get(3),column),directionList)){
                   return true;

               //上下左右路都走不通将当前路设置为3 表示走不通的路
               }else{
                   System.out.println("当前路 行"+line+"==列"+column+"值是"+maze[line][column]);
                   maze[line][column]=3;
                   System.out.println("当前路是死路 行"+line+"==列"+column+"值是"+maze[line][column]);
                   return  false;
               }

           }else{
               //代表当前路是死路返回上一步走的路线
               return  false;
           }
       }
    }


    //方向行的操作
    public static int  directionLine(String directionStr,int lineValue){
        if(directionStr.equals("下")){
            lineValue+=1;

        }else if(directionStr.equals("上")){
            lineValue-=1;
        }
        return  lineValue;
    }

    //方向列的操作
    public static int  directionColumn(String directionStr,int columnValue){
        if(directionStr.equals("右")){
            columnValue+=1;

        }else if(directionStr.equals("左")){
            columnValue-=1;
        }
        return  columnValue;
    }



    public static void showMaze(int [][] maze){
        for(int [] arr1:maze){
            for(int arr:arr1){
                System.out.print(arr+"\t");
            }
            System.out.println();
        }
    }

    public static void  setRoute(){

        String [] routeArray={"上","下","左","右"};


        //创建一个数值存储各种字符串的组合
        String [] []strCombinationArray=new String [4*6][4];

        int count=0;

        for(int i=0;i<routeArray.length;i++){

            //获取当前索引第一个字符串
            String currIStr=routeArray[i];

            for(int j=0;j<routeArray.length;j++){

                if(j==i)continue;

                //获取获取第二个非i的索引字符串
                String currJStr=routeArray[j];

                for(int t=0;t<routeArray.length;t++){

                    if(t==i||t==j) continue;

                    //获取第三个非i j的索引字符串
                    String currTStr=routeArray[t];

                    for(int k=0;k<routeArray.length;k++){

                        if(k==i||k==j||k==t) continue;

                        //获取第四个非 i j t的索引字符串
                        String currKStr=routeArray[k];


                        //创建一个一位数组封装当前组合
                        String [] currCombinationArray={currIStr,currJStr,currTStr,currKStr};
                        //将currCombinationArray存储到strCombinationArray二维数组中
                        strCombinationArray[count++]=currCombinationArray;
                    }
                }
            }
        }
                                               

        for(String [] strArray:strCombinationArray){
            for(String str:strArray){
                System.out.print(str+"\t");
            }
            System.out.println();
            System.out.println();
        }

    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/15 14:14
     * 描述: 使用回溯的方式解决多个字符串组合有多少种组合法
     * 参数名        类型            说明
     * resultList   ArrayList      存储各种组合的集合对象 (用户提供)
     * macthingArr  String[]       要匹配组合的字符串数组(用户提供)
     * indexArr     Stack<Integer> 存储当前对应的一些循环索引(用户不需要提供)
     * 返回： 无
     ******************************/
    public static void setRouteBack(ArrayList<ArrayList<String>> resultList, String [] macthingArr, Stack<Integer> indexStack){

        //判断indexArr是否为空
        if(indexStack==null){
            indexStack=new Stack<Integer>();
        }

        //循环当前要匹配的字符串数组
        for(int index=0;index<macthingArr.length;index++){

            //如果当前索引index等于indexArr数组其中的一个值就跳过本次循环
            if(indexStack.contains(index)) continue;

            //并将当前的索引添加到indexList.add(index);中
            indexStack.add(index);

            //判断当前indexList长度是否等于macthingArr长度,代表已经回溯到了一个最后的一个循环方法里面
            if(indexStack.size()>=macthingArr.length){

                //创建一个数组存储出栈的各个内容 提示：在栈的最下面才是真正指定之前的字符串索引
                int [] indexData=new int[indexStack.size()]; int count=0;
                while(indexStack.size()>0){
                    indexData[count++]=indexStack.pop();
                }

                //依次将当前的索引指定的字符串存储到一个新的集合中
                ArrayList<String> newCombinationList=new ArrayList<>();

                //遍历索引数组中的索引依次取出组装成一个字符串组合
                for(int currIndex=indexData.length-1;currIndex>=0;currIndex--){
                    newCombinationList.add(macthingArr[indexData[currIndex]]);

                    if(currIndex!=0){
                        //除currIndex==0 将当前数组中的元素重新存入到栈中
                        indexStack.push(indexData[currIndex]);
                    }
                }

                //将当前的newCombinationList存储到中
                resultList.add(newCombinationList);

            }else{

                //代表还没有到达最后的回调函数中的循环使用回调当前函数
                setRouteBack(resultList,macthingArr,indexStack);
                //回调结束后将栈顶元素弹出更新当前栈顶元素信息
                indexStack.pop();
            }
        }
    }

}

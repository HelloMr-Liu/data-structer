package com.king2.kewai;


import java.util.ArrayList;

//迷宫回溯方法
public class PracticeMazeBack {

    public static void main(String[] args) {



        String [] macthingArray={"上","下","左","右"};
        ArrayList<ArrayList<String>> resultList=new ArrayList<>();

        PracticeStringCombination.stringCombination(resultList,macthingArray,null);
        for(ArrayList<String> arr:resultList){

            //创建一个二维数组(迷宫) 0===未走过  1==围墙    2==已走过   3===走过且思路  4===设置了挡板
            int [][] mazeArray=new int[7][8];

            //设置对应的围墙
            for(int i=0;i<mazeArray.length;i++){
                for(int j=0;j<mazeArray[i].length;j++){

                    if(i>=1&&i<=5){
                        mazeArray[i][0]=1;
                        mazeArray[i][7]=1;
                    }else{
                        mazeArray[i][j]=1;
                        mazeArray[i][j]=1;
                    }
                }
            }

            //设置了挡板
            mazeArray[3][1]=4;
            mazeArray[3][2]=4;

            System.out.println("==============="+arr.toString()+"方向=============");

            mazeBackVersion1(mazeArray,1,1,arr);

            showMaze(mazeArray);
            System.out.println("==============="+arr.toString()+"方向结束=============");
            System.out.println();
            System.out.println();

        }
    }




    public static void showMaze(int [][] maze){
        for(int [] arr1:maze){
            for(int arr:arr1){
                System.out.print(arr+"\t");
            }
            System.out.println();
            System.out.println();
        }
    }




    //迷宫回溯版本1
    public static boolean mazeBackVersion1(int [][] mazeArray, int line, int column, ArrayList<String> directionList){

        if(mazeArray[5][6]==2){
            //代表当前重点已经被找到
            return true;
        }
        //表示当前的路径没有走过
        if(mazeArray[line][column]==0){
            //将当前路设置成已走过状态
            mazeArray[line][column]=2;

            //方向1
            if(mazeBackVersion1(mazeArray,directionLine(directionList.get(0),line),directionColumn(directionList.get(0),column),directionList)){
                return true;
            //方向2 下
            }else if(mazeBackVersion1(mazeArray,directionLine(directionList.get(1),line),directionColumn(directionList.get(1),column),directionList)){
                return true;
            //方向3 左
            }else if(mazeBackVersion1(mazeArray,directionLine(directionList.get(2),line),directionColumn(directionList.get(2),column),directionList)){
                return true;
            //方向4 右
            }else if(mazeBackVersion1(mazeArray,directionLine(directionList.get(3),line),directionColumn(directionList.get(3),column),directionList)){
                return true;
            }else{
                System.out.println("当前路行："+line+"=列："+column+"走不通");
                //说明当前这个行列的位置是走不通的路设置成3
                mazeArray[line][column]=3;
                return false;
            }

        }else{
            //代表当前的路有可能已经是死路被挡的路所以返回该路
            return  false;

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
}

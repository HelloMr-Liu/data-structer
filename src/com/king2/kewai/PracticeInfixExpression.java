package com.king2.kewai;


import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//中缀表达式练习
public class PracticeInfixExpression {

    public static void main(String[] args) {

        //创建一个表达式
        //String expression="(70+20*((66-2)*(5+5*20-40)-60*5)*4-4)";
        String expression="-20*5-20*4+1";

        //创建一个数值栈
        Stack<Integer> numberStack=new Stack<>();

        //创建一个符号栈集存储每一个括号里面的符号栈对象,默认存储一个栈对象
        Stack<Stack<String>> symbolStacks=new Stack<>();
        symbolStacks.push(new Stack<String>());

        //创建一个数值拼接变量,用于处理多位数的
        String numberLink="";


        //将当前表达式转换成一个字符数组
        char [] charArray=expression.toCharArray();

        //遍历当前字符数组
        for(int index=0;index<charArray.length;index++){

            //获取当前字符
            char currentChar=charArray[index];


            if(currentChar==' ') continue;

            //判断当前是否是运算符
            if(isSymbol(currentChar+"")){
                //特俗判断，判断当前是否是减号字符
                if(currentChar=='-'){
                    //判断当前索引是否是第一个 或者前一个字符是非数字并且前一个字符不等于右括号‘)’
                    if(index==0||!((charArray[index-1]+"").matches("\\d+"))&&charArray[index-1]!=')'){

                        //连接到numberLink变量上 当前减号代表的是一个负号
                        numberLink+=currentChar;
                        //并跳过本次循环
                        continue;
                    }
                }

                //获取当前符号栈集中栈顶对象
                Stack<String> currStack=symbolStacks.peek();
                if(!currStack.isEmpty()){

                    while(!currStack.isEmpty()){
                        //判断是否当前字符的运算等级<=当前符号栈栈顶元素的运算等级
                        if(symbolLevel(currentChar)<=symbolLevel(currStack.peek().toCharArray()[0])){

                            //获取栈顶元素
                            String topElement=currStack.pop();

                            //获取数值栈中的栈顶元素和次顶元素
                            int num1=numberStack.pop();
                            int num2=numberStack.pop();
                            //使用次顶元素运算与栈顶元素得到的结果再次存入到数值栈中
                            numberStack.push(calculate(num1,num2,topElement));
                        }else{
                            break;
                        }

                    }

                }
                //将当前字符存入到当前符号栈中
                currStack.push(currentChar+"");

            //判断当前是否是括号
            }else if(currentChar=='('|| currentChar==')'){

                if(currentChar=='('){
                    //如果是左括号就创建一个新的符号栈对象
                    Stack<String> newSymbolStack=new Stack<>();

                    //并将当前新符号栈对象存入到 符号栈集中
                    symbolStacks.push(newSymbolStack);
                }else{

                    //取出符号栈集中的栈顶元素对象
                    Stack<String> stackTopObject=symbolStacks.pop();
                    //遍历当前栈顶对象
                    while(!stackTopObject.isEmpty()){
                        //获取栈顶元素
                        String topElement=stackTopObject.pop();

                        //获取数值栈中的栈顶元素和次顶元素
                        int num1=numberStack.pop();
                        int num2=numberStack.pop();
                        //使用次顶元素运算与栈顶元素得到的结果再次存入到数值栈中
                        numberStack.push(calculate(num1,num2,topElement));
                    }
                }


            //当前是数值
            }else{

                //拼接当前字符数值
                numberLink+=currentChar;

                //判断当前索引是否是最后一个
                if(index==charArray.length-1){
                    //将当前数组拼接变量转换成数值存入到数值栈中并将当前遍历设置为空
                    numberStack.push(Integer.parseInt(numberLink));
                    numberLink="";

                }else{

                    //判断当前下一个字符不等于空，并且下一个字符是非数值
                    if(charArray[index+1]!=' '&&!isNumeric(charArray[index+1]+"")){
                        //将当前数组拼接变量转换成数值存入到数值栈中并将当前遍历设置为空
                        numberStack.push(Integer.parseInt(numberLink));
                        numberLink="";
                    }
                }

            }
        }

        //循环完毕后将依次的将符号栈集中的内容取出与对应的数组栈栈顶和次顶元素做比较
        while(!symbolStacks.isEmpty()){

            Stack<String> stack=symbolStacks.pop();
            while(stack.size()>0){
                //获取数值栈中的栈顶元素和次顶元素
                int num1=numberStack.pop();
                int num2=numberStack.pop();
                //使用次顶元素运算与栈顶元素得到的结果再次存入到数值栈中
                numberStack.push(calculate(num1,num2,stack.pop()));
            }
        }

        System.out.println("结果是"+numberStack.pop());




    }


    public static boolean isSymbol(String ch){
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
    }

    /**
     * 判断是否为数字(正负数都行)
     * @param str 需要验证的字符串
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 19:58
     * 描述: 判断符号等级  等级越大 数值就越大
     ******************************/
    public static int symbolLevel(int ch){
        if(ch=='+'|| ch=='-'){
            return 1;
        }else if(ch=='*'|| ch=='/'){
            return 2;
        }else{
            return -1;
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 9:25
     * 描述: 计算2个数值
     ******************************/
    public static int calculate(int num1,int num2,String ch){
        switch (ch){
            case "+":
                return num2+num1; //由于是栈特点所以最前面的数取的时候最末尾

            case "-":
                return num2-num1; //由于是栈特点所以最前面的数取的时候最末尾

            case "*":
                return num2*num1; //由于是栈特点所以最前面的数取的时候最末尾

            case "/":
                return num2/num1; //由于是栈特点所以最前面的数取的时候最末尾

            default:
                return -1;
        }
    }
}

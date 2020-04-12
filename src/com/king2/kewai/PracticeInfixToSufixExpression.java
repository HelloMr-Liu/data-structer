package com.king2.kewai;


import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//中缀转后缀表达式
public class PracticeInfixToSufixExpression {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> strings = inffixToSufixExpression("(70+20*((66-2)*(5+5*20-40)-60*5)*4-4)");
        sufixExpressionOpreatin(strings);
    }

    //中缀表达式转后缀表达式
    public static ConcurrentLinkedQueue<String> inffixToSufixExpression(String infixExpression){

        //将中缀表达式转换成一个字符数组
        char[] charArray=infixExpression.toCharArray();


        //创建一个符号栈对象
        Stack<String> symbolStack=new Stack<>();

        //创建一个内容队列
        ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();

        //创建一个数组拼接变量用于处理多位数中
        String numberLink="";

        //遍历当前字符数组
        for(int index=0;index<charArray.length;index++){

            //获取当前字符
            char currentChar=charArray[index];

            if(currentChar==' ') continue;


            //判断当前是否是运算符
            if(isSymbol(currentChar+"")){

                //特殊判断判断当前是否是减号
                if (currentChar == '-') {

                    //判断当前索引是否是第一个或者判断前一个字符是否是非数值并且前一个字符是否不等于右括号‘)’
                    if(index==0||!((charArray[index-1]+"").matches("\\d+"))&&charArray[index-1]!=')'){

                        //当前减号代表的是符号拼接到数值拼接变量上
                        numberLink+=currentChar;
                        //跳过本次循环
                        break;
                    }
                }

                //判断当前符号栈中是否为空
                //判断当前字符运算等级<=符号栈栈顶元素等级
                while(!symbolStack.isEmpty()&&symbolLevel(currentChar)<=symbolLevel(symbolStack.peek().toCharArray()[0])){

                    //将符号栈顶元素弹出存储到内容队列中 直到栈顶元素小于当前运算符等级就退出弹栈
                    queue.add(symbolStack.pop());
                }

                //将当前字符存储到符号栈中
                symbolStack.push(currentChar+"");

            //判断当前是否是括号
            }else if(currentChar=='('|| currentChar==')'){

                if(currentChar=='('){

                    //将当前左括号存储到符号栈中
                    symbolStack.push(currentChar+"");
                }else{
                    //依次的将栈顶元素取出直到取出的栈顶元素时一个左括号就停止弹栈
                    while(!symbolStack.isEmpty()){
                        String topStackElement=symbolStack.pop();
                        if(topStackElement.equals("(")){
                            break;
                        }else{
                            //将当前弹出的栈顶元素存储到内容队列中
                            queue.add(topStackElement);
                        }
                    }
                }
            //数值
            }else{
                //拼接当前字符
                numberLink+=currentChar;

                //判断当前索引是否是最后一个
                if(index==charArray.length-1){

                    //将当前数值拼接变量存储到内容队列上
                    queue.add(numberLink);
                    numberLink="";
                }else{
                    //判断下一个字符不等于空并且下一个字符是非数值
                    if(charArray[index+1]!=' '&&!isNumeric(charArray[index+1]+"")){
                        //将当前数值拼接变量存储到内容队列上
                        queue.add(numberLink);
                        numberLink="";
                    }
                }
            }
        }

        //字符数组循环结束后将多余的符号栈元素依次取出存储到内容队列中
        while(symbolStack.size()>0){
            queue.add(symbolStack.pop());
        }
        return queue;
    }

    public static void sufixExpressionOpreatin(ConcurrentLinkedQueue<String> sufixExpressionQueue){
        //创建一个数值栈对象存储运算数值信息
        Stack<Integer> numberStack=new Stack<>();

        while(!sufixExpressionQueue.isEmpty()){

            //取出对应的元素
            String element=sufixExpressionQueue.poll();

            if(isNumeric(element)){
                //转换成数值存入到数值栈中
                numberStack.push(Integer.parseInt(element));
            }else{
                //将数值栈中的栈顶元素和次顶元素取出
                int number1=numberStack.pop();
                int number2=numberStack.pop();

                //与当前元素做运算运算后的结果再次存入到数值栈中
                numberStack.push(calculate(number1,number2,element));
            }
        }
        System.out.println("结果："+numberStack.pop());
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

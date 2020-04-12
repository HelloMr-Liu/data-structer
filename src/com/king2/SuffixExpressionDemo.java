package com.king2.stack;


import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/13 14:26
 * 描述: 后缀表达式演示
 ******************************/
public class SuffixExpressionDemo {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<String> strings = SuffixExpression.infixToSuffixExpression("12+(2-3)*4+10/5");
        System.out.println(strings);
        SuffixExpression.suffixExpressionOperation(strings);



    }
}



/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/13 14:27
 * 描述: 后缀表达式类
 ******************************/
class SuffixExpression{




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
    public static int calculate(int num1,int num2,int ch){
        switch (ch){
            case '+':
                return num2+num1; //由于是栈特点所以最前面的数取的时候最末尾

            case '-':
                return num2-num1; //由于是栈特点所以最前面的数取的时候最末尾

            case '*':
                return num2*num1; //由于是栈特点所以最前面的数取的时候最末尾

            case '/':
                return num2/num1; //由于是栈特点所以最前面的数取的时候最末尾

            default:
                return -1;
        }
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/13 14:28
     * 描述: 将中缀表达式转后缀表达式过程
     ******************************/
    public static ConcurrentLinkedQueue<String> infixToSuffixExpression(String infixExpression){
        //将中缀表达式转换成一个字符数组
        char[] charArray=infixExpression.toCharArray();

        //创建一个符号栈、
        Stack<Character> symbolStack=new Stack<>();
        //创建一个内容队列
        ConcurrentLinkedQueue<String> contentQueue=new ConcurrentLinkedQueue<>();

        //创建一个字符数组拼接变量用于拼接数组多位数用
        String numberLink="";

        for(int index=0;index<charArray.length;index++){
                //获取当前字符
                char currChar=charArray[index];

                if(currChar!=' '){
                    //判断是否是运算符
                    if(isSymbol(currChar+"")){

                        //判断是否是减号
                        if(currChar=='-'){

                            //如果当前索引是第一个或者上一个字符是非数值的话代表当前减号是一个负数
                            if(index==0||(!((charArray[index-1]+"").matches("\\d+"))&&(charArray[index-1]!=')'))){
                                //将当前减号拼接到  numberLink中
                                numberLink+=currChar;
                                //并跳过本次循环
                                continue;
                            }
                        }

                        //判断符号栈是否为空
                        if(!symbolStack.isEmpty()){

                            //循环当前符号栈信息
                            while(!symbolStack.isEmpty()){
                                //判断当前字符是否小于等于栈顶元素,直到栈顶元素等级小当前字符
                                if(symbolLevel(currChar)<=symbolLevel(symbolStack.peek())){
                                    //弹出栈顶信息存入到内容队列中
                                    contentQueue.add(symbolStack.pop()+"");
                                }else{
                                    break;
                                }
                            }

                        }
                        //将当前字符存储到符号栈中
                        symbolStack.add(currChar);


                        //判断是否是括号
                    }else if(currChar== '('|| currChar==')'){

                        if(currChar== '('){
                            //将当前括号存入到符号栈中
                            symbolStack.add(currChar);
                        }else{
                            //循环当前符号栈
                            while(!symbolStack.isEmpty()){
                                //获取当前栈顶元素判断是否是左括号 如果是左括号就停止弹栈
                                char deleteTopStack=symbolStack.pop();
                                if(deleteTopStack!='('){
                                    //弹出栈顶信息存入到内容队列中
                                    contentQueue.add(deleteTopStack+"");
                                }else{
                                    break;
                                }
                            }
                        }

                        //数值
                    }else{
                        //将当前的数值拼接到对应的numberLink变量上
                        numberLink+=currChar;

                        //判断当前索引是否是最后一个
                        if(index>=charArray.length-1){
                            //将当前数值变量存入到内容队列中 并将当前数值变量zhi为空
                            contentQueue.add(numberLink);
                            numberLink="";
                            break;
                        }

                        //判断下一个字符是否是非数值
                        if(charArray[index+1]!=' '&&!isNumeric(charArray[index+1]+"")){
                            //将当前数值拼接变量存入到内容队列中并且将拼接变量设置为空
                            contentQueue.add(numberLink);
                            numberLink="";
                        }
                    }
                }
        }
        //将符号栈中的内容依次弹出存储到内容队列中
        while(!symbolStack.isEmpty()){
            contentQueue.add(symbolStack.pop()+"");
        }

        return contentQueue;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/13 15:05
     * 描述: 后缀表达式运算结果
     ******************************/
    public static  void suffixExpressionOperation(ConcurrentLinkedQueue<String> suffixExpressionQueue){

        //创建一个数值栈
        Stack<Integer> numberStack=new Stack<>();

        //循环队列
        while(!suffixExpressionQueue.isEmpty()){

            //获取当前队列取出的元素信息
            String currQueueContent=suffixExpressionQueue.poll();

            if(isNumeric(currQueueContent)){
                //当前内容是数值直接存入到数值栈中
                numberStack.add(Integer.parseInt(currQueueContent));
            }else{
                //取出数值栈中的栈顶元素和次顶元素得到的结果再次存入到数值栈中 注意：是次顶元素运算栈顶元素
                Integer result=calculate(numberStack.pop(),numberStack.pop(),currQueueContent.toCharArray()[0]);
                numberStack.add(result);
            }
        }
        System.out.println("结果为："+numberStack.pop());
    }



}
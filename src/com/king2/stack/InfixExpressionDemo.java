package com.king2.stack;


import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/13 15:34
 * 描述: 中缀表达式演示
 ******************************/
public class InfixExpressionDemo {

    public static void main(String[] args) {
        InfixExpression.infixExpressionOperation("-1+2");


    }
}



/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/13 15:34
 * 描述: 中缀表达式类
 ******************************/
class InfixExpression{


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

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/13 20:03
     * 描述: 中缀表达式运算
     ******************************/
    public static void infixExpressionOperation(String infixExpression){

        //创建一个数值栈存储数值及对应的结果内容
        Stack<Integer> numberStack=new Stack<>();

        //创建一个符号栈集 存储各个括号里面的符号栈对象,默认有一个符号栈对象
        Stack<Stack<String>> symbolStacks=new Stack<>();
        symbolStacks.add(new Stack<String>());

        //将对应的表达式转换成字符数组
        char[] charArrray=infixExpression.toCharArray();

        //创建一个数值字符拼接变量用于处理多位数用
        String numberLink="";

        for (int index=0;index<charArrray.length;index++) {

            //获取当前字符
            char currChar = charArrray[index];

            if(currChar==' ') {
                continue;
            }

            //判断当前是否是运算符
            if (isSymbol(currChar + "")) {
                //判断当前是否是减号
                if (currChar == '-') {
                    //判断当前字符是否是第一个或者前一个字符是非数值并且前一个字符不等于右括号  这样该减号就是运算符其实就是一个数值
                    if (index == 0 || !(charArrray[index - 1] + "").matches("\\d+") && charArrray[index - 1] != ')') {
                        //由于当前减号的作用是负数所以拼接到当前numberLink变量上
                        numberLink += currChar;
                        //并跳过本次循环
                        continue;
                    }
                }

                //判断当前符号栈是否为空
                if (!symbolStacks.peek().isEmpty()) {

                    //获取当前符号栈及中栈顶的符号栈对象信息
                    Stack<String> currStack = symbolStacks.peek();

                    //判断当前符号等级是否小于等于当前符号栈集栈顶的符号栈的栈顶元素的内容
                    while (!symbolStacks.peek().isEmpty()&&symbolLevel(currChar) <= symbolLevel(currStack.peek().toCharArray()[0])) {

                        //将数值栈中的栈顶元素和次顶元素取出与当前符号栈栈顶元素做对应的运算 注意是次顶元素运算栈顶元素
                        int num1 = numberStack.pop();
                        int num2 = numberStack.pop();

                        //并将当前的运算的结果存入到数值栈中
                        numberStack.push(calculate(num1, num2, currStack.pop()));
                    }
                }

                //将当前运算符添加到当前符号栈集栈顶的符号栈中
                symbolStacks.peek().add(currChar + "");


                //判断是否是括号
            } else if (currChar == '(' || currChar == ')') {
                if (currChar == '(') {

                    //如果是左括号将会创建一个新的符号栈存储当前括号中的一些符号信息
                    Stack<String> newSymbolStack = new Stack<>();

                    //并将创建好的符号栈存入到符号栈集中
                    symbolStacks.push(newSymbolStack);

                } else {
                    //如果是右括号将取出当前符号栈集中栈顶的符号栈信息对象
                    Stack<String> currDeleteStack = symbolStacks.pop();

                    //依次循环当前的currDeleteStack中的元素取出与数值栈中的信息做运算 运算的结果再次存入到数值栈中
                    while (!currDeleteStack.isEmpty()) {
                        //获取数值栈中栈顶元素和次顶元素
                        int num1 = numberStack.pop();
                        int num2 = numberStack.pop();

                        //运算后的结果存入到数值栈中
                        numberStack.push(calculate(num1, num2, currDeleteStack.pop()));
                    }

                }

                //判断是否是数值
            } else {
                //将当前的字符拼接到对应的数值变量上
                numberLink += currChar;

                //判断当前索引是否是最后的
                if (index >= charArrray.length - 1) {
                    //将当前数值变量信息存入到数值栈中
                    numberStack.push(Integer.parseInt(numberLink));
                    //并将当前的变量设置为空
                    numberLink = "";
                    break;
                }

                //判断当前下一个字符是否是非数值如果是非数值就将当前数值变量存入到数值栈中
                if(charArrray[index+1]!=' '&&!isNumeric(charArrray[index+1]+"")){
                    numberStack.push(Integer.parseInt(numberLink));
                    numberLink="";  //便于下次另一个多位数处理
                }
            }
        }

        //循环符号栈集中各个符号栈信息依次取出遍历运算
        while(!symbolStacks.isEmpty()){

            //获取当前符号栈集中的栈顶信息
            Stack<String> currTopStack=symbolStacks.pop();

            while (!currTopStack.isEmpty()){
                //获取出对应数值栈中的栈顶元素和次顶元素并对应着对应的符号做运算
                int num1=numberStack.pop();
                int num2=numberStack.pop();

                //结果集存入到数值栈中
                numberStack.push(calculate(num1,num2,currTopStack.pop()));
            }
        }

        System.out.println(infixExpression+"运算结果为："+numberStack.pop());

    }

}

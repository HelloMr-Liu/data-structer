package com.king2.stack;


import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/12 19:40
 * 描述: 逆波兰表达式演示
 * 参数名        类型           说明
 * xx1         Integer        年龄
 * 返回：
 ******************************/
public class InversePolishExpressionDemo {
    public static void main(String[] args) {

        ConcurrentLinkedQueue<String> queue=InversePolishExpression.infixToSuffix("(70+20*((66-2)*(5+5*20-40)-60*5)*4-4)"); // 70  20 66 2 - * 4 * 4 - +
        InversePolishExpression.postfixExpressionOperation(queue);
    }
}



/******************************
 * 作者: 刘梓江
 * 时间: 2019/12/12 19:40
 * 描述: 逆波兰表达类
 ******************************/
class InversePolishExpression{


    public static boolean currCharIsSymbol(String ch){
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
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
     * 时间: 2019/12/12 19:41
     * 描述: 中缀表达式转后缀表达式
     ******************************/
    public static   ConcurrentLinkedQueue<String>  infixToSuffix(String infixExpression){
        //创建一个符号栈 用于存储运算符
        Stack<Character>  symbolStack=new Stack<>();

        //创建一个数值字符串队列
        ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<>();

        //将当前中缀表达式转换成字符数组进行遍历扫描
        char [] charArray=infixExpression.trim().toCharArray();

        //创建一个数值拼接字符串变量
        String numberLinke="";

        //遍历字符数组
        for(int index=0;index<charArray.length;index++){

            //获取当前索引字符
            char currChar=charArray[index];

            //判断当前字符是否是空白字符
            if(currChar!=' '){

                //判断当前是否是运算符
                if(currCharIsSymbol(currChar+"")){

                    //与栈顶的信息做比较
                    getsSymbolLowerThanItself(symbolStack,queue,currChar);

                    //将当前字符运算符存储到符号栈中
                    symbolStack.push(currChar);

                //判断是否是左括号
                }else if(currChar == '('){

                    //将当前字符运算符存储到符号栈中
                    symbolStack.push(currChar);

                //判断是否是右括号
                }else if(currChar == ')'){
                    //将符号栈中的依次弹出直到第一次出现了左括号停止
                    while(symbolStack.size()>0){

                        char deleteChar=symbolStack.pop();

                        if(deleteChar!='('){
                            //将弹出栈中的运算符依次存入到数值字符串队列中
                            queue.add(deleteChar+"");
                        }else{
                            //跳出循环弹栈
                            break;
                        }
                    }

                //数值
                }else{
                    //将当前数值拼接到numberLinke变量中
                    numberLinke+=currChar;

                    if(index>=charArray.length-1){
                        //直接将当前的numberLinke存入到数值字符串队列中
                        queue.add(numberLinke);
                        numberLinke="";

                    }else{
                        //判断下一个字符是否是非数值
                        if(!(charArray[index+1]+"").matches("\\d+")){
                            //直接将当前的numberLinke存入到数值字符串队列中
                            queue.add(numberLinke);
                            numberLinke="";
                        }
                    }

                }
            }
        }

        //判断符号栈中是否有内容如果有就依次弹出并赋值给字符串数值队列
        while(symbolStack.size()>0){
            char currPopChar=symbolStack.pop();
            if(currPopChar!='('){
                //直接将当前的currPopChar存入到数值字符串队列中
                queue.add(currPopChar+"");
            }
        }
        return queue;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 22:09
     * 描述: 设置符号栈的栈顶的符号等级比当前指定的符号等级低
     ******************************/
    public static void getsSymbolLowerThanItself(Stack<Character> symbolStack,Queue<String> quque,char ch){

        while(!symbolStack.isEmpty()){
            //查看当前栈顶符号
            char topStackData=symbolStack.peek();
            if(symbolLevel(ch)>symbolLevel(topStackData)&& ch!='('){
                break;
            }else{
                //否则就继续弹栈到队列中
                quque.add(symbolStack.pop()+"");

            }
        }
    }
    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 20:33
     * 描述: 与队列中的的内容进行后缀表达式运算
     ******************************/
    public static void postfixExpressionOperation(Queue<String> queue){

        //创建一个数值栈
        Stack<Integer> numberOperationStack=new Stack<>();

        //循环遍历队列中的信息
        while(!queue.isEmpty()){
            String currQueueData=queue.poll();
            if(currCharIsSymbol(currQueueData)){
                //获取栈中的栈顶元素和次顶元素
                Integer topData=numberOperationStack.pop();
                Integer onceTopData=numberOperationStack.pop();

                //得到的结果再次放入数值栈中
                numberOperationStack.push(calculate(topData,onceTopData,currQueueData.toCharArray()[0]));

            }else{
                //将当前队列数据转换成数值并存入到数值栈中
                numberOperationStack.push(Integer.parseInt(currQueueData));
            }
        }
        System.out.println("最后的结果："+numberOperationStack.pop());
    }

}
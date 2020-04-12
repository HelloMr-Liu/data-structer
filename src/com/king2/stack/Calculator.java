package com.king2.stack;

public class Calculator {

    public static void main(String[] args) {

        //获取一个表达式
        String expression="20*5-20*4+1" ;

        //创建一个字符数值存储表达式内容
        char [] charArray=expression.toCharArray();

        //创建一个数值栈
        ArrayStack2 numberStack=new ArrayStack2(100);

        //创建一个符号栈
        ArrayStack2 symbolStack=new ArrayStack2(100);

        //创建2个数值变量 存储数值栈中最前栈顶的2个数值
        int num1; int num2;

        //创建1一个字符变量用于存储符号内容
        int sym;

        //创建一个变量用于多数值链接
        String strNumber="";

        //循环整个表达式
        for(int index=0;index<charArray.length;index++){

            //获取当前索引指定的字符
            char charStr=charArray[index];

            //判断当前字符是否是（
            if(charStr=='('){
                //调用栈类中的静态方法处理括号的处理方法
                int currIndex=ArrayStack2.bracketOperation(charArray,index,numberStack,new ArrayStack2(100));

                //更新当前索引值
                index=currIndex;

            //判断当前字符是数值还是符号
            }else if(ArrayStack2.isSymbol(charStr)){

                //判断符号栈中是否为空
                if(!symbolStack.isNull()){

                    //判断当前符号是否小于等于栈顶符号
                    while(!symbolStack.isNull()&&ArrayStack2.symbolLevel(charStr)<=ArrayStack2.symbolLevel(symbolStack.showTop())){
                        //获取栈中的2个最前值
                        num1=numberStack.pop();
                        num2=numberStack.pop();
                        //获取栈中的符号
                        sym=symbolStack.pop();

                        //计算后将新结果存入栈中
                        numberStack.push(ArrayStack2.calculate(num1,num2,sym));
                    }
                }
                //符号栈中为空直接存入符号
                symbolStack.push(charStr);
            }else{

                //拼接当前字符
                strNumber+=charStr;


                if(index>=charArray.length-1){
                    //是数值就直接进入数值栈中
                    numberStack.push(Integer.parseInt(strNumber));
                }else{
                   //判断下一个字符是否是符号如果是符号就将当前数值字符串存储到数值栈中
                   if(ArrayStack2.isSymbol(charArray[index+1])){
                       //是数值就直接进入数值栈中
                       numberStack.push(Integer.parseInt(strNumber));
                       //添加成功后将strNumber设置为空
                       strNumber="";
                   }
                }
            }
        }

        //就符号栈中的内容进行依次出栈运算
        while(!symbolStack.isNull()){
            //获取对应的数值栈中的最前栈顶数值
            num1=numberStack.pop();
            num2=numberStack.pop();

            //获取当前的符号内容运算新结果存入到数值栈中
            numberStack.push(ArrayStack2.calculate(num1,num2,symbolStack.pop()));
        }
        System.out.println(expression+"表达式结果为："+numberStack.pop());


    }

}

//先创建一个栈,直接使用前面创建好
//定义一个 ArrayStack2 表示栈, 需要扩展功能
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 8:54
     * 描述: 初始化栈容器
     ******************************/
    public ArrayStack2(int initMaxSize){
        this.maxSize=initMaxSize;
        this.stack=new int[this.maxSize];
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 8:56
     * 描述: 判断栈容器是否为满
     ******************************/
    public boolean isFull(){
        return top==maxSize-1;
    }

    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 8:57
     * 描述: 判断栈容器是否为空
     ******************************/
    public boolean isNull(){
        return top==-1;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 8:58
     * 描述: 压栈
     ******************************/
    public void push(int value){
        if(isFull()){
            System.out.println("栈为满");
            return;
        }
        top++;
        this.stack[this.top]=value;
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 8:59
     * 描述: 出栈
     ******************************/
    public int pop(){
        if(isNull()){
            throw  new RuntimeException("栈为空");
        }
        return this.stack[this.top--];
    }


    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 9:38
     * 描述: 查看栈顶信息
     ******************************/
    public int showTop(){
        return stack[top];
    }
    /******************************
     * 作者: 刘梓江
     * 时间: 2019/12/12 9:15
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
     * 时间: 2019/12/12 9:16
     * 描述: 判断是不是符号
     ******************************/
    public static boolean isSymbol( int ch){
        return ch=='+' || ch=='-' || ch=='*' || ch=='/';
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
     * 时间: 2019/12/12 10:29
     * 描述: 使用回调函数实现括号里面的内容
     * 参数名              类型           说明
     * charArray         char[]         表达式字符数组
     * expressionIndex  int            表达式当前的索引位置
     * numberStack      ArrayStack2    栈对象 存储数值的栈
     * symbolStack      ArrayStack2    栈对象 存储符号的栈
     ******************************/
    public static  int bracketOperation(char[] charArray,int expressionIndex,ArrayStack2 numberStack,ArrayStack2 symbolStack ){
        //创建2个数值变量 存储数值栈中最前栈顶的2个数值
        int num1; int num2;

        //创建1一个字符变量用于存储符号内容
        int sym;

        //创建一个变量用于多数值链接
        String strNumber="";

        //循环整个表达式(为什么要+1应为当前的索引未+1的时候是括号位置，+1后是阔后第一个内容)
        for(int index=expressionIndex+1;index<charArray.length;index++){

            //更新当前变量索引值
            expressionIndex=index;

            //获取当前索引指定的字符
            char charStr=charArray[index];

            if(charStr=='('){

                //进行回调当前方法进行另一个括号方法中执行的内容
                expressionIndex=bracketOperation(charArray,expressionIndex,numberStack,new ArrayStack2(100));

                //并更新当前扫描器索引
                index=expressionIndex;

            }else if(charStr==')'){

                //说明当前括号中循环已经结束跳出即可
                break;

            }else{

                //判断当前字符是否是符号
                if(ArrayStack2.isSymbol(charStr)){
                    //判断当前的符号是否<=当前符号栈的栈顶符号
                    if(!symbolStack.isNull()){
                        while(!symbolStack.isNull()&&ArrayStack2.symbolLevel(charStr)<=ArrayStack2.symbolLevel(symbolStack.showTop())){

                            //获取当前数值栈中最前的两个栈顶的数值
                            num1=numberStack.pop();
                            num2=numberStack.pop();

                            //并获取符号栈顶的内容对该2个数值做运算结果入到数值栈中
                            numberStack.push(ArrayStack2.calculate(num1,num2,symbolStack.pop()));
                        }
                    }
                    //并将当前符号存入到符号栈中
                    symbolStack.push(charStr);

                }else{
                  //将当前字符拼接到 strNumber中
                    strNumber+=charStr;

                    //判断当前字符是否是最后一个
                    if(index>=charArray.length-1){
                        //直接将当前数值字符串转换成数值存储到数值栈中（不过当前这个代码块中 理论是执行不到的）
                        numberStack.push(Integer.parseInt(strNumber));

                    }else{

                        //判断当前索引+1的字符是否是非数值如果非数值就将当前数值字符串拼接的结果转换成数值存入到数值栈中
                        if(ArrayStack2.isSymbol(charArray[index+1])|| charArray[index+1]==')'){
                            numberStack.push(Integer.parseInt(strNumber));
                            //并将数值设置为空用于下一个多位数拼接用
                            strNumber="";
                        }
                    }
                }
            }
        }

        //剩余的符号栈中依次取出与数值栈中做比较
        while(!symbolStack.isNull()){

            //获取对应数值栈顶中前2个数值
            num1=numberStack.pop();
            num2=numberStack.pop();

            //将当前符号对该2个数值做运算  运算完以后的结果入到数值栈中
            numberStack.push(ArrayStack2.calculate(num1,num2,symbolStack.pop()));
        }
        //返回当前扫描器对应的索引
        return expressionIndex;
    }
}

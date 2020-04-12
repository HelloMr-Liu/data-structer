package com.king2.kewai;


import java.text.SimpleDateFormat;
import java.util.*;

//指定某一段时间段  按年份输出有多个星期一 到星期天
public class DateDemo {


    public static void main(String[] args) throws Exception {

        //创建一个日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //创建一个Map对象用于封装各个年份的YearDate对象
        Map<Integer, YearDate> yearMap = new TreeMap<>();

        //创建一个开始时间
        Date startDate = dateFormat.parse("2019-1-1");
        Calendar startCalentdate = Calendar.getInstance(); //创建一个开始的日期对象
        startCalentdate.setTime(startDate);


        //创建一个结束时间
        Date endDate = dateFormat.parse("2023-01-10");

        //创建一个死循环 直到开始日期大于结束日期为止
        while (true) {
            //判断当前开始日期是否大于结束日期
            if (startDate.getTime() > endDate.getTime()) {
                break;
            }

            //获取当天是第几年
            int currYear = startCalentdate.get(Calendar.YEAR);
            //按照年份获取对应的YearDate对象
            if(yearMap.get(currYear)==null){
                //创建一个当前年对象
                yearMap.put(currYear,new YearDate(currYear));
            }
            YearDate currYearDate=yearMap.get(currYear);


            //获取当天是那个月 注意由于返回的1月份是从0开始的
            int currMonth = startCalentdate.get(Calendar.MONTH);
            Map<Integer, WeekInMonth> currYearMonthMap= currYearDate.getMonthMap();
            //获取当前年份对应的月对象
            if(currYearMonthMap.get(currMonth+1)==null){
                currYearMonthMap.put(currMonth+1,new WeekInMonth(currMonth+1));
            }
            WeekInMonth weekMonth=currYearMonthMap.get(currMonth+1);

            //获取当前当天是周几 1 -7 注意 1是星期天
            int currWeek = startCalentdate.get(Calendar.DAY_OF_WEEK);
            weekMonth.setWeek(currWeek);

            //对当天时间天数往后加1
            startCalentdate.add(Calendar.DATE,1);

            //更新当前Date对象
            startDate= startCalentdate.getTime();
        }

        //输出yearMap
        Iterator<Integer> iterator = yearMap.keySet().iterator();
        while(iterator.hasNext()){
            Integer key=iterator.next();
            //获取年份对象
            YearDate yearDate = yearMap.get(key);
            Map<Integer, WeekInMonth>  currMonthMap=yearDate.getMonthMap();
            //循环遍历各个年份下的月份
            Iterator<Integer> iterator1 = currMonthMap.keySet().iterator();
            while(iterator1.hasNext()){
                //获取当前月份
                Integer currMonth=iterator1.next();
                System.out.println("当前"+yearDate.getYear()+"/"+currMonth+"月");
                WeekInMonth weekInMonth = currMonthMap.get(currMonth);
                System.out.println("星期一："+weekInMonth.mondayNumber+"天\t"+"星期二："+weekInMonth.tuesdayNumber+"天\t"+
                                   "星期三："+weekInMonth.wednesdayNumber+"天\t"+"星期四："+weekInMonth.thursdayNumber+"天\t"+
                                   "星期五："+weekInMonth.fridayNumber+"天\t"+"星期六："+weekInMonth.saturdayNumber+"天\t"+
                                   "星期天："+weekInMonth.sundayNumber+"天\t"
                );
                System.out.println();

            }
            System.out.println("====================");

        }


    }
}



class YearDate{

    private int currYear; //指定年份
    private Map<Integer,WeekInMonth> monthMap=new TreeMap<>(); //封装各个月信息
    public Integer getYear() {
        return currYear;
    }
    public void setYear(Integer year) {
        this.currYear = year;
    }
    public Map<Integer, WeekInMonth> getMonthMap() {
        return monthMap;
    }
    public YearDate(int year){
        currYear=year;
    }
    public void setMonthMap(Map<Integer, WeekInMonth> monthMap) {
        this.monthMap = monthMap;
    }
}


//封装某一个月有几个星期一到星期天类
class WeekInMonth{

    public int currentMonth;    //当前月份
    public int mondayNumber;    //存储星期一有多少天
    public int tuesdayNumber;   //存储星期二有多少天
    public int wednesdayNumber; //存储星期三有多少天
    public int thursdayNumber;  //存储星期四有多少天
    public int fridayNumber;    //存储星期五有多少天
    public int saturdayNumber;  //存储星期六有多少天
    public int sundayNumber;    //存储星期天有多少天
    public static Integer test1=new Integer(1);

    public void setWeek(int currWeek){
        //老外的第一天是从星期日开始的
        switch (currWeek){
            case 1:
                sundayNumber++;     //星期天
                break;
            case 2:
                mondayNumber++;     //星期一
                break;
            case 3:
                tuesdayNumber++;    //星期二
                break;
            case 4:
                wednesdayNumber++;  //星期三
                break;
            case 5:
                thursdayNumber++;   //星期四
                break;
            case 6:
                fridayNumber++;     //星期五
                break;
            case 7:
                saturdayNumber++;   //星期六
                break;
            default:break;
        }
    }
    public WeekInMonth(int currMonth){
        currentMonth=currMonth;
    }

}

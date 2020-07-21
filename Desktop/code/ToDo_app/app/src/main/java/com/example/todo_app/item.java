package com.example.todo_app;

public class item {
    public String Str;
    public int Year;
    public int Month;
    public int Day;
    public int Hour;
    public int Minute;
    public item(String str,int year,int month,int day,int hour,int minute){
        Str=str;
        Year=year;
        Month=month;
        Day=day;
        Hour=hour;
        Minute=minute;
    }

    public String getStr() {
        return Str;
    }

    public int getYear() {
        return Year;
    }

    public int getMonth() {
        return Month;
    }

    public int getDay() {
        return Day;
    }

    public int getHour() {
        return Hour;
    }

    public int getMinute() {
        return Minute;
    }
}

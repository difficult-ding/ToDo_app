package com.example.todo_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class add_item extends AppCompatActivity {
    private EditText inputText;
    private Calendar c;
    private int hourofDay;
    private int minute;
    private int year;
    private int month;
    private int day;
    private Button send;
    private Button SetClock;
    private Button SetDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        c=Calendar.getInstance();
        send= findViewById(R.id.add_item);
        inputText=(EditText)findViewById(R.id.edit_item);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=inputText.getText().toString();
                Intent intent=new Intent(add_item.this,MainActivity.class);
                Task task=new Task();
                task.setItem(content);
                task.save();
                startActivity(intent);
            }
        });
        SetClock=findViewById(R.id.set_clock);
        SetDate=findViewById(R.id.set_date);
        SetClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour=c.get(Calendar.HOUR_OF_DAY);
                int mMinute=c.get(Calendar.MINUTE);
                new TimePickerDialog(add_item.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hourofDay=i;
                        minute=i1;
                        Toast.makeText(add_item.this,i+" "+i1,Toast.LENGTH_SHORT).show();
                    }
                },mHour,mMinute,true).show();
            }
        });
        SetDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                int mMonth=c.get(Calendar.MONTH);
                int myear=c.get(Calendar.YEAR);
                new DatePickerDialog(add_item.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        year=i;
                        month=i1;
                        day=i2;
                        Toast.makeText(add_item.this,i+" "+i1+" "+i2,Toast.LENGTH_SHORT).show();
                    }
                },myear,mMonth,mDay).show();
            }
        });
    }
    public int getHourofDay(){
        return hourofDay;
    }
    public int getMinute(){
        return minute;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
}
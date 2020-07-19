package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class add_item extends AppCompatActivity {
    private EditText inputText;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
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
    }
}
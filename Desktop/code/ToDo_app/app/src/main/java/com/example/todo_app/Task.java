package com.example.todo_app;

import org.litepal.crud.DataSupport;

public class Task extends DataSupport {
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

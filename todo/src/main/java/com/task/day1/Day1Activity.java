package com.task.day1;

import android.os.Bundle;

import com.example.baselibrary.ui.BaseActivity;
import com.example.todo.R;

import org.jetbrains.annotations.Nullable;

/**
 * @author Lai
 * @time 2018/8/20 16:01
 * @Description
 */
public class Day1Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_day_1);
    }
}

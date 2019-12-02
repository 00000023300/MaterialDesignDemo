package com.example.materialdesigndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.materialdesigndemo.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Rec,Tab,Nav,Coor,Not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置标题栏标题
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Android 5.0 新特性");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setSubtitle("Material Design Support 控件");
        setSupportActionBar(toolbar);


        Rec=findViewById(R.id.Rec);
        Tab=findViewById(R.id.Tab);
        Nav=findViewById(R.id.Nav);
        Coor=findViewById(R.id.Coor);
        Not=findViewById(R.id.Not);

        Rec.setOnClickListener(this);
        Tab.setOnClickListener(this);
        Nav.setOnClickListener(this);
        Coor.setOnClickListener(this);
        Not.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Rec:
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;

            case R.id.Tab:
                intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.Nav:
                intent = new Intent(MainActivity.this, DrawerNavigationActivity.class);
                startActivity(intent);
                break;

            case R.id.Coor:
                intent = new Intent(MainActivity.this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.Not:
                intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;



        }
    }
}

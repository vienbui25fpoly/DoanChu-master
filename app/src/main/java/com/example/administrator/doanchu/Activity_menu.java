package com.example.administrator.doanchu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_menu extends AppCompatActivity {
    Button button_batdau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button_batdau =(Button)findViewById(R.id.batdau);
        // thiết lập sự kiện khi lick nút bắt đầu
        button_batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển activity
                Intent intent = new Intent(Activity_menu.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

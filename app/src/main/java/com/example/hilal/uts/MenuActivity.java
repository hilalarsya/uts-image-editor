package com.example.hilal.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btngal = (Button) findViewById(R.id.btngal);
        Button btnedit = (Button) findViewById(R.id.btnedit);

        btngal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte1 = new Intent(MenuActivity.this, ListActivity.class);
                startActivity(inte1);
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte2 = new Intent(MenuActivity.this, EditActivity.class);
                startActivity(inte2);
            }
        });

    }
}


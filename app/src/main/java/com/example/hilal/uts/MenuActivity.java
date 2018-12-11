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
        Button btnvideo = (Button) findViewById(R.id.btnvideo);
        Button btnplace = (Button) findViewById(R.id.btnplace);

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

        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte3 = new Intent(MenuActivity.this, MediaActivity.class);
                startActivity(inte3);
            }
        });

        btnplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte4 = new Intent(MenuActivity.this, MapActivity.class);
                startActivity(inte4);
            }
        });

    }
}


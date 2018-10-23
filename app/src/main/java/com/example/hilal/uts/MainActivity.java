package com.example.hilal.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String tittle;
    public static String body;

    public TextView txtitle, txbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtitle = (TextView)findViewById(R.id.txtitle);
        txbody = (TextView)findViewById(R.id.txbody);

        if(tittle!=null || body!=null){
            txtitle.setText(tittle);
            txbody.setText(body);
        }

        Button btnmenu = (Button) findViewById(R.id.btnmenu);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(inte);
            }
        });
    }
}

package com.example.hilal.uts;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class ListActivity extends AppCompatActivity implements FotoListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null)
        {
            ListDetailFragment details = new ListDetailFragment();
            //mulai fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            details.setFoto(id);

            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            //commit (lakukan) transaksi
            ft.commit();
        }
        else
        {
            Toast.makeText(this, "Item" + id + " was clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.getApplicationContext(), DetailActivity.class);
            Bundle b = new Bundle();
            b.putDouble("id", id);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}

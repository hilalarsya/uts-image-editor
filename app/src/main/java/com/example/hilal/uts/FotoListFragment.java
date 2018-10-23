package com.example.hilal.uts;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotoListFragment extends ListFragment {

    static interface Listener {
        void itemClicked(long id);
    };

    private Listener listener;

    public FotoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] names = new String[Foto.foto.length];

        for (int i = 0; i < names.length; i++) {
            names[i] = Foto.foto[i].getName(); }
        ArrayAdapter<String> adapter = new ArrayAdapter<>( inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener)context;
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
//        super.onListItemClick(listView, itemView, position, id);
//        Toast.makeText(getActivity(), "Item " + position + " was clicked", Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
//        Bundle b = new Bundle();
//        String[] names = new String[Resep.resepmakanan.length];
//        b.putInt("id",position);
//        intent.putExtras(b);
//        startActivity(intent);
    }

}

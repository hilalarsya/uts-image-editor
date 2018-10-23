package com.example.hilal.uts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDetailFragment extends Fragment {

    private long fotoId;

    public ListDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_detail, container, false);
    }

    public void setFoto(long id){
        this.fotoId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            Foto foto = Foto.foto[(int) fotoId];
            title.setText(foto.getName());

            ImageView img1 = (ImageView) view.findViewById(R.id.gambar1);
            img1.setImageResource(foto.getGambar1());

            ImageView img2 = (ImageView) view.findViewById(R.id.gambar2);
            img2.setImageResource(foto.getGambar2());

        }
    }

}

package com.openclassrooms.mynavdrawer.Controllers.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.mynavdrawer.Controllers.Activities.UneCategorie;
import com.openclassrooms.mynavdrawer.R;

/**
 * Created by Philippe on 20/11/2017.
 */

public class FragmentCategories extends Fragment {

    GridView gridView;
    //Le tableau contenant toutes les categories
    String[] nomCategories = {"Administration","Aggriculture","Communication", "Comptabilité", "Consultation","Direction", "Droit", "Eau Hygiène", "Economie", "Education", "Formation", "Informatique"};
    int[] imagesCategories = {R.drawable.administration, R.drawable.agri, R.drawable.communication, R.drawable.compta, R.drawable.consultation, R.drawable.direction, R.drawable.droit, R.drawable.eau, R.drawable.economie, R.drawable.education, R.drawable.formation, R.drawable.code};

    public static FragmentCategories newInstance() {
        return (new FragmentCategories());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        //setContentView(R.layout.activity_main);

        //finding listview
        gridView = v.findViewById(R.id.gridview);

        CategorieAdapter categorieAdapter = new CategorieAdapter();
        gridView.setAdapter(categorieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),nomCategories[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), UneCategorie.class);
                intent.putExtra("name", nomCategories[i]);
                intent.putExtra("image", imagesCategories[i]);
                intent.putExtra("id", i);
                startActivity(intent);

            }
        });
        return v;
    }

    private class CategorieAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nomCategories.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.cat_name);
            ImageView image = view1.findViewById(R.id.cat_imageView);

            name.setText(nomCategories[i]);
            image.setImageResource(imagesCategories[i]);
            return view1;

        }
    }
}
package com.example.tfalkenberg.myfragrment;

/**
 * Created by tfalkenberg on 06/04/2017.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by tfalkenberg on 05/04/2017.
 */

public class MyAdapter extends ArrayAdapter<Livre> {

    private List<Livre> livres;

    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Livre> livres) {
        super(context, resource, livres);
        this.livres = livres;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView titreLivre = (TextView) view.findViewById(android.R.id.text1);
        Livre livre = livres.get(position);
        titreLivre.setText(livre.getTitre());


        return view;
    }

    public List<Livre> getData() {
        return livres;
    }

    public void reloadData(List<Livre> livres) {
        getData().clear();
        getData().addAll(livres);
        notifyDataSetChanged();
    }
}

package com.example.tfalkenberg.myfragrment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tfalkenberg on 06/04/2017.
 */


public class ListLivreActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;


    private LivresDao livreBdd;;

    private ListView listView;

    public final static String LIVRE= "LIVRE";
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlist);

        //Récupération de la référence
        listView = (ListView) findViewById(R.id.list_livre);

        //Instanciation du DAO
        livreBdd = new LivresDao(this);

        //Charger les données
        livreBdd.open();
        List<Livre> listLivres = livreBdd.getAlllivres();
        livreBdd.close();

        //affiche liste
        myAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1, listLivres);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(android.R.id.text1);


                Intent intent = new Intent(ListLivreActivity.this, LivreActivity.class);
                intent.putExtra(LIVRE,textView.getText().toString()); // envoie de donner
                startActivityForResult(intent, REQUEST_CODE);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE) {
            //TODO Recharger la liste des livres
            livreBdd.open();
            List<Livre> listLivres = livreBdd.getAlllivres();
            livreBdd.close();
            myAdapter.reloadData(listLivres);


        }
    }
}

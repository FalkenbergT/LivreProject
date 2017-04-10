package com.example.tfalkenberg.myfragrment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tfalkenberg on 07/04/2017.
 */

public class LivreActivity extends AppCompatActivity {

    private Button erase;
    private LivresDao livreBdd;
    private TextView titre;
    private TextView cat;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.livre);

        titre =(TextView) findViewById(R.id.titre_livre);
        cat =(TextView) findViewById(R.id.cat_livre);


        erase=(Button) findViewById(R.id.erase);
        erase.setOnClickListener(eraseListener);
        Intent intent = getIntent();
        TextView textDisplay = (TextView) findViewById(R.id.titre_livre);
        textDisplay.setText(intent.getStringExtra(ListLivreActivity.LIVRE));
        TextView catDisplay = (TextView) findViewById(R.id.cat_livre);

        //Instanciation du DAO
        livreBdd = new LivresDao(this);
        //Charger les données
        livreBdd.open();
        String isbn=livreBdd.getLivreWithTitre(intent.getStringExtra(ListLivreActivity.LIVRE)).getIsbn();
        catDisplay.setText(isbn);
        List<Livre> listLivres = livreBdd.getAlllivres();
        livreBdd.close();




    }

    private View.OnClickListener eraseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            livreBdd.open();
            livreBdd.removeLivreWithTitre(titre.getText().toString());
            livreBdd.close();

            setResult(1);
            finish();


        }
    };
}

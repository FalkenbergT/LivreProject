package com.example.tfalkenberg.myfragrment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText entree;
    private EditText recherche;
    private Button save;
    private Button search;
    private Button turn;
    private LivresDao livreBdd;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;

    public final static String TEXTE= "TEXTE";
    public final static String LIVRE= "LIVRE";
    public String categorie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        entree=(EditText) findViewById(R.id.entree) ;
        recherche=(EditText) findViewById(R.id.recherche) ;
        save = (Button)findViewById(R.id.save);
        search = (Button)findViewById(R.id.search);
        turn=(Button) findViewById(R.id.turn);
        search.setOnClickListener(searchListener);
        turn.setOnClickListener(turnListener);
        save.setOnClickListener(saveListener);

        //Création d'une instance de ma classe LivresDao
        livreBdd = new LivresDao(this);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataGroup, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                 categorie = ((TextView)view.findViewById(R.id.lblListItem)).getText().toString();
                return false;
            }
        });

    }






            private View.OnClickListener saveListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String titreLivre = entree.getText().toString();
                    Livre livre = new Livre(titreLivre,categorie);
                    livreBdd.open();
                    livreBdd.insertLivre(livre);
                    livreBdd.close();
                    entree.setText("");
                }
            };

            private View.OnClickListener turnListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListLivreActivity.class);
                    startActivity(intent);
                }
            };

    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String titreLivre = recherche.getText().toString();
            livreBdd.open();

            if (livreBdd.LivreWithTitre(titreLivre).equals("présent"))
            {
                Intent intent = new Intent(MainActivity.this, LivreActivity.class);
                intent.putExtra(LIVRE,recherche.getText().toString());
                recherche.setText("");
                startActivity(intent);
            }
            else if (livreBdd.LivreWithTitre(titreLivre).equals("absent")){
                recherche.setText("");
            }

            livreBdd.close();


        }
    };

    private void prepareListData() {
        listDataGroup = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataGroup.add("Catégorie");

        // Adding child data
        List<String> Categorie = new ArrayList<String>();
        Categorie.add("Thriller");
        Categorie.add("Science Fiction");
        Categorie.add("Animé");
        Categorie.add("Policier");
        Categorie.add("Documentaire");
        Categorie.add("Héroïque Fantaisie");
        Categorie.add("Sport");


        listDataChild.put(listDataGroup.get(0), Categorie); // Header, Child data

        }
}

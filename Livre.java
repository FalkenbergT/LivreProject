package com.example.tfalkenberg.myfragrment;

// définition du constructeur livre
public class Livre {

    private int id;
    private String isbn;
    private String titre; // caractèristique du constructeur

    public Livre() {
    }

    public Livre(String titre, String isbn) { //attribution du isbn et du titre
        this.titre = titre;
        this.isbn = isbn;

    }

    public int getId() { //recupérer l'id
        return id;
    }

    public void setId(int id) { //attribué une id
        this.id = id;
    }

    public String getIsbn() { // récupérer un isbn
        return isbn;
    }

    public void setIsbn(String isbn) { // attribué un Isbn
        this.isbn = isbn;
    }

    public String getTitre() { // récupérer le Titre
        return titre;
    }

    public void setTitre(String titre) { //attribué un Titre
        this.titre = titre;
    }

}

package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Plat {
    int id_plat;
    String nom_plat;
   double prix_unitaire;

    public Plat(int id_plat, String nom_plat, double prix_unitaire) {
        this.id_plat = id_plat;
        this.nom_plat = nom_plat;
        this.prix_unitaire = prix_unitaire;
    }

    public Plat(String nom_plat, double prix_unitaire) {
        this.nom_plat = nom_plat;
        this.prix_unitaire = prix_unitaire;
    }
    @Override
    public  String toString(){
String prixFormat= String.format("%9.2f",prix_unitaire);
        String platFormat= String.format("%9s",nom_plat);
        return String.format ("%9s",id_plat+". "+platFormat+" :"+prixFormat);
    }

    public static List<Plat> getPlat(Connection connection) throws SQLException {
        // Pouvoir lister des personnages
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from Plat");

        List<Plat> platList = new ArrayList<>();

        while (resultats.next()) {
            Plat dbPlat = new Plat(resultats.getInt("id_plat"),
                    resultats.getString("nom_plat"),
                    resultats.getFloat("prix_unitaire"));
            platList.add(dbPlat);

            System.out.println(dbPlat);
        }

        resultats.close();
        ordreSQL.close();

        return platList;
    }

//pour un id de plat , donne le prix
    public  static float getPrixUnitaire(Connection connection,int id_plat ) throws SQLException {
        // Pouvoir lister des personnages
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("select prix_unitaire\n" +
                "from plat\n" +
                "where  id_plat="+ id_plat);
        float price=0;
        while (resultats.next()) {
           float  res = resultats.getFloat("prix_unitaire");
        price=res;
           }
        resultats.close();
       ordreSQL.close();
return  price;
    }


}

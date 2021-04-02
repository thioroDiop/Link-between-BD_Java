package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    int id_serveur;
    String nom;
    String prenom;

    public Serveur( String prenom,String nom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Serveur(int id_serveur,  String prenom,String nom) {
        this.id_serveur = id_serveur;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public  String toString(){
return id_serveur+". "+prenom+" "+nom;
    }




    public static List<Serveur> getServeurLibre(Connection connection) throws SQLException {
        // Pouvoir lister des serveurs libres
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("select id_serveur, prenom, nom\n" +
                "from serveur\n" +
                "left join  facture f on serveur.id_serveur = f.serveur_idx\n" +
                "where id_facture is null");

        List<Serveur> serveurList = new ArrayList<>();

        while (resultats.next()) {
            Serveur dbServeur = new Serveur(resultats.getInt("id_serveur"),
                    resultats.getString("prenom"),
                    resultats.getString("nom"));
            serveurList.add(dbServeur);

            System.out.println(dbServeur);
        }

        resultats.close();
        ordreSQL.close();

        return serveurList;
    }

}

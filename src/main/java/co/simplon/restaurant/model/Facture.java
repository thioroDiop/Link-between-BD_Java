package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    int id_facture;
    int serveur_idx;
   int tables_idx;
   float prixTotal;

    public Facture(int id_facture, int serveur_idx, int tables_idx, float prixTotal) {
        this.id_facture = id_facture;
        this.serveur_idx = serveur_idx;
        this.tables_idx = tables_idx;
        this.prixTotal=prixTotal;
    }

    public Facture(int serveur_idx, int tables_idx, float prixTotal){
    this.serveur_idx=serveur_idx;
    this.tables_idx=tables_idx;
        this.prixTotal=prixTotal;
}



    @Override
    public  String toString(){
        return id_facture+". "+serveur_idx+" "+tables_idx+" "+prixTotal;
    }

    public void saveFacture(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO facture (serveur_idx, tables_idx,prixtotal) VALUES ('" + serveur_idx + "','" + tables_idx + "','" + prixTotal + "')");
        ordreSQL.close();
    }


    public static List<Facture> getFacture(Connection connection) throws SQLException {
        // Pouvoir lister des personnages
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from facture");

        List<Facture> personnageList = new ArrayList<>();

        while (resultats.next()) {
            Facture dbFacture = new Facture(resultats.getInt("id_facture"),
                    resultats.getInt("serveur_idx"),
                    resultats.getInt("tables_idx"),
                    resultats.getInt("prixTotal"));
            personnageList.add(dbFacture);

            System.out.println(dbFacture);
        }

        resultats.close();
        ordreSQL.close();

        return personnageList;
    }

}

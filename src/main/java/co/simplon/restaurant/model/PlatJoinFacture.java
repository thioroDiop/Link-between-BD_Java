package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlatJoinFacture {
    int id;
    int plat_idx;
    int facture_idx;
    int quantite;

    public PlatJoinFacture(int id, int plat_idx, int facture_idx, int quantite) {
        this.id = id;
        this.plat_idx = plat_idx;
        this.facture_idx = facture_idx;
        this.quantite = quantite;
    }

    public PlatJoinFacture(int plat_idx, int numfacture, int quantite) {
        this.plat_idx = plat_idx;
        this.facture_idx = numfacture;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return id + ". plat numero" + plat_idx + " Facture_" + facture_idx + " quantité:" + quantite;
    }


    public static List<PlatJoinFacture> getjointure(Connection connection) throws SQLException {
        // Pouvoir lister du tableau join
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT * from plat_join_facture");

        List<PlatJoinFacture> tableJointureList = new ArrayList<>();

        while (resultats.next()) {
            PlatJoinFacture dbtableJointure = new PlatJoinFacture(
                    resultats.getInt("id"),
                    resultats.getInt("plat_idx"),
                    resultats.getInt("facture_idx"),
                    resultats.getInt("quantite"));
            tableJointureList.add(dbtableJointure);


            System.out.println(dbtableJointure);
        }

        resultats.close();
        ordreSQL.close();

        return tableJointureList;
    }


    public void savetableJoin(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO plat_join_facture (plat_idx,facture_idx,quantite) VALUES ('" + plat_idx + "','" + facture_idx+"','" +quantite + "')");
        ordreSQL.close();
    }
}

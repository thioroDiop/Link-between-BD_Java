package co.simplon.restaurant.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tables {
    int id_tables;
    String nom_table;
    int nombre_convives;

    public Tables(String nom_table, int nombre_convives) {
        this.nom_table = nom_table;
        this.nombre_convives = nombre_convives;
    }

    public Tables(int id_tables, String nom_table, int nombre_convives) {
        this.id_tables = id_tables;
        this.nom_table = nom_table;
        this.nombre_convives = nombre_convives;
    }

    @Override
    public  String toString(){
        return  id_tables+". "+nom_table+" "+nombre_convives;
//        return String.format("%3$.2f", id_tables+". "+nom_table+" "+nombre_convives);
    }
    public static List<Tables> getTablesLibre(Connection connection) throws SQLException {
        // Pouvoir lister des tables libres
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("select id_tables, nom_table,nombre_convives\n" +
                "    from tables\n" +
                "    left join  facture f on tables.id_tables = f.tables_idx\n" +
                "    where id_facture is null");

        List<Tables> tablesList = new ArrayList<>();

        while (resultats.next()) {
            Tables dbTable = new Tables(resultats.getInt("id_tables"),
                    resultats.getString("nom_table"),
                    resultats.getInt("nombre_convives"));
            tablesList.add(dbTable);

            System.out.println(dbTable);
        }

        resultats.close();
        ordreSQL.close();

        return tablesList;
    }


}

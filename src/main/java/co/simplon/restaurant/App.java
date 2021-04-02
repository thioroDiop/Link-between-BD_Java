package co.simplon.restaurant;

import co.simplon.restaurant.model.Facture;
import co.simplon.restaurant.model.Plat;
import co.simplon.restaurant.model.Serveur;
import co.simplon.restaurant.model.Tables;

import java.sql.*;
import java.util.Scanner;

public class App {
     public static void main(String[] args) {
         String url = "jdbc:postgresql://localhost:5432/restaurant";
         String user = "postgres";
         String password = "postgres";

         Scanner scanner = new Scanner(System.in);
try {
         Connection connection = DriverManager.getConnection(url, user, password);

    Statement statement = connection.createStatement();
    /*
    //ResultSet resultSet = statement.executeQuery("SELECT * from serveur");
    ResultSet resultSet1 = statement.executeQuery(" SELECT sum(prix_unitaire) chiffre_affaire,t.nom_table\n" +
            "from plat\n" +
            "         join plat_join_facture pjf on plat.id_plat = pjf.plat_idx\n" +
            "         join facture f on f.id_facture = pjf.facture_idx\n" +
            "join tables t on t.id_tables = f.tables_idx\n" +
            "group by t.nom_table\n" +
            "order by chiffre_affaire desc");


    System.out.println("Liste des meilleures tables par chiffre d'affaire: ");
    while (resultSet1.next()) {
        System.out.print(resultSet1.getString("nom_table"));
        System.out.print(" - chiffre d'affaire:");
        System.out.println(resultSet1.getString("chiffre_affaire"));
    }

    ResultSet resultSet2= statement.executeQuery("SELECT nom_plat,sum(prix_unitaire ) total_price\n" +
            "from plat\n" +
            "         join plat_join_facture pjf on plat.id_plat = pjf.plat_idx\n" +
            "         join facture f on f.id_facture = pjf.facture_idx\n" +
            "group by nom_plat\n" +
            "order by total_price desc");
    System.out.println();
    System.out.println("Liste des meilleurs plats par chiffre d'affaire: ");
    while (resultSet2.next()) {
        System.out.print(resultSet2.getString("nom_plat"));
        System.out.print(" - chiffre d'affaire:");
        System.out.println(resultSet2.getString("total_price"));
    }

   // resultSet.close();
    resultSet1.close();
    resultSet2.close();
    */








    //    afficher les serveur dispo
    System.out.println("Les serveurs libres sont: ");
    Serveur.getServeurLibre(connection);

  System.out.println("Coucou, choisir l'id de ton serveur au choix");
    int idServeur=scanner.nextInt();

    System.out.println();

    //afficher les tables libres
    System.out.println("Les tables libres sont: ");
    Tables.getTablesLibre(connection);

  System.out.println(" choisie un numéro de la table libre sur la 1er colonne ");
    int idTable=scanner.nextInt();

// bouble while tant que j'ai une saisie de plat
    int platChoisi=1;
    float prixTTC=0;
    while (platChoisi!=0) {
        System.out.println("voici le menu: ");
        //affiche les plats
        Plat.getPlat(connection);
        System.out.println("choisis un numero de plat: ");
        platChoisi = scanner.nextInt();
       if (platChoisi==0){break;}
       else {
           System.out.println("choisis la quantité: ");
           int quantite = scanner.nextInt();


           float prixUnit = Plat.getPrixUnitaire(connection, platChoisi);
           System.out.println("le prix du plat choisi est:" + prixUnit);

           prixTTC = prixTTC + (prixUnit * quantite);


       }
    }
    System.out.println("prixTTC est de:"+prixTTC);
Facture newFacture=new Facture(idServeur,idTable,prixTTC);

newFacture.saveFacture(connection);// erreur de connexion à la base de donnée
    Facture.getFacture(connection);


    statement.close();
        connection.close();

}
 catch (SQLException exception) {
     // Ma gestion du problème
     System.out.println("Erreur de connexion à la base de données");
     // exception.printStackTrace();
}

     }
}

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Relation test = new Relation("csvFile.csv");
        Relation test2 = new Relation("csvFile2.csv");
        Relation test3 = new Relation("ordini.csv");
        Relation test4 = new Relation("persone.csv");
        Relation test5 = new Relation("Prodotti.csv");

        System.out.println("test");

        Relation selezione = test.Selection(test, "Merluzzo", "cognome");
        System.out.println("test");

        Relation proiezione = test.Projection(test, new ArrayList<>(Arrays.asList("cognome")));
        System.out.println("test");

        Relation union = test.union(test, test2);
        System.out.println("test");

        Relation differenza = test.Difference(test, test2);
        System.out.println("test");

        Relation prodottocartesiano = test.CartesianProduct(test3,test5);
        System.out.println("test");


        Relation giunzione = test.Junction(test3, test5, new String[]{"id_prodotto"});
        System.out.println("test");
    }
}
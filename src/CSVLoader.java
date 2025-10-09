import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVLoader {
    String file = "";

    public CSVLoader(String file) {
        this.file = file;
    }

    public Relation loadCSVinRelation() {
        Relation loaded = new Relation();
        List<List<String>> records = new ArrayList<>();
        int rowNumber = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = br.readLine()) != null) {

                String[] campi = line.split(",");


                if (rowNumber == 0) {
                    for (int i = 0; i < campi.length; i++) {
                        loaded.header.add(campi[i]);
                    }
                } else {
                    Row nuova = new Row();
                    for (int i = 0; i < campi.length; i++) {
                        nuova.values.add(campi[i]);
                    }
                    loaded.rows.add(nuova);
                }

                String[] values = line.split(",");
                records.add(Arrays.asList(values));
                rowNumber++;
            }
        } catch (Exception e) {
            System.out.println("can't load " + this.file);

        }

        return loaded;
    }

}
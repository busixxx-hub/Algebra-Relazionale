import java.util.ArrayList;

public class Relation {

    ArrayList<String> header = new ArrayList<>();
    ArrayList<Row> rows = new ArrayList<>();

    Relation() {

    }

    Relation(String csvFile) {
        CSVLoader csv = new CSVLoader(csvFile);
        Relation temp = csv.loadCSVinRelation();

        this.header = temp.header;
        this.rows = temp.rows;

    }

    public Relation Selection(Relation input, String key, String value) {
        int indice = 0;
        Relation relazioneOutput = new Relation();
        relazioneOutput.header.addAll(input.header);
        for (int i = 0; i < input.header.size(); i++) {
            if (input.header.get(i).equals(value)) {
                indice = i;
            }
        }
        for (int i = 0; i < input.rows.size(); i++) {
            if (input.rows.get(i).values.get(indice).equalsIgnoreCase(key)) {
                relazioneOutput.rows.add(input.rows.get(i));
            }
        }
        return relazioneOutput;
    }

    public Relation Projection(Relation input, ArrayList<String> keys) {
        Relation relazioneOutput = new Relation();
        relazioneOutput.header.addAll(keys);

        ArrayList<Integer> indici = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            indici.add(input.header.indexOf(keys.get(i)));
        }

        for (int i = 0; i < input.rows.size(); i++) {
            Row nuova = new Row();

            for (int j = 0; j < indici.size(); j++) {
                nuova.values.add(input.rows.get(i).values.get(indici.get(j)));
            }
            relazioneOutput.rows.add(nuova);
        }
        return relazioneOutput;

    }


    public Relation union(Relation one, Relation two) {
        Relation relazioneOutput = new Relation();
        if (one.header.equals(two.header)) {
            relazioneOutput.header = one.header;
        } else {
            return relazioneOutput;
        }

        relazioneOutput.rows.addAll(one.rows);

        for (int j = 0; j < two.rows.size(); j++) {
            if (!(two.rows.get(j).values.equals(relazioneOutput.rows.get(j).values))) {
                relazioneOutput.rows.add(two.rows.get(j));
            }

        }
        return relazioneOutput;
    }


    public Relation Difference(Relation one, Relation two) {
        Relation relazioneOutput = new Relation();
        if (one.header.equals(two.header)) {
            relazioneOutput.header = one.header;
        } else {
            return relazioneOutput;
        }

        for (int i = 0; i < one.rows.size(); i++) {
            if (!(one.rows.get(i).values.equals(two.rows.get(i).values))) {
                relazioneOutput.rows.add(one.rows.get(i));
            }
        }
        return relazioneOutput;
    }


    public Relation CartesianProduct(Relation one, Relation two) {
        Relation relazioneOutput = new Relation();
        relazioneOutput.header.addAll(one.header);
        relazioneOutput.header.addAll(two.header);

        for (int i = 0; i < one.rows.size(); i++) {
            for (int j = 0; j < two.rows.size(); j++) {
                Row nuovaRiga = new Row();

                nuovaRiga.values.addAll(one.rows.get(i).values);
                nuovaRiga.values.addAll(two.rows.get(j).values);

                relazioneOutput.rows.add(nuovaRiga);
            }
        }
        return relazioneOutput;
    }

    public Relation Junction(Relation one, Relation two, String[] junctionField) {
        Relation relazioneOutput = new Relation();
        Relation relazioneAppoggio = CartesianProduct(one,two);
        ArrayList<Integer> indice = new ArrayList<>();

        relazioneOutput.header.addAll(relazioneAppoggio.header);

        for(int i = 0; i < relazioneAppoggio.header.size(); i++) {
            for(int j = 0; j < junctionField.length; j++) {
                if(relazioneAppoggio.header.get(i).equals(junctionField[j])) {
                    indice.add(i);
                }
            }
        }

        for(int i = 0; i < relazioneAppoggio.rows.size(); i++) {
            if(relazioneAppoggio.rows.get(i).values.get(indice.get(0)).equals(relazioneAppoggio.rows.get(i).values.get(indice.get(1)))) {
                relazioneOutput.rows.add(relazioneAppoggio.rows.get(i));
            }
        }
        return relazioneOutput;
    }
}
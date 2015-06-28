package dataset;

import java.util.*;

public class Dataset {
    List<HashMap<String, Object>> dataset;

    public Dataset() {
        dataset = new ArrayList<>();
    }

    public void addRow(HashMap<String, Object> row){
        this.dataset.add(row);
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "dataset=" + dataset +
                '}';
    }
}

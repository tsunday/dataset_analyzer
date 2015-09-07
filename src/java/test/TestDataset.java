/*
Author: Tomasz Niedziela-Brach
 */
package test;

import dataset.*;
import reader.Field;
import reader.Reader;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestDataset {
    public static void main(String[] args){
        Path path = FileSystems.getDefault().getPath("D:\\Desktop\\Kompilatory\\dataset_analyzer\\src\\java\\test\\test_data\\test.txt");
        String delimiter = ";";
        List<Field> fields = new ArrayList<>();
        Field f1 = new Field("f1", Field.Type.Number);
        Field f2 = new Field("f2", Field.Type.String);
        Field f3 = new Field("f3", Field.Type.Time, "HH:mm:ss");

        fields.add(f1);
        fields.add(f2);
        fields.add(f3);

        // Read data
        Reader reader = new Reader(path, fields, delimiter);
        Dataset dataset = reader.GenerateDataset();
        System.out.println(dataset.toString());

        // Define Event
        Double limit = 100.50;
        String weather = "pada";
        Occurance o1 = new Occurance(Occurance.Condition.Less, limit);
        Occurance o2 = new Occurance(Occurance.Condition.Less, weather);
        Event eventA = new Event();
        eventA.addOccurance("f3", o1);
        eventA.addOccurance("f2", o2);

        // Filter data
        DatasetFilter filter = new DatasetFilter(new SingleEventExpression(eventA));
        Dataset result = filter.FilterDataset(dataset);
        System.out.println(result.toString());
    }
}

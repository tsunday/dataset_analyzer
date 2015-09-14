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

public class TestDatasetObligation {
    public static void main(String[] args){
        Path path = FileSystems.getDefault().getPath("D:\\Desktop\\Kompilatory\\dataset_analyzer\\src\\java\\test\\test_data\\test_obligation.txt");
        String delimiter = ",";
        List<Field> fields = new ArrayList<>();
        Field f1 = new Field("f1", Field.Type.Number);
        Field f2 = new Field("f2", Field.Type.String);
        Field f3 = new Field("f3", Field.Type.Number);
        Field f4 = new Field("f4", Field.Type.Number);
        Field f5 = new Field("f5", Field.Type.Time, "HH:mm:ss");

        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        fields.add(f4);
        fields.add(f5);

        // Read data
        Reader reader = new Reader(path, fields, delimiter);
        Dataset dataset = reader.GenerateDataset();
        System.out.println(dataset.toString());

        // Define Event
        Double light = 90.00;
        Double noise = 80.00;
        String weather = "pada";

        Occurance o1 = new Occurance(Occurance.Condition.More, light);
        Occurance o2 = new Occurance(Occurance.Condition.More, noise);
        Occurance o3 = new Occurance(Occurance.Condition.Equal, weather);

        Event eventA = new Event();
        eventA.addOccurance("f3", o1);
        eventA.addOccurance("f2", o3);

        Event eventB = new Event();
        eventB.addOccurance("f4", o2);
        eventA.addOccurance("f2", o3);

        // Filter data
        DoubleEventExpression.Operator op = DoubleEventExpression.Operator.Obligation;
        DoubleEventExpression ex = new DoubleEventExpression(eventA, eventB, op);

        DatasetFilter filter = new DatasetFilter(ex);
        Dataset result = filter.FilterDataset(dataset);

        System.out.println(result.toString());
        System.out.println("Result of matching logical operator: " + ex.VerifyOperator());
    }
}

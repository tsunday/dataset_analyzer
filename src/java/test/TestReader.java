package test;

import dataset.Dataset;
import reader.Field;
import reader.Reader;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestReader {
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

        Reader reader = new Reader(path, fields, delimiter);
        Dataset dataset = reader.GenerateDataset();
        System.out.println(dataset.toString());
    }
}

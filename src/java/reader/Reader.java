/*
Author: Tomasz Niedziela-Brach
 */
package reader;

import dataset.Dataset;
import reader.Field.Type;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Reader extends AbstractReader{
    Dataset dataset;
    List<String> content;

    public Reader(Path path, List<Field> fields, String delimiter) {
        super(path, fields, delimiter);
        dataset = new Dataset();
        try {
            content = Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dataset GenerateDataset() {
        for(String row : this.content)
            this.dataset.addRow(parseRow(row));
        return this.dataset;
    }

    public HashMap<String, Object> parseRow(String row){
        HashMap<String, Object> dict = new HashMap<>();
        String[] subrows = row.split(this.delimiter, this.fields.size());

        int counter = 0;
        for(Field field : fields){
            dict.put(field.name, castValue(field, subrows[counter]));
            counter++;
        }
        return dict;
    }

    public Object castValue(Field field, String value){
        if (field.type.equals(Type.Number))
            return Double.parseDouble(value);
        else if(field.type.equals(Type.Time))
            return parseTime(field.time_regexp, value);
        else
            return value;
    }

    public Date parseTime(String time_regexp, String value){
        try {
            return new SimpleDateFormat(time_regexp).parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

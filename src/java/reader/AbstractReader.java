package reader;
import dataset.Dataset;

import java.nio.file.Path;
import java.util.List;

public abstract class AbstractReader{
    public Path path;
    public List<Field> fields;
    public String delimiter;

    public AbstractReader(Path path, List<Field> fields, String delimiter) {
        this.path = path;
        this.fields = fields;
        this.delimiter = delimiter;
    }

    public abstract Dataset GenerateDataset();
}
package reader;

public class Field {
    public String name;
    public Type type;
    public String time_regexp;

    public Field(String name, Type type) {
        this.name = name;
        this.type = type;
        time_regexp = null;
    }

    public Field(String name, Type type, String time_regexp) {
        this.name = name;
        this.type = type;
        this.time_regexp = time_regexp;
    }

    public enum Type{
        String, Number, Time
    }
}

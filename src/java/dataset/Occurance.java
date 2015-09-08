/*
Author: Tomasz Niedziela-Brach
 */
package dataset;
import java.util.Date;

public class Occurance {
    public Condition condition;
    public Object value;
    public Object range;

    public Occurance(Condition condition, Object value) {
        this.condition = condition;
        this.value = value;
        this.range = null;
    }

    public Occurance(Condition condition, Object value, Object range){
        this.condition = condition;
        this.value = value;
        this.range = range;
    }

    public boolean CheckValue(Double value)
    {
        Double current_value = (Double) this.value;
        Double current_range = (Double) this.value;

        switch(this.condition){
            case Less:
                if(value < current_value) return true;
                break;
            case More:
                if(value > current_value) return true;
                break;
            case Equal:
                if(value == current_value) return true;
                break;
            case InRange:
                if(value > current_value && value < current_range) return true;
                break;
        }
        return false;
    }

    public boolean CheckValue(String value)
    {
        if(this.condition == Condition.Equal && value.equals((String)this.value))
            return true;
        return false;
    }

    public boolean CheckValue(Date value)
    {
        Date current_value = (Date)this.value;
        Date current_range = (Date)this.range;

        switch(this.condition){
            case Less:
                if(current_value.before(value)) return true;
                break;
            case More:
                if(current_value.after(value)) return true;
                break;
            case Equal:
                if(current_value.equals(value)) return true;
                break;
            case InRange:
                if(current_value.after(value) && current_value.before(current_range)) return true;
                break;
        }
        return false;
    }

    public boolean CheckValue(Object value) throws IllegalArgumentException{
        if(value instanceof Double)
            return CheckValue((Double) value);
        else if(value instanceof String)
            return CheckValue((String)value);
        else if(value instanceof  Date)
            return CheckValue((Date)value);
        else
            throw new IllegalArgumentException("Wrong Occurance value type");
    }

    public enum Condition{
        Less, More, Equal, InRange
    }
}

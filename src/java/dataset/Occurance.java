package dataset;


public class Occurance {
    public Condition condition;
    public Object value;
    public Object range;

    public Occurance(Condition condition, Object value) {
        this.condition = condition;
        this.value = value;
        this.range = null;
    }

    public Occurance(Condition condition, Object value, Object range) {
        this.condition = condition;
        this.value = value;
        this.range = range;
    }

    public boolean CheckValue(Double value)
    {
        switch(condition){

            case Less:
//                if(value < this.value) return true;
                break;
            case More:
                break;
            case Equal:
                break;
            case InRange:
                break;
        }
        if(this.value.equals(value))
            return true;
        return false;
    }

    public enum Condition{
        Less, More, Equal, InRange
    }
}

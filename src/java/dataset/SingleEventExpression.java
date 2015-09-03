package dataset;

import java.util.HashMap;

public class SingleEventExpression extends EventExpression{
    private Event event;
    private Operator operator;

    public SingleEventExpression(Event event, Operator operator) {
        this.event = event;
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public boolean MatchExpression(HashMap<String, Object> row) throws ArrayIndexOutOfBoundsException{
        // IMPLEMENT
        if(row.size() < event.occurances.size())
            throw new ArrayIndexOutOfBoundsException("Wrong expression");
        event.VerifyRow(row);

        return false;
    }

    @Override
    public String toString() {
        // IMPLEMENT
        return null;
    }
}

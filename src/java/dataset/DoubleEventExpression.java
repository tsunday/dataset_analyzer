package dataset;

import java.util.HashMap;

public class DoubleEventExpression extends EventExpression{
    private Event event1;
    private Event event2;

    public DoubleEventExpression(Event event1, Event event2) {
        this.event1 = event1;
        this.event2 = event2;
    }

    @Override
    public boolean MatchExpression(HashMap<String, Object> row) {
        // IMPLEMENT
        return false;
    }

    @Override
    public String toString() {
        // IMPLEMENT
        return null;
    }
}

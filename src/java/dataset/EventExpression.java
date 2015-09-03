package dataset;

import java.util.HashMap;

public abstract class EventExpression {
    private boolean negative;
    private State state;

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public State getState() {
        return state;
    }

    public abstract boolean MatchExpression(HashMap<String, Object> row) throws ArrayIndexOutOfBoundsException;

    public enum State{
        NotFound,
        Found,
        FoundInterrupted
    }

    public enum Operator{
        Absence,
        Existence,
        Autoresponsiveness,
        Persistence,
    }
}

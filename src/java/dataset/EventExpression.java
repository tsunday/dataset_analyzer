/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;

public abstract class EventExpression {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state){ this.state = state; }

    public abstract boolean MatchExpression(HashMap<String, Object> row) throws ArrayIndexOutOfBoundsException;

    protected abstract void UpdateState(boolean rowMatched);

    public abstract boolean VerifyOperator();

    public enum State{
        None,
        NotFound,
        Found,
        FoundInterrupted,
        NotFoundInterrupted
    }
}

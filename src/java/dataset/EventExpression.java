/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;

public abstract class EventExpression {


    public abstract boolean MatchExpression(HashMap<String, Object> row) throws ArrayIndexOutOfBoundsException;

    public abstract boolean VerifyOperator();

    public enum State{
        None,
        NotFound,
        Found,
        FoundInterrupted,
        NotFoundInterrupted
    }
}

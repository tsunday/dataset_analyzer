/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;
import java.util.LinkedList;

public class SingleEventExpression extends EventExpression{
    private Event event;
    private Operator operator;
    private State state;
    private LinkedList<State> expectedState;

    public SingleEventExpression(Event event, Operator operator) {
        this.event = event;
        this.operator = operator;
        setState(State.None);
        this.expectedState = new LinkedList<State>();
        switch(operator){
            case Absence:
                expectedState.push(State.NotFound);
                break;
            case Invariance:
                expectedState.push(State.Found);
                break;
            case Existence:
                expectedState.push(State.Found);
                expectedState.push(State.FoundInterrupted);
                expectedState.push(State.NotFoundInterrupted);
                break;
            case Autoresponsiveness:
                expectedState.push(State.FoundInterrupted);
                expectedState.push(State.NotFoundInterrupted);
                break;
            case Persistence:
                expectedState.push(State.NotFoundInterrupted);
                break;
            default:
                throw new IllegalArgumentException("Operator not known: " + operator.toString());
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public State getState() {
        return state;
    }

    public void setState(State state){ this.state = state; }

    @Override
    public boolean MatchExpression(HashMap<String, Object> row) throws ArrayIndexOutOfBoundsException{
        // IMPLEMENT
        if(row.size() < event.occurances.size())
            throw new ArrayIndexOutOfBoundsException("Wrong expression");
        if(event.VerifyRow(row)) {
            UpdateState(true);
            return true;
        }
        UpdateState(false);
        return false;
    }

    protected void UpdateState(boolean rowMatched){
        if(rowMatched){
            switch(getState()) {
                case None:
                    setState(State.Found);
                    break;
                case NotFound:
                    setState(State.NotFoundInterrupted);
                    break;
                case FoundInterrupted:
                    setState(State.NotFoundInterrupted);
                    break;
                default:
                    break;
            }
        }
        else{
            switch(getState()) {
                case None:
                    setState(State.NotFound);
                    break;
                case Found:
                    setState(State.FoundInterrupted);
                    break;
                case NotFoundInterrupted:
                    setState(State.FoundInterrupted);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean VerifyOperator(){
        if(expectedState.contains(getState()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        // IMPLEMENT
        return null;
    }

    public enum Operator{
        Absence,
        Invariance,
        Existence,
        Autoresponsiveness,
        Persistence,
    }
}

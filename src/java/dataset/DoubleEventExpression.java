/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;
import java.util.LinkedList;

public class DoubleEventExpression extends EventExpression{
    private Operator operator;
    private Event event1;
    private Event event2;
    private State state1 = State.None;
    private State state2 = State.None;
    private LinkedList<State> expectedState1;
    private LinkedList<State> expectedState2;

    public DoubleEventExpression(Event event1, Event event2, Operator operator) {
        this.event1 = event1;
        this.event2 = event2;
        this.operator = operator;
        this.expectedState1 = new LinkedList<State>();
        this.expectedState2 = new LinkedList<State>();

        switch(operator) {
            case Obligation:
                expectedState1.push(State.Found);
                expectedState1.push(State.FoundInterrupted);
                expectedState1.push(State.NotFoundInterrupted);

                expectedState2.push(State.Found);
                expectedState2.push(State.FoundInterrupted);
                expectedState2.push(State.NotFoundInterrupted);
                break;
            case Response:
                expectedState1.push(State.Found);
                expectedState1.push(State.FoundInterrupted);
                expectedState1.push(State.NotFoundInterrupted);

                expectedState2.push(State.Found);
                expectedState2.push(State.FoundInterrupted);
                expectedState2.push(State.NotFoundInterrupted);
                break;
            case Reaction:
                expectedState1.push(State.Found);
                expectedState1.push(State.NotFoundInterrupted);

                expectedState2.push(State.Found);
                expectedState2.push(State.NotFoundInterrupted);
                break;
            default:
                throw new IllegalArgumentException("Operator not known" + operator.toString());
        }
    }

    @Override
    public boolean MatchExpression(HashMap<String, Object> row) {
        if(row.size() < event1.occurances.size() || row.size() < event2.occurances.size())
            throw new ArrayIndexOutOfBoundsException("Wrong expression");
        boolean matchResult1 = event1.VerifyRow(row);
        boolean matchResult2 = event2.VerifyRow(row);
        UpdateState(matchResult1, matchResult2);
        if(matchResult1 || matchResult2)
            return true;
        return false;
    }

    protected void UpdateState(boolean rowMatched1, boolean rowMatched2) {
        switch(operator){
            case Obligation:
                UpdateStateForObligationOrReaction(rowMatched1, rowMatched2);
                break;
            case Response:
                UpdateStateForResponse(rowMatched1, rowMatched2);
                break;
            case Reaction:
                UpdateStateForObligationOrReaction(rowMatched1, rowMatched2);
                break;
            default:
                break;
        }
    }

    private void UpdateStateForObligationOrReaction(boolean rowMatched1, boolean rowMatched2){
        if(rowMatched1){
            switch(getState1()){
                case None:
                    setState1(State.Found);
                    break;
                case FoundInterrupted:
                    setState1(State.NotFoundInterrupted);
                    break;
                case NotFound:
                    setState1(State.NotFoundInterrupted);
            }
        }
        else{
            switch(getState1()){
                case None:
                    setState1(State.NotFound);
                    break;
                case NotFoundInterrupted:
                    setState1(State.FoundInterrupted);
                    break;
                case Found:
                    setState1(State.FoundInterrupted);
            }
        }

        if(rowMatched2){
            switch(getState2()){
                case None:
                    setState2(State.Found);
                    break;
                case FoundInterrupted:
                    setState2(State.NotFoundInterrupted);
                    break;
                case NotFound:
                    setState2(State.NotFoundInterrupted);
            }
        }
        else{
            switch(getState2()){
                case None:
                    setState2(State.NotFound);
                    break;
                case NotFoundInterrupted:
                    setState2(State.FoundInterrupted);
                    break;
                case Found:
                    setState2(State.FoundInterrupted);
            }
        }
    }

    private void UpdateStateForResponse(boolean rowMatched1, boolean rowMatched2){
        if(rowMatched1){
            switch(getState1()){
                case None:
                    setState1(State.Found);
                    break;
                case NotFound:
                    setState1(State.NotFoundInterrupted);
            }
        }
        else{
            switch(getState1()){
                case None:
                    setState1(State.NotFound);
                    break;
                case NotFoundInterrupted:
                    setState1(State.FoundInterrupted);
                    break;
                case Found:
                    setState1(State.FoundInterrupted);
            }
        }

        if(rowMatched2 && expectedState1.contains(getState1())){
            switch(getState2()){
                case None:
                    setState2(State.Found);
                    break;
                case FoundInterrupted:
                    setState2(State.NotFoundInterrupted);
                    break;
                case NotFound:
                    setState2(State.NotFoundInterrupted);
            }
        }
        else if(!rowMatched2){
            switch(getState2()){
                case None:
                    setState2(State.NotFound);
                    break;
                case NotFoundInterrupted:
                    setState2(State.FoundInterrupted);
                    break;
                case Found:
                    setState2(State.FoundInterrupted);
            }
        }

    }

    public Event getEvent1() {
        return event1;
    }

    public Event getEvent2() {
        return event2;
    }

    public State getState1() {
        return state1;
    }

    public void setState1(State state1) {
        this.state1 = state1;
    }

    public State getState2() {
        return state2;
    }

    public void setState2(State state2) {
        this.state2 = state2;
    }

    @Override
    public boolean VerifyOperator() {
        if(expectedState1.contains(getState1()) && expectedState2.contains(getState2()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        // IMPLEMENT
        return null;
    }

    public enum Operator{
        Response,
        Obligation,
        Reaction
    }
}

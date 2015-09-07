/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;


public class DatasetFilter {
    private EventExpression eventExpression;

    public DatasetFilter(EventExpression eventExpression) {
        this.eventExpression = eventExpression;
    }

    public EventExpression getEventExpression() {
        return eventExpression;
    }

    public void setEventExpression(EventExpression eventExpression) {
        this.eventExpression = eventExpression;
    }

    // filters given Dataset using set EventExpression and returns matching data only
    public Dataset FilterDataset(Dataset dataset)
    {
        Dataset result = new Dataset();
        for(HashMap<String, Object> row : dataset.dataset){
            if(this.eventExpression.MatchExpression(row))
                result.addRow(row);
        }
        return result;
    }
}

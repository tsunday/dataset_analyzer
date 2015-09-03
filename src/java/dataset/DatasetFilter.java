package dataset;

/**
 * Created by Tolina on 03.09.2015.
 */
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

        return dataset;
    }
}

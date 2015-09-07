/*
Author: Tomasz Niedziela-Brach
 */
package dataset;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Event {
    public HashMap<String, Occurance> occurances;
    public Event(){ occurances = new HashMap<String, Occurance>(); }
    public Event(HashMap<String, Occurance> occurances)
    {
        this.occurances = occurances;
    }
    public void addOccurance(String key, Occurance occurance){ this.occurances.put(key, occurance); }
    public boolean VerifyRow(HashMap<String, Object> row)
    {
        for(Map.Entry<String, Occurance> entry : occurances.entrySet())
        {
            String key = entry.getKey();
            Occurance occurance = entry.getValue();
            if(!row.containsKey(key) || !(occurance.CheckValue(row.get(key)))){
                return false;
            }
        }
        return true;
    }
}

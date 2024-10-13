package persistence;

import org.json.JSONObject;

// SOURCE: Writable example given for phase 2 instructions
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

package lv.div.geotext;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

/**
 * Helper class for JSON deserialize
 */
class JsonHelper<T> implements Serializable {

    private static final long serialVersionUID = 1249212669962273989L;

    private ObjectMapper jsonObjectMapper;

    public JsonHelper() {
        this.jsonObjectMapper = new ObjectMapper();
    }

    public T buildPojo(String sourceJson, Class<T> cls) {
        try {
            return jsonObjectMapper.readValue(sourceJson, cls);
        } catch (Exception e) {
            return null;
        }
    }

}

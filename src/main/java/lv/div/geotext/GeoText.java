package lv.div.geotext;

import lv.div.geotext.tools.GoogleGeoCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main GeoText toolkit class
 */
public class GeoText {

    public static final String GOOGLE_GEOCODE_JSON_URL = "http://maps.googleapis.com/maps/api/geocode/json?";
    private URIBuilder builder = null;
    private String latitude = "";
    private String longitude = "";
    private HttpClient httpclient = null;

    public GeoText() {

        try {
            builder = new URIBuilder(GOOGLE_GEOCODE_JSON_URL);
            httpclient = HttpClients.createDefault();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public GoogleGeoCode getTextData(String latitude, String longitude) {
        return getTextData(Double.valueOf(latitude), Double.valueOf(longitude));
    }

    public GoogleGeoCode getTextData(float latitude, float longitude) {
        return getTextData(Double.valueOf(latitude), Double.valueOf(longitude));
    }

    public GoogleGeoCode getTextData(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);

        GoogleGeoCode result = new GoogleGeoCode();
        try {
            builder.setParameter("latlng", getLatitude() + "," + getLongitude());
            builder.setParameter("sensor", "false");
            final URI uri = builder.build();
            final HttpPost request = new HttpPost(uri);
            final HttpResponse response = httpclient.execute(request);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                JsonHelper json = new JsonHelper();
                final String serviceAnswer = EntityUtils.toString(entity);
                result = (GoogleGeoCode) json.buildPojo(serviceAnswer, GoogleGeoCode.class);
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = String.valueOf(latitude);
    }

    public void setLatitude(float latitude) {
        this.latitude = String.valueOf(latitude);
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = String.valueOf(longitude);
    }

    public void setLongitude(float longitude) {
        this.longitude = String.valueOf(longitude);
    }
}

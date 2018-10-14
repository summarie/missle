package gov.dod.missile;

/**
 * Created by uengine on 2018. 10. 14..
 */
public class LaunchPoint {


    public LaunchPoint(String id, String city, double longitude, double latitude) {
        this.id = id;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    String id;
    String city;
    double longitude;
    double latitude;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

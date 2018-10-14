package gov.dod.missile;

/**
 * Created by uengine on 2018. 10. 14..
 */
public class ImpactPoint extends LaunchPoint{


    public ImpactPoint(String id, String city, double longitude, double latitude, int hMin, int hMax, int dMin, int dMax, int angle) {

        super(id, city, longitude, latitude);

        this.hMin = hMin;
        this.hMax = hMax;
        this.dMin = dMin;
        this.dMax = dMax;
        this.angle = angle;
    }

    int hMin;
    int hMax;
    int dMin;
    int dMax;
    int angle;



    public int gethMin() {
        return hMin;
    }

    public void sethMin(int hMin) {
        this.hMin = hMin;
    }

    public int gethMax() {
        return hMax;
    }

    public void sethMax(int hMax) {
        this.hMax = hMax;
    }

    public int getdMin() {
        return dMin;
    }

    public void setdMin(int dMin) {
        this.dMin = dMin;
    }

    public int getdMax() {
        return dMax;
    }

    public void setdMax(int dMax) {
        this.dMax = dMax;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }





}

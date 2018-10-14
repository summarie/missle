package gov.dod.missile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uengine on 2018. 9. 28..
 */
public class Missile {

    static Map<String, ImpactPoint> impactPointMap = new HashMap<String, ImpactPoint>();
    static Map<String, LaunchPoint> launchPointMap = new HashMap<String, LaunchPoint>();

    static{

        impactPointMap.put("T1", new ImpactPoint("T1","서울특별시	",	37.56667	,	126.97806	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T2", new ImpactPoint("T2","부산광역시	",	35.17944	,	129.07556	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T3", new ImpactPoint("T3","인천광역시	",	37.45639	,	126.70528	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T4", new ImpactPoint("T4","대구광역시	",	35.87222	,	128.6025	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T5", new ImpactPoint("T5","대전광역시	",	36.35111	,	127.385	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T6", new ImpactPoint("T6","광주광역시	",	35.15972	,	126.85306	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T7", new ImpactPoint("T7","울산광역시	",	35.53889	,	129.31667	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T8", new ImpactPoint("T8","고양시	",	37.65833	,	126.83056	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T9", new ImpactPoint("T9","성남시	",	37.44722	,	127.1375	,	5	,	40	,	5	,	40	,	120));
        impactPointMap.put("T10", new ImpactPoint("T10","세종특별자치시	",	36.4875	,	127.28167	,	5	,	40	,	5	,	40	,	120));

        launchPointMap.put("L1", new LaunchPoint("L1","	해주시	",	125.71667	,	38.03333));
        launchPointMap.put("L2", new LaunchPoint("L2	","	평산	",	126.39	,	38.33));
        launchPointMap.put("L3", new LaunchPoint(		"	L3	","	사리원시	",	125.74	,	38.5));
        launchPointMap.put("L4", new LaunchPoint(		"	L4	","	남포특별시	",	125.4	,	38.73333));
        launchPointMap.put("L5", new LaunchPoint(		"	L5	","	원산시	",	127.44611	,	39.1475));
        launchPointMap.put("L6", new LaunchPoint(		"	L6	","	창림	",	126.21	,	39.18));
        launchPointMap.put("L7", new LaunchPoint(		"	L7	","	순천시	",	125.91	,	39.42));
        launchPointMap.put("L8", new LaunchPoint(		"	L8	","	철산군	",	124.66	,	39.77));
        launchPointMap.put("L9", new LaunchPoint(		"	L9	","	함흥시	",	127.5	,	40));
        launchPointMap.put("L10", new LaunchPoint(				"	L10	","	신포시	",	128.18389	,	40.03639));
        launchPointMap.put("L11", new LaunchPoint(				"	L11	","	신의주시	",	124.4	,	40.1));
        launchPointMap.put("L12", new LaunchPoint(				"	L12	","	김책시	",	129.20056	,	40.66722));
        launchPointMap.put("L13", new LaunchPoint(				"	L13	","	삼지연	",	128.31	,	41.8));
        launchPointMap.put("L14", new LaunchPoint(		"	L14	","	라진구역	",	130.27	,	42.25));
    }


    boolean outOfRange;
    boolean burnedOut;
    double xLocation;
    double yLocation;
    double zLocation;

    LaunchPoint launchPoint;
    ImpactPoint impactPoint;

    String launchPointId;
    String impactPointId;


    public double getxLocation() {
        return xLocation;
    }

    public void setxLocation(double xLocation) {
        this.xLocation = xLocation;
    }

    public double getyLocation() {
        return yLocation;
    }

    public void setyLocation(double yLocation) {
        this.yLocation = yLocation;
    }

    public double getzLocation() {
        return zLocation;
    }

    public void setzLocation(double zLocation) {
        this.zLocation = zLocation;
    }

    public boolean isOutOfRange() {
        return outOfRange;
    }

    public void setOutOfRange(boolean outOfRange) {
        this.outOfRange = outOfRange;
    }

    public boolean isBurnedOut() {
        return burnedOut;
    }

    public void setBurnedOut(boolean burnedOut) {
        this.burnedOut = burnedOut;
    }


    public LaunchPoint getLaunchPoint() {
        return launchPoint;
    }

    public void setLaunchPoint(LaunchPoint launchPoint) {
        this.launchPoint = launchPoint;
    }

    public String getLaunchPointId() {
        return launchPointId;
    }

    public void setLaunchPointId(String launchPointId) {
        this.launchPointId = launchPointId;
    }

    public String getImpactPointId() {
        return impactPointId;
    }

    public void setImpactPointId(String impactPointId) {
        this.impactPointId = impactPointId;
    }

    public ImpactPoint getImpactPoint() {
        return impactPoint;
    }

    public void setImpactPoint(ImpactPoint impactPoint) {
        this.impactPoint = impactPoint;
    }




    public void init() {

        setLaunchPoint(launchPointMap.get(getLaunchPointId()));
        setImpactPoint(impactPointMap.get(getImpactPointId()));

    }
}

package gov.dod.missile;

import gov.dod.radar.Battery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class MissileApplication {


	static int R = 6378;
	static EnemyMissileType scud_c = new EnemyMissileType();

	public static void main(String[] args) {
	//	SpringApplication.run(MissileApplication.class, args);

		Missile missile = new Missile();
		missile.setImpactPointId("T8");
		missile.setLaunchPointId("L2");

		MissileApplication missileApplication = new MissileApplication();
		missileApplication.setMissile(missile);

		System.out.println(missileApplication.engageFeasible());
		System.out.println(missileApplication.bda());
		if(missileApplication.bda()) {
			System.out.println("Success Rate " + Math.random()*(30)+40);
		}
	}

	static Missile missile = new Missile();

	@RequestMapping(path="/missile", method = RequestMethod.GET)
	public Missile getMissile(){

		///System.out.println("now the missile info has been requested");

		return missile;
	}

	@RequestMapping(path="/missile", method = RequestMethod.POST)
	public Missile setMissile(@RequestBody  Missile missile_){
		missile = missile_;

		return missile;
	}

	@RequestMapping(path="/missile", method = RequestMethod.DELETE)
	public Missile deleteMissile(){
		missile = new Missile();

		return missile;
	}

	@RequestMapping(path="/estimate", method = {RequestMethod.POST, RequestMethod.GET})
	public Missile estimate(@RequestBody Missile missile){

		setMissile(missile);

		System.out.println("estimated");

		missile.setBurnedOut(true);

		return missile;
	}

	@RequestMapping(path="/selectBattery", method = {RequestMethod.POST, RequestMethod.GET})
	public Battery selectBattery(){

		System.out.println("battery selected");


		//missile 기반으로 포대를 선택.

		Battery battery =  new Battery();
		battery.setBatteryId("B1");
		return battery;
	}


	@RequestMapping(path="/engageFeasible", method = {RequestMethod.GET})
	public boolean engageFeasible() {
		return checkPossibility(0);
	}

	@RequestMapping(path="/dba", method = {RequestMethod.GET})
	public boolean bda(){
		int fcq = 18;//15 + (int)(5 * Math.random());



		return checkPossibility(fcq);
	}

	public boolean checkPossibility(int fcq){

		double speedx;

		missile.init();

		double targetX = R * (Math.abs( missile.getLaunchPoint().getLongitude() - missile.getImpactPoint().getLongitude()) * Math.PI / 180)
				* Math.cos((missile.getLaunchPoint().getLatitude() + missile.getImpactPoint().getLatitude()) * Math.PI / (2*180));

		double targetY = R * ((Math.abs(missile.getLaunchPoint().getLatitude() - missile.getImpactPoint().getLatitude()) * Math.PI / 180));

		//System.out.println(missile.getLaunchPoint().getLongitude());
		//System.out.println(missile.getImpactPoint().getLongitude());
		//System.out.println(targetX);
		//System.out.println(targetY);
		///////////// implicitly set
		//targetX = 29;
		//targetY = 55;

		double s = Math.sqrt(targetX * targetX + targetY * targetY);
		double theta = Math.atan2(targetX, targetY);

		speedx = missile.getspeed(s);
		System.out.println(speedx);
		///
		double a = scud_c.getAlphaA() * Math.exp(scud_c.getBetaA() * s) + scud_c.getGammaA() * Math.exp(scud_c.getDeltaA() * s);
		double b = scud_c.getAlphaB() * Math.exp(scud_c.getBetaB() * s) + scud_c.getGammaB() * Math.exp(scud_c.getDeltaB() * s);

		List<Double> dList = new ArrayList<Double>();
		List<Double> mxList = new ArrayList<Double>();
		List<Double> myList = new ArrayList<Double>();
		List<Double> xList = new ArrayList<Double>();
		List<Double> yList = new ArrayList<Double>();
		for(double x = 0; x <= s; x+=speedx){
			double y = a * x*x + b * x + scud_c.getEpsilon();
			double d = ( ( b * -1 - Math.sqrt(b *b + 4*a*y )) / (2*a));
			//double d = x;
			double mx = d * Math.sin(theta);
			double my = d * Math.cos(theta);

			xList.add(x);
			yList.add(y);
			mxList.add(mx);
			myList.add(my);
			dList.add(d);
		}

		double maximum = 0;
		int indexY = 0;


		for(int i=0; i < yList.size(); i++){

			Double y = yList.get(i);

			if(maximum < y){
				maximum = y;
				indexY = i;
			}
		}

		// System.out.println(indexY);

		int burnoutPosition = indexY;

		//double decisionA = targetY - myList.get(burnoutPosition);

		///

		int token = 0, detect = 0;

		List<Double> decisionB1List = new ArrayList<Double>();
		List<Double> decisionB2List = new ArrayList<Double>();
		List<Double> decisionCList = new ArrayList<Double>();
		List<Integer> tokenList = new ArrayList<Integer>();

		for(int i=burnoutPosition + fcq; i < xList.size(); i++){

			double decisionA = targetY - myList.get(i);
			if(decisionA > 0)
				token = 1;

			double deltaX = decisionA * Math.tan(Math.toRadians(missile.getImpactPoint().getAngle() /2));
			double decisionB1 = targetX + deltaX;
			double decisionB2 = targetX - deltaX;

			double compareB = mxList.get(i);

			if(compareB < decisionB1) 	{
				token ++; }

			if(compareB > decisionB2) {
				token ++;
				}

			double decisionC = Math.sqrt((targetX - mxList.get(i)) * (targetX - mxList.get(i)) + (targetY - myList.get(i)) * (targetY - myList.get(i)) + (0 - yList.get(i)) * (0 - yList.get(i)));

			decisionB1List.add(decisionB1);
			decisionB2List.add(decisionB2);
			decisionCList.add(decisionC);

			if(decisionC > getMissile().getImpactPoint().getdMin())
				token ++;
			if(decisionC < getMissile().getImpactPoint().getdMax())
				token ++;

			tokenList.add(token);

			if(token > 4)
				return true;

			token = 0;

		}

		return false;

	}



}

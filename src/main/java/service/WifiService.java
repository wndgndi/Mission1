package service;

import java.util.List;

import domain.JsonResponse;
import domain.Wifi;

public class WifiService {
	
	public double getTotalCount(JsonResponse response) {
		return response.getTbPublicWifiInfo().getTotalCount();
	}
	
	public List<Wifi> getWifiList(JsonResponse response) {
		return response.getTbPublicWifiInfo().getRow();
	}
	
	public double calculateDistance(double lat1, double lnt1, double lat2, double lnt2) {
	       
		final double EARTH_RADIUS = 6371;
		
        double lat1Rad = Math.toRadians(lat1);
        double lnt1Rad = Math.toRadians(lnt1);
        double lat2Rad = Math.toRadians(lat2);
        double lnt2Rad = Math.toRadians(lnt2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLnt = lnt2Rad - lnt1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLnt / 2) * Math.sin(deltaLnt / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }

}

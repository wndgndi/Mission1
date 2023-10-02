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
}

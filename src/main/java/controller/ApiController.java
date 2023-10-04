package controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.JsonResponse;
import domain.Wifi;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiController {

	public long getTotalCount() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

		urlBuilder.append("/" + URLEncoder.encode("556d72546e776e6438396a525a7878", "UTF-8")); // *인증키
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); // *요청파일타입(xml,xmlf,xls,json)
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode("2", "UTF-8"));
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("전체 데이터 개수 Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		String json = sb.toString();

		Gson gson = new Gson();
		JsonResponse response = gson.fromJson(json, JsonResponse.class);
		long totalCount = (long) response.getTbPublicWifiInfo().getTotalCount();

		conn.disconnect();

		return totalCount;
	}
	
	public List<Wifi> fetchApiData(long start, long end) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

		urlBuilder.append("/" + URLEncoder.encode("556d72546e776e6438396a525a7878", "UTF-8")); // *인증키
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); // *요청파일타입(xml,xmlf,xls,json)
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));

//		urlBuilder.append("/" + URLEncoder.encode("", "UTF-8")); /* 서비스별 추가 요청인자들: 자치구명 */
//		urlBuilder.append("/" + URLEncoder.encode("", "UTF-8")); /* 서비스별 추가 요청인자들: 도로명 주소 */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		String json = sb.toString();
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String prettyJson = gson.toJson(gson.fromJson(json, Object.class));
//		
//		System.out.println(prettyJson);

		Gson gson = new Gson();
		JsonResponse response = gson.fromJson(json, JsonResponse.class);
		
		conn.disconnect();

		return response.getTbPublicWifiInfo().getRow();
	}

	public List<Wifi> getAllData() throws IOException {
		long start = 1;
		long end = 1000;
		List<Wifi> wifiList = new ArrayList<>();
		long totalCount = getTotalCount();
//		long remain = totalCount;
				
		for (long i = start; i <= totalCount; i += 1000) {
			start = i;
			end = i+999;
			try {
			if(i + 1000 > totalCount) {
				wifiList.addAll(fetchApiData(i, Math.min(end, totalCount)));
				break;
			}
			
//			remain -= 1000;
			wifiList.addAll(fetchApiData(start, end));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return wifiList;
	}

}
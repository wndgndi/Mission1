package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Wifi;
import service.DMLService;
import service.check;

@WebServlet("/loadWifi")
public class DBController extends HttpServlet {
	private DMLService DML = new DMLService("jdbc:sqlite:mission1.db");
    private DDLController DDL = new DDLController();
	private ApiController api = new ApiController(); 
    
	// 데이터 입력 함수
	public void insertAll() throws SQLException, IOException {
		List<Wifi> wifiList = api.getAllData();
	     // 데이터 입력
	     int inserted = DML.insertWifiInfo(wifiList, "WIFI_LIST");
	     if( inserted >= 0 ) {
	         System.out.println(String.format("데이터 입력 성공: %d건", inserted));
	     } else {
	         System.out.println("데이터 입력 실패");
	     }
	 }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		DBController db = new DBController();
		
		try {
			DDL.dropTable("WIFI_LIST");
			DDL.createTable("WIFI_LIST");
			db.insertAll();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		long totalCount = api.getTotalCount();
		request.setAttribute("totalCount", totalCount);
		request.getRequestDispatcher("/loadWifi.jsp").forward(request, response);
	}
	
}

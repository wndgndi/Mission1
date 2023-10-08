package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.History;
import domain.Wifi;
import service.DMLService;

@WebServlet("/history")
public class HistoryController extends HttpServlet{
	private DMLService DML = new DMLService("jdbc:sqlite:mission1.db");

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lnt = Double.parseDouble(request.getParameter("lnt"));
		System.out.println(lat);
		System.out.println(lnt);
		try {
			insertHistory(lat, lnt);
			List<Wifi> wifiList = DML.insertDistance(lat, lnt);		
			request.setAttribute("wifiList", wifiList);
			for(Wifi wifi : wifiList) {
				System.out.println(wifi.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("/Mission1/index.jsp").forward(request, response);
		}
	}
	
	public int insertHistory(double lat, double lnt) throws SQLException {
		History history = new History();
		
		history.setLatitude(lat);
		history.setLongitude(lnt);
		return DML.insertHistory("HISTORY", history);
	}
	
}

package controller;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.History;
import service.DMLService;

@WebServlet("/Mission1/history")
public class HistoryController extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lnt = Double.parseDouble(request.getParameter("lnt"));
		try {
			insertHistory(lat, lnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertHistory(double lat, double lnt) throws SQLException {
		History history = new History();
		DMLService dml = new DMLService();
		
		history.setLatitude(lat);
		history.setLongitude(lnt);
		
		return dml.insertHistory("HISTORY", history);
	}
	
}

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Wifi;
import service.DDLService;
import service.DMLService;

public class DMLController {
	private DDLService DDL = new DDLService("jdbc:sqlite:mission1.db");
	private DMLService DML = new DMLService("jdbc:sqlite:mission1.db");
	ApiController api = new ApiController();
	
	public void insertAll(String tableName) throws SQLException, IOException {
		List<Wifi> wifiList = api.getAllData();
		DML.insertWifiInfo(wifiList, tableName);
	}
	
}

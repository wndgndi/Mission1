package service;

import java.sql.SQLException;

import controller.DDLController;

public class check {
	public static void main(String[] args) throws SQLException {
		DDLController ddl = new DDLController();
		ddl.dropTable("HISTORY");
		ddl.createHistoryTable("HISTORY");
	}
}

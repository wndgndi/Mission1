package service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.SQLiteManager;

public class DDLService extends SQLiteManager{
	
	public DDLService() {
		
	}
	
	public DDLService(String url) {
		super(url);
	}
	
	public ResultType executeSQL(final String SQL) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		
		ResultType result = ResultType.FAILURE;
		
		try {
			conn = ensureConnection();
			stmt = conn.createStatement();
			stmt.execute(SQL);
			conn.commit();
			
			result = ResultType.SUCCESS;
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
			if(conn != null) {
				conn.rollback();
			}
			
			result = ResultType.FAILURE;
			
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public boolean checkTable(String tableName) throws SQLException {
		Connection conn = ensureConnection();
		DatabaseMetaData meta = conn.getMetaData();
		
		ResultSet tables = meta.getTables(null, null, tableName, null);
		return (tables.next() ? tables.getRow() != 0 : false);
	}
	
	public ResultType createTable(String tableName) throws SQLException {
		 final String SQL = "CREATE TABLE IF NOT EXISTS " + tableName +  "(  " + "\n"
					 + " MGRNO TEXT PRIMARY KEY," + "\n"
					 + " BOROUGH TEXT," + "\n"
					 + " WIFINAME TEXT," + "\n"
					 + " ROAD_ADDRESS TEXT," + "\n"
					 + " DETAIL_ADDRESS TEXT," + "\n"
					 + " INSTALLFLOOR TEXT," + "\n"
					 + " INSTALLTYPE TEXT," + "\n"
					 + " INSTALLAGENCY TEXT," + "\n"
					 + " SERVICETYPE TEXT," + "\n"
					 + " NETWORKTYPE TEXT," + "\n"
					 + " INSTALLYEAR TEXT," + "\n"
					 + " IN_OUT_DOOR TEXT," + "\n"
					 + " ENVIRONMENT TEXT," + "\n"
					 + " LATITUDE REAL," + "\n"
					 + " LONGITUDE REAL," + "\n"
					 + " WORKDATE TEXT," + "\n"
				 	 + " DISTANCE REAL DEFAULT 0.0)";
;
		 
		 if(checkTable(tableName)) {
				return ResultType.WARNING;
		 }
		return executeSQL(SQL);
	}
	
	
	public ResultType createHistory(String tableName) throws SQLException {
		 final String SQL = "CREATE TABLE IF NOT EXISTS " + tableName +  "(  " + "\n"
				 	 + " HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "\n"
					 + " LATITUDE REAL," + "\n"
					 + " LONGITUDE REAL," + "\n"
					 + " INQUIRYDATE TEXT)" + "\n";
		 
		 if(checkTable(tableName)) {
				return ResultType.WARNING;
		 }
		return executeSQL(SQL);
	}
	
	public ResultType dropTable(String tableName) throws SQLException {
		if(!checkTable(tableName)) {
			return ResultType.WARNING;
		}
		
		return executeSQL("DROP TABLE IF EXISTS " + tableName);
	}
	
	public enum ResultType {
		SUCCESS(1),
		WARNING(0),
		FAILURE(-1);
		
		private int code = 0;
		
		private ResultType(int code) {
			this.code = code;
		}
			
		public int getCode() {
			return this.code;
		}
	}
}

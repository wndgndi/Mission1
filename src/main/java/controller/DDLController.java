package controller;

import java.sql.SQLException;

import service.DDLService;
import service.DDLService.ResultType;

public class DDLController {
	private DDLService DDL = new DDLService("jdbc:sqlite:mission1.db");
	
	public void createTable(String tableName) throws SQLException {
		
	
		ResultType result = DDL.createTable(tableName);
		
		switch(result) {
			case SUCCESS:
				System.out.println("테이블 생성 완료");
				break;
			case WARNING:
				System.out.println("테이블이 이미 존재합니다.");
				break;
			case FAILURE:
				System.out.println("테이블 생성 실패");
				break;
		}
		
		DDL.closeConnection();
	}
	
	public void createHistoryTable(String tableName) throws SQLException {
		
		
		ResultType result = DDL.createHistory(tableName);
		
		switch(result) {
			case SUCCESS:
				System.out.println("테이블 생성 완료");
				break;
			case WARNING:
				System.out.println("테이블이 이미 존재합니다.");
				break;
			case FAILURE:
				System.out.println("테이블 생성 실패");
				break;
		}
		
		DDL.closeConnection();
	}
	
	public void dropTable(String tableName) throws SQLException{
		ResultType result = DDL.dropTable(tableName);
		
		switch(result) {
			case SUCCESS:
				System.out.println("테이블 삭제 완료");
				break;
			case WARNING:
				System.out.println("테이블이 존재하지 않습니다.");
				break;
			case FAILURE:
				System.out.println("테이블 삭제 실패");
				break;
			}
		DDL.closeConnection();
	}
	
}

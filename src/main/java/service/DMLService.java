package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import config.SQLiteManager;
import domain.History;
import domain.Wifi;

public class DMLService extends SQLiteManager {

	public DMLService() {

	}

	public DMLService(String url) {
		super(url);
	}

	int inserted = 0;

	public int insertWifiInfo(List<Wifi> wifiList, String tableName) throws SQLException, IOException {
			Connection conn = ensureConnection();
				
			final String sql = "INSERT INTO " + tableName + "(" + "\n" 
							 + " MGRNO," + "\n"	
							 + " BOROUTH," + "\n"
							 + " WIFINAME," + "\n"
							 + " ROADADDRESS," + "\n"
							 + " DETAILADDRESS," + "\n"
							 + " INSTALLFLOOR," + "\n"
							 + " INSTALLTYPE," + "\n"
							 + " INSTALLAGENCY," + "\n"
							 + " SERVICETYPE," + "\n"
							 + " NETWORKTYPE," + "\n"
							 + " INSTALLYEAR," + "\n"
							 + " INDOOROUTDOOR," + "\n"
							 + " WIFIENVIRONMENT," + "\n"
							 + " LATITUDE," + "\n"
							 + " LONGITUDE," + "\n"
							 + " WORKDATE" + "\n"
							 + " ) VALUES ("
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?," + "\n"
							 + " ?" + "\n"
							 + ")";
			
			PreparedStatement pstmt = null;
			
			try {
				for(int i=0; i<wifiList.size(); i++) {	
				pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, wifiList.get(i).getMgrNo());
				pstmt.setObject(2, wifiList.get(i).getBorough());				
				pstmt.setObject(3, wifiList.get(i).getWifiName());
				pstmt.setObject(4, wifiList.get(i).getRoadAddress());
				pstmt.setObject(5, wifiList.get(i).getDetailAddress());
				pstmt.setObject(6, wifiList.get(i).getInstallFloor());
				pstmt.setObject(7, wifiList.get(i).getInstallType());
				pstmt.setObject(8, wifiList.get(i).getInstallAgency());
				pstmt.setObject(9, wifiList.get(i).getServiceType());
				pstmt.setObject(10, wifiList.get(i).getNetworkType());
				pstmt.setObject(11, wifiList.get(i).getInstallYear());
				pstmt.setObject(12, wifiList.get(i).getIndoorOutdoor());
				pstmt.setObject(13, wifiList.get(i).getWifiEnvironment());
				pstmt.setObject(14, wifiList.get(i).getLatitude());
				pstmt.setObject(15, wifiList.get(i).getLongitude());
				pstmt.setObject(16, wifiList.get(i).getWorkDate());
				
				pstmt.executeUpdate();
				inserted = pstmt.getUpdateCount();
				} 
				conn.commit();
	
			}catch (SQLException e){
				System.out.println(e.getMessage());
				if(conn != null) {
					conn.rollback();
				}
				
				inserted = -1;
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return inserted;
		}
	
	public int insertHistory(String tableName, History history) throws SQLException {
		Connection conn = ensureConnection();

		final String sql = "INSERT INTO " + tableName + "(" + "\n" 
						 + " LONGITUDE," + "\n"	
						 + " LATITUDE" + "\n"
						 + " ) VALUES ("
						 + " ?," + "\n"
						 + " ?" + "\n"
						 + ")";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, history.getLongitude());
			pstmt.setObject(2, history.getLatitude());				

			
			pstmt.executeUpdate();
			inserted = pstmt.getUpdateCount();
			conn.commit();

		}catch (SQLException e){
			System.out.println(e.getMessage());
			if(conn != null) {
				conn.rollback();
			}
			
			inserted = -1;
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return inserted;
	}
}

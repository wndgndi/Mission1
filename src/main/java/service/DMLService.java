package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		

		final String sql = "INSERT INTO " + tableName + "(" + "\n" + " MGRNO," + "\n" + " BOROUGH," + "\n"
				+ " WIFINAME," + "\n" + " ROAD_ADDRESS," + "\n" + " DETAIL_ADDRESS," + "\n" + " INSTALLFLOOR," + "\n"
				+ " INSTALLTYPE," + "\n" + " INSTALLAGENCY," + "\n" + " SERVICETYPE," + "\n" + " NETWORKTYPE," + "\n"
				+ " INSTALLYEAR," + "\n" + " IN_OUT_DOOR," + "\n" + " ENVIRONMENT," + "\n" + " LATITUDE," + "\n"
				+ " LONGITUDE," + "\n" + " WORKDATE" + "\n" + " ) VALUES (" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n"
				+ " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n"
				+ " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?," + "\n" + " ?" + "\n" + ")";
		
		Connection conn = ensureConnection();
		PreparedStatement pstmt = null;

		try {
			for (int i = 0; i < wifiList.size(); i++) {
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
				pstmt.setObject(14, wifiList.get(i).getLongitude());
				pstmt.setObject(15, wifiList.get(i).getLatitude());
				pstmt.setObject(16, wifiList.get(i).getWorkDate());

				pstmt.executeUpdate();
				inserted = pstmt.getUpdateCount();
			}
			conn.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			if (conn != null) {
				conn.rollback();
			}

			inserted = -1;
		} finally {
			if (pstmt != null) {
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

		final String sql = "INSERT INTO " + tableName + "(" + "\n" + " LONGITUDE," + "\n" + " LATITUDE," + "\n"
				+ " INQUIRYDATE" + "\n" + " ) VALUES (" + " ?," + "\n" + " ?," + "\n" + " datetime('now', 'localtime')"
				+ "\n" + ")";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, history.getLongitude());
			pstmt.setObject(2, history.getLatitude());
			
			pstmt.executeUpdate();
			inserted = pstmt.getUpdateCount();
			conn.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			if (conn != null) {
				conn.rollback();
			}

			inserted = -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return inserted;
	}
	
	public List<Wifi> insertDistance(double lat1, double lnt1) throws SQLException {
		WifiService wifiService = new WifiService();
        Connection conn = ensureConnection();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<Wifi> wifiList = new ArrayList<>();
        int cnt = 0;
        
		try {
	        final String selectSql = "SELECT MGRNO, LATITUDE, LONGITUDE FROM WIFI";
	        pstmt = conn.prepareStatement(selectSql);

	        resultSet = pstmt.executeQuery();

	        final String updateSql = "UPDATE WIFI SET DISTANCE = ? WHERE MGRNo = ?"; 
	        pstmt = conn.prepareStatement(updateSql);

	        while (resultSet.next()) {
	            String mgrNo = resultSet.getString("MGRNo");
	            double lat2 = resultSet.getDouble("LATITUDE"); 
	            double lnt2 = resultSet.getDouble("LONGITUDE");

	            double distance = wifiService.calculateDistance(lat1, lnt1, lat2, lnt2);

	            pstmt.setObject(1, distance);
	            pstmt.setObject(2, mgrNo);
	            pstmt.executeUpdate();
	            conn.commit();
	            
	            cnt++;
	            System.out.println("두 지점 사이의 거리: " + distance + " km");
	            System.out.println(cnt+"개");
	        }
	        
	        final String Sql = "SELECT * FROM WIFI ORDER BY DISTANCE LIMIT 10";
	        pstmt = conn.prepareStatement(Sql);
	        resultSet = pstmt.executeQuery();
		        while (resultSet.next()) {
		            String mgrNo = resultSet.getString("MGRNo");
		            String borough = resultSet.getString("BOROUGH");
		            String wifiName = resultSet.getString("WIFINAME");
		            String roadAddress = resultSet.getString("ROAD_ADDRESS");
		            String detailAddress = resultSet.getString("DETAIL_ADDRESS");
		            String installFloor = resultSet.getString("INSTALLFLOOR");
		            String installType = resultSet.getString("INSTALLTYPE");
		            String installAgency = resultSet.getString("INSTALLAGENCY");
		            String serviceType = resultSet.getString("SERVICETYPE");
		            String networkType = resultSet.getString("NETWORKTYPE");
		            String installYear = resultSet.getString("INSTALLYEAR");
		            String indoorOutdoor = resultSet.getString("IN_OUT_DOOR");
		            String wifiEnvironment = resultSet.getString("ENVIRONMENT");
		            double latitude = resultSet.getDouble("LATITUDE"); 
		            double longitude = resultSet.getDouble("LONGITUDE");
		            String workDate = resultSet.getString("WORKDATE");
		            double distance = resultSet.getDouble("DISTANCE");
					Wifi wifi = new Wifi(mgrNo, borough, wifiName, roadAddress, detailAddress, installFloor, installType, installAgency, serviceType, networkType, installYear,
							indoorOutdoor, wifiEnvironment, latitude, longitude, workDate, distance);
		            
		            wifiList.add(wifi);
		            }
		            
	        }  catch (SQLException e) {
				System.out.println(e.getMessage());
				if (conn != null) {
					conn.rollback();
				}

				inserted = -1;
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}

	        return wifiList;
	}
	
}


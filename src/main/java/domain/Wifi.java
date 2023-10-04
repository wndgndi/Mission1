package domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wifi {
	private double distance;
	
	@SerializedName("X_SWIFI_MGR_NO")
	private String mgrNo;  // 관리번호
	
	@SerializedName("X_SWIFI_WRDOFC")
	private String borough;  // 자치구
	
	@SerializedName("X_SWIFI_MAIN_NM")
	private String wifiName;  // 와이파이명
	
	@SerializedName("X_SWIFI_ADRES1")
	private String roadAddress;  // 도로명 주소
	
	@SerializedName("X_SWIFI_ADRES2")
	private String detailAddress;  // 상세 주소
	
	@SerializedName("X_SWIFI_INSTL_FLOOR")
	private String installFloor;  // 설치 위치(층)
    
	@SerializedName("X_SWIFI_INSTL_TY")
	private String installType;  // 설치 유형
	
	@SerializedName("X_SWIFI_INSTL_MBY")
    private String installAgency;  // 설치 기관
	
	@SerializedName("X_SWIFI_SVC_SE")
    private String serviceType;  // 서비스 구분
    
	@SerializedName("X_SWIFI_CMCWR")
	private String networkType;  // 망종류
	
	@SerializedName("X_SWIFI_CNSTC_YEAR")
    private String installYear;  // 설치년도
    
	@SerializedName("X_SWIFI_INOUT_DOOR")
	private String indoorOutdoor;  // 실내외 구분
    
	@SerializedName("X_SWIFI_REMARS3")
	private String wifiEnvironment;  // wifi 접속환경
   
	@SerializedName("LAT")
	private double latitude;  // Y 좌표
    
	@SerializedName("LNT")
	private double longitude;  // X 좌표
    
	@SerializedName("WORK_DTTM")
	private String workDate;  // 작업일자
	
    public Wifi(String mgrNo, String borough, String wifiName, String roadAddress, String detailAddress,
			String installFloor, String installType, String installAgency, String serviceType, String networkType,
			String installYear, String indoorOutdoor, String wifiEnvironment, double latitude, double longitude,
			String workDate) {
		super();
		this.mgrNo = mgrNo;
		this.borough = borough;
		this.wifiName = wifiName;
		this.roadAddress = roadAddress;
		this.detailAddress = detailAddress;
		this.installFloor = installFloor;
		this.installType = installType;
		this.installAgency = installAgency;
		this.serviceType = serviceType;
		this.networkType = networkType;
		this.installYear = installYear;
		this.indoorOutdoor = indoorOutdoor;
		this.wifiEnvironment = wifiEnvironment;
		this.latitude = latitude;
		this.longitude = longitude;
		this.workDate = workDate;
	}

	@Override
	public String toString() {
		return "Wifi [mgrNo=" + mgrNo + ", borough=" + borough + ", wifiName=" + wifiName + ", roadAddress="
				+ roadAddress + ", detailAddress=" + detailAddress + ", installFloor=" + installFloor + ", installType="
				+ installType + ", installAgency=" + installAgency + ", serviceType=" + serviceType + ", networkType="
				+ networkType + ", installYear=" + installYear + ", indoorOutdoor=" + indoorOutdoor
				+ ", wifiEnvironment=" + wifiEnvironment + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", workDate=" + workDate + "]";
	}
    
    
}

package domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History {
	private long history_id;
	private double longitude;
	private double latitude;
	private LocalDateTime inquiryDate;

	public History() {

	}

	public History(long history_id, double longitude, double latitude, LocalDateTime inquiryDate) {
		super();
		this.history_id = history_id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.inquiryDate = inquiryDate;
	}

}

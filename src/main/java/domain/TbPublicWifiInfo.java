package domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class TbPublicWifiInfo {
	@SerializedName("list_total_count")
	private double totalCount;
	private Result result;
	private List<Wifi> row;
}


class Result {
	private String code;
	private String message;
}
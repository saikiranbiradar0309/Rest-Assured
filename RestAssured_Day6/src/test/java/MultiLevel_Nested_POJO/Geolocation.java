package MultiLevel_Nested_POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Geolocation {
	
	private String lat;
	
	@JsonProperty(value = "long")
	private String geoLong;
}

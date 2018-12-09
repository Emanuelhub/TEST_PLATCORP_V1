package br.com.api.templateRest;

public class WeatherDataTemplate {
	private String id;
	private String applicable_date;
	private String min_temp;
	private String max_temp;
	
	public WeatherDataTemplate() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicable_date() {
		return applicable_date;
	}

	public void setApplicable_date(String applicable_date) {
		this.applicable_date = applicable_date;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	@Override
	public String toString() {
		return "WeatherDataTemplate [id=" + id + ", applicable_date=" + applicable_date + ", min_temp=" + min_temp
				+ ", max_temp=" + max_temp + "]";
	}
	
}

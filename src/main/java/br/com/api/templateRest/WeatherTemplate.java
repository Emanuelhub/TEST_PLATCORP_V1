package br.com.api.templateRest;

import java.util.List;

public class WeatherTemplate {
	private List<WeatherDataTemplate> consolidated_weather;
	
	public WeatherTemplate() {
		
	}

	public List<WeatherDataTemplate> getConsolidated_weather() {
		return consolidated_weather;
	}

	public void setConsolidated_weather(List<WeatherDataTemplate> consolidated_weather) {
		this.consolidated_weather = consolidated_weather;
	}

	@Override
	public String toString() {
		return "WeatherTemplate [consolidated_weather=" + consolidated_weather + "]";
	}
	
}

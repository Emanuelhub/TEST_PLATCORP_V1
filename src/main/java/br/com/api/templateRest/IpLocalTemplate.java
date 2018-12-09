package br.com.api.templateRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpLocalTemplate {
	private String status;
	private DataIpLocalTemplate data;
	
	public IpLocalTemplate(){
	
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DataIpLocalTemplate getData() {
		return data;
	}

	public void setData(DataIpLocalTemplate data) {
		this.data = data;
	}
	
	
	
}
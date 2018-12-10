package br.com.api.dtos;

import java.util.Date;

public class RequestDTO {
	private Long id;
	private String ipOrigem;
	private Double latitude;
	private Double longitude;
	private Date data;
	private Float minima;
	private Float maxima;
	
	public RequestDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpOrigem() {
		return ipOrigem;
	}
	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Float getMinima() {
		return minima;
	}
	public void setMinima(Float minima) {
		this.minima = minima;
	}
	public Float getMaxima() {
		return maxima;
	}
	public void setMaxima(Float maxima) {
		this.maxima = maxima;
	}
	
	@Override
	public String toString() {
		return "RequestDTO [id=" + id + ", ipOrigem=" + ipOrigem + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", data=" + data + ", minima=" + minima
				+ ", maxima=" + maxima + "]";
	}
}

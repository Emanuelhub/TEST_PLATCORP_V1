package br.com.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request implements Serializable{

	private static final long serialVersionUID = 8172744663755594800L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String ipOrigem;
	private Date   dataRequisicao;
	private Double latitude;
	private Double longitude;
	
	public String getIpOrigem() {
		return ipOrigem;
	}
	public void setIpOrigem(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}
	public Date getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
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
	@Override
	public String toString() {
		return "Request [id=" + id + ", ipOrigem=" + ipOrigem + ", dataRequisicao=" + dataRequisicao + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
}

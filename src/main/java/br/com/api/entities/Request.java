package br.com.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="request")
public class Request implements Serializable{

	private static final long serialVersionUID = 8172744663755594800L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="ip_origem",nullable=false)
	private String ipOrigem;
	
	@Column(name="latitude",nullable=true)
	private Double latitude;
	
	@Column(name="longitude",nullable=true)
	private Double longitude;
	
	@Column(name="cidade",nullable=true)
	private String cidade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data",nullable=false)
	private Date data;
	
	@Column(name="temp_minima",nullable=true)
	private Float minima;
	
	@Column(name="temp_maxima",nullable=true)
	private Float maxima;

	@PrePersist
	public void prePersist() {
		data =  new Date();
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", ipOrigem=" + ipOrigem + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", data=" + data + ", cidade=" + cidade + ", minima=" + minima + ", maxima=" + maxima + "]";
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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
}

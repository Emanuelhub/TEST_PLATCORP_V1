package br.com.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Clima implements Serializable{
	
	private static final long serialVersionUID = 4641395635901892644L;
	
	@Id
	private Long id;
	private String cidade;
	private Float minima;
	private Float maxima;
	private String img;
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Clima [id=" + id + ", cidade=" + cidade + ", minima=" + minima + ", maxima=" + maxima + ", img=" + img
				+ "]";
	}
}

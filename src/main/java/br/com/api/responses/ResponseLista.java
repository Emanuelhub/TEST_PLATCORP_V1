package br.com.api.responses;

import java.util.ArrayList;
import java.util.List;

public class ResponseLista<T> {

	private List<T> data;
	private List<String> erros;
	
	public ResponseLista() {
	}


	public List<String> getErros() {
		if(this.erros==null) {
			this.erros = new ArrayList<String>();
		}
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}


	public List<T> getData() {
		if(this.data==null) {
			this.data = new ArrayList<T>();
		}
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}



	
}

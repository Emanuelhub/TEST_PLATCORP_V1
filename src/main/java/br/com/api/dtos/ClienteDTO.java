package br.com.api.dtos;

public class ClienteDTO {
	private Long id;
	private String nome;
	private Integer idade;
	private RequestDTO requestCreate;
	
	public ClienteDTO() {
		
	}
	
	public String getLattlong() {
		return requestCreate.getLatitude()+","+requestCreate.getLongitude();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public RequestDTO getRequestCreate() {
		return requestCreate;
	}
	public void setRequestCreate(RequestDTO requestCreate) {
		this.requestCreate = requestCreate;
	}
	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nome=" + nome + ", idade=" + idade + ", requestCreate=" + requestCreate
				+ "]";
	}
	
}

package fiap.tds.model.vo;

public class Oficina {
	private int id;
	private String nome;
	private int cep;
	private String logradouro;
	private String complementoEndereco;
	private String telefone;
	private String email;
	private String cnpj;
	private String especialidade;
	private String horarioFuncionamento;
	
	public Oficina(int id, String nome, int cep, String logradouro, String complementoEndereco, String telefone,
			String email, String cnpj, String especialidade, String horarioFuncionamento) {
		this.id = id;
		this.nome = nome;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complementoEndereco = complementoEndereco;
		this.telefone = telefone;
		this.email = email;
		this.cnpj = cnpj;
		this.especialidade = especialidade;
		this.horarioFuncionamento = horarioFuncionamento;
	}
	
	public Oficina() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	@Override
	public String toString() {
		return "Oficina [id=" + id + ", nome=" + nome + ", cep=" + cep + ", logradouro=" + logradouro
				+ ", complementoEndereco=" + complementoEndereco + ", telefone=" + telefone + ", email=" + email
				+ ", cnpj=" + cnpj + ", especialidade=" + especialidade + ", horarioFuncionamento="
				+ horarioFuncionamento + "]";
	}
}

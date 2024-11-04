package fiap.tds.model.vo;

public class Usuario {
	private int idUsuario;
	private String nomeUsuario;
	private String telefoneUsuario;
	private String emailUsuario;
	private String cpfUsuario;
	private int cep;
	private boolean clientePorto;
	
	public Usuario(int idUsuario, String nomeUsuario, String telefoneUsuario, String emailUsuario, int cep,
			boolean clientePorto, String cpfUsuario) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.telefoneUsuario = telefoneUsuario;
		this.emailUsuario = emailUsuario;
		this.cpfUsuario = cpfUsuario;
		this.cep = cep;
		this.clientePorto = clientePorto;
	}
	
	public Usuario() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTelefoneUsuario() {
		return telefoneUsuario;
	}

	public void setTelefoneUsuario(String telefoneUsuario) {
		this.telefoneUsuario = telefoneUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public boolean isClientePorto() {
		return clientePorto;
	}

	public void setClientePorto(boolean clientePorto) {
		this.clientePorto = clientePorto;
	}
	

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", telefoneUsuario="
				+ telefoneUsuario + ", emailUsuario=" + emailUsuario + ", cpfUsuario=" + cpfUsuario + ", cep=" + cep
				+ ", clientePorto=" + clientePorto + "]";
	}
}

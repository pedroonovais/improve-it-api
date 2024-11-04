package fiap.tds.model.vo;

public class Servico {
	private int idServico;
	private String nomeServico;
	private String tipoServico;
	private float preco;
	
	public Servico(int idServico, String nomeServico, String tipoServico, float preco) {
		this.idServico = idServico;
		this.nomeServico = nomeServico;
		this.tipoServico = tipoServico;
		this.preco = preco;
	}
	
	public Servico() {
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", nomeServico=" + nomeServico + ", tipoServico=" + tipoServico
				+ ", preco=" + preco + "]";
	}	
}

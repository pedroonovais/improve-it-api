package fiap.tds.model.vo;

import java.util.Date;

public class Orcamento {
	private int idOrcamento;
	private int idUsuario;
	private int idOficina;
	private String statusOrcamento;
	private float valorOrcamento;
	private Date dataCriacao;
	
	public Orcamento(int idOrcamento, int idUsuario, int idOficina, String statusOrcamento, float valorOrcamento,
			Date dataCriacao) {
		this.idOrcamento = idOrcamento;
		this.idUsuario = idUsuario;
		this.idOficina = idOficina;
		this.statusOrcamento = statusOrcamento;
		this.valorOrcamento = valorOrcamento;
		this.dataCriacao = dataCriacao;
	}
	
	public Orcamento() {
	}

	public int getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}

	public String getStatusOrcamento() {
		return statusOrcamento;
	}

	public void setStatusOrcamento(String statusOrcamento) {
		this.statusOrcamento = statusOrcamento;
	}

	public float getValorOrcamento() {
		return valorOrcamento;
	}

	public void setValorOrcamento(float valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "Orcamento [idOrcamento=" + idOrcamento + ", idUsuario=" + idUsuario + ", idOficina=" + idOficina
				+ ", statusOrcamento=" + statusOrcamento + ", valorOrcamento=" + valorOrcamento + ", dataCriacao="
				+ dataCriacao + "]";
	}
	
}

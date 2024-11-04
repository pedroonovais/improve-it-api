package fiap.tds.model.vo;

public class Veiculo {
	private int idVeiculo;
	private String modeloVeiculo;
	private String marcaVeiculo;
	private int anoFabricacaoVeiculo;
	private int anoModeloVeiculo;
	private String placaVeiculo;
	private int quilometragemVeiculo;
	
	public Veiculo(int idVeiculo, String modeloVeiculo, String marcaVeiculo, int anoFabricacaoVeiculo,
			int anoModeloVeiculo, String placaVeiculo, int quilometragemVeiculo) {
		this.idVeiculo = idVeiculo;
		this.modeloVeiculo = modeloVeiculo;
		this.marcaVeiculo = marcaVeiculo;
		this.anoFabricacaoVeiculo = anoFabricacaoVeiculo;
		this.anoModeloVeiculo = anoModeloVeiculo;
		this.placaVeiculo = placaVeiculo;
		this.quilometragemVeiculo = quilometragemVeiculo;
	}
	
	public Veiculo() {
	}

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public void setMarcaVeiculo(String marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	public int getAnoFabricacaoVeiculo() {
		return anoFabricacaoVeiculo;
	}

	public void setAnoFabricacaoVeiculo(int anoFabricacaoVeiculo) {
		this.anoFabricacaoVeiculo = anoFabricacaoVeiculo;
	}

	public int getAnoModeloVeiculo() {
		return anoModeloVeiculo;
	}

	public void setAnoModeloVeiculo(int anoModeloVeiculo) {
		this.anoModeloVeiculo = anoModeloVeiculo;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public int getQuilometragemVeiculo() {
		return quilometragemVeiculo;
	}

	public void setQuilometragemVeiculo(int quilometragemVeiculo) {
		this.quilometragemVeiculo = quilometragemVeiculo;
	}

	@Override
	public String toString() {
		return "Veiculo [idVeiculo=" + idVeiculo + ", modeloVeiculo=" + modeloVeiculo + ", marcaVeiculo=" + marcaVeiculo
				+ ", anoFabricacaoVeiculo=" + anoFabricacaoVeiculo + ", anoModeloVeiculo=" + anoModeloVeiculo
				+ ", placaVeiculo=" + placaVeiculo + ", quilometragemVeiculo=" + quilometragemVeiculo + "]";
	}
}

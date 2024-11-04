package fiap.tds.model.dao;

import java.util.List;
import java.util.Map;

import fiap.tds.model.vo.Veiculo;

public interface VeiculoDAO {
	public int cadastro(String idUsuario, String ano, String marca, String modelo, String quilometragem);
	public List<Veiculo> listar();
	public Veiculo listarPorId(int id);
	public boolean atualizar(String idVeiculo, String marca, String modelo, String ano, String quilometragem);
	public void deletar(int id);

	public List<Map<String, Object>> buscarVeiculosPorUsuario(int usuarioId);
}

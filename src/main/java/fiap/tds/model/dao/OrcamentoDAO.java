package fiap.tds.model.dao;

import java.util.List;
import java.util.Map;

import fiap.tds.model.vo.Orcamento;

public interface OrcamentoDAO {
	public boolean cadastro(String idUsuario, String idVeiculo, String idOficina, String tipoOrcamento, String status, String diagnostico, String solucao, String preco);
	public List<Orcamento> listar();
	public Orcamento listarPorId(int id);
	public void atualizar(Orcamento orcamento);
	public void deletar(int id);

	public List<Map<String, Object>> buscarOrcamentosPorUsuario(int usuarioId);
}

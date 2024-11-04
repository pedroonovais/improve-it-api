package fiap.tds.model.dao;

import java.util.List;

import fiap.tds.model.vo.Servico;

public interface ServicoDAO {
	public void inserir(Servico servico);
	public List<Servico> listar();
	public Servico listarPorId(int id);
	public void atualizar(Servico servico);
	public void deletar(int id);
}

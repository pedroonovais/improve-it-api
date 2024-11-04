package fiap.tds.model.dao;

import java.util.List;

import fiap.tds.model.vo.Oficina;

public interface OficinaDAO {
	public void inserir(Oficina oficina);
	public List<Oficina> listar();
	public Oficina listarPorId(int id);
	public void atualizar(Oficina oficina);
	public void deletar(int id);
}

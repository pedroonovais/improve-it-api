package fiap.tds.model.dao;

import java.util.List;

import fiap.tds.model.vo.Usuario;

public interface UsuarioDAO {
	public int cadastro(String nome, String cpf, String email, String senha);
	public List<Usuario> listar();
	public Usuario listarPorId(int id);
	public void atualizar(Usuario usuario);
	public void deletar(int id);

	public int login(String login, String senha);
}

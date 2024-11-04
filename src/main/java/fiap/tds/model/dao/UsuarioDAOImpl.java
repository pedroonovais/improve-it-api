package fiap.tds.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fiap.tds.model.vo.Usuario;
import fiap.tds.util.DatabaseConnection;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }

	@Override
	public int cadastro(String nome, String cpf, String email, String senha) {
		int idUsuario = -1;

		// Inserir na tabela TB_USUARIO, sem incluir o id_usuario, pois será preenchido pela trigger
		String sqlUsuario = "INSERT INTO TB_USUARIO (nome_usuario, cpf_usuario, cliente_porto) VALUES (?, ?, ?)";

		try (Connection connection = getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sqlUsuario)) {

			stmt.setString(1, nome);
			stmt.setString(2, cpf);
			stmt.setString(3, "N");
			stmt.executeUpdate();

			// Consultar o valor atual da sequência para obter o ID do usuário inserido
			String sqlGetId = "SELECT TB_USUARIO_ID_USUARIO_SEQ.CURRVAL FROM DUAL";
			try (PreparedStatement stmtId = connection.prepareStatement(sqlGetId);
				 ResultSet rs = stmtId.executeQuery()) {

				if (rs.next()) {
					idUsuario = rs.getInt(1); // Pega o valor atual da sequência
				} else {
					throw new SQLException("Falha ao obter ID do usuário.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Se o ID foi obtido com sucesso, prosseguir com as outras inserções
		if (idUsuario != -1) {
			inserirContato(idUsuario, email);
			inserirLogin(idUsuario, email, senha);
		}

		return idUsuario;
	}

	private void inserirContato(int idUsuario, String email) {
		String sqlContato = "INSERT INTO TB_CONTATO_USUARIO (id_usuario, telefone, email) VALUES (?, ?, ?)";
		try (Connection connection = getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sqlContato)) {

			stmt.setInt(1, idUsuario);
			stmt.setInt(2, 0); // Telefone como exemplo, ajuste conforme necessário
			stmt.setString(3, email);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void inserirLogin(int idUsuario, String email, String senha) {
		String sqlLogin = "INSERT INTO TB_LOGIN_USUARIO (id_usuario, login, senha) VALUES (?, ?, ?)";
		try (Connection connection = getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sqlLogin)) {

			String[] partes = email.split("@");
			String login = partes[0];

			stmt.setInt(1, idUsuario);
			stmt.setString(2, login);
			stmt.setString(3, senha);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}





	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM TB_USUARIO";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nomeUsuario = rs.getString("nome_usuario");
                String telefoneUsuario = rs.getString("telefone_usuario");
                String emailUsuario = rs.getString("email_usuario");
                int cepUsuario = rs.getInt("cep");
                boolean clientePorto = rs.getBoolean("cliente_porto");
                String cpf = rs.getString("cpf_usuario");
                
                Usuario usuario = new Usuario(idUsuario, nomeUsuario, telefoneUsuario, emailUsuario, cepUsuario, clientePorto, cpf);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
	}

	@Override
	public Usuario listarPorId(int id) {
		Usuario usuario = new Usuario();
        String sql = "SELECT * FROM TB_USUARIO WHERE id_usuario = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	usuario.setIdUsuario(rs.getInt("id_usuario"));
            	usuario.setNomeUsuario(rs.getString("nome_usuario"));
				if (rs.getString("cliente_porto") == "S"){
					usuario.setClientePorto(true);
				}else{
					usuario.setClientePorto(false);
				}
            	usuario.setCpfUsuario(rs.getString("cpf_usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
	}

	@Override
	public void atualizar(Usuario usuario) {
		String sql = "UPDATE TB_USUARIO SET id_usuario = ?, nome_usuario = ?, telefone_usuario = ?, email_usuario = ?, cep = ?,"
				+ "cliente_porto = ?, cpf_usuario = ? WHERE id_usuario = ?";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, usuario.getIdUsuario());
	        stmt.setString(2, usuario.getNomeUsuario());
	        stmt.setString(3, usuario.getTelefoneUsuario());
	        stmt.setString(4, usuario.getEmailUsuario());
	        stmt.setInt(5, usuario.getCep());
	        stmt.setBoolean(6, usuario.isClientePorto());
	        stmt.setString(7, usuario.getCpfUsuario());
	        stmt.setInt(8, usuario.getIdUsuario());
		    stmt.execute();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "DELETE FROM TB_USUARIO WHERE id_usuario = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}

	@Override
	public int login(String login, String senha) {
		int id = 0;
		String sql = "SELECT id_usuario FROM TB_LOGIN_USUARIO WHERE login = ? AND senha = ?";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id_usuario");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}

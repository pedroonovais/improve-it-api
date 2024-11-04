package fiap.tds.model.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.tds.model.vo.Usuario;
import fiap.tds.util.DatabaseConnection;

public class UsuarioBO {

	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }
	
	public List<Usuario> consultaUsuariosNaoPorto(){
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM TB_USUARIO WHERE cliente_porto = true";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nomeUsuario = rs.getString("nome_usuario");
                String telefoneUsuario = rs.getString("telefone_usuario");
                String emailUsuario = rs.getString("email_usuario");
                int cepUsuario = rs.getInt("cep");
                boolean clientePorto = rs.getBoolean("cliente_porto");
                String cpfUsuario = rs.getString("cpf_usuario");
                
                Usuario usuario = new Usuario(idUsuario, nomeUsuario, telefoneUsuario, emailUsuario, cepUsuario, clientePorto, cpfUsuario);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
	}
}

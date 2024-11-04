package fiap.tds.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.tds.model.vo.Servico;
import fiap.tds.util.DatabaseConnection;

public class ServicoDAOImpl implements ServicoDAO{

	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }
	
	@Override
	public void inserir(Servico servico) {
		String sql = "INSERT INTO TB_SERVICO (id_servico, nome_servico, tipo_servico, preco) "
                + "VALUES (?, ?, ?, ?)";
	    try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, servico.getIdServico());
	        stmt.setString(2, servico.getNomeServico());
	        stmt.setString(3, servico.getTipoServico());
	        stmt.setFloat(4, servico.getPreco());	   
	        stmt.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	
	}

	@Override
	public List<Servico> listar() {
		List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM TB_SERVICO";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idServico = rs.getInt("id_servico");
                String nomeServico = rs.getString("nome_servico");
                String tipoServico = rs.getString("tipo_servico");
                float preco = rs.getFloat("preco");
                
                Servico servico = new Servico(idServico, nomeServico, tipoServico, preco);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
	}

	@Override
	public Servico listarPorId(int id) {
		Servico servico = new Servico();
        String sql = "SELECT * FROM TB_SERVICO WHERE id_servico = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	servico.setIdServico(rs.getInt("id_servico"));
            	servico.setNomeServico(rs.getString("nome_servico"));
            	servico.setTipoServico(rs.getString("tipo_servico"));
            	servico.setPreco(rs.getFloat("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servico;
	}

	@Override
	public void atualizar(Servico servico) {
		String sql = "UPDATE TB_SERVICO SET id_servico = ?, nome_servico = ?, tipo_servico = ?, preco = ? WHERE id_servico = ?";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, servico.getIdServico());
	        stmt.setString(2, servico.getNomeServico());
	        stmt.setString(3, servico.getTipoServico());
	        stmt.setFloat(4, servico.getPreco());
	        stmt.setInt(5, servico.getIdServico());
		    stmt.execute();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "DELETE FROM TB_SERVICO WHERE id_servico = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }			
	}

}

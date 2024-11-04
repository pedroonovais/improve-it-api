package fiap.tds.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import fiap.tds.model.vo.Orcamento;
import fiap.tds.util.DatabaseConnection;

public class OrcamentoDAOImpl implements OrcamentoDAO {

	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }

	@Override
	public boolean cadastro(String idUsuario, String idVeiculo, String idOficina, String tipoOrcamento, String status, String diagnostico, String solucao, String preco) {
		boolean response = false;
		java.util.Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		String sql = "INSERT INTO TB_ORCAMENTO (id_usuario, id_veiculo, id_oficina, tipo_orcamento, status, diagnostico, solucao, preco, data_criacao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, idUsuario);
	        stmt.setString(2, idVeiculo);
	        stmt.setString(3, idOficina);
	        stmt.setString(4, tipoOrcamento);
	        stmt.setString(5, status);
			stmt.setString(6, diagnostico);
			stmt.setString(7, solucao);
			stmt.setString(8, preco);
			stmt.setDate(9, sqlDate);
	        stmt.execute();
			return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return response;
	}

	@Override
	public List<Orcamento> listar() {
		List<Orcamento> orcamentos = new ArrayList<>();
        String sql = "SELECT * FROM TB_ORCAMENTO";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idOrcamento = rs.getInt("id_orcamento");
                int idUsuario = rs.getInt("id_usuario");
                int idOficina = rs.getInt("id_oficina");
                String statusOrcamento = rs.getString("status_orcamento");
                Float valorOrcamento = rs.getFloat("valor_orcamento");
                java.sql.Date sqlDate = rs.getDate("data_criacao");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                
                Orcamento orcamento = new Orcamento(idOrcamento, idUsuario, idOficina, statusOrcamento, valorOrcamento, utilDate);
                orcamentos.add(orcamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orcamentos;
	}

	@Override
	public Orcamento listarPorId(int id) {
		Orcamento orcamento = new Orcamento();
        String sql = "SELECT * FROM TB_ORCAMENTO WHERE id_orcamento = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	orcamento.setIdOrcamento(rs.getInt("id_orcamento"));
            	orcamento.setIdUsuario(rs.getInt("id_usuario"));
            	orcamento.setIdOficina(rs.getInt("id_oficina"));
            	orcamento.setStatusOrcamento(rs.getString("status_orcamento"));
            	orcamento.setValorOrcamento(rs.getFloat("valor_orcamento"));
            	java.sql.Date sqlDate = rs.getDate("data_criacao");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                orcamento.setDataCriacao(utilDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orcamento;
	}

	@Override
	public void atualizar(Orcamento orcamento) {
		java.util.Date utilDate = orcamento.getDataCriacao();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		String sql = "UPDATE TB_ORCAMENTO SET id_orcamento = ?, id_usuario = ?, id_oficina = ?, status_orcamento = ?, valor_orcamento = ?,"
				+ "data_criacao = ? WHERE id_orcamento = ?";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, orcamento.getIdOrcamento());
	        stmt.setInt(2, orcamento.getIdUsuario());
	        stmt.setInt(3, orcamento.getIdOficina());
	        stmt.setString(4, orcamento.getStatusOrcamento());
	        stmt.setFloat(5, orcamento.getValorOrcamento());
	        stmt.setDate(6, sqlDate);
	        stmt.setInt(7, orcamento.getIdOrcamento());
		    stmt.execute();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "DELETE FROM TB_ORCAMENTO WHERE id_orcamento = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	@Override
	public List<Map<String, Object>> buscarOrcamentosPorUsuario(int usuarioId) {
		List<Map<String, Object>> orcamentos = new ArrayList<>();

		String sql = "SELECT id_orcamento, nome_usuario, marca_veiculo, modelo_veiculo, " +
				"tipo_orcamento, status, diagnostico, solucao, preco " +
				"FROM TB_USUARIO U " +
				"INNER JOIN TB_ORCAMENTO O ON U.id_usuario = O.id_usuario " +
				"INNER JOIN TB_VEICULO_USUARIO V ON O.id_veiculo = V.id_veiculo " +
				"WHERE O.id_usuario = ?";

		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, usuarioId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> orcamento = new HashMap<>();
				orcamento.put("idOrcamento", rs.getInt("id_orcamento"));
				orcamento.put("nomeUsuario", rs.getString("nome_usuario"));
				orcamento.put("marcaVeiculo", rs.getString("marca_veiculo"));
				orcamento.put("modeloVeiculo", rs.getString("modelo_veiculo"));
				orcamento.put("tipoOrcamento", rs.getString("tipo_orcamento"));
				orcamento.put("status", rs.getString("status"));
				orcamento.put("diagnostico", rs.getString("diagnostico"));
				orcamento.put("solucao", rs.getString("solucao"));
				orcamento.put("preco", rs.getDouble("preco"));

				orcamentos.add(orcamento);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Trate a exceção conforme necessário
		}

		return orcamentos;
	}
}

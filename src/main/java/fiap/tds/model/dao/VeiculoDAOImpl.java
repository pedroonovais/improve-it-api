package fiap.tds.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fiap.tds.model.vo.Veiculo;
import fiap.tds.util.DatabaseConnection;

public class VeiculoDAOImpl implements VeiculoDAO {

	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }

	@Override
	public int cadastro(String idUsuario, String ano, String marca, String modelo, String quilometragem) {
		int idVeiculo = -1;

		String sql = "INSERT INTO TB_VEICULO_USUARIO (id_usuario, marca_veiculo, modelo_veiculo, ano_veiculo, quilometragem_veiculo) "
                + "VALUES (?, ?, ?, ?, ?)";
	    try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, idUsuario);
	        stmt.setString(2, marca);
	        stmt.setString(3, modelo);
	        stmt.setString(4, ano);
			stmt.setString(5, quilometragem);
	        stmt.execute();

			String sqlGetId = "SELECT TB_VEICULO_USUARIO_SEQ.CURRVAL FROM DUAL";
			try (PreparedStatement stmtId = connection.prepareStatement(sqlGetId);
				 ResultSet rs = stmtId.executeQuery()) {

				if (rs.next()) {
					idVeiculo = rs.getInt(1); // Pega o valor atual da sequência
				} else {
					throw new SQLException("Falha ao obter ID do usuário.");
				}
			}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return idVeiculo;
	}

	@Override
	public List<Veiculo> listar() {
		List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM TB_VEICULO";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idVeiculo = rs.getInt("id_veiculo");
                String modeloVeiculo = rs.getString("modelo_veiculo");
                String marcaVeiculo = rs.getString("marca_veiculo");
                int anoFabricacao = rs.getInt("ano_fabricacao_veiculo");
                int anoModelo = rs.getInt("ano_modelo_veiculo");
                String placa = rs.getString("placa_veiculo");
                int quilometragem = rs.getInt("quilometragem_veiculo");
                
                Veiculo veiculo = new Veiculo(idVeiculo, modeloVeiculo, marcaVeiculo, anoFabricacao, anoModelo, placa, quilometragem);
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
	}

	@Override
	public Veiculo listarPorId(int id) {
		Veiculo veiculo = new Veiculo();
        String sql = "SELECT * FROM TB_VEICULO WHERE id_veiculo = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	veiculo.setIdVeiculo(rs.getInt("id_veiculo"));
            	veiculo.setModeloVeiculo(rs.getString("modelo_veiculo"));
            	veiculo.setMarcaVeiculo(rs.getString("marca_veiculo"));
            	veiculo.setAnoFabricacaoVeiculo(rs.getInt("ano_fabricacao_veiculo"));
            	veiculo.setAnoModeloVeiculo(rs.getInt("ano_modelo_veiculo"));
            	veiculo.setPlacaVeiculo(rs.getString("placa_veiculo"));
            	veiculo.setQuilometragemVeiculo(rs.getInt("quilometragem_veiculo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
	}

	@Override
	public boolean atualizar(String idVeiculo, String marca, String modelo, String ano, String quilometragem) {
		String sql = "UPDATE TB_VEICULO_USUARIO SET modelo_veiculo = ?, marca_veiculo = ?, ano_veiculo = ?, "
				+ "quilometragem_veiculo = ? WHERE id_veiculo = ?";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, modelo);
	        stmt.setString(2, marca);
	        stmt.setString(3, ano);
	        stmt.setString(4, quilometragem);
	        stmt.setString(5, idVeiculo);
		    stmt.execute();
			return true;
		} catch (SQLException e) {
		    e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "DELETE FROM TB_VEICULO WHERE id_veiculo = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }	
	}

	@Override
	public List<Map<String, Object>> buscarVeiculosPorUsuario(int usuarioId) {
		List<Map<String, Object>> veiculos = new ArrayList<>();

		String sql = "SELECT * FROM TB_VEICULO_USUARIO WHERE ID_USUARIO = ?";

		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, usuarioId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> veiculo = new HashMap<>();
				veiculo.put("idVeiculo", rs.getInt("id_veiculo"));
				veiculo.put("marcaVeiculo", rs.getString("marca_veiculo"));
				veiculo.put("modeloVeiculo", rs.getString("modelo_veiculo"));
				veiculo.put("anoVeiculo", rs.getInt("ano_veiculo"));
				veiculo.put("quilometragemVeiculo", rs.getString("quilometragem_veiculo"));
				veiculos.add(veiculo);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Trate a exceção conforme necessário
		}

		return veiculos;
	}

}

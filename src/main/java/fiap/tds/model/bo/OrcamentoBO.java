package fiap.tds.model.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.tds.model.dao.OrcamentoDAOImpl;
import fiap.tds.model.vo.Orcamento;
import fiap.tds.util.DatabaseConnection;

public class OrcamentoBO {
	
	private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }
	
	public float calculaPrecoTodosOrcamentoFinalizado(List<Orcamento> lista) {
		float precoTotal = 0;
		for (Orcamento orcamento : lista) {
			if(orcamento.getStatusOrcamento() == "Finalizado") {
				precoTotal += orcamento.getValorOrcamento();
			}
		}
		return precoTotal;
	}
	
	public void alterarStatusOrcamento(Orcamento orcamento, String novoStatus) {
		var dao = new OrcamentoDAOImpl();
		orcamento.setStatusOrcamento(novoStatus);
		dao.atualizar(orcamento);
	}
	
	public void aplicarPorcentagemDesconto(Orcamento orcamento, int desconto) {
		var dao = new OrcamentoDAOImpl();
		var precoAtual = orcamento.getValorOrcamento();
		var descontoCalculado = desconto * precoAtual / 100;
		orcamento.setValorOrcamento(precoAtual - descontoCalculado);
		dao.atualizar(orcamento);
	}
	
	public List<Orcamento> consultaOrcamentosFinalizados(){
		List<Orcamento> orcamentos = new ArrayList<>();
		String sql = "SELECT * FROM TB_ORCAMENTO WHERE status_orcamento = 'Finalizado'";
		try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
	    	while(rs.next()) {
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
}

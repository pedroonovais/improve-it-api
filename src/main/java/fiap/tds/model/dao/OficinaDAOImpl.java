package fiap.tds.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.tds.model.vo.Oficina;
import fiap.tds.util.DatabaseConnection;

public class OficinaDAOImpl implements OficinaDAO {

    private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }

    @Override
    public void inserir(Oficina oficina) {
        String sql = "INSERT INTO TB_OFICINA (id_oficina, nome_oficina, cep_oficina, logradouro_oficina, complemento_endereco_oficina, "
                   + "telefone_oficina, email_oficina, cnpj_oficina, especialidade_oficina, horario_funcionamento_oficina) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, oficina.getId());
            stmt.setString(2, oficina.getNome());
            stmt.setInt(3, oficina.getCep());
            stmt.setString(4, oficina.getLogradouro());
            stmt.setString(5, oficina.getComplementoEndereco());
            stmt.setString(6, oficina.getTelefone());
            stmt.setString(7, oficina.getEmail());
            stmt.setString(8, String.valueOf(oficina.getCnpj()));
            stmt.setString(9, oficina.getEspecialidade());
            stmt.setString(10, oficina.getHorarioFuncionamento());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Oficina> listar() {
        List<Oficina> oficinas = new ArrayList<>();
        String sql = "SELECT * FROM TB_OFICINA";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_oficina");
                String nome = rs.getString("nome_oficina");
                int cep = rs.getInt("cep_oficina");
                String logradouro = rs.getString("logradouro_oficina");
                String complementoEndereco = rs.getString("complemento_endereco_oficina");
                String telefone = rs.getString("telefone_oficina");
                String email = rs.getString("email_oficina");
                String cnpj = rs.getString("cnpj_oficina");
                String especialidade = rs.getString("especialidade_oficina");
                String horarioFuncionamento = rs.getString("horario_funcionamento_oficina");

                Oficina oficina = new Oficina(id, nome, cep, logradouro, complementoEndereco, telefone, email, cnpj, especialidade, horarioFuncionamento);
                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficinas;
    }

    @Override
    public Oficina listarPorId(int idOficina) {
        Oficina oficina = new Oficina();
        String sql = "SELECT * FROM TB_OFICINA WHERE id_oficina = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOficina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oficina.setId(rs.getInt("id_oficina"));
                oficina.setNome(rs.getString("nome_oficina"));
                oficina.setCep(rs.getInt("cep_oficina"));
                oficina.setLogradouro(rs.getString("logradouro_oficina"));
                oficina.setComplementoEndereco(rs.getString("complemento_endereco_oficina"));
                oficina.setTelefone(rs.getString("telefone_oficina"));
                oficina.setEmail(rs.getString("email_oficina"));
                oficina.setCnpj(rs.getString("cnpj_oficina"));
                oficina.setEspecialidade(rs.getString("especialidade_oficina"));
                oficina.setHorarioFuncionamento(rs.getString("horario_funcionamento_oficina"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficina;
    }

    @Override
    public void atualizar(Oficina oficina) {
        String sql = "UPDATE TB_OFICINA SET nome_oficina = ?, cep_oficina = ?, logradouro_oficina = ?, complemento_endereco_oficina = ?, "
                   + "telefone_oficina = ?, email_oficina = ?, cnpj_oficina = ?, especialidade_oficina = ?, horario_funcionamento_oficina = ? "
                   + "WHERE id_oficina = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, oficina.getNome());
            stmt.setInt(2, oficina.getCep());
            stmt.setString(3, oficina.getLogradouro());
            stmt.setString(4, oficina.getComplementoEndereco());
            stmt.setString(5, oficina.getTelefone());
            stmt.setString(6, oficina.getEmail());
            stmt.setString(7, String.valueOf(oficina.getCnpj())); // CNPJ como String
            stmt.setString(8, oficina.getEspecialidade());
            stmt.setString(9, oficina.getHorarioFuncionamento());
            stmt.setInt(10, oficina.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM TB_OFICINA WHERE id_oficina = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

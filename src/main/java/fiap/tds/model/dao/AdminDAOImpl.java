package fiap.tds.model.dao;

import fiap.tds.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO{

    private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }

    @Override
    public int login(String login, String senha) {
        int id = 0;
        String sql = "SELECT id_admin FROM TB_LOGIN_ADMIN WHERE login = ? AND senha = ?";
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

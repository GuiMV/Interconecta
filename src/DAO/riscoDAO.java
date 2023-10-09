package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Risco;
import controller.DatabaseConnection;

public class riscoDAO {
    public void cadastrarRisco(Risco riscos){
        String sql = "INSERT INTO RISCO (CODIGO, NOME, DESCRICAO) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getDatabaseConnection().prepareStatement(sql);
            ps.setInt(1, riscos.getCodigo());
            ps.setString(2, riscos.getNome());
            ps.setString(3, riscos.getDescricao());

            ps.execute();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

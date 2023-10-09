package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Risco_Projeto;
import controller.DatabaseConnection;

public class riscoprojetoDAO {
    public void cadastrarRisco(Risco_Projeto risco_projeto){
        String sql = "INSERT INTO RISCO_PROJETO (ID, COD_RISCO, PROBABILIDADE, IMPACTO) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getDatabaseConnection().prepareStatement(sql);
            ps.setInt(1, risco_projeto.getId());
            ps.setInt(2, risco_projeto.getCod_Risco());
            ps.setFloat(3, risco_projeto.getProbabilidade());
            ps.setFloat(4, risco_projeto.getImpacto());

            ps.execute();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
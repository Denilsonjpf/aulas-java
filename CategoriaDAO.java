/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siisvendas;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denil
 */
public class CategoriaDAO {

    Connection con;

    public CategoriaDAO() {
        con = FabricaConexao.getConnection();
    }

    public int salvar(CategoriaBean categoria) throws SQLException {
        int id = -1;

        try {
            con.setAutoCommit(false);
            String sql = "INSERT INTO categoria (descricao) VALUES (?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, categoria.getDescricao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            con.commit();

        } catch (SQLException e) {
            con.rollback();
            id = 0;

        } finally {
            con.close();
        }

        return id;
    }

    public int alterar(CategoriaBean categoria) throws SQLException {
        int id = -1;

        try {
            con.setAutoCommit(false);
            String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getID());
            stmt.executeUpdate();
            id = categoria.getID();
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            id = 0;

        } finally {
            con.close();
        }

        return id;
    }

    public int deletar(CategoriaBean categoria) throws SQLException {
        int id = -1;

        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM categoria WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, categoria.getID());
            stmt.executeUpdate();
            id = categoria.getID();
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            id = 0;

        } finally {
            con.close();
        }

        return id;
    }

    public List<CategoriaBean> listarCategorias() throws SQLException {

        PreparedStatement smts = null;
        ResultSet rs = null;
        List<CategoriaBean> lista = new ArrayList<>();

        String comandoSqlSelect = "SELECT * FROM categoria ORDER BY id ASC";
        try {
            smts = con.prepareStatement(comandoSqlSelect);
            rs = smts.executeQuery(); /// arqui ele foi no BD e executou o comando
            while (rs.next()) {
                CategoriaBean categoria = new CategoriaBean();
                categoria.setID(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));

                lista.add(categoria);
            }

        } catch (SQLException ex) {
            System.out.println("Erro Consulta" + ex.getMessage());
        } finally {
            con.close();
        }

        return lista;
    }
}

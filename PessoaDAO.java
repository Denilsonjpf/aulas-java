package siisvendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PessoaDAO {

    Connection conexao;

    public PessoaDAO() {
        conexao = FabricaConexao.getConnection();
    }

    public int salvar(PessoaBean pessoa) {

        int idGerado = -1;
        // NOME = NOME; SELECT * FROM pessoa
        String comandoSQL = "INSERT INTO pessoa (nome, idade) "
                + " VALUES (?, ?)";

        try {
            //abrindo uma transação
            conexao.setAutoCommit(false);
            PreparedStatement stmt
                    = conexao.prepareStatement(comandoSQL,
                            Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            conexao.commit();
        } catch (SQLException ex) {
            System.out.println("Erro ao gravar " + ex.getMessage());

            try {
                conexao.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro rollback " + ex1.getMessage());
            }

        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao gravar " + ex.getMessage());
            }
        }

        return idGerado;
    }

    public boolean alterar(PessoaBean pessoa) {
        boolean retorno = false;

        String comando = "UPDATE pessoa SET nome = ?, idade = ? "
                + " WHERE id = ?";

        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(comando);
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setInt(3, pessoa.getId());
            stmt.executeUpdate();
            conexao.commit();
            retorno = true;

        } catch (SQLException ex) {
            System.out.println("Erro SQL " + ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro Rollback");
            }
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão");
            }
        }

        return retorno;
    }

}

package siisvendas;

public class TestePessoa {

    public static void main(String[] args) {
        PessoaBean pessoa = new PessoaBean();
        pessoa.setNome("Joaozinho");
        pessoa.setIdade(22);

        PessoaDAO pessaoDAO = new PessoaDAO();
        int idPessoa = pessaoDAO.salvar(pessoa);
        if (idPessoa >= 1) {
            System.out.println("Pessoa cadastrada com sucesso");
        } else {
            System.out.println("Erro ao cadastrar");
        }

        pessoa.setId(idPessoa);
        pessoa.setNome("João da Silva");
        pessaoDAO = new PessoaDAO();
        boolean retorno = pessaoDAO.alterar(pessoa);
        if (retorno == true) {
            System.out.println("Pessoa Alterada");
        } else {
            System.out.println("Erro ao alterar");
        }

    }
}

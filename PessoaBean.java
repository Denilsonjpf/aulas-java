package siisvendas;

public class PessoaBean implements java.io.Serializable {

    public PessoaBean() {
        this.nome = "";
        this.idade = 0;
    }

    private int id;
    private String nome;
    private int idade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return this.idade;
    }

    public static void main(String[] args) {
        PessoaBean pessoa = new PessoaBean();
        pessoa.setNome("Iran");
        pessoa.setIdade(22);

        System.out.println("Nome " + pessoa.getNome()
                + " Idade " + pessoa.getIdade());

    }

}

package iftm.edu.br.vendas.Domain;


import lombok.Data;

@Data

public class Clientes {

    private Long cod_cliente;
    private String nome;
    private String cpf;
    private String dataDeNascimento;
    private String sexo;
    private String cidade;
    private String uf;

    public Clientes() {
    }

    public Clientes(Long cod_cliente, String nome, String cpf, String dataDeNascimento, String sexo, String cidade,
            String uf) {
        this.cod_cliente = cod_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Clientes findByCpf(String cpf2) {
        return null;
    }

    public void setNomeCliente(String string) {
    }

}
package iftm.edu.br.vendas.Domain;

import lombok.Data;

@Data
public class Produtos {
    private long cod_prod;
    private String descricao;
    private String unidadeMedida;
    private double valorUnitario;
    private int qteComprada;
    private String nomeFornecedor;

    public Produtos() {
    }

    public Produtos(long cod_prod, String descricao, String unidadeMedida, double valorUnitario, int qteComprada, String nomeFornecedor) {
        this.cod_prod = cod_prod;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.valorUnitario = valorUnitario;
        this.qteComprada = qteComprada;
        this.nomeFornecedor = nomeFornecedor;
    }
}
package iftm.edu.br.vendas.Domain;

public class Produto {
    private long cod_produto;
    private String descricao;
    private String unidadeMedida;
    private double valorUnitario;
    private int qteComprada;
    private String nomeFornecedor;

    // getters
    public long getCod_produto() {
        return cod_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public int getQteComprada() {
        return qteComprada;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    // setters
    public void setCod_produto(long cod_produto) {
        this.cod_produto = cod_produto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setQteComprada(int qteComprada) {
        this.qteComprada = qteComprada;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}

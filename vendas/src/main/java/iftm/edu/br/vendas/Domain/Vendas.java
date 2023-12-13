package iftm.edu.br.vendas.Domain;


public class Vendas {

    private Long codVenda;
    private String dataVenda;
    private String cpfCliente;
    private String nomeCliente;
    private Long cod_prodProduto;
    private String descricao;
    private double valorTotal;    
    private double qteVendida;
    private double precoVenda;

    public Vendas() {
    }

    public Vendas(Long codVenda, String dataVenda, String cpfCliente, String nomeCliente, Long cod_prodProduto, String descricao, double valorTotal) {
        this.codVenda = codVenda;
        this.dataVenda = dataVenda;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.cod_prodProduto = cod_prodProduto;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }

    public Long getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Long codVenda) {
        this.codVenda = codVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getCod_prodProduto() {
        return cod_prodProduto;
    }

    public void setCod_prodProduto(Long cod_prodProduto) {
        this.cod_prodProduto = cod_prodProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // Adicione estes métodos
    public double getQteVendida() {
        return qteVendida;
    }

    public void setQteVendida(double qteVendida) {
        this.qteVendida = qteVendida;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
